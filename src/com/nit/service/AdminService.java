/**
 *====================================================
 * 文件名称: AdminService.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service;

import java.io.Serializable;
import java.util.List;

import com.nit.entity.Admin;

/**
 * Service - 管理员
 * @ClassName: AdminService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午1:19:39
 */
public interface AdminService extends BaseService<Admin, Long>, Serializable {
	/**
	 * 获取当前登录管理员
	 * @return 当前登录管理员,若不存在则返回null
	 */
	Admin getCurrent();

	/**
	 * @return
	 */
	String getCurrentUsername();

	/**
	 * @param id
	 * @return
	 */
	List<String> findAuthorities(Long id);

	/**
	 * @param username
	 * @return
	 */
	Admin findByUsername(String username);

}
