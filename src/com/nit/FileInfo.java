/**
 *====================================================
 * 文件名称: FileType.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月9日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit;

import java.util.Date;

/**
 * @ClassName: FileInfo
 * @Description: 文件信息
 * @author Administrator
 * @date 2015年1月9日 上午11:33:34
 */
public class FileInfo {
	/**
	 * 文件类型
	 */
	public enum FileType {

		/** 图片 */
		image,

		/** Flash */
		flash,

		/** 媒体 */
		media,

		/** 文件 */
		file
	}

	/**
	 * 排序类型
	 */
	public enum OrderType {

		/** 名称 */
		name,

		/** 大小 */
		size,

		/** 类型 */
		type
	}

	/** 名称 */
	private String name;

	/** URL */
	private String url;

	/** 是否为目录 */
	private Boolean isDirectory;

	/** 大小 */
	private Long size;

	/** 最后修改日期 */
	private Date lastModified;

	/**
	 * 获取名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取URL
	 * @return URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置URL
	 * @param url URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取是否为目录
	 * @return 是否为目录
	 */
	public Boolean getIsDirectory() {
		return isDirectory;
	}

	/**
	 * 设置是否为目录
	 * @param isDirectory 是否为目录
	 */
	public void setIsDirectory(Boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	/**
	 * 获取大小
	 * @return 大小
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * 设置大小
	 * @param size 大小
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * 获取最后修改日期
	 * @return 最后修改日期
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * 设置最后修改日期
	 * @param lastModified 最后修改日期
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
