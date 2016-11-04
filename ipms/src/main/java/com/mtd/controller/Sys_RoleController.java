package com.mtd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mtd.entity.Sys_Role;
import com.mtd.entity.Sys_Users;
import com.mtd.page.Page;
import com.mtd.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/sysrole")
public class Sys_RoleController {

	@Resource(name = "userService")
	private UserService userService;
	/***
	 * 角色列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("sys:dict:view")
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request) throws Exception {
		Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			String hql = "from Sys_Role t";
			String search = request.getParameter("search");
			
			if (search != null) {
				hql += " where t.name like '%" + search + "%'";
			}
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
			List<Object> list = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
			pageModel.setItems(list);
			request.setAttribute("count", list.size());
			request.setAttribute("list", list);
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
			return "admin/admin_role";
		} else {
			return "/logout";
		}
	}

	@RequiresPermissions("sys:dict:add")
	@RequestMapping("/addRole")
	public String addRole(Sys_Role role, HttpServletRequest request) {
		userService.create(role);
		return "redirect:/sysrole/getList.do";
	}
	
	
	/***
	 * 获取角色
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getRole")
	public void getRole(HttpServletResponse response) {
		List<Sys_Role> list = userService.listByHQL("from Sys_Role t where roleCode!='other'");
		Map<String, String> map = new HashMap<String, String>();
		if (list.size() > 0) {
			for (Sys_Role t : list) {
				map.put(t.getRoleCode(), t.getName());
			}
		}
		JSONObject object = JSONObject.fromObject(map);
		String reust = object.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(reust);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
