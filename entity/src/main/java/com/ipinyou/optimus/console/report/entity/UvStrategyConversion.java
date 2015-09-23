/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

/**
 * @author lijt
 *
 */
@Entity
@Table(name = "rpt_uv_strategy_conversion")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UvStrategyConversion extends BaseUvConversion {

	private static final long serialVersionUID = -3416718446907666420L;

	/**
	 * 订单id
	 */
	private long orderId;
	
	/**
	 * 计划id
	 */
	private long campaignId;
	
	/**
	 * 策略id
	 */
	@Index(name="strategyId")
	private long strategyId;
	
}
