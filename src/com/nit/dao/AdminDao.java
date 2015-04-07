/**
 *====================================================
 * 文件名称: AdminDao.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.dao;

import com.nit.entity.Admin;

/**
 * @ClassName: AdminDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午1:24:10
 */
public interface AdminDao extends BaseDao<Admin, Long> {

	/**
	 * @param username
	 * @return
	 */
	Admin findByUsername(String username);

}
