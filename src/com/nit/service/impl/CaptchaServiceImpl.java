/**
 *====================================================
 * 文件名称: CaptchaServiceImpl.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service.impl;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.nit.Setting;
import com.nit.Setting.CaptchaType;
import com.nit.service.CaptchaService;
import com.nit.util.SettingUtils;

/** 
 * @ClassName: CaptchaServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Administrator 
 * @date 2014年12月19日 下午2:23:23  
 */
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements CaptchaService {
	
	@Resource(name = "imageCaptchaService")
	private com.octo.captcha.service.CaptchaService imageCaptchaService;
	/* (non-Javadoc)
	 * @see com.nit.service.CaptchaService#buildImage(java.lang.String)
	 */
	@Override
	public BufferedImage buildImage(String captchaId) {
		return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
	}

	/* (non-Javadoc)
	 * @see com.nit.service.CaptchaService#isValid(com.nit.Setting.CaptchaType, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isValid(CaptchaType captchaType, String captchaId, String captcha) {
		Setting setting = SettingUtils.get();
		if (captchaType == null || ArrayUtils.contains(setting.getCaptchaTypes(), captchaType)) {
			if (StringUtils.isNotEmpty(captchaId) && StringUtils.isNotEmpty(captcha)) {
				try {
					return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
				} catch (Exception e) {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

}
