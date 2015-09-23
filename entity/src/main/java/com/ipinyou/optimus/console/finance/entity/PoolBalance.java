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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.Pool;

/**
 * 池余额，和池使用外键一对一关联（为了保持模块之前的单向依赖性）
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PoolBalance extends TimedEntity implements Auditable<PoolBalance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5984076016158116373L;

	/**
	 * 池
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pool_id", nullable = false)
	private Pool pool;

	/**
	 * 用户充值总额
	 */
	private BigDecimal rechargeTotal = new BigDecimal("0.00");

	/**
	 * 分配总额
	 */
	private BigDecimal distributeTotal = new BigDecimal("0.00");

	
	/**
	 * 单功能消耗总额
	 */
	@Column(precision = 19, scale = 5, nullable = false)
	private BigDecimal chargeFuncCostTotal = new BigDecimal("0.00");
	
	/**
	 * 单功能退回总额
	 */
	@Column(precision = 19, scale = 5, nullable = false)
	private BigDecimal chargeFuncRefundTotal = new BigDecimal("0.00");

	/**
	 * 用户账户余额
	 */
	private BigDecimal balance = new BigDecimal("0.00");

	@Transient
	private PoolBalance orig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Auditable#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return "池余额";
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
}
