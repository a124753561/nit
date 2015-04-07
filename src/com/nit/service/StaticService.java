/**
 *====================================================
 * 文件名称: StaticService.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月9日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service;

/**
 * @ClassName: StaticService
 * @Description: Service - 静态化
 * @author Administrator
 * @date 2015年1月9日 下午1:24:34
 */
public interface StaticService {
	/**
	 * 生成首页静态
	 * @return 生成数量
	 */
	int buildIndex();

	/**
	 * 生成其它静态
	 * @return 生成数量
	 */
	int buildOther();
}
