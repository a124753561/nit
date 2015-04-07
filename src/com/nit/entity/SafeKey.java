/**
 *====================================================
 * 文件名称: SafeKey.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 安全密钥
 * @ClassName: SafeKey
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午3:53:15
 */
@Embeddable
public class SafeKey implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 2997562797578525554L;

	/** 密钥 */
	@Column(name = "safe_key_value")
	private String value;

	/** 到期时间 */
	@Column(name = "safe_key_expire")
	private Date expire;

	/** 判断是否已过期 */
	public boolean hasExpired() {
		return getExpire() != null && new Date().after(getExpire());
	}

	public SafeKey() {
	}

	public SafeKey(String value, Date expire) {
		this.value = value;
		this.expire = expire;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

}
