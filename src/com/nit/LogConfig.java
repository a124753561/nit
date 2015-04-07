/**
 *====================================================
 * 文件名称: LogConfig.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit;

import java.io.Serializable;

/**
 * @ClassName: LogConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午1:07:34
 */
public class LogConfig implements Serializable {
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = -5301214782573329446L;

	/** 操作名称 */
	private String operation;

	/** 请求URL */
	private String urlPattern;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

}
