/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Campaign.CampaignType;
import com.ipinyou.optimus.console.ad.model.KPIAttribute;

/**
 * 订单
 * @author lijt
 * 
 */
@Entity
@Table(name="ad_order")
@Data
@ToString(callSuper = true, exclude = { "orig", "advertiser" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "advertiser" })
public class Order extends TimedEntity implements Auditable<Order> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5318396922544034660L;
	

	public final static String DEFAULT_NAME = com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Order.1001")/*默认订单*/;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("ftl.common.order")/*订单*/;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	
	

	/**
	 * 统计信息
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrderStats stats;
	
	
	@NotNull
	@Size(min = 2, max = 30)
	@Column(nullable = false, length = 64)
	@Index(name="name")
	private String name;
	
	@NotNull
	@Column(nullable = false)
	private int adZone=AdZone.China.getCode();
	

	public static enum AdZone{
		China("optimus.console.ad.entity.Order.1002"/*中国*/,0),NorthAmerica("optimus.console.ad.entity.Order.1003"/*北美*/,1),Europe("optimus.console.ad.entity.Order.1004"/*欧洲*/,2);
		
		private String text;
		
		private int code;
		
		private AdZone(String text, int code){
			this.text=text;
			this.code = code;
		}
		
		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
		
		public int getCode() {
			return code;
		}
		
	}
//	/**
//	 * 开启／关闭
//	 */
//	@NotNull
//	@Column(nullable = false)
//	private boolean active = true;
	
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	 /**
	 * 总预算（ 订单消耗预算） E版无此字段，F版必填
	 */
	@DecimalMin(value="0",groups=SaveChecksF.class) 
	@Max(value=1000000000,message="超过系统允许的最大数值{value}")
	@NotNull(groups=SaveChecksF.class)
	 private BigDecimal totalBudget;
//	
//	 /**
//	 * 订单每日消耗预算
//	 */
//	 private BigDecimal dailyBudget;	

	 /**
	 * 合同号
	 */
	@Size(max=30)
	@Column(length = 64)
	 private String contractNo;
	/**
	 * DSP放量，0-1
	 */
	private double qpsRate = 1.0;
	
	
	/**
	 * 转化点定义
	 */
	@Size(max=64)
	@Column(length=64)
	private String convertPoint;
	
	/**
	 * KPI目标属性
	 */
	@Embedded
	private KPIAttribute kpiAttr;
	

	/**
	 * 每日预算，单位：元，GM专用
	 */
	@DecimalMin(value="0")
	private BigDecimal dailyBudget;
	

	/**
	 * 每周预算，单位：元，GM专用
	 */
	@DecimalMin(value="0")
	private BigDecimal weeklyBudget;

	@Transient
	private Order orig;
	
	/**
	 * 订单类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private OrderType type = OrderType.Normal;

	public static enum OrderType {
		Normal, // 普通
		ViewAd // 查看广告
	}
	
	/**
	 * F版保存检查检验标识接口
	 * @author zhyhang
	 *
	 */
	public static interface SaveChecksF{}

}
