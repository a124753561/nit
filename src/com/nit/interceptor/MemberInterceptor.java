/**
 *====================================================
 * 文件名称: MemberInterceptor.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.interceptor;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nit.Principal;
import com.nit.entity.Member;
import com.nit.service.MemberService;

/**
 * @ClassName: MemberInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午3:15:56
 */
public class MemberInterceptor extends HandlerInterceptorAdapter {

	private static final String REDIRECT_VIEW_NAME_PREFIX = "redirect:";

	private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";

	private static final String MEMBER_ATTRIBUTE_NAME = "member";

	private String loginUrl = "/login.jhtml";

	private boolean subSite = Boolean.FALSE;

	private List<String> subSiteArray;

	@Value("${url_escaping_charset}")
	private String urlEscapingCharset;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Principal principal = (Principal) session.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		if (principal != null) {
			return true;
		}
		// ajax 请求
		String requestType = request.getHeader("X-Requested-With");
		if ((requestType != null) && (requestType.equalsIgnoreCase("XMLHttpRequest"))) {
			response.addHeader("loginStatus", "accessDenied");
			response.sendError(403);
			return false;
		}
		if (request.getMethod().equalsIgnoreCase("GET")) {
			String redirectUrl = request.getQueryString() != null ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
			String path = redirectUrl.substring(request.getContextPath().length() + 1);
			String[] split = path.split("/");
			String base = "/" + split[0];
			if (subSite && subSiteArray != null && subSiteArray.contains(base)) {
				response.sendRedirect(request.getContextPath() + base + this.loginUrl + "?" + REDIRECT_URL_PARAMETER_NAME + "="
						+ URLEncoder.encode(redirectUrl, this.urlEscapingCharset));
			} else {
				response.sendRedirect(request.getContextPath() + this.loginUrl + "?" + REDIRECT_URL_PARAMETER_NAME + "="
						+ URLEncoder.encode(redirectUrl, this.urlEscapingCharset));
			}
		}
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			String viewName = modelAndView.getViewName();
			if (!StringUtils.startsWith(viewName, REDIRECT_VIEW_NAME_PREFIX))
				modelAndView.addObject(MEMBER_ATTRIBUTE_NAME, this.memberService.getCurrent());
		}
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public boolean isSubSite() {
		return subSite;
	}

	public void setSubSite(boolean subSite) {
		this.subSite = subSite;
	}

	public List<String> getSubSiteArray() {
		return subSiteArray;
	}

	public void setSubSiteArray(List<String> subSiteArray) {
		this.subSiteArray = subSiteArray;
	}
}
