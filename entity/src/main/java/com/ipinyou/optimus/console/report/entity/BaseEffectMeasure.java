/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipinyou.base.entity.Entity;

/**
 * 投放效果相关报表的度量列，即竞价、曝光、广告主消费等列
 */
@Data
@MappedSuperclass
public abstract class BaseEffectMeasure implements Entity {

	private static final long serialVersionUID = -5133548893164331918L;

	@Transient
	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());

	/**
	 * 竞价
	 */
	protected long bid;

	/**
	 * 曝光
	 */
	protected long imp;

	/**
	 * 点击
	 */
	protected long click;

	/**
	 * 到达，按品友统计统计标准（同时也是一跳）
	 */
	protected long reach;

	/**
	 * 二跳
	 */
	protected long twoJump;

	/**
	 * 三跳
	 */
	protected long threeJump;

	/**
	 * 四跳
	 */
	protected long fourJump;

	/**
	 * 五跳
	 */
	protected long fiveJump;

	/**
	 * 曝光转化，(品友的转换，看了广告但没有点击广告产生的转化), 注意，不包括点击转化，前端显示的时候无需扣除点击转化
	 */
	protected long impConversion;

	/**
	 * 点击转化，（品友的转换）
	 */
	protected long clickConversion;

	/**
	 * 媒体采购费(内)。单位元，8位小数
	 */
	protected BigDecimal inCost;
	
	/**
	 * 品友服务费(内)，品友收取的代理商总服务费。单位元，8位小数
	 */
	protected BigDecimal inAgencySrvCost;

	/**
	 * 媒体采购费。单位元，8位小数
	 */
	protected BigDecimal cost;

	/**
	 * 品友服务费，品友收取的代理商服务费。单位元，8位小数
	 */
	protected BigDecimal agencySrvCost;

	/**
	 * 代理商消费(内)。单位元，8位小数
	 */
	protected BigDecimal inAgencyCost;
	
	/**
	 * 代理商服务费(内)，代理商收取的广告主的总服务费。单位元，8位小数
	 */
	protected BigDecimal inAdvSrvCost;
	
	/**
	 * 代理商总费用，单位元，8位小数， agencyCost=cost+agencySrvCost
	 */
	protected BigDecimal agencyCost;
	
	/**
	 * 代理商应该收取的广告主服务费。单位元，8位小数
	 */
	protected BigDecimal advSrvCost;
	
	/**
	 * 额外显示的广告主服务费，注意是服务费。单位元，8位小数。注意：这一部分仅仅在报表中显示，不算入余额控制、阈值控制、账单
	 */
	protected BigDecimal extraShowAdvCost;

	/**
	 * 广告主总费用，单位元，8位小数， advCost=agencyCost+advSrvCost, 不包括extraShowAdvCost
	 */
	protected BigDecimal advCost;
	
	/**
	 * 视频广告播放进度统计
	 */
	private Long vastPoint1;
	private Long vastPoint2;
	private Long vastPoint3;
	private Long vastPoint4;
	private Long vastPoint5;

}
