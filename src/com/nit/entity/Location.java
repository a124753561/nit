/**
 *====================================================
 * 文件名称: Location.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @ClassName: Location
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午3:46:34
 */
@Embeddable
public class Location implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 5362169889399292249L;

	/** 经度 */
	@Column(name = "location_x", precision = 28, scale = 8)
	private BigDecimal x;

	/** 纬度 */
	@Column(name = "location_y", precision = 28, scale = 8)
	private BigDecimal y;

	/** 最后一次更新时间 */
	private Date latestTime;

	/** 地区 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_area")
	private Area area;

	public boolean isExists() {
		return this.x != null && this.y != null;
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public Date getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
