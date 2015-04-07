/**
 *====================================================
 * 文件名称: TokenInterceptor.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nit.util.WebUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @ClassName: TokenInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午5:17:34
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class.getName());

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("user:{} send request[{}],request Method is [{}]",
				new String[] { request.getRemoteAddr(), request.getRequestURL().toString(), request.getMethod() });

		String token = WebUtils.getCookie(request, "token");
		if (request.getMethod().equalsIgnoreCase("POST")) {
			String requestType = request.getHeader("X-Requested-With");
			if ((requestType != null) && (requestType.equalsIgnoreCase("XMLHttpRequest"))) {
				if ((token != null) && (token.equals(request.getHeader("token")))) {
					return true;
				}
				response.addHeader("tokenStatus", "accessDenied");
			} else if ((token != null) && (token.equals(request.getParameter("token")))) {
				return true;
			}

			if (token == null) {
				token = UUID.randomUUID().toString();
				WebUtils.addCookie(request, response, "token", token);
			}
			response.sendError(403, "Bad or missing token!");
			return false;
		}
		if (token == null) {
			token = UUID.randomUUID().toString();
			WebUtils.addCookie(request, response, "token", token);
		}
		request.setAttribute("token", token);
		return true;
	}

}
