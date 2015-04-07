/**
 *====================================================
 * 文件名称: AccessDeniedFilter.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import sun.misc.BASE64Decoder;

/**
 * @ClassName: AccessDeniedFilter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午1:20:19
 */
public class AccessDeniedFilter implements Filter {
	private static final String ERROR_MESSAGE = "Access denied!";

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		httpServletResponse.addHeader(new String(new BASE64Decoder().decodeBuffer("UG93ZXJlZEJ5"), "utf-8"),
				new String(new BASE64Decoder().decodeBuffer("U2hvcHh4Lm5ldA=="), "utf-8"));
		httpServletResponse.sendError(403, ERROR_MESSAGE);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void destroy() {

	}
}
