/**
 *====================================================
 * 文件名称: WebUtils.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nit.Setting;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * @ClassName: WebUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午5:35:46
 */
public final class WebUtils {

	private static final String ENCODE = "UTF-8";

	/**
	 * 不可实例化
	 */
	private WebUtils() {
	}

	/**
	 * 添加cookie
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param name cookie名称
	 * @param value cookie值
	 * @param maxAge 有效期(单位: 秒)
	 * @param path 路径
	 * @param domain 域
	 * @param secure 是否启用加密
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path,
			String domain, Boolean secure) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, ENCODE);
			value = URLEncoder.encode(value, ENCODE);
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge.intValue());
			}
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			if (secure != null) {
				cookie.setSecure(secure.booleanValue());
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加cookie
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param name cookie名称
	 * @param value cookie值
	 * @param maxAge 有效期(单位: 秒)
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge) {
		Setting setting = SettingUtils.get();
		addCookie(request, response, name, value, maxAge, setting.getCookiePath(), setting.getCookieDomain(), null);
	}

	/**
	 * 添加cookie
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param name cookie名称
	 * @param value cookie值
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		Setting setting = SettingUtils.get();
		addCookie(request, response, name, value, null, setting.getCookiePath(), setting.getCookieDomain(), null);
	}

	/**
	 * 获取cookie
	 * @param request HttpServletRequest
	 * @param name cookie名称
	 * @return 若不存在则返回null
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Assert.notNull(request);
		Assert.hasText(name);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			try {
				name = URLEncoder.encode(name, ENCODE);
				for (Cookie cookie : cookies) {
					if (name.equals(cookie.getName())) {
						return URLDecoder.decode(cookie.getValue(), ENCODE);
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 移除cookie
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param name cookie名称
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Setting setting = SettingUtils.get();
		removeCookie(request, response, name, setting.getCookiePath(), setting.getCookieDomain());
	}

	/**
	 * 移除cookie
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param name cookie名称
	 * @param path 路径
	 * @param domain 域
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, ENCODE);
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
