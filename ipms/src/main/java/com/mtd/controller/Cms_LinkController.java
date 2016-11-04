package com.mtd.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mtd.entity.Cms_Link;
import com.mtd.entity.Sys_Users;
import com.mtd.page.Page;
import com.mtd.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/cms_link")
public class Cms_LinkController {

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/getAllList")
	public String getAllList(HttpServletRequest request) {
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
		String hql = "from Cms_Link c where c.isvaild='Y'";
		String search = request.getParameter("search");
		if (search != null) {
			hql += " and c.linkname like '%" + search + "%'";
		}
		pageModel.setPageSize(10);// 设置页面显示最大 值
		pageModel.setTotalCount(userService.FindAll(hql).size()); // 数据总条数
		pageModel.setNum(2); // 设置当前页的前后距离,/**前后各显示5页**/
		// 通过当前页和
		List<Object> cmslist = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
		pageModel.setItems(cmslist);
		request.setAttribute("count", cmslist.size());// 放置在request�?
		request.setAttribute("cmslist", cmslist);
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
		String url = "http://" + request.getServerName() // 服务器地�?
				+ ":" + request.getServerPort() // 端口�?
				+ request.getContextPath() // 项目名称
				+ request.getServletPath() // 请求页面或其他地�?
				+ "?" + (obj); // 参数 ;
		request.setAttribute("pageuri", url);
		request.setAttribute("search", search);
		return "cms/cms_link";
	}

	@RequestMapping("/getLink")
	public String getLink(Integer id, HttpServletRequest request) {
		request.setAttribute("cms", userService.findByID(Cms_Link.class, id));
		return "cms/cms_link_edit";
	}

	@RequestMapping("/addLink")
	public String addLink(@RequestParam(value = "file", required = false) MultipartFile file, Cms_Link c, HttpServletRequest request) {
		Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			String refno = "link" + sf.format(date);
			if (file.getSize() != 0) {
				String path = request.getSession().getServletContext().getRealPath("upload/cms/link");
				String url = "http://" + request.getServerName() // 服务器地址
						+ ":" + request.getServerPort() // 端口号
						+ request.getContextPath() // 项目名称
						+ "/upload/cms/link";// 请求页面或其他地址
				String fileName = refno + ".jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					file.transferTo(targetFile);
					c.setIconurl(url + "\\" + fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			c.setIsvaild("Y");
			c.setAddtime(date);
			c.setUpdtime(date);
			c.setAdduser(user.getName());
			c.setUpduser(user.getName());
			userService.create(c);
			return "redirect:/cms_link/getAllList.do";
		} else {
			return "logout";
		}
	}

	@RequestMapping("/delLink")
	public void delLink(Cms_Link c, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			userService.delete(c);
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

	@RequestMapping("/updateLink")
	public String updateLink(@RequestParam(value = "file", required = false) MultipartFile file, Cms_Link cms, HttpServletRequest request) {
		Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			try {
				Cms_Link link = (Cms_Link) userService.findByID(Cms_Link.class, cms.getId());
				if (file.getSize() != 0) {
					String path = request.getSession().getServletContext().getRealPath("upload/cms/link");
					String url = "http://" + request.getServerName() // 服务器地址
							+ ":" + request.getServerPort() // 端口号
							+ request.getContextPath() // 项目名称
							+ "/upload/cms/link";// 请求页面或其他地址

					String fileName = link.getRefno() + ".jpg";
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						file.transferTo(targetFile);
						link.setIconurl(url + "\\" + fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				link.setLinkname(cms.getLinkname());
				link.setLinkUrl(cms.getLinkUrl());
				link.setOrderby(cms.getOrderby());
				link.setRemark(cms.getRemark());
				link.setUpdtime(new Date());
				link.setUpduser(user.getName());
				userService.update(link);
				return "redirect:/cms_link/getAllList.do";
			} catch (Exception ex) {
				ex.printStackTrace();
				return "/error";
			}
		} else {
			return "/logout";
		}
	}

	// -----------------------wechat----------------------

	/**
	 * 课程列表
	 * 
	 * @param request
	 */
	@RequestMapping("/wechat/getWechatLinkList")
	public void getWechatLinkList(HttpServletResponse response) {
		List<Cms_Link> list = userService.listByHQL("from Cms_Link c where c.isvaild='Y'  order by c.orderby asc");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		String reust = json.toString();
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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true:允许输入空�?�，false:不能为空�?
	}
}
