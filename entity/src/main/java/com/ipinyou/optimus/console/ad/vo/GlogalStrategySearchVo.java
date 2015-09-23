package com.ipinyou.optimus.console.ad.vo;

import com.ipinyou.optimus.console.ad.entity.StrategyStatus;
import com.ipinyou.optimus.console.ad.entity.Strategy;
import com.ipinyou.optimus.console.ad.model.MobileAttribute;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
public class GlogalStrategySearchVo {
	private String name;
	private Long advertiserId;
	private Long orderId;
	private Long campaignId;
	private Boolean active;
	private StrategyStatus.Status status;
	private MobileAttribute.MobileType mobileType;
	private Strategy.AdvertisingMode advertisingMode;
}
