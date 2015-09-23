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
@Table(name = "rpt_uv_strategy")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UvStrategy extends BaseUv {

	private static final long serialVersionUID = -9116967082318526423L;
	
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
