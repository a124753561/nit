/**
 *====================================================
 * 文件名称: TemplateService.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月9日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service;

import com.nit.Template;

/**
 * @ClassName: TemplateService
 * @Description: Service - 模板
 * @author Administrator
 * @date 2015年1月9日 下午1:39:10
 */
public interface TemplateService {
	/**
	 * 获取模板
	 * @param id ID
	 * @return 模板
	 */
	Template get(String id);
}
