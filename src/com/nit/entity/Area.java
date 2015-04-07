/**
 *====================================================
 * 文件名称: Area.java
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
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

/**
 * 地区
 * @ClassName: Area
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午3:39:16
 */
@Entity
@Table(name = "xx_area")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_area_sequence")
public class Area extends OrderEntity {
	/** 类型 */
	public enum Status {
		/** 无定义 */
		none,
		/** 开通 */
		enabled,
		/** 关闭 */
		disabled
	}

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	/** 树路径分隔符 */
	public static final String AREA_NAME = "area_name";

	/** 树路径分隔符 */
	public static final String AREA_ID = "area_id";

	/** 名称 */
	@Expose
	@NotEmpty
	@Length(max = 100)
	@Column(nullable = false, length = 100)
	private String name;

	/** 编码 */
	@Expose
	@JsonProperty
	@Column(length = 6)
	private String code;

	/** 编码 */
	@Expose
	@Column(length = 6)
	private String zipCode;

	/** 编码 */
	@Expose
	@Column(length = 4)
	private String telCode;

	/** 全称 */
	@Expose
	@JsonProperty
	@Column(nullable = false, length = 500)
	private String fullName;

	/** 树路径 */
	@Column(nullable = false, updatable = false)
	private String treePath;

	/** 上级地区 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Area parent;

	@JsonProperty
	private Status status;

	/** 下级地区 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("order asc")
	private Set<Area> children = new HashSet<Area>();

	/** 会员 */
	@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
	private Set<Member> members = new HashSet<Member>();

	public Status getStatus() {
		if (this.status == null) {
			return Status.none;
		}
		return status;
	}

	/** 持久化前处理 */
	@PrePersist
	public void prePersist() {
		Area parent = getParent();
		if (parent != null) {
			setFullName(parent.getFullName() + getName());
			setTreePath(parent.getTreePath() + parent.getId() + TREE_PATH_SEPARATOR);
		} else {
			setFullName(getName());
			setTreePath(TREE_PATH_SEPARATOR);
		}
	}

	/** 更新前处理 */
	@PreUpdate
	public void preUpdate() {
		Area parent = getParent();
		if (parent != null) {
			setFullName(parent.getFullName() + getName());
		} else {
			setFullName(getName());
		}
	}

	/** 删除前处理 */
	@PreRemove
	public void preRemove() {
		Set<Member> members = getMembers();
		if (members != null) {
			for (Member member : members) {
				member.setArea(null);
			}
		}
	}

	/** 重写toString方法 */
	@Override
	public String toString() {
		return getFullName();
	}

	// ===========================================getter/setter===========================================//
	/**
	 * 获取名称
	 * @return 名称
	 */
	@JsonProperty
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
	 * 获取编码
	 * @return 编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置编码
	 * @param code 编码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取编码
	 * @return 编码
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置编码
	 * @param code 编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取编码
	 * @return 编码
	 */
	public String getTelCode() {
		return telCode;
	}

	/**
	 * 设置编码
	 * @param code 编码
	 */
	public void setTelCode(String telCode) {
		this.telCode = telCode;
	}

	/**
	 * 获取全称
	 * @return 全称
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 设置全称
	 * @param fullName 全称
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * 获取树路径
	 * @return 树路径
	 */
	public String getTreePath() {
		return treePath;
	}

	/**
	 * 设置树路径
	 * @param treePath 树路径
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	/**
	 * 获取上级地区
	 * @return 上级地区
	 */
	public Area getParent() {
		return parent;
	}

	/**
	 * 设置上级地区
	 * @param parent 上级地区
	 */
	public void setParent(Area parent) {
		this.parent = parent;
	}

	/**
	 * 获取下级地区
	 * @return 下级地区
	 */
	public Set<Area> getChildren() {
		return children;
	}

	/**
	 * 设置下级地区
	 * @param children 下级地区
	 */
	public void setChildren(Set<Area> children) {
		this.children = children;
	}

	/**
	 * 获取会员
	 * @return 会员
	 */
	public Set<Member> getMembers() {
		return members;
	}

	/**
	 * 设置会员
	 * @param members 会员
	 */
	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}
