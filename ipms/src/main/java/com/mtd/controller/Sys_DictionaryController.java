package com.mtd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mtd.entity.Sys_Dictionary;
import com.mtd.page.Page;
import com.mtd.service.UserService;

import net.sf.json.JSONObject;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/sysdictionary")
public class Sys_DictionaryController {

	@Resource(name = "userService")
	private UserService userService;

	private Date date;

	/***
	 * 获取字典列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllList")
	public String getAllList(HttpServletRequest request) {
		Page pageModel = new Page();
		// 获得当前页
		String pageinfo = (String) request.getParameter("page");
		if (pageinfo != null) {
			int page = Integer.parseInt(pageinfo);
			if (page != 0) {
				pageModel.setPage(page);
			}
		} else {
			pageModel.setPage(1);
		}
		String hql = "from Sys_Dictionary dictionary ";
		String search = request.getParameter("search");
		if (search != null) {
			hql += " where (dictionary.name like '%" + search + "%' or dictionary.mcode like  '%" + search + "%')";
		}
		hql += " order by dictionary.ocode,dictionary.mcode,dictionary.orderby";
		pageModel.setPageSize(10);// 设置页面显示最大 值
		pageModel.setTotalCount(userService.FindAll(hql).size()); // 数据总条数
		pageModel.setNum(2); // 设置当前页的前后距离,/**前后各显示5页**/
		// 通过当前页和
		List<Object> dictionaryList = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
		pageModel.setItems(dictionaryList);
		request.setAttribute("count", dictionaryList.size());// 放置在request中
		request.setAttribute("dictionaryList", dictionaryList);
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
		return "admin/admin_dictionary";
	}

	@RequestMapping("/getDict")
	public String getDict(Integer id, HttpServletRequest request) {
		request.setAttribute("dict", userService.findByID(Sys_Dictionary.class, id));
		return "admin/admin_dictionary_Edit";
	}

	@RequestMapping("/toAddUser")
	public String toAddUser() {
		return "admin/addUser";
	}

	@RequestMapping("/addDict")
	public String addDict(Sys_Dictionary dict, HttpServletRequest request) {
		date = new Date();
		dict.setAddtime(date);
		dict.setUpdtime(date);
		dict.setIsvalid("Y");
		userService.create(dict);
		return "redirect:/sysdictionary/getAllList.do";
	}

	@RequestMapping("/delDict")
	public void delDict(Sys_Dictionary dict, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			userService.delete(dict);
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

	@RequestMapping("/updDict")
	public String updDict(Sys_Dictionary dict, HttpServletRequest request) {
		try {
			userService.update(dict);
			dict = (Sys_Dictionary) userService.findByID(Sys_Dictionary.class, dict.getId());
			request.setAttribute("dict", dict);
			return "redirect:/sysdictionary/getAllList.do";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "/error";
		}

	}

	// ---------------------------------组织管理部分--------------------------
	/***
	 * 获取组织列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOrganizationList")
	public String getOrganizationList(HttpServletRequest request) {
		Page pageModel = new Page();
		// 获得当前页
		String pageinfo = (String) request.getParameter("page");
		if (pageinfo != null) {
			int page = Integer.parseInt(pageinfo);
			if (page != 0) {
				pageModel.setPage(page);
			}
		} else {
			pageModel.setPage(1);
		}
		String hql = "from Sys_Dictionary dictionary where dictionary.ocode='organization'";
		String search = request.getParameter("search");
		if (search != null) {
			hql += " where dictionary.name like '%" + search + "%' ";
		}
		hql += " order by dictionary.orderby";
		pageModel.setPageSize(10);// 设置页面显示最大 值
		pageModel.setTotalCount(userService.FindAll(hql).size()); // 数据总条数
		pageModel.setNum(2); // 设置当前页的前后距离,/**前后各显示5页**/
		// 通过当前页和
		List<Object> dictionaryList = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
		pageModel.setItems(dictionaryList);
		request.setAttribute("count", dictionaryList.size());// 放置在request中
		request.setAttribute("dictionaryList", dictionaryList);
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
		return "admin/admin_dictionary_org";
	}

	@RequestMapping("/addOrganization")
	public String addOrganization(Sys_Dictionary dict, HttpServletRequest request) {
		date = new Date();
		dict.setOcode("organization");
		dict.setAddtime(date);
		dict.setUpdtime(date);
		dict.setIsvalid("Y");
		userService.create(dict);
		return "redirect:/sysdictionary/getOrganizationList.do";
	}

	@RequestMapping("/getOrganization")
	public String getOrganization(Integer id, HttpServletRequest request) {
		request.setAttribute("dict", userService.findByID(Sys_Dictionary.class, id));
		return "admin/admin_dictionary_org_edit";
	}

	@RequestMapping("/delOrganization")
	public void delOrganization(Sys_Dictionary dict, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			userService.delete(dict);
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

	@RequestMapping("/updOrganization")
	public String updOrganization(Sys_Dictionary dict, HttpServletRequest request) {
		try {
			dict.setUpdtime(new Date());
			userService.update(dict);
			dict = (Sys_Dictionary) userService.findByID(Sys_Dictionary.class, dict.getId());
			request.setAttribute("dict", dict);
			return "redirect:/sysdictionary/getOrganizationList.do";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "/error";
		}

	}

	// -------------------获取字典属性
	/**
	 * 获取党组织
	 * @param response
	 */
	@RequestMapping("/getOrgId")
	public void getOrgId(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.mcode='organization' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/***
	 * 获取党支部
	 * 
	 * @param response
	 */
//	@RequestMapping("/getBranch")
//	public void getBranch(String code,HttpServletResponse response) throws Exception {
//		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.mcode='org_branch' and order by dict.orderby asc");
//		List<String> map =new  ArrayList<String>();
//		if (list.size() > 0) {
//			for (Sys_Dictionary dict : list) {
//				map.add(dict.getName());
//			}
//		}
//		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
//		String reust = object.toString();
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		try {
//			PrintWriter out = response.getWriter();
//			out.write(reust);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	/***
	 * 获取党支部
	 * 
	 * @param response
	 */
	@RequestMapping("/getBranch")
	public void getBranch(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String org = request.getParameter("org");
		org= URLDecoder.decode(org,"UTF-8");
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.mcode='org_branch' and dict.remark='"+org+"'  order by dict.orderby asc");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
	/***
	 * 获取党支部(去除党员管理组）
	 * 
	 * @param response
	 */
	@RequestMapping("/getBranchWithoutSys")
	public void getBranchWithoutSys(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String org = request.getParameter("org");
		org= URLDecoder.decode(org,"UTF-8");
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.mcode='org_branch' and  dict.code!='sys' and dict.remark='"+org+"'  order by dict.orderby asc");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
	
	/**
	 * 获取学历
	 * @param response
	 */
	@RequestMapping("/getEducation")
	public void getEducation(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.mcode='education' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/**
	 * 获取内容栏目
	 * @param response
	 */
	@RequestMapping("/getTopic")
	public void getTopic(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='topic' and mcode='content' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/***
	 * 获取话题
	 * 
	 * @param response
	 */
	@RequestMapping("/getTheme")
	public void getTheme(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='topic' and mcode='theme' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/***
	 * 获取党工作室栏目
	 * 
	 * @param response
	 */
	@RequestMapping("/getStudioTopic")
	public void getStudioTopic(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='topic' and mcode='studio' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/***
	 * 获取会议栏目
	 * 
	 * @param response
	 */
	@RequestMapping("/getMeetingTopic")
	public void getMeetingTopic(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='topic' and mcode='Meeting' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/***
	 * 获取党费
	 * 
	 * @param response
	 */
	@RequestMapping("/getDues")
	public void getDues(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='dues' and mcode='dues' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/***
	 * 获取用户状态
	 * 
	 * @param response
	 */
	@RequestMapping("/getUserStatus")
	public void getUserStatus(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='UserStatus' and mcode='UserStatus' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		// map.put("0", "请选择当前状态");
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
	
	/***
	 * 获取个人身份
	 * 
	 * @param response
	 */
	@RequestMapping("/getCareerID")
	public void getCareerID(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='careerID' and mcode='careerID' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
	
	/***
	 * 获取新阶层
	 * 
	 * @param response
	 */
	@RequestMapping("/getNewClass")
	public void getNewClass(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='newClass' and mcode='newClass' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
	
	/***
	 * 获取转正情况
	 * 
	 * @param response
	 */
	@RequestMapping("/getQualifiedStatus")
	public void getQualifiedStatus(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='qualifiedStatus' and mcode='qualifiedStatus' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/***
	 * 获取发展类型
	 * 
	 * @param response
	 */
	@RequestMapping("/getRecruitType")
	public void getRecruitType(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='recruitType' and mcode='recruitType' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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

	/***
	 * 获取一线情况
	 * 
	 * @param response
	 */
	@RequestMapping("/getIndustryStatus")
	public void getIndustryStatus(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='industryStatus' and mcode='industryStatus' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
	/***
	 * 获取单位属性
	 * 
	 * @param response
	 */
	@RequestMapping("/getAttribute")
	public void getAttribute(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='attribute' and mcode='attribute' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
	/***
	 * 获取单位属性
	 * 
	 * @param response
	 */
	@RequestMapping("/getDegree")
	public void getDegree(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='degree' and mcode='degree' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
	
	/***
	 * 获取党代表
	 * 
	 * @param response
	 */
	@RequestMapping("/getDdb")
	public void getDdb(HttpServletResponse response) {
		List<Sys_Dictionary> list = userService.listByHQL("from Sys_Dictionary dict where dict.ocode='ddb' and mcode='ddb' order by dict.orderby ");
		List<String> map =new  ArrayList<String>();
		if (list.size() > 0) {
			for (Sys_Dictionary dict : list) {
				map.add(dict.getName());
			}
		}
		net.sf.json.JSONArray object = net.sf.json.JSONArray.fromObject(map);
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
