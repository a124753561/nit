/**
 *====================================================
 * 文件名称: EnumConverter.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * 枚举类型转换
 * @ClassName: EnumConverter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 上午11:37:12
 */
public class EnumConverter extends AbstractConverter {

	/** 枚举类型 */
	private final Class<?> enumClass;

	/**
	 * @param enumClass 枚举类型
	 */
	public EnumConverter(Class<?> enumClass) {
		this(enumClass, null);
	}

	/**
	 * @param enumClass 枚举类型
	 * @param defaultValue 默认值
	 */
	public EnumConverter(Class<?> enumClass, Object defaultValue) {
		super(defaultValue);
		this.enumClass = enumClass;
	}

	/**
	 * 获取默认类型
	 * @return 默认类型
	 */
	@Override
	protected Class<?> getDefaultType() {
		return this.enumClass;
	}

	/**
	 * 转换为枚举对象
	 * @param type 类型
	 * @param value 值
	 * @return 枚举对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object convertToType(Class type, Object value) {
		String stringValue = value.toString().trim();
		return Enum.valueOf(type, stringValue);
	}

	/**
	 * 转换为字符串
	 * @param value 值
	 * @return 字符串
	 */
	protected String convertToString(Object value) {
		return value.toString();
	}

}
