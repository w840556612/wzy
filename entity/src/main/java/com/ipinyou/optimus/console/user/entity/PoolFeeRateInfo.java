package com.ipinyou.optimus.console.user.entity;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineList;
import com.ipinyou.optimus.console.ad.entity.Strategy;
import com.ipinyou.optimus.console.ad.entity.Advertiser.ConsumerPattern;

@Entity
@Table(name="usr_pool_fee_rate")
@Data
@ToString(callSuper = true, exclude = { "orig"})
@EqualsAndHashCode(callSuper = true, exclude = { "orig","poolTradingMode"})
@JsonIgnoreProperties(value = { "orig" }, ignoreUnknown = true)
public class PoolFeeRateInfo extends TimedEntity implements Auditable<PoolFeeRateInfo>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4504797882565029623L;
	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return  "合作费率信息";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PoolFeeRateInfo getOrig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrig(PoolFeeRateInfo t) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * 模式名称
	 */
	@Column(length = 64)
	private String name;
	
	/*
	 * 合作模式
	 */
	@Enumerated(EnumType.STRING)
	private ConsumerPattern consumerPattern;
	
	/*
	 * 合作费率
	 */
	@DecimalMin("0")
	@Column(nullable = false)
	@NumberFormat(pattern = "##.####%")
	private BigDecimal feeRate = new BigDecimal(0);
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "trading_mode_id", nullable = false)
	private PoolTradingMode poolTradingMode;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long tradingModeId;
	
	/*
	 * 逻辑删除标记
	 */
	private boolean removed=false;
	
}
