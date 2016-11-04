package com.mtd.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mtd.page.Page;
import com.mtd.service.UserService;

@Controller
@RequestMapping("/syslog")
public class Sys_LogController {

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
		String hql = "from Sys_Log t ";
		String search = request.getParameter("search");
		if (search != null) {
			hql += " where t.username like '%" + search + "%'";
		}
		hql+=" order by t.id  desc";
		pageModel.setPageSize(10);// 设置页面显示最大 值
		pageModel.setTotalCount(userService.FindAll(hql).size()); // 数据总条数
		pageModel.setNum(2); // 设置当前页的前后距离,/**前后各显示5页**/
		// 通过当前页和
		List<Object> list = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
		pageModel.setItems(list);
		request.setAttribute("count", list.size());// 放置在request�?
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
		String url = "http://" + request.getServerName() // 服务器地�?
				+ ":" + request.getServerPort() // 端口�?
				+ request.getContextPath() // 项目名称
				+ request.getServletPath() // 请求页面或其他地�?
				+ "?" + (obj); // 参数 ;
		request.setAttribute("pageuri", url);
		request.setAttribute("search", search);
		return "admin/admin_log";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true:允许输入空�?�，false:不能为空�?
	}
}
