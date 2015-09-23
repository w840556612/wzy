/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lijt
 * 
 */
@Entity
@Table(name = "rpt_effect_day", 
		uniqueConstraints={@UniqueConstraint(columnNames={"strategy_id", "creative_id", 
				"creative_unit_id", "platform", "platform_mapping", "ad_unit_id", "day", 
				"conversion_target_id", "removed", "hidden_srv_cost"})})
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EffectDay extends BaseEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5274450797276250450L;
	
	@Column(name = "agency_cost_bak", insertable = false, updatable = false)
	protected BigDecimal agencyCostBak;
	
	@Column(name = "adv_srv_cost_bak", insertable = false, updatable = false)
	protected BigDecimal advSrvCostBak;
	
}
