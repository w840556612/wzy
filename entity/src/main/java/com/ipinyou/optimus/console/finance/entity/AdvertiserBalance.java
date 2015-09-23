/**
 * 
 */
package com.ipinyou.optimus.console.finance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.NoIdTimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;

/**
 * 广告主余额 和广告主模型使用主键一对一关联
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = { "id", "version", "creation", "lastModified",
		"advertiser", "orig" })
public class AdvertiserBalance extends NoIdTimedEntity implements
		Auditable<AdvertiserBalance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912801217755325541L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return "广告主余额";
	}

	// @OneToOne(optional=false, fetch=FetchType.LAZY)
	// @JoinColumn(name="user_id", nullable = false, unique = true)
	// private User user;
	// @Column(insertable = false, updatable = false, nullable = false)
	// private Long userId;
	@Id
	protected Long id;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Advertiser advertiser;

	/**
	 * 用户充值总额
	 */
	@Column(precision = 19, scale = 2, nullable = false)
	private BigDecimal rechargeTotal = new BigDecimal("0.00");

	/**
	 * 消耗总额
	 */
	@Column(precision = 19, scale = 5, nullable = false)
	private BigDecimal costTotal = new BigDecimal("0.00");
	
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
	@Column(precision = 19, scale = 5, nullable = false)
	private BigDecimal balance = new BigDecimal("0.00");

	/**
	 * 最后充值时间
	 */
	private Timestamp lastRecharge;

	@Override
	public String getName() {
		return advertiser.getName();
	}

	@Transient
	private AdvertiserBalance orig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Auditable#getPrimaryKey()
	 */
	@Override
	public String getPrimaryKey() {
		return id == null ? null : String.valueOf(id);
	}
}
