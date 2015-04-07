/**
 *====================================================
 * 文件名称: Filter.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit;

import java.io.Serializable;

/**
 * @ClassName: Filter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午3:27:08
 */
public class Filter implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = 1L;

	private String property;

	private Operator operator;

	private Object value;

	private Boolean ignoreCase = Boolean.valueOf(false);
	

	public Filter() {
	}
	
	public Filter(String property, Operator operator, Object value) {
		this.property = property;
		this.operator = operator;
		this.value = value;
	}
	public Filter(String property, Operator operator, Object value, boolean ignoreCase) {
		this.property = property;
		this.operator = operator;
		this.value = value;
		this.ignoreCase = Boolean.valueOf(ignoreCase);
	}
	public static Filter eq(String property, Object value) {
		return new Filter(property, Operator.eq, value);
	}
	public static Filter eq(String property, Object value, boolean ignoreCase) {
		return new Filter(property, Operator.eq, value, ignoreCase);
	}

	public static Filter ne(String property, Object value) {
		return new Filter(property, Operator.ne, value);
	}

	public static Filter ne(String property, Object value, boolean ignoreCase) {
		return new Filter(property, Operator.ne, value, ignoreCase);
	}
	public static Filter gt(String property, Object value) {
		return new Filter(property, Operator.gt, value);
	}

	public static Filter lt(String property, Object value) {
		return new Filter(property, Operator.lt, value);
	}

	public static Filter ge(String property, Object value) {
		return new Filter(property, Operator.ge, value);
	}

	public static Filter le(String property, Object value) {
		return new Filter(property, Operator.le, value);
	}

	public static Filter like(String property, Object value) {
		return new Filter(property, Operator.like, value);
	}

	public static Filter in(String property, Object value) {
		return new Filter(property, Operator.in, value);
	}

	public static Filter isNull(String property) {
		return new Filter(property, Operator.isNull, null);
	}

	public static Filter isNotNull(String property) {
		return new Filter(property, Operator.isNotNull, null);
	}
	public String getProperty() {
		return property;
	}


	public void setProperty(String property) {
		this.property = property;
	}


	public Operator getOperator() {
		return operator;
	}


	public void setOperator(Operator operator) {
		this.operator = operator;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}


	public Boolean getIgnoreCase() {
		return ignoreCase;
	}


	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}


	public static enum Operator {
		eq, ne, gt, lt, ge, le, like, in, isNull, isNotNull;

		public static Operator fromString(String value) {
			return valueOf(value.toLowerCase());
		}
	}
}
