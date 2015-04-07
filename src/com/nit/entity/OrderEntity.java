/**
 *====================================================
 * 文件名称: OrderEntity.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName: OrderEntity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午4:18:08
 */
@MappedSuperclass
public class OrderEntity extends BaseEntity implements Comparable<OrderEntity> {

	/** "排序"属性名称 */
	public static final String ORDER_PROPERTY_NAME = "order";

	/** 排序 */
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@Min(0)
	@Column(name = "orders")
	private Integer order;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(OrderEntity orderEntity) {
		return new CompareToBuilder().append(getOrder(), orderEntity.getOrder()).append(getId(), orderEntity.getId()).toComparison();
	}

	/**
	 * 获取排序
	 * @return 排序
	 */
	@JsonProperty
	public Integer getOrder() {
		return order;
	}

	/**
	 * 设置排序
	 * @param order 排序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
}
