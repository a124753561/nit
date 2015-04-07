/**
 *====================================================
 * 文件名称: Log.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @ClassName: Log
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午1:07:28
 */
@Entity
@Table(name = "xx_log")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_log_sequence")
public class Log extends BaseEntity{
	/** "日志内容"属性名称 */
	public static final String LOG_CONTENT_ATTRIBUTE_NAME = Log.class.getName() + ".CONTENT";

	/** 操作 */
	@Column(nullable = false, updatable = false)
	private String operation;

	/** 操作员 */
	@Column(updatable = false)
	private String operator;

	/** 内容 */
	@Column(length = 3000, updatable = false)
	private String content;

	/** 请求参数 */
	@Lob
	@Column(updatable = false)
	private String parameter;

	/** IP */
	@Column(nullable = false, updatable = false)
	private String ip;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
