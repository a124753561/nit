/**
 *====================================================
 * 文件名称: DateEditor.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月9日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * @ClassName: DateEditor
 * @Description: 日期类型转换
 * @author Administrator
 * @date 2015年1月9日 上午11:03:35
 */
public class DateEditor extends PropertyEditorSupport {
	/** 默认日期格式 */
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** 是否将空转换为null */
	private boolean emptyAsNull;

	/** 日期格式 */
	private String dateFormat = DEFAULT_DATE_FORMAT;

	/**
	 * @param emptyAsNull 是否将空转换为null
	 */
	public DateEditor(boolean emptyAsNull) {
		this.emptyAsNull = emptyAsNull;
	}

	/**
	 * @param emptyAsNull 是否将空转换为null
	 * @param dateFormat 日期格式
	 */
	public DateEditor(boolean emptyAsNull, String dateFormat) {
		this.emptyAsNull = emptyAsNull;
		this.dateFormat = dateFormat;
	}

	/**
	 * 获取日期
	 * @return 日期
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return value != null ? new SimpleDateFormat(dateFormat).format(value) : "";
	}

	/**
	 * 设置日期
	 * @param text 字符串
	 */
	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text.trim();
			if (emptyAsNull && "".equals(value)) {
				setValue(null);
			} else {
				try {
					setValue(DateUtils.parseDate(value, CommonAttributes.DATE_PATTERNS));
				} catch (ParseException e) {
					setValue(null);
				}
			}
		}
	}

}
