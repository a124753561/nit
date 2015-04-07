/**
 *====================================================
 * 文件名称: FileService.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月9日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nit.FileInfo;
import com.nit.FileInfo.FileType;
import com.nit.FileInfo.OrderType;

/**
 * @ClassName: FileService
 * @Description: Service - 文件
 * @author Administrator
 * @date 2015年1月9日 上午11:32:09
 */
public interface FileService {
	/**
	 * 文件验证
	 * @param fileType 文件类型
	 * @param multipartFile 上传文件
	 * @return 文件验证是否通过
	 */
	boolean isValid(FileType fileType, MultipartFile multipartFile);

	/**
	 * 文件上传至本地
	 * @param fileType 文件类型
	 * @param multipartFile 上传文件
	 * @return 路径
	 */
	String uploadLocal(FileType fileType, MultipartFile multipartFile);

	/**
	 * 文件上传
	 * @param fileType 文件类型
	 * @param multipartFile 上传文件
	 * @param async 是否异步
	 * @return 访问URL
	 */
	String upload(FileType fileType, MultipartFile multipartFile, boolean async);

	/**
	 * 文件浏览
	 * @param path 浏览路径
	 * @param fileType 文件类型
	 * @param orderType 排序类型
	 * @return 文件信息
	 */
	List<FileInfo> browser(String path, FileType fileType, OrderType orderType);

}
