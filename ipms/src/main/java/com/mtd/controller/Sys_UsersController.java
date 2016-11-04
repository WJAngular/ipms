package com.mtd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mtd.common.utils.SystemConfig;
import com.mtd.entity.Sys_Log;
import com.mtd.entity.Sys_Users;
import com.mtd.page.Page;
import com.mtd.security.MD5;
import com.mtd.security.OnlineUserListener;
import com.mtd.service.RoleService;
import com.mtd.service.UserRoleService;
import com.mtd.service.UserService;

/**
 * 用户/党员
 * 
 * @author Chao
 *
 */
@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/sysusers")
public class Sys_UsersController {

	@Resource(name = "userService")
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;

	private static String iswechat = SystemConfig.get("iswechat");

	/***
	 * 列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request) throws Exception {
		String  hql = "from Sys_Users user where user.isvaild='Y' and user.type='other'";
		String search = request.getParameter("search");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if (startDate != null && startDate != "") {
			hql += "  and user.inDate >= '" + startDate + "'";
		}
		if (endDate != null && endDate != "") {
			hql += " and user.inDate<='" + endDate + "'";
		}
		if (search != null) {
			hql += " and (user.name like '%" + search + "%' or user.sex like '%" + search + "%' or user.tel like '%" + search + "%' or user.branch like '%" + search + "%' or user.organization like '%" + search + "%')";
		}
		hql += " order  by user.branch,user.id desc";
		
		System.out.println("hql="+hql);
		Page pageModel = new Page();
		String pageinfo = (String) request.getParameter("page");
		if (pageinfo != null) {
			int page = Integer.parseInt(pageinfo);
			if (page != 0) {
				pageModel.setPage(page);
			}
		} else {
			pageModel.setPage(1);
		}

		pageModel.setPageSize(10);// 设置页面显示最大 值
		pageModel.setTotalCount(userService.FindAll(hql).size()); // 数据总条数
		pageModel.setNum(2); // 设置当前页的前后距离,/**前后各显示5页**/
		// 通过当前页和
		List<Object> userList = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
		pageModel.setItems(userList);
		request.setAttribute("count", userList.size());// 锟斤拷锟斤拷锟斤拷request锟斤拷
		request.setAttribute("userList", userList);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("page", pageModel.getPage());
		String obj = request.getQueryString();
		if (obj != null) {
			String[] fenge = obj.split("[ = & ]");
			String op = "&";
			for (int i = 0; i < fenge.length; i++) {
				if ("page".equals(fenge[i])) {
					op += fenge[i] + "=" + fenge[++i];
					break;
				}
			}
			obj = obj.replace(op, "");
		} else {
			obj = "";
		}
		String url = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath() // 项目名称
				+ request.getServletPath() // 请求页面或其他地址
				+ "?" + (obj); // 参数 ;
		request.setAttribute("pageuri", url);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("search", search);
		return "user/user_list";
	}


	/***
	 * 查找党员
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public String getUser(Integer id, HttpServletRequest request) {
		request.setAttribute("user", userService.findByID(Sys_Users.class, id));
		return "user/user_edit";
	}

	/***
	 * 党员用户新增
	 * 
	 * @param file
	 * @param user
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/addUser")
	public String addUser(Sys_Users user, HttpServletRequest request) throws IOException, ParseException {
				user.setAddtime(new Date());
				user.setStatus(user.getStatus());
				user.setType("admin");
				user.setIsvaild("Y");
				userService.create(user);
				return "redirect:/user/getAllUser.do";
	}

	/***
	 * 党员用户删除
	 * 
	 * @param user
	 * @param response
	 */
	@RequestMapping("/delUser")
	public void delUser(Sys_Users user, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		user = (Sys_Users) userService.findByID(Sys_Users.class, user.getId());
		try {
			userService.delete(user);
			result = "{\"result\":\"success\"}";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 党员用户更新
	 * 
	 * @param file
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(Sys_Users user,HttpServletRequest request) {
		Sys_Users u = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (u != null) {
			Sys_Users users = (Sys_Users) userService.findByID(Sys_Users.class, user.getId());
			try {
				users.setUpdtime(new Date());
				users.setUpduser(u.getUsername());
				userService.update(users);
				return "redirect:/user/getAllUser.do";
			} catch (Exception ex) {
				ex.printStackTrace();
				return "/error";
			}
		} else {
			request.setAttribute("message_login", "系统超时，请重新登录");
			return "/login";
		}
	}

	/***
	 * 日期绑定
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	
	// ------------------------------------账号部分-----------------------------

	/**
	 * 获取账号信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllList")
	public String getAllList(HttpServletRequest request) {
		String hql = "from Sys_Users user where user.isvaild='Y' and user.type!='other'";
		String search = request.getParameter("search");
		if (search != null) {
			hql += " and (user.name like '%" + search + "%' or user.sex like '%" + search + "%' or user.tel like '%" + search + "%' or user.branch like '%" + search + "%' or user.organization like '%" + search + "%')";
		}
		hql += " order  by user.id desc";
		Page pageModel = new Page();
		String pageinfo = (String) request.getParameter("page");
		if (pageinfo != null) {
			int page = Integer.parseInt(pageinfo);
			if (page != 0) {
				pageModel.setPage(page);
			}
		} else {
			pageModel.setPage(1);
		}

		pageModel.setPageSize(10);// 设置页面显示最大 值
		pageModel.setTotalCount(userService.FindAll(hql).size()); // 数据总条数
		pageModel.setNum(2); // 设置当前页的前后距离,/**前后各显示5页**/
		// 通过当前页和
		List<Object> userList = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
		pageModel.setItems(userList);
		request.setAttribute("count", userList.size());//
		request.setAttribute("userList", userList);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("page", pageModel.getPage());
		String obj = request.getQueryString();
		if (obj != null) {
			String[] fenge = obj.split("[ = & ]");
			String op = "&";
			for (int i = 0; i < fenge.length; i++) {
				if ("page".equals(fenge[i])) {
					op += fenge[i] + "=" + fenge[++i];
					break;
				}
			}
			obj = obj.replace(op, "");
		} else {
			obj = "";
		}
		String url = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath() // 项目名称
				+ request.getServletPath() // 请求页面或其他地址
				+ "?" + (obj); // 参数 ;
		request.setAttribute("pageuri", url);
		request.setAttribute("search", search);
		return "admin/admin_user";
	}

	/***
	 * 查找账号信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAdminUser")
	public String getAdminUser(Integer id, HttpServletRequest request) {
		request.setAttribute("user", userService.findByID(Sys_Users.class, id));
		return "admin/admin_user_edit";
	}

	/***
	 * 用户删除
	 * 
	 * @param user
	 * @param response
	 */
	@RequestMapping("/delAdminUser")
	public void delAdminUser(Sys_Users user, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		user = (Sys_Users) userService.findByID(Sys_Users.class, user.getId());
		try {
			int uid=user.getId();
			userService.delete(user);
			result = "{\"result\":\"success\"}";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 新增账号信息
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addAdminUser")
	public String addAdminUser(Sys_Users user, HttpServletRequest request) {
		Sys_Users u = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			try {
				user.setPassword(MD5.GetMD5Code(user.getPassword()));
				user.setAdduser(u.getUsername());
				user.setAddtime(new Date());
				user.setUpdtime(new Date());
				user.setType("admin");
				user.setIsvaild("Y");
				userService.create(user);
				return "redirect:/user/getAllList.do";
			} catch (Exception ex) {
				ex.printStackTrace();
				return "/error";
			}
		} else {
			return "/logout";
		}
	}

	/***
	 * 修改账号信息
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateAdminUser")
	public String updateAdminUser(Sys_Users user, HttpServletRequest request) {
		try {
			Sys_Users u = (Sys_Users) userService.findByID(Sys_Users.class, user.getId());
			u.setUsername(user.getUsername());
			String password = user.getPassword();
			if (u.getPassword() != null) {
				if (!u.getPassword().equals(password)) {
					u.setPassword(MD5.GetMD5Code(password));
				}
			} else {
				u.setPassword(MD5.GetMD5Code(password));
			}
			u.setUpdtime(new Date());
			u.setUpduser(user.getUpduser());
			userService.update(u);
			return "redirect:/user/getAllList.do";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "/logout";
		}
	}

	/**
	 * 系统登录
	 *
	 * @author Wooce Yang
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/sysLogin")
	public String Login(String username, String password, String code, HttpServletRequest request) {
		String info = "";
		if ((code.equalsIgnoreCase(request.getSession().getAttribute("code").toString()))) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token,

					ToStringStyle.MULTI_LINE_STYLE));
			// 获取当前的Subject
			Subject currentUser = SecurityUtils.getSubject();
			try {
				// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
				// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
				// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
				if (currentUser.isAuthenticated())
					currentUser.logout();
				System.out.println("对用户[" + username + "]进行登录验证..验证开始");
				currentUser.login(token);
				System.out.println("对用户[" + username + "]进行登录验证..验证通过");
				// 验证是否登录成功
				if (currentUser.isAuthenticated()) {
					info = "用户[" + username + "]登录认证通过!";
					System.out.println(info);

					HttpSession session = request.getSession();
					if (OnlineUserListener.sessionMap.get(username.trim()) != null && OnlineUserListener.sessionMap.get(username.trim()).toString().length() > 0) {
						// 当前用户已经在线 删除用户
						HttpSession sessionold = (HttpSession) OnlineUserListener.sessionMap.get(username.trim());
						// 注销已在线用户session
						try {
							sessionold.invalidate();
						} catch (Exception e) {
						}
						OnlineUserListener.sessionMap.remove(username.trim());
						// 清除已在线用户，更新map key 当前用户 value session对象
						OnlineUserListener.sessionMap.put(username.trim(), session);
						OnlineUserListener.sessionMap.remove(session.getId());
					} else {
						// 根据当前sessionid 取session对象。 更新map key=用户名
						// value=session对象
						// 删除map
						// key= sessionid
						OnlineUserListener.sessionMap.get(session.getId());
						OnlineUserListener.sessionMap.put(username.trim(), OnlineUserListener.sessionMap.get(session.getId()));
						OnlineUserListener.sessionMap.remove(session.getId());
					}
					session.setMaxInactiveInterval(60*60);
					Sys_Log log = new Sys_Log(username, "", "登陆成功", info, getIp(request), "Login");
					userService.create(log);
					return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/main.jsp";
				} else {
					token.clear();
				}
			} catch (UnknownAccountException uae) {
				info = "对用户[" + username + "]进行登录验证..验证未通过,未知账户";
				System.out.println(info);
				request.setAttribute("message_login", "未知账户");
			} catch (IncorrectCredentialsException ice) {
				info = "对用户[" + username + "]进行登录验证..验证未通过,错误的凭证";
				System.out.println(info);
				request.setAttribute("message_login", "密码不正确");
			} catch (LockedAccountException lae) {
				info = "对用户[" + username + "]进行登录验证..验证未通过,账户已锁定";
				System.out.println(info);
				request.setAttribute("message_login", "账户已锁定");
			} catch (ExcessiveAttemptsException eae) {
				info = "对用户[" + username + "]进行登录验证..验证未通过,错误次数过多";
				System.out.println(info);
				request.setAttribute("message_login", "用户名或密码错误次数过多");
			} catch (AuthenticationException ae) {
				// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
				info = "对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下";
				System.out.println(info);
				ae.printStackTrace();
				request.setAttribute("message_login", "用户名或密码不正确");
			}
		}else{
			info="验证码错误！";
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("message_login", "验证码错误");
		}
		Sys_Log log = new Sys_Log(username, password, "登陆失败", info, getIp(request), "Login");
		userService.create(log);
		return "/login";

	}

	/***
	 * 密码修改
	 * 
	 * @param user
	 * @param response
	 */
	@RequestMapping("/updatePass")
	public void updatePass(Sys_Users user, HttpServletResponse response) {
		String result = "error";
		String password = user.getPassword();
		user = (Sys_Users) userService.findByID(Sys_Users.class, user.getId());
		try {
			if (!user.getPassword().trim().equals("")) {
				user.setPassword(MD5.GetMD5Code(password));
				user.setUpdtime(new Date());
				userService.update(user);
				result = "success";
			} else {
				result = "error";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			Sys_Log log = new Sys_Log(user.getUsername(), null, "安全退出", "安全退出", getIp(request), "Logout");
			userService.create(log);
		}
		SecurityUtils.getSubject().logout();
		HttpSession ses = request.getSession();
		if (ses.getAttribute("sysuser") != null)
			ses.removeAttribute("sysuser");
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}

	@RequestMapping("/getRoles")
	@RequiresPermissions("sys:user:getRoles")
	public String getRoles(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameterValues("id")[0]);
		Sys_Users currentUser = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (currentUser != null) {
			Sys_Users user = (Sys_Users) userService.findByID(Sys_Users.class, id);
			List<Object> list = roleService.FindAll("from Sys_Role t");
			List<Integer> userRoles = userRoleService.getRoleIdList(id);
			request.setAttribute("user", user);
			request.setAttribute("roles", list);
			request.setAttribute("userRoles", userRoles);
			return "admin/admin_user_role";
		} else {
			return "/login";
		}
	}

	@RequestMapping("/saveRoles")
	@RequiresPermissions("sys:user:saveUserRoles")
	public void saveUserRoles(Integer id, HttpServletRequest request, HttpServletResponse resp) {
		String result = "{\"result\":\"error\"}";
		resp.setContentType("application/json");
		Sys_Users currentUser = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (currentUser != null) {
			try {
				String[] roleIds = request.getParameterValues("role_id");
				List newList = Arrays.asList(roleIds);
				userRoleService.updateUserRole(id, userRoleService.getRoleIdList(id), newList);
				result = "{\"result\":\"success\"}";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	

	/**
	 * 获取真实IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (ip != null && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
