/**
 *====================================================
 * 文件名称: CaptchaService.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service;

import java.awt.image.BufferedImage;

import com.nit.Setting.CaptchaType;

/**
 * @ClassName: CaptchaService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午2:21:20
 */
public interface CaptchaService {
	/**
	 * 生成验证码图片
	 * @param captchaId 验证ID
	 * @return 验证码图片
	 */
	BufferedImage buildImage(String captchaId);

	/**
	 * 验证码验证
	 * @param captchaType 验证码类型
	 * @param captchaId 验证ID
	 * @param captcha 验证码(忽略大小写)
	 * @return 验证码验证是否通过
	 */
	boolean isValid(CaptchaType captchaType, String captchaId, String captcha);
}
