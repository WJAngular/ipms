package com.mtd.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mtd.common.utils.SystemConfig;
import com.mtd.entity.Cms_Content;
import com.mtd.entity.Cms_LikesVo;
import com.mtd.entity.Sys_Users;
import com.mtd.page.Page;
import com.mtd.service.CmsLikeService;
import com.mtd.service.UserService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.wechat.WeChat;
import com.wechat.api.MediaApi;
import com.wechat.bean.WeChatMedia;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/cms_content")
public class Cms_ContentController {
	@Resource(name = "userService")
	private UserService userService;
	@Resource
	private CmsLikeService cmsLikeService;
	
	private static String author = SystemConfig.get("author");
	private static String iswechat = SystemConfig.get("iswechat");
	private float  quality=0.5f;
	
	@RequestMapping("/getAllList")
	public String getAllList(HttpServletRequest request) {
			String hql = "from Cms_Content c where c.isvaild='Y'";
			String search = request.getParameter("search");
			if (search != null) {
				hql += " and (c.title like '%" + search + "%' or c.topic like '"+ search + "')";
			}
			hql += " order by c.id desc";
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
			pageModel.setPageSize(10);
			pageModel.setTotalCount(userService.FindAll(hql).size()); //
			pageModel.setNum(2);

			List<Object> cmslist = userService.findByPage(hql, pageModel.getPageSize(), pageModel.getPage());
			pageModel.setItems(cmslist);
			request.setAttribute("count", cmslist.size());// request
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
			String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + request.getServletPath() + "?" + (obj);
			request.setAttribute("pageuri", url);
			request.setAttribute("search", search);
			return "cms/cms_content";
	}

	/**
	 * 查找内容
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getContent")
	public String getContent(Integer id, HttpServletRequest request) throws Exception {
		request.setAttribute("content", userService.findByID(Cms_Content.class, id));
		return "cms/cms_content_edit";
	}

	/**
	 * 预览内容
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/show")
	public String show(Integer id, HttpServletRequest request) throws Exception {
		request.setAttribute("cms", userService.findByID(Cms_Content.class, id));
		return "cms/cms_show";
	}

	/**
	 * 新增内容
	 * 
	 * @param file
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping("/addContent")
	public String addContent(@RequestParam(value = "file", required = false) MultipartFile file, Cms_Content content, HttpServletRequest request) {
		Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			String refno = "CMS" + sf.format(date) + "001";
			List<Cms_Content> list = userService.listByHQL(" from Cms_Content  c where c.id= (select max(id) from Cms_Content)");
			if (list.size() > 0) {
				Cms_Content cms = (Cms_Content) list.get(0);
				String cmsdate = sf.format(cms.getAddtime());
				if (sf.format(date).equals(cmsdate)) {
					String cmsrfno = cms.getRefno().substring(cms.getRefno().length() - 3, cms.getRefno().length());
					int a = Integer.parseInt(cmsrfno) + 1;
					String refust = String.format("%03d", a);
					refno = "CMS" + sf.format(date) + refust;
				}
			}

			if (file.getSize() != 0) {
				String path = request.getSession().getServletContext().getRealPath("upload/cms/content");
				String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/cms/content";
				String fileName = "test"+refno + ".jpg";
				String fileName1 = refno + ".jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					file.transferTo(targetFile);
					content.setPicUrl(url + "/" + fileName1);
					WeChatMedia a = new MediaApi().uploadMedia(WeChat.getAccessToken(), url + "/" + fileName1);
					if (a != null) {
						content.setPicMedia(a.getMedia_id());
						content.setPicMediaUrl(a.getUrl());
					}
					BufferedImage image = new BufferedImage(200, 200,BufferedImage.TYPE_INT_RGB ); 
					BufferedImage img = ImageIO.read(targetFile); 
					image.getGraphics().drawImage(img, 0, 0, 200, 200, null); // 绘制缩小后的图
					File destFile = new File(path, fileName1);
					FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
					// 可以正常实现bmp、png、gif转jpg
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					JPEGEncodeParam jep=JPEGCodec.getDefaultJPEGEncodeParam(img); 
					jep.setQuality(quality, true);
					encoder.encode(img, jep); // JPEG编码
					out.close();
					if(targetFile.isFile())
					{
						targetFile.delete();
						System.err.println("文件已压缩转换，删除原文件");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			content.setAuthor(author);
			content.setRefno(refno);
			content.setIsvaild("Y");
			content.setStatus("N");
			content.setAddtime(date);
			content.setAdduser(user.getName());
			content.setUpduser(user.getName());
			content.setUpdtime(date);
			userService.create(content);
			return "redirect:/cms_content/getAllList.do";
		} else {
			return "/check";
		}

	}

	/**
	 * 删除内容
	 * 
	 * @param content
	 * @param response
	 */
	@RequestMapping("/delContent")
	public void delContent(Cms_Content content, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			userService.delete(content);
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

	/**
	 * 无效
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getInvalid")
	public void getInvalid(Integer id, HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		try {
			Cms_Content cms = (Cms_Content) userService.findByID(Cms_Content.class, id);
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

	/**
	 * 更新内容
	 * 
	 * @param file
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping("/updatContent")
	public String updatContent(@RequestParam(value = "file", required = false) MultipartFile file, Cms_Content content, HttpServletRequest request) {
		Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			try {
				Cms_Content c = (Cms_Content) userService.findByID(Cms_Content.class, content.getId());

				if (file.getSize() != 0) {
					String path = request.getSession().getServletContext().getRealPath("upload/cms/content");
					String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/cms/content";
					
					String fileName = "test"+c.getRefno() + ".jpg";
					String fileName1 = c.getRefno() + ".jpg";
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					try {
						file.transferTo(targetFile);
						c.setPicUrl(url + "/" + fileName1);
						WeChatMedia a = new MediaApi().uploadMedia(WeChat.getAccessToken(), url + "/" + fileName1);
						if (a != null) {
							c.setPicMedia(a.getMedia_id());
							c.setPicMediaUrl(a.getUrl());
						}
						BufferedImage image = new BufferedImage(200, 200,BufferedImage.TYPE_INT_RGB ); 
						BufferedImage img = ImageIO.read(targetFile); 
						image.getGraphics().drawImage(img, 0, 0, 200, 200, null); // 绘制缩小后的图
						File destFile = new File(path, fileName1);
						FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
						// 可以正常实现bmp、png、gif转jpg
						JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
						JPEGEncodeParam jep=JPEGCodec.getDefaultJPEGEncodeParam(img); 
						jep.setQuality(quality, true);
						encoder.encode(img, jep); // JPEG编码
						out.close();
						if(targetFile.isFile())
						{
							targetFile.delete();
							System.err.println("文件已压缩转换，删除原文件");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Date date = new Date();
				c.setTopic(content.getTopic());
				c.setVaildtime(content.getVaildtime());
				c.setAuthority(content.getAuthority());
				c.setTitle(content.getTitle());
				c.setAbstracts(content.getAbstracts());
				c.setContents(content.getContents());
				c.setUpdtime(date);
				c.setUpduser(user.getName());
				userService.update(c);
				return "redirect:/cms_content/getAllList.do";
			} catch (Exception ex) {
				ex.printStackTrace();
				return "/error";
			}
		} else {
			return "/check";
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
	@RequestMapping("/approve")
	public String approve(Integer id, HttpServletRequest request) throws Exception {
		Sys_Users user = (Sys_Users) request.getSession().getAttribute("sysuser");
		if (user != null) {
			Cms_Content c = (Cms_Content) userService.findByID(Cms_Content.class, id);
			c.setStatus("Y");
			c.setAppuser(user.getName());
			c.setApptime(new Date());
			userService.update(c);
			return "redirect:/cms_content/getAllList.do";
		} else {
			return "/check";
		}
	}
	
	/**
	 * 办事指南
	 * @param userid
	 * @param request
	 * @return
	 */
	@RequestMapping("/{userid}/wechat/getXZSWList")
	public String getXZSWList(@PathVariable("userid")String userid,HttpServletRequest request) {
		String hql = "from Cms_Content c where c.isvaild='Y' and c.status='Y' and c.topic='办事指南'  order by c.id desc";
		List<Object> list = userService.findByPage(hql, 20, 1);
		ArrayList<Cms_Content> cmslist = new ArrayList<Cms_Content>();
		for (Object obj : list) {
			cmslist.add((Cms_Content) obj);
		}
		request.setAttribute("cmslist", cmslist);
		request.setAttribute("userid", userid);
		return "wechat/admin_affairs";
	}
	
	/**
	 * 企业号专用社区动态
	 * @param userid
	 * @param request
	 * @return
	 */
	@RequestMapping("/{userid}/qywechat/getSQSWList")
	public String getSQSWList(@PathVariable("userid")String userid,HttpServletRequest request) {
		String hql = "from Cms_Content c where c.isvaild='Y' and c.status='Y' and c.topic='行政事务'  order by c.id desc";
		List<Object> list = userService.findByPage(hql, 20, 1);
		ArrayList<Cms_Content> cmslist = new ArrayList<Cms_Content>();
		for (Object obj : list) {
			cmslist.add((Cms_Content) obj);
		}
		request.setAttribute("cmslist", cmslist);
		request.setAttribute("userid", userid);
		return "wechat/admin_affairs";
	}

	@RequestMapping("/{userid}/wechat/getWechatXZSW")
	public String getWechatXZSW(@PathVariable("userid")String userid,Integer id, HttpServletRequest request) throws Exception {
		Cms_Content news=(Cms_Content)userService.findByID(Cms_Content.class, id);
		if(news!=null)news.updateReadCount();
		userService.update(news);
		Cms_LikesVo like=cmsLikeService.getCmsLike("content", news.getRefno(), userid);
		request.setAttribute("news", news);
		request.setAttribute("like", like);
		request.setAttribute("userid", userid);
		return "wechat/article";
	}

	/**
	 * 获取新闻列表
	 * 
	 * @param topic
	 * @param request
	 * @return
	 */
	@RequestMapping("/wechat/getNewList")
	public String getNewList(HttpServletRequest request) {
		String topic = request.getParameter("topic");
		String t = "";

		switch (topic) {
			case "djyw":
				t = "党建要闻";
				break;
			case "hdjy":
				t = "活动剪影";
				break;
			case "hytz":
				t = "会议通知";
				break;
			default:
				break;
		}

		String hql = "from Cms_Content c where c.isvaild='Y' and c.status='Y' and c.topic='" + t + "'  order by c.id desc";
		List<Object> list = userService.findByPage(hql, 15, 1);
		ArrayList<Cms_Content> cmslist = new ArrayList<Cms_Content>();
		for (Object obj : list) {
			cmslist.add((Cms_Content) obj);
		}
		request.setAttribute("cmslist", cmslist);
		request.setAttribute("title", t);
		request.setAttribute("topic", topic);
		return "wechat/home_news";
	}

	/**
	 * 获取微信内容详情
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/wechat/getNewsDetail")
	public String getNewsDetail(Integer id, HttpServletRequest request) throws Exception {
		request.setAttribute("news", userService.findByID(Cms_Content.class, id));
		return "wechat/article";
	}

	/**
	 * 获取内容列表
	 * 
	 * @param request
	 */
	@RequestMapping("/wechat/getWechatContentList")
	public void getWechatContentList(String code, HttpServletResponse response) {
		String hql="from Cms_Content c where c.isvaild='Y' and c.status='Y' and (c.topic='会议通知' or c.topic='活动通知')  order by c.id desc";
		List<Object> list = userService.findByPage(hql,15,1);
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
	
	/**
	 * 内容发布
	 * @param response
	 * @param topic 主题
	 */
	@RequestMapping("/wechat/getWechatNewList")
	public void getWechatNewList(HttpServletResponse response,HttpServletRequest request) {
		String topic = request.getParameter("topic");
		String t = "";
		switch (topic) {
			case "djyw":
				t = "党建要闻";
				break;
			case "hdjy":
				t = "活动剪影";
				break;
			case "xwda":
				t = "新闻档案";
				break;
			case "dwzs":
				t = "党务知识";
				break;
			default:
				break;
		}
		List<Cms_Content> list = userService.listByHQL("from Cms_Content c where c.isvaild='Y' and c.topic='"+t+"' and c.status='Y' order by c.addtime desc");
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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
