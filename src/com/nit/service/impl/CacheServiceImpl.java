/**
 *====================================================
 * 文件名称: CacheServiceImpl.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月9日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.nit.service.CacheService;
import com.nit.util.SettingUtils;

import freemarker.template.TemplateModelException;

/**
 * @ClassName: CacheServiceImpl
 * @Description: Service - 缓存
 * @author Administrator
 * @date 2015年1月9日 下午12:00:22
 */
@Service("cacheServiceImpl")
public class CacheServiceImpl implements CacheService {
	@Resource(name = "messageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.CacheService#clear()
	 */
	@CacheEvict(value = { "setting", "logConfig", "template" }, allEntries = true)
	public void clear() {
		reloadableResourceBundleMessageSource.clearCache();
		try {
			freeMarkerConfigurer.getConfiguration().setSharedVariable("setting", SettingUtils.get());
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		freeMarkerConfigurer.getConfiguration().clearTemplateCache();
	}

}
