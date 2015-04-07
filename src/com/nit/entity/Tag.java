/**
 *====================================================
 * 文件名称: Tag.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: Tag
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午3:44:13
 */
@Entity
@Table(name = "xx_tag")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_tag_sequence")
public class Tag extends OrderEntity {

	/** 类型 */
	public enum Type {
		/** 文章标签 */
		article,
		/** 商品标签 */
		product,
		/** 企业标签 */
		tenant,
		/** 社区标签 */
		community,
		/** 商品分类 */
		productCategory,
		/** 商盟分类 */
		tenantUnion
	};

	/** 名称 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	private String name;

	/** 类型 */
	@NotNull(groups = Save.class)
	@Column(nullable = false, updatable = false)
	private Type type;

	/** 图标 */
	@Length(max = 200)
	private String icon;

	/** 备注 */
	@Length(max = 200)
	private String memo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
