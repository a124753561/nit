/**
 *====================================================
 * 文件名称: Order.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * @ClassName: Order
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午3:43:21
 */
public class Order implements Serializable {

	private static final Direction DEFAULT_DIRECTION = Direction.desc;

	private String property;

	private Direction direction = DEFAULT_DIRECTION;

	public Order() {
	}

	public Order(String property, Direction direction) {
		this.property = property;
		this.direction = direction;
	}

	public static Order asc(String property) {
		return new Order(property, Direction.asc);
	}

	public static Order desc(String property) {
		return new Order(property, Direction.desc);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (super.getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Order other = (Order) obj;
		return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getDirection(), other.getDirection()).isEquals();
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public static enum Direction {
		asc, desc;

		public static Direction fromString(String value) {
			return valueOf(value.toLowerCase());
		}
	}
}
