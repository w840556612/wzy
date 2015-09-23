/**
 * 
 */
package com.ipinyou.optimus.console.user.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipinyou.base.constant.Regex;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Strategy.StrategyAreaList;

/**
 * 池，表示代理，作为多租户（Multi-Tenancy）系统的一个租户 池分三种：
 * Express池，即大算盘直客，大算盘用户注册时会自动加入该池，目前poolId=1, dasuanpan=true 大算盘代理池
 * dasuanpan=true 代理池（F版代理池）dasuanpan=false
 * 
 * @author lijt
 * 
 */
@Entity
@Table(name = "usr_pool")
@Data
@ToString(callSuper = true, exclude = { "orig", "users",
		"roles", "operationInfo" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig",
		"users", "roles", "operationInfo","poolTradingMode" })
public class Pool extends TimedEntity implements Auditable<Pool> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4897924928416408191L;
	@Column(length = 45, nullable = false, unique = true)
	private String name;
	
	/**
	 * 创建代理商的用户
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false, unique = true)
	private User creator;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long creatorId;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usr_pool_owner", inverseJoinColumns = @JoinColumn(name = "user_id"), joinColumns = @JoinColumn(name = "pool_id"))
	private Set<User> owners;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Pool parent;

	@Index(name = "parentId")
	@Column(insertable = false, updatable = false, nullable = true)
	private Long parentId;

	/**
	 * 是否为Demo演示池
	 */
	private boolean demo;

	/**
	 * 是否为族群投放
	 */
	private boolean family;
	
	/**
	 * 是否品友内部池
	 */
	private boolean internal;
	
	/**
	 * 是否品友测试池
	 */
	private boolean test;

	/**
	 * 是否为大算盘池（包括Express池和大算盘代理池该字段都是true）
	 */
	private boolean dasuanpan;
	
	/**
	 * 广告点击时，是否将我们从exchange平台拿到的URL传递给广告主。
	 */
	private boolean transferWebsiteUrl = false;

	/**
	 * 代理商类型
	 */
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private PoolType type = PoolType.Full;
	
	public static enum PoolType {

		Full("新优驰"), Dasuanpan("大算盘"), Tradingdesk("tradingdesk");

		private String text;

		private PoolType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	/**
	 * 代理商业务模式
	 */
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private PoolMode mode = PoolMode.Normal;

	public static enum PoolMode {
		Normal("普通"), DirectClient("直客"), OEM("OEM");

		private String text;

		private PoolMode(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	/**
	 * 代理商层级
	 */
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private Hierarchy hierarchy = Hierarchy.Single;
	
	public static enum Hierarchy {
		Single("单级"), First("一级"), Second("二级");
		
		private String text;

		private Hierarchy(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		} 
	}
	
	/**
	 * 若为EXPRESS表示为Express池（非大算盘代理池），若为IPINYOU表示为品友互动池，除此之外表示为其它agency
	 */
	@Column(length = 20)
	@Index(name = "sysId")
	private String sysId;

	public final static String SYS_ID_EXPRESS = "EXPRESS";

	public final static String SYS_ID_IPINYOU = "IPINYOU";
	
	public final static String SYS_ID_GM = "GM";

	/**
	 * 信用额度，正数，例如1000000
	 */
	@DecimalMin("0")
	@Column(nullable = false)
	private BigDecimal quota = new BigDecimal("0");

	@DecimalMin("0")
	@Column(nullable = false)
	private BigDecimal totalFeeRate = new BigDecimal("0");
	
	/**
	 * 费率系数
	 */
	@DecimalMin("0")
	@Column(nullable = false)
	private BigDecimal serviceFeeRate = new BigDecimal("1");

	/**
	 * 该池自注册用户默认设置的服务费率
	 */
	@DecimalMin("0")
	@Column(nullable = false)
	@NumberFormat(pattern = "##.####%")
	private BigDecimal registerDefaultSrvFeeRate = new BigDecimal("0.7");


	/**
	 * 该池自注册用户默认设置的最大允许的服务费率
	 */
	@DecimalMin("0")
	@Column(nullable = false)
	@NumberFormat(pattern = "##.####%")
	private BigDecimal maxRegisterSrvFeeRate = new BigDecimal("1.0");

	/**
	 * 该池下的用户
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "pool", fetch = FetchType.LAZY)
	private Set<User> users;

	/**
	 * 该池中的角色
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usr_pool_role", inverseJoinColumns = @JoinColumn(name = "role_id"), joinColumns = @JoinColumn(name = "pool_id"))
	private Set<Role> roles;

	/**
	 * 
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private PoolOperationInfo operationInfo;

	@Transient
	private BigDecimal balance;

	/*
	 * 代理商地址
	 */
	@Size(max = 128)
	@Column(length = 128)
	private String address;

	/**
	 * 联系人名称
	 */
	@Size(min = 2, max = 45)
	@Column(length = 45)
	private String contactName;

	/**
	 * 联系人手机号码
	 */
	@Pattern(regexp = Regex.CELLPHONE)
	@Column(length = 11)
	private String cellphone;

	/**
	 * 联系人邮箱
	 */
	@Size(min = 3, max = 100)
	@Pattern(regexp = Regex.EMAIL)
	@Column(length = 100)
	private String email;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	@Transient
	public String getEntityName() {
		return "池";
	}

	//
	// /** 是否为代理池，例如OMG，若为EXPREE池或者IPINYOU池则不是代理池
	// * @return
	// */
	// @Transient
	// public boolean isAgency(){
	// if (!SYS_ID_EXPRESS.equals(sysId) && !SYS_ID_IPINYOU.equals(sysId)) {
	// return true;
	// }else{
	// return false;
	// }
	//
	// }

	/*
	 * 注册标示
	 */
	@Size(min = 2, max = 64)
	@Column(nullable = true, unique = true, length = 64)
	@Pattern(regexp = "^[a-z0-9A-Z]+$")
	private String regSymbol;
	/**
	 * OEM客户，如果需将来源设置为客户的域名，需设置该属性
	 */
	@Size(max = 255)
	@Column(length = 255)
	private String statsDomain;

	/**
	 * OEM客户，如果需使用特定的CDN域名，需设置该属性，主要用来提供创意文件服务
	 */
	@Size(max = 255)
	@Column(length = 255)
	private String cdnDomain;
	
	/**
	 * 合同号
	 */
	@Size(max=30)
	@Column(length = 64)
	private String contractNo;
	
	/**
	 * 合同签订时间日期，年-月-日
	 */
	@Column(nullable = true)
	protected Date contractDate;
	
	/**
	 * 是否为Express池
	 * 
	 * @return
	 */
	@Transient
	public boolean isExpress() {
		return SYS_ID_EXPRESS.equals(sysId);
	}

	@Transient
	private Pool orig;
	

//	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn
//	private AgencyProtectSetting setting;
	
	@NotNull
	@Max(50)
	@Min(0)
	private Integer numLimit = 10;

	public Integer getNumLimit(){
		if(numLimit == null){
			return 10;
		}else{
			return this.numLimit;
		}
	}
	
	@NotNull
	@Max(5)
	@Min(0)
	private Integer timesLimit = 1;
	
	public Integer getTimesLimit(){
		if(timesLimit == null){
			return 1;
		}else{
			return this.timesLimit;
		}
	}

	@NotNull
	@Max(100)
	@Min(0)
	private Integer dayLimit = 30;

	public Integer getDayLimit(){
		if(dayLimit == null){
			return 30;
		}else{
			return this.dayLimit;
		}
	}
	
	/**
	 * 保护区域
	 */
	@Column(length = 64)
	private String area;

	/**
	 * 保护数量
	 */
	@Transient
	private Long protectNum = 0L;

	@Size(max = 45)
	@Column(length = 45)
	private String level;

	@Size( max = 45)
	@Column(length = 45)
	private String keyWords;

	@Size( max = 40)
	@Column(length = 40)
	private String telephone;

	@Size(max = 128)
	@Pattern(regexp = "^[https?://]?(\\S)+?\\.(\\S)+$")
	@Column(length = 128)
	private String website;

	@Index(name="py_audit")
	@NotNull
	@Column(nullable = false)
	private boolean pyAudit = true;
	
	/**
	 * 已合作
	 */
	@Transient
	private Long cooperateNum = 0L;
	
	
	/**
	 * 允许代理区域
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "protect_areas", length = 2000))
	private StrategyAreaList protectAreas;
	
	/**
	 * 允许代理区域计数，用于列表展示
	 */
	private Integer protectAreasCounter;
	
	
	/*
	 * 关联模式id
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "trading_mode_id", nullable = false)
	private PoolTradingMode poolTradingMode;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long tradingModeId;
	
}
