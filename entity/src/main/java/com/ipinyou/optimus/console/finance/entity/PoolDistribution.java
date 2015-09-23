/**
 * 
 */
package com.ipinyou.optimus.console.finance.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.user.entity.Pool;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PoolDistribution extends TimedEntity implements
		Auditable<PoolDistribution> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4054311309335101339L;

	/**
	 * 池
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "pool_id", nullable = false)
	private Pool pool;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long poolId;

	/**
	 * 用户交易金额
	 */
	@NotNull
	private BigDecimal tradeAmount;

	/**
	 * 操作者
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "operator_id", nullable = false)
	private User operator;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long operatorId;
	
	/**
	 * 分配目标广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "dist_advertiser_id", nullable = false)
	private Advertiser distAdvertiser;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long distAdvertiserId;
	
	/**
	 * 分配目标代理商
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "dist_pool_id", nullable = false)
	private Pool distPool;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long distPoolId;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 消耗信用额度
	 */
	private BigDecimal costQuota;

	/**
	 * 账户余额
	 */
	private BigDecimal balance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Auditable#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return "池账单信息";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Auditable#getName()
	 */
	@Override
	public String getName() {
		return pool.getName();
	}

	@Transient
	private PoolDistribution orig;

}
