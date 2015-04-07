/**
 *====================================================
 * 文件名称: AdminServiceImpl.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.nit.Principal;
import com.nit.dao.AdminDao;
import com.nit.entity.Admin;
import com.nit.entity.Role;
import com.nit.service.AdminService;

/**
 * 管理员
 * @ClassName: AdminServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午1:22:00
 */
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long> implements AdminService {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 9209118051078092724L;

	@Resource(name = "adminDaoImpl")
	private AdminDao adminDao;
	
	@Resource(name = "adminDaoImpl")
	public void setBaseDao(AdminDao adminDao) {
		super.setBaseDao(adminDao);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.AdminService#getCurrent()
	 */
	public Admin getCurrent() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return adminDao.find(principal.getId());
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nit.service.AdminService#getCurrentUsername()
	 */
	public String getCurrentUsername() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nit.service.AdminService#findAuthorities(java.lang.Long)
	 */
	public List<String> findAuthorities(Long id) {
		List<String> authorities = new ArrayList<String>();
		Admin admin = adminDao.find(id);
		if (admin != null) {
			for (Role role : admin.getRoles()) {
				authorities.addAll(role.getAuthorities());
			}
		}
		return authorities;
	}

	/* (non-Javadoc)
	 * @see com.nit.service.AdminService#findByUsername(java.lang.String)
	 */
	@Override
	public Admin findByUsername(String username) {
		return adminDao.findByUsername(username);
	}

}
