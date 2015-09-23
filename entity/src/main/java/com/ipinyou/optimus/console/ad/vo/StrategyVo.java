package com.ipinyou.optimus.console.ad.vo;

import java.sql.Date;

import com.ipinyou.optimus.console.ad.entity.Strategy.AdvertisingMode;

import lombok.Data;

/**
 * 策略查询结果封装对象
 */
@Data
public class StrategyVo {
	
	private String orderName;
	private String campaignName;
	private Long strategyId;
	private String strategyName;
	private boolean strategyActive;
	private AdvertisingMode strategyMode;
	private Date beginDate;
	private Date endDate;
	
	public StrategyVo(){
		
	}
	
	public StrategyVo(String orderName, String campaignName, Long strategyId,
			String strategyName, boolean strategyActive,
			AdvertisingMode strategyMode, Object beginDate, Object endDate) {
		this.orderName = orderName;
		this.campaignName = campaignName;
		this.strategyId = strategyId;
		this.strategyName = strategyName;
		this.strategyActive = strategyActive;
		this.strategyMode = strategyMode;
		this.beginDate = (Date) beginDate;
		this.endDate = (Date) endDate;
	}
}
