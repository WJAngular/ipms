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

import com.mtd.entity.Cms_Message;
import com.mtd.entity.Sys_Users;
import com.mtd.page.Page;
import com.mtd.service.UserService;

@Controller
@RequestMapping("/cms_message")
public class Cms_MessageController {

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/getAllList")
	public String getAllList(HttpServletRequest request) {
		Page pageModel = new Page();
		// ��õ�ǰҳ
		String pageinfo = (String) request.getParameter("page");
		if (pageinfo != null) {
			int page = Integer.parseInt(pageinfo);
			if (page != 0) {
				pageModel.setPage(page);
			}
		} else {
			pageModel.setPage(1);
		}
		String hql = "from Cms_Message c where c.isvaild='Y'";
		String search = request.getParameter("search");
		if (search != null) {
			hql += "  and c.title like '%" + search + "%'";
		}
		pageModel.setPageSize(10);
		pageModel.setTotalCount(userService.FindAll(hql).size());
		pageModel.setNum(2); 
	
		List<Object> cmslist = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
		pageModel.setItems(cmslist);
		request.setAttribute("count", cmslist.size());
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
		String url = "http://" + request.getServerName() 
				+ ":" + request.getServerPort() 
				+ request.getContextPath() 
				+ request.getServletPath() 
				+ "?" + (obj); 
		request.setAttribute("pageuri", url);
		request.setAttribute("search", search);
		return "cms/cms_message";
	}

	@RequestMapping("/getMessage")
	public String getMessage(Integer id, HttpServletRequest request) throws Exception{
		request.setAttribute("message", userService.findByID(Cms_Message.class, id));
		return "cms/cms_message_edit";
	}

	@RequestMapping("/addMessage")
	public String addMessage(@RequestParam(value = "file", required = false) MultipartFile file, Cms_Message message, HttpServletRequest request) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String refno = "MS" + sf.format(date) + "001";

		List list = userService.listByHQL(" from Cms_Message  c where c.id= (select max(id) from Cms_Message)");
		if (list.size() > 0) {
			Cms_Message cms = (Cms_Message) list.get(0);
			String cmsdate = sf.format(cms.getAddtime());
			if (sf.format(date).equals(cmsdate)) {
				String cmsrfno = cms.getRefno().substring(cms.getRefno().length() - 3, cms.getRefno().length());
				int a = Integer.parseInt(cmsrfno) + 1;
				String refust = String.format("%03d", a);
				refno = "MS" + sf.format(date) + refust;
			}
		}

		if (file.getSize() != 0) {
			String path = request.getSession().getServletContext().getRealPath("upload/cms/message");
			String url = "http://" + request.getServerName() 
					+ ":" + request.getServerPort() 
					+ request.getContextPath() 
					+ "/upload/cms/message";

			System.out.println("url:" + url);

			String fileName = refno + ".jpg";
			System.out.println(path);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			
			try {
				file.transferTo(targetFile);
				message.setPicUrl(url + "/" + fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		message.setRefno(refno);
		message.setIsvaild("Y");
		message.setStatus("N");
		message.setAddtime(date);
		message.setUpdtime(date);
		userService.create(message);
		return "redirect:/cms_message/getAllList.do";
	}

	@RequestMapping("/delMessage")
	public void delMessage(Cms_Message message, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			userService.delete(message);
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

	@RequestMapping("/getInvalid")
	public void getInvalid(Integer id, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			Cms_Message cms = (Cms_Message) userService.findByID(Cms_Message.class, id);
			cms.setIsvaild("N");
			cms.setUpdtime(new Date());
			userService.update(cms);
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

	@RequestMapping("/updatMessage")
	public String updatMessage(@RequestParam(value = "file", required = false) MultipartFile file, Cms_Message message, HttpServletRequest request) {
		try {
			if (file.getSize() != 0) {
				String path = request.getSession().getServletContext().getRealPath("upload/cms/message");
				String url = "http://" + request.getServerName() 
						+ ":" + request.getServerPort() 
						+ request.getContextPath() 
						+ "/upload/cms/message";

				System.out.println("url:" + url);

				String fileName = message.getRefno() + ".jpg";
				System.out.println(path);
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// ����
				try {
					file.transferTo(targetFile);
					message.setPicUrl(url + "/" + fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Date date = new Date();
			message.setUpdtime(date);
			userService.update(message);
			message = (Cms_Message) userService.findByID(Cms_Message.class, message.getId());
			request.setAttribute("content", message);
			return "redirect:/cms_message/getAllList.do";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "/error";
		}
	}

	/**
	 * 审核
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/approveContent")
	public String approveContent(Integer id, HttpServletRequest request) throws Exception {
		Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			Cms_Message c = (Cms_Message) userService.findByID(Cms_Message.class, id);
			c.setStatus("Y");
			c.setAppuser(user.getName());
			c.setApptime(new Date());
			userService.update(c);
			return "redirect:/cms_message/getAllList.do";
		} else {
			request.setAttribute("message_login", "系统超时，请重新登录");
			return "/login";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
