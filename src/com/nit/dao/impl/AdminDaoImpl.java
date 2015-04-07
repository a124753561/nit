/**
 *====================================================
 * 文件名称: AdminDaoImpl.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.nit.dao.AdminDao;
import com.nit.entity.Admin;

/** 管理员
 * @ClassName: AdminDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Administrator 
 * @date 2014年12月19日 下午1:24:47  
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao {

	/* (non-Javadoc)
	 * @see com.nit.dao.AdminDao#findByUsername(java.lang.String)
	 */
	@Override
	public Admin findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select admin from Admin admin where lower(admin.username) = lower(:username)";
			return entityManager.createQuery(jpql, Admin.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}



}
