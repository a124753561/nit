/**
 *====================================================
 * 文件名称: Department.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: Department
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午1:34:58
 */
@Entity
@Table(name = "xx_department")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_department_sequence")
public class Department extends BaseEntity {
	/** 部门名称 */
	@NotBlank
	@Column(nullable = false)
	private String name;

	/** 企业信息 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Enterprise enterprise;

	/** 管理员信息 */
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Admin> admins = new HashSet<Admin>();
	
	// ===========================================getter/setter===========================================//
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Set<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}

}
