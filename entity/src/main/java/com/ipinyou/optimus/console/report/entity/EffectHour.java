/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lijt
 *
 */
@Entity
@Table(name = "rpt_effect_hour", 
		uniqueConstraints={@UniqueConstraint(columnNames={"strategy_id", "creative_id", 
				"creative_unit_id", "platform", "platform_mapping", "ad_unit_id", "day", 
				"conversion_target_id", "removed", "hidden_srv_cost", "hour"})})
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EffectHour extends BaseEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4189300270000748893L;
	/**
	 * 小时，0-23
	 */
	@Index(name="hour")
	private int hour;
}
