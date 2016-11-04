package com.mtd.security;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionFilter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		// 后台session控制
		Object user = request.getSession().getAttribute("sysuser");
		String returnUrl = request.getRequestURI();
		if (null == user) {
			if (returnUrl.equals(request.getContextPath()+"/sysusers/sysLogin.do") || returnUrl.equals(request.getContextPath()+"/code/getcode.do")|| returnUrl.equals(request.getContextPath()+"/sysusers/logout.do") ) {
				return true;
			}else if(returnUrl.contains("wechat") || returnUrl.contains("qywechat")) {
				return true;
			}
			else {
				System.out.println("拦截成功============================"+returnUrl);
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
				builder.append("alert(\"系统超时，请重新登录\");");
				builder.append("window.top.location.href=\""+request.getContextPath()+"/login.jsp\";");
				builder.append("</script>");
				out.print(builder.toString());
				out.close();
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) throws Exception {

	}

	/**
	 * @param request
	 * @return Create Date:2013-6-5
	 * @author Shine Description:获取IP
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}