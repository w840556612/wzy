package com.ipinyou.optimus.console.ad.vo;

import java.util.List;

import lombok.Data;

import org.springframework.data.domain.Pageable;

import com.ipinyou.optimus.console.ad.entity.Strategy.AdvertisingMode;
import com.ipinyou.optimus.console.ad.entity.Strategy.StrategyType;

/**
 * 策略查询对象
 */
@Data
public class StrategyQvo {
	
	private Long advertiserId;
	private Long orderId;
	private Long campaignId;
	private Long strategyId;
	private String strategyName;
	private Boolean strategyActive;
	private List<AdvertisingMode> strategyModes;
	
	private String name;
	private List<StrategyType> types;
	private boolean removed;
	private Pageable pageable;
	
}
