/**
 *====================================================
 * 文件名称: Member.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.nit.interceptor.MemberInterceptor;

/**
 * @ClassName: Member
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午4:43:35
 */
@Entity
@Table(name = "xx_member")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_member_sequence")
public class Member extends BaseEntity {

	/** 性别 */
	public enum Gender {
		/** 男 */
		male,
		/** 女 */
		female
	}

	/** 绑定状态 */
	public enum BindStatus {
		/** 没绑定 */
		none,
		/** 已绑定 */
		binded,
		/** 已解绑 */
		unbind
	}

	/** 用户角色 */
	public enum Role {
		/** 普通会员 */
		ordinaryMember,
		/** 安装商 */
		installer,
		/** 销售商 */
		vendors,
		/** 经销商 */
		dealers
	}

	/** "身份信息"参数名称 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".PRINCIPAL";

	/** "所属区域"参数名称 */
	public static final String AREA_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".AREA";

	/** "校证码"参数名称 */
	public static final String CHALLEGE_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".CHALLEGE";

	/** "接口权限"参数名称 */
	public static final String INTERFACE_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".INTERFACE";

	/** "用户名"Cookie名称 */
	public static final String USERNAME_COOKIE_NAME = "username";

	/** "令牌"Cookie名称 */
	public static final String TOKEN_COOKIE_NAME = "token";

	/** 会员注册项值属性个数 */
	public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 10;

	/** 会员注册项值属性名称前缀 */
	public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";

	/** 最大收藏商品数 */
	public static final Integer MAX_FAVORITE_COUNT = 10;

	/** 用户名 */
	@Expose
	@JsonProperty
	@NotEmpty(groups = Save.class)
	@Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$|^(\\w+)([\\-+.\\'][\\w]+)*@(\\w[\\-\\w]*\\.){1,5}([A-Za-z]){2,6}$")
	@Column(nullable = false, updatable = false, unique = true, length = 100)
	private String username;

	/** 密码 */
	@Expose
	@NotEmpty(groups = Save.class)
	@Pattern(regexp = "^[^\\s&\"<>]+$")
	@Column(nullable = false)
	private String password;

	/** 支付密码 */
	@NotEmpty(groups = Save.class)
	@Pattern(regexp = "^[^\\s&\"<>]+$")
	@Column(nullable = false)
	private String paymentPassword;

	/** E-mail */
	@Expose
	@NotEmpty
	@Email
	@Length(max = 200)
	@Column(nullable = false)
	private String email;

	/** E-mail绑定 */
	@Expose
	@Column(nullable = false)
	private BindStatus bindEmail;

	/** 积分 */
	@Expose
	@JsonProperty
	@NotNull(groups = Save.class)
	@Min(0)
	@Column(nullable = false)
	private Long point;

	/** 诚信积分 */
	@Min(0)
	@Column(nullable = false, columnDefinition = "bigint default 0")
	private Long totalScore;

	/** 消费金额 */
	@Expose
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal amount;

	/** 消费返利 */
	@Expose
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal profitAmount;

	/** 代理收益 */
	@Expose
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal rebateAmount;

	/** 冻结余额 */
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(nullable = false, precision = 27, scale = 12, columnDefinition = "decimal default 0")
	private BigDecimal freezeBalance;

	/** 特权数 */
	@Expose
	@JsonProperty
	@Column(nullable = false)
	private Integer privilege;

	/** 余额 */
	@Expose
	@JsonProperty
	@NotNull(groups = Save.class)
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(nullable = false, precision = 27, scale = 12)
	private BigDecimal balance;

	/** 是否启用 */
	@Expose
	@NotNull
	@Column(nullable = false)
	private Boolean isEnabled;

	/** 是否锁定 */
	@Expose
	@Column(nullable = false)
	private Boolean isLocked;

	/** 连续登录失败次数 */
	@Expose
	@Column(nullable = false)
	private Integer loginFailureCount;

	/** 锁定日期 */
	@Expose
	private Date lockedDate;

	/** 注册IP */
	@Expose
	@Column(nullable = false, updatable = false)
	private String registerIp;

	/** 最后登录IP */
	@Expose
	private String loginIp;

	/** 最后登录日期 */
	@Expose
	private Date loginDate;

	/** 姓名 */
	@Expose
	@JsonProperty
	@NotEmpty
	@Length(max = 200)
	private String name;

	/** 头像 */
	@Expose
	@JsonProperty
	@Length(max = 255)
	private String headImg;

	/** 性别 */
	@Expose
	@JsonProperty
	private Gender gender;

	/** 出生日期 */
	@Expose
	@JsonProperty
	private Date birth;

	/** 地址 */
	@Expose
	@JsonProperty
	@Length(max = 200)
	private String address;

	/** 邮编 */
	@Expose
	@JsonProperty
	@Length(max = 200)
	private String zipCode;

	/** 电话 */
	@Expose
	@JsonProperty
	@Length(max = 200)
	private String phone;

	/** 手机 */
	@Expose
	@JsonProperty
	@NotEmpty
	@Length(max = 200)
	private String mobile;

	/** 提现手续费 */
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 27, scale = 12)
	private BigDecimal withdrawCashScale;

	/** 手机 绑定 */
	@Expose
	@Column(nullable = false)
	private BindStatus bindMobile;

	/** 会员注册项值0 */
	@Length(max = 200)
	private String attributeValue0;

	/** 会员注册项值1 */
	@Length(max = 200)
	private String attributeValue1;

	/** 会员注册项值2 */
	@Length(max = 200)
	private String attributeValue2;

	/** 安全密匙 */
	@Embedded
	private SafeKey safeKey;

	/** 地区 */
	@Expose
	@JsonProperty
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Area area;

	/** 地理位置 */
	@Embedded
	private Location location;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BindStatus getBindEmail() {
		return bindEmail;
	}

	public void setBindEmail(BindStatus bindEmail) {
		this.bindEmail = bindEmail;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public Long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(BigDecimal profitAmount) {
		this.profitAmount = profitAmount;
	}

	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public BigDecimal getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(BigDecimal freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getWithdrawCashScale() {
		return withdrawCashScale;
	}

	public void setWithdrawCashScale(BigDecimal withdrawCashScale) {
		this.withdrawCashScale = withdrawCashScale;
	}

	public BindStatus getBindMobile() {
		return bindMobile;
	}

	public void setBindMobile(BindStatus bindMobile) {
		this.bindMobile = bindMobile;
	}

	public String getAttributeValue0() {
		return attributeValue0;
	}

	public void setAttributeValue0(String attributeValue0) {
		this.attributeValue0 = attributeValue0;
	}

	public String getAttributeValue1() {
		return attributeValue1;
	}

	public void setAttributeValue1(String attributeValue1) {
		this.attributeValue1 = attributeValue1;
	}

	public String getAttributeValue2() {
		return attributeValue2;
	}

	public void setAttributeValue2(String attributeValue2) {
		this.attributeValue2 = attributeValue2;
	}

	public SafeKey getSafeKey() {
		return safeKey;
	}

	public void setSafeKey(SafeKey safeKey) {
		this.safeKey = safeKey;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
