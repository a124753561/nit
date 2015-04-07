/**
 *====================================================
 * 文件名称: AuthenticationToken.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName: AuthenticationToken
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午2:45:16
 */
public class AuthenticationToken extends UsernamePasswordToken {
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = -877691985245292047L;

	/** 验证码ID */
	private String captchaId;

	/** 验证码 */
	private String captcha;

	/**
	 * @param username 用户名
	 * @param password 密码
	 * @param captchaId 验证码ID
	 * @param captcha 验证码
	 * @param rememberMe 记住我
	 * @param host IP
	 */
	public AuthenticationToken(String username, String password, String captchaId, String captcha, boolean rememberMe, String host) {
		super(username, password, rememberMe);
		this.captchaId = captchaId;
		this.captcha = captcha;
	}

	public String getCaptchaId() {
		return captchaId;
	}

	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
