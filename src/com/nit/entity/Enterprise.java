/**
 *====================================================
 * 文件名称: Enterprise.java
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.nit.entity.Admin;
import com.nit.entity.Area;
import com.nit.entity.Community;
import com.nit.entity.Department;
import com.nit.entity.Member;
import com.nit.entity.Role;

import com.google.gson.annotations.Expose;

/**
 * @ClassName: Enterprise
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午1:35:34
 */
@Entity
@Table(name = "xx_enterprise")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_enterprise_sequence")
public class Enterprise extends BaseEntity {
	/** 企业类型 */
	public enum EnterpriseType {
		/** 代理企业 */
		proxy,
		/** 物流公司 */
		delivery
	}

	/** 企业名称 */
	@Column(nullable = false, length = 255)
	private String name;

	/** 企业简称 */
	@Expose
	private String shortName;

	/** 企业类型 */
	@Expose
	private EnterpriseType enterprisetype;

	/** 经营许可证 */
	@Column(length = 255)
	private String licenseCode;

	/** 营业执照 **/
	private String licensePhoto;

	/** 拼音码 */
	@Column(length = 255)
	private String spell;

	/** 法人代表 */
	@Expose
	@Column(length = 255)
	private String legalRepr;

	/** 联系人 */
	@Expose
	@Column(length = 255)
	private String linkman;

	/** 联系电话 */
	@Column(length = 255)
	private String telephone;

	/** 传真 */
	@Column(length = 255)
	private String faxes;

	/** 地址 */
	@Column(length = 255)
	private String address;

	/** 邮编 */
	@Column(length = 255)
	private String zipcode;

	/** 绑定会员 */
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Member member;

	/** 区域 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Area area;

	/** 商圈信息 */
	@OneToMany(mappedBy = "enterprise", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Community> communities = new HashSet<Community>();

	/** 部门信息 */
	@OneToMany(mappedBy = "enterprise", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Department> departments = new HashSet<Department>();

	/** 管理员信息 */
	@OneToMany(mappedBy = "enterprise", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Admin> admins = new HashSet<Admin>();

	/** 角色信息 */
	@OneToMany(mappedBy = "enterprise", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Role> roles = new HashSet<Role>();
}
