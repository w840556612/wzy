/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.constant.Regex;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.listener.PinyinListener;
import com.ipinyou.base.util.I18nResourceUtil;
import com.ipinyou.optimus.console.ad.vo.AdvPlatformAuditVo;
import com.ipinyou.optimus.console.finance.entity.AdvertiserBalance;
import com.ipinyou.optimus.console.user.entity.Pool;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 广告主
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig", "creator", "owners", "pool",
		"company", "stats", "operationInfo", "balance", "advPlatformAuditVo" ,"agencyProtectHistory"})
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "creator", "owners",
		"pool", "company", "stats", "operationInfo", "balance",
		"advPlatformAuditVo","agencyProtectHistory" })
@JsonIgnoreProperties(value = { "version", "lastModified", "creator", "owners",
		"pool", "stats", "operationInfo", "company", "showLogo", "balance","agencyProtectHistory" })
@EntityListeners({ PinyinListener.class })
public class Advertiser extends TimedEntity implements Auditable<Advertiser> {

	private static final long serialVersionUID = -4876741447370968654L;

	public static enum AdvertiserType {
		Company("optimus.console.ad.entity.Advertiser.1000"/*公司*/), Personal("optimus.console.ad.entity.Advertiser.1001"/*个人*/);

		private String text;

		private AdvertiserType(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}

	}

	public static enum AdvertiserStatus {
		CAN_NOT_AUDIT("optimus.console.ad.entity.Advertiser.1002"/*资质待完善*/), NOT_CHECKED("optimus.console.ad.entity.Advertiser.1003"/*审核中*/), APPROVED("optimus.console.ad.entity.Advertiser.1004"/*审核通过*/), DISAPPROVED(
				"optimus.console.ad.entity.Advertiser.1005"/*审核拒绝*/);

		private String text;

		private AdvertiserStatus(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}

		// public static Advertiser refreshStatus(Advertiser advertiser) {
		// if (advertiser.getApprovalStatus() == AdvertiserStatus.APPROVED) {
		// return advertiser;
		// }
		// if (StringUtils.isNotEmpty(advertiser.getLicenseFileExt())
		// && advertiser.getType() != null
		// && StringUtils.isNotEmpty(advertiser.getName())
		// && StringUtils.isNotEmpty(advertiser.getContactName())) {
		// if (advertiser.getType() == AdvertiserType.Company
		// && StringUtils.isNotEmpty(advertiser.getLegalPersonIdExt())) {
		// advertiser.setApprovalStatus(NOT_CHECKED);
		// } else if (advertiser.getType() == AdvertiserType.Personal
		// && StringUtils.isNotEmpty(advertiser.getWangwang())) {
		// advertiser.setApprovalStatus(NOT_CHECKED);
		// } else {
		// advertiser.setApprovalStatus(CAN_NOT_AUDIT);
		// }
		// } else {
		// advertiser.setApprovalStatus(CAN_NOT_AUDIT);
		// }
		//
		// return advertiser;
		// }
	}

	@Override
	@Transient
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Advertiser.1006")/*广告主*/;
	}

	/**
	 * 池
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "pool_id", nullable = false)
	private Pool pool;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long poolId;

	/**
	 * 统计信息
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private AdvertiserStats stats;

	/**
	 * 
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private AdvertiserOperationInfo operationInfo;
	
	
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private AdvertiserConverateIndex convertionRateIndex;

	/**
	 * 所属的用户
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "advertiser_owner", inverseJoinColumns = @JoinColumn(name = "user_id"), joinColumns = @JoinColumn(name = "advertiser_id"))
	private Set<User> owners;
	
	private boolean selfSupport = true;

	/**
	 * 创建广告主的用户
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false, unique = true)
	private User creator;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long creatorId;

	/**
	 * 广告主公司注册名称，Express版为空；Full版页面填入
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	@NotNull(groups = {SaveChecksFOnly.class,SaveChecksAgencyProtectOnly.class})
	private String registerName;

	/**
	 * 广告主公司注册名称 广告主公司注册名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 1000)
	private String registerNamePinyin;

	/**
	 * 广告主系统内部名称
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	@NotNull(groups = SaveChecksFOnly.class)
	@Index(name = "name")
	private String name;

	/**
	 * 广告主系统内部名称 广告主系统内部名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 1000)
	private String namePinyin;
	
	/**
	 * 广告主地址
	 */
	@Size(max = 128)
	@Column(length = 128)
	private String address;

	/**
	 * 同一个页面是否排斥出现同一广告主的多个广告
	 */
	@Column(nullable = false)
	private boolean repulsive = false;

	/**
	 * 是否显示品友小Logo，true表示开启
	 */
	private boolean showLogo = true;

	/**
	 * 开启／关闭 (暂不对前台开放，仅在后台使用)
	 */
	@Column(nullable = false)
	private boolean active = true;

	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	/**
	 * 广告主网址
	 */
	@NotNull
	@Size(min = 10, max = 128)
	@Pattern(regexp = Regex.URL)
	@Column(nullable = false, length = 128)
	private String website;

	/**
	 * 隐藏来源，默认为隐藏 (暂不对前台开放，仅在后台使用)
	 */
	private boolean hiddenSource = true;

	/**
	 * 联系人邮箱
	 */
	@NotNull
	@Size(min = 3, max = 50)
	@Pattern(regexp = Regex.EMAIL)
	@Column(nullable = false, length = 100)
	@Index(name = "email")
	private String email;

	/**
	 * 联系方式
	 */
	@NotNull
	@Size(min = 7, max = 32, groups = SaveChecksFOnly.class)
	@Column(nullable = false, length = 32)
	private String cellphone;
	// /**
	// * 固定电话
	// */
	// @Size(min = 3, max = 40)
	// @Column(length = 40)
	// private String telephone;
	/**
	 * 联系人名称
	 */
	@NotNull
	@Size(min = 2, max = 45)
	@Column(length = 45)
	private String contactName;

	/**
	 * 联系人名称 联系人名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 1000)
	private String contactNamePinyin;

	/**
	 * 类型，目前为公司或者个人
	 */
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private AdvertiserType type;

	// /**
	// * 身份证、广告主企业营业执照复印件扩展名，例如jpg、jpeg、png、gif
	// */
	// @Column(length = 64)
	// private String licenseFileExt;
	//
	// /**
	// * 企业法人身份证复印件扩展名，例如jpg、jpeg、png、gif
	// */
	// @Column(length = 64)
	// private String legalPersonIdExt;

	/**
	 * 企业资质为taobao旺旺时的淘宝旺旺帐号
	 */
	@Size(max = 100)
	@Column(length = 100)
	private String wangwang;

	// database file
	/**
	 * 广告主内部审核状态,默认通过
	 */
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private AdvertiserStatus approvalStatus = AdvertiserStatus.CAN_NOT_AUDIT;

	/**
	 * 广告主最近内部审核通过/拒绝时间
	 */
	@Column(updatable = true, nullable = true)
	protected Timestamp lastAudited;

	/**
	 * 广告主内部审核拒绝原因描述
	 */
	@Size(max = 500)
	@Column(length = 500)
	private String disapprovalDesc;

	@DecimalMin("0")
	@Column(nullable = false)
	@NumberFormat(pattern = "##.####%")
	private BigDecimal totalFeeRate = new BigDecimal("0");
	
	@DecimalMin("0")
	@NumberFormat(pattern = "##.####%")
	private BigDecimal serviceFeeRate;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private AdvertiserCompany company;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long companyId;

	// /**
	// * ICP证复印件扩展名，例如jpg、jpeg、png、gif
	// */
	// @Column(length = 64)
	// private String icpFileExt;

	/**
	 * 是否使用正则化(曝光人群分析时用到)
	 */
	private boolean regular = false;

	public static enum AdvertiserCategory {
		NotSet("optimus.console.ad.entity.Advertiser.1007"/*未分类*/), Main("optimus.console.ad.entity.Advertiser.1008"/*重点客户*/), Normal("optimus.console.ad.entity.Advertiser.1009"/*一般用户*/), Small("optimus.console.ad.entity.Advertiser.1010"/*小客户*/), WeakIntention(
				"optimus.console.ad.entity.Advertiser.1011"/*弱合作客户*/), Refund("optimus.console.ad.entity.Advertiser.1012"/*退款客户*/);

		private String text;

		private AdvertiserCategory(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private AdvertiserCategory category = AdvertiserCategory.NotSet;

	/**
	 * 广告主余额信息
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private AdvertiserBalance balance;



	/**
	 * 广告主余额报警阀值
	 */
	@Column(precision = 19, scale = 5, nullable = true)
	private BigDecimal alarmingBalance;
	
	/**
	 * 广告主余额报警时间
	 */	
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = true)
	private Timestamp alarmingTime;
	
	 /**
   * 广告主余额报警次数
   */ 
  @Size(max = 20)
  @Column(length = 20)
  private String alarmingCount;
	
	// /**
	// * 联系人QQ号码
	// */
	// @Column(length = 32)
	// private String qq;

	/**
	 * 新的广告主所属行业ID
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "vertical_tag_id")
	private AdvertiserVerticalTag verticalTag;
	@Column(insertable = false, updatable = false, nullable = true)
	@NotNull(groups = SaveChecksFOnly.class)
	private Long verticalTagId;

	/**
	 * 曝光转化、点击转化回溯天数
	 */
	private Short recallDays = 15;

	/**
	 * 组织机构代码证号
	 * */
	@Size(max = 128)
	@Column(length = 128)
	private String orgCodeNo;
	
	@Transient
	private Advertiser orig;

	/**
	 * 广告主平台审核结果(不持久化,用于前台展示)
	 */
	@Transient
	private AdvPlatformAuditVo advPlatformAuditVo;

	@Transient
	public String getDisplayName() {
		if (null == getName()) {
			return I18nResourceUtil.getResource("optimus.console.ad.entity.Advertiser.1013")
					+ (getCreator() == null ? "" : getCreator().getAccount())
					+ "]";
		} else {
			return getName();
		}
	}

	public static interface SaveChecksF {
	}

	public static interface SaveChecksFOnly {
	}
	public static interface SaveChecksAgencyProtectOnly {
	}
	
	
	/**
	 * 保护的池
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "protect_pool_id", nullable = false)
	private Pool protectPool;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long protectPoolId;
	
	@NotNull(groups=SaveChecksAgencyProtectOnly.class)
	private String province;

	private String city;
	
	private Timestamp protectStart;

	private Timestamp protectEnd;

	private int protectTimes = 0;
	
	/**
	 * 标示创建类型，
	 * protect为true，则为保护创建的广告主
	 * protect为false，则是代理商创建的广告主
	 */
	private boolean protect = false;
	
	@Column(length = 45)
	@NotNull(groups=SaveChecksAgencyProtectOnly.class)
	@Size(min = 2, max = 45,groups=SaveChecksAgencyProtectOnly.class)
	private String protectContactName;
	
	@Column(length = 32)
	@NotNull(groups=SaveChecksAgencyProtectOnly.class)
	@Size(min = 7, max = 32, groups = SaveChecksAgencyProtectOnly.class)
	private String protectCellphone;
	
	@NotNull(groups=SaveChecksAgencyProtectOnly.class)
	@Size(min = 10, max = 128, groups = SaveChecksAgencyProtectOnly.class)
	@Pattern(regexp = Regex.URL, groups = SaveChecksAgencyProtectOnly.class)
	@Column(length = 128)
	private String protectUrl;
	
	/**
	 * 内部添加的脚本代码信息, 长度改为longtext
	 */
	@Column(length = 16777215)
	private String scriptCode;
	
	
	@OneToMany(mappedBy="advertiser", fetch = FetchType.LAZY)
	private Set<AgencyProtectHistory> agencyProtectHistory = new HashSet<AgencyProtectHistory>();
	
	private static final long MILLI_SECOND_PER_DAY = 1000 * 60 * 60 * 24;
	private static final long MILLI_SECOND_PER_HOUR = 60 * 60 * 1000;
	private static final long HOUR_PER_DAY = 24;

	public long getProtectedDays() {
		if (protectStart == null) {
			return 0;
		}
		long l = System.currentTimeMillis() - protectStart.getTime();
		long day = l / MILLI_SECOND_PER_DAY;
		return day;
	}

	public long getProtectedDayHours() {
		if (protectStart == null) {
			return 0;
		}
		long l = System.currentTimeMillis() - protectStart.getTime();
		long day = l / MILLI_SECOND_PER_DAY;
		long hour = (l / MILLI_SECOND_PER_HOUR - day * HOUR_PER_DAY);
		return hour;
	}

	public long getProtectRemainDays() {
		if (protectEnd == null) {
			return 0;
		}
		long l = protectEnd.getTime() - System.currentTimeMillis();
		long day = l / MILLI_SECOND_PER_DAY;
		return day;
	}

	public long getProtectRemainDayHours() {
		if (protectEnd == null) {
			return 0;
		}
		long l = protectEnd.getTime() - System.currentTimeMillis();
		long day = l / MILLI_SECOND_PER_DAY;
		long hour = (l / MILLI_SECOND_PER_HOUR - day * HOUR_PER_DAY);
		return hour;
	}
	
	@Transient
	private ProtectStatus protectStatus = ProtectStatus.Na;
	
	public static enum ProtectStatus {
		Na("Na"), Protect("optimus.console.ad.entity.Advertiser.1015"/*已保护*/), NotProtect("optimus.console.ad.entity.Advertiser.1016"/*未保护*/),Coorp("optimus.console.ad.entity.Advertiser.1017"/*已合作*/);

		private String text;

		private ProtectStatus(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}

	}
	
	@Enumerated(EnumType.STRING)
	private ConsumerPattern consumerPattern;
	
	public static enum ConsumerPattern {
		SelfHelp("optimus.console.ad.entity.Advertiser.1018"/*自助炒股型*/),CarteBlanche("optimus.console.ad.entity.Advertiser.1019"/*银行存款型*/),Negotitation("optimus.console.ad.entity.Advertiser.1020"/*基金购买型*/);
		
		private String text;
		
		private ConsumerPattern(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
}
