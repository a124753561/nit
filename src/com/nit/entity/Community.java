/**
 *====================================================
 * 文件名称: Community.java
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

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName: Community
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午3:43:05
 */
@Entity
@Table(name = "xx_community")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_community_sequence")
public class Community extends BaseEntity {
	/** 状态 */
	public enum Status {
		/** 申请 */
		wait,
		/** 开通 */
		open,
		/** 关闭 */
		close
	}

	/** 编码 */
	private String code;

	/** 名称 */
	@Length(max = 255)
	@JsonProperty
	private String name;

	/** 描述 */
	@Lob
	private String descr;

	/** 地区 */
	@JsonProperty
	@ManyToOne(fetch = FetchType.LAZY)
	private Area area;

	/** 状态 */
	private Status status;

	/** 展示图片 */
	@Length(max = 200)
	private String image;

	/** 归属配送物业公司 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Enterprise enterprise;

	/** 标签 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "xx_community_tag")
	@OrderBy("order asc")
	private Set<Tag> tags = new HashSet<Tag>();

	/** 经纬度 */
	@Embedded
	private Location location;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
