/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.springframework.format.annotation.NumberFormat;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Strategy.DayPacingType;
import com.ipinyou.optimus.console.ad.model.IndividualLimitation;
import com.ipinyou.optimus.console.ad.model.KPIAttribute;
import com.ipinyou.optimus.console.ad.model.Limitation;
import com.ipinyou.optimus.console.ad.vo.CampaignAdvertisingStatusVo;

/**
 * 计划
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig", "order", "stats" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "order","stats" })
public class Campaign extends TimedEntity implements Auditable<Campaign> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3873506809913633122L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("ftl.common.campaign")/*计划*/;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long orderId;

	/**
	 * 计划统计信息
	 */
//	@OneToOne(mappedBy = "campaign", fetch = FetchType.LAZY)
	@org.hibernate.annotations.LazyToOne (org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private CampaignStats stats;

	/**
	 * 运行投放状态
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private CampaignStatus runStatus;
	
	@Index(name="name")
	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 64)
	private String name;

	/**
	 * 同一个页面是否排斥出现同一计划下的多个广告
	 */
	@Column(nullable = false)
	private boolean repulsive = false;

	/**
	 * 开启／关闭
	 */
	@Index(name="active")
	@NotNull
	@Column(nullable = false)
	private boolean active;

	/**
	 * true:表示从未开启过
	 */
	private boolean original = true;
	
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	/**
	 * 限制
	 */
	@Valid
	@Embedded
	private Limitation limit;
	
	/**
	 * 针对计划的单人阈值限制
	 */
	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="impLimit", column=@Column(name="cp_imp_limit")),
	    @AttributeOverride(name="impLimitPeriod", column=@Column(name="cp_imp_limit_period",length=50)),
	    @AttributeOverride(name="clickLimit", column=@Column(name="cp_click_limit")),
	    @AttributeOverride(name="clickLimitPeriod", column=@Column(name="cp_click_limit_period",length=50))
	})
	private IndividualLimitation campaignIndvdLimit;
	
	/**
	 * 针对该计划下创意的单人阈值限制
	 */
	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="impLimit", column=@Column(name="ct_imp_limit")),
	    @AttributeOverride(name="impLimitPeriod", column=@Column(name="ct_imp_limit_period",length=50)),
	    @AttributeOverride(name="clickLimit", column=@Column(name="ct_click_limit")),
	    @AttributeOverride(name="clickLimitPeriod", column=@Column(name="ct_click_limit_period",length=50))
	})
	private IndividualLimitation creativeIndvdLimit;

//	/**
//	 * 计划总预算
//	 */
//	@NotNull
//	private BigDecimal totalBudget;
//
//	/**
//	 * 计划每日预算
//	 */
//	private BigDecimal dailyBudget;
	

	/**
	 * 投放开始日期
	 */
	@Index(name="beginDate")
	@NotNull
	// @Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = DateFormat.DATE)
	private Date beginDate;

	/**
	 * 投放结束日期
	 */
	@Index(name="endDate")
	@NotNull
	// @Temporal(TemporalType.DATE)
	// @DateTimeFormat(pattern = DateFormat.DATE)
	private Date endDate;

	@DecimalMin("0")
	@Column(nullable=false)
	@NumberFormat(pattern = "##.####%")
	private BigDecimal extraSrvFeeRate = new BigDecimal("0");
	
	@OneToMany(mappedBy = "campaign")
	@OrderBy("lastModified")
	private Set<Strategy> strategies;
	
	/**
	 * KPI目标属性
	 */
	@Embedded
	private KPIAttribute kpiAttr;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private DayPacingType dayPacing;
	
	private Long unionLimitId;

	@Transient
	private Campaign orig;
	
	/**
	 * 计划类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private CampaignType type = CampaignType.Normal;

	public static enum CampaignType {
		Normal, // 普通
		ViewAd // 查看广告
	}
	
	/**
	 * 投放状态枚举
	 * @author zhyhang
	 *
	 */
	/*public static enum AdvertisingStatus{
		Advertising("投放中"),
		Stop("未投放"),
		Approving("广告主资质待审核"),
		ApproveDenial("广告主资质审核拒绝"),
		Close("关闭");
		
		private String text;
		
		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		private AdvertisingStatus(String text){
			this.text=text;
		}
	}*/
	
	/**
	 * 投放状态，非持久化，以vo形式存在
	 */
	@Transient
	private CampaignAdvertisingStatusVo advertisingStatus;
	
	/*
	 * 诊断状态
	 */
	@Transient
	private DiagnoseStatus diagnoseStatus= DiagnoseStatus.Na;
	
	public static enum DiagnoseStatus {
		Na, Immediate, Exist;
	}
	
	
}
