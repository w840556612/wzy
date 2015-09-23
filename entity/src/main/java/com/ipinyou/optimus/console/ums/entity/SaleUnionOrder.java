package com.ipinyou.optimus.console.ums.entity;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.ad.entity.Strategy;
import com.ipinyou.optimus.console.ad.entity.Strategy.StrategyAreaList;
import com.ipinyou.optimus.console.user.entity.User;

@Entity
@Table(name = "ums_order")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = { "saleUnionPrice"})
public class SaleUnionOrder extends TimedEntity implements Auditable<SaleUnionOrder> ,java.io.Serializable{
	
    private static final long serialVersionUID = -4089054389272469207L;

    @Override
    public String getEntityName() {
	    return "营销联盟订单";
    }
    
	@Transient
	private SaleUnionOrder orig;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	
	@OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "strategy_id", nullable = false)
	private Strategy strategy;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long strategyId;
	
	@OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "price_id", nullable = false)
	private SaleUnionPrice saleUnionPrice;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long priceId;
	
	/**
	 * 订单名称
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	private String name;
	
	/**
	 * 订单编码
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	private String code;
	
	
	/**
	 * 银联用户ID
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	private String thirdUserId;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long userId;
	
	/**
	 * 应投放天数
	 */
	private BigDecimal price;
	
	/**
	 * 日曝光
	 */
	private long impl;
	
	/**
	 * 应投放天数
	 */
	private int day;
	
	/**
	 * 总花费
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal totalCost;
	
	
	/**
	 * 余额，app只能看到最新订单，用户订单余额放到最新订单，以后app要看到所有订单，余额放到用户上
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal balance;
	/**
	 * 总曝光
	 */
	@Column(precision = 19, scale = 2)
	private long totalImpl;
	
	/**
	 * 实际支付
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal payMoney;
	
	public enum ScopeType {
		CITY("城市"),LBS("LBS定向");
		private String text;
		
		private ScopeType(String text){
			this.text = text;
		}
		public String getText(){
			return text;
		}
	}
	
	/**
	 * 投放范围
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length=20)
	private ScopeType scopeType;
	
	public enum PayStatus {
		NOPAY("等待支付"),PAID("已支付"),ARRIVED("已到帐");
		private String text;
		
		private PayStatus(String text){
			this.text = text;
		}
		public String getText(){
			return text;
		}
	}
	
	/**
	 * 支付状态
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length=20)
	private PayStatus payStatus;
	
	/**
	 * 已投放天数
	 */
	private int execDay;
	
	
	/**
	 * 店铺地址
	 */
	@Column(length = 2000)
	private String shopAddrs;
	
	/**
	 * LBS选中的点
	 */
	@Transient
	@Column(length = 2000)
	private String lbsPoints;
	
	/**
	 * 地域定向属性
	 */
	@Transient
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "areas", length = 2000))
	private StrategyAreaList areas;
	
	/**
	 * 广告语
	 */
	@Column(length=512)
	private String adSentence;
	
	/**
	 * 图标类型
	 */
	@Column(length=20)
	private String iconType;
	
	/**
	 * 开启／关闭
	 */
	@Index(name = "active")
	private boolean active;
	
	
	/**
	 * 已终止
	 */
	@Index(name = "terminate")
	private boolean terminate = false;
	
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

}
