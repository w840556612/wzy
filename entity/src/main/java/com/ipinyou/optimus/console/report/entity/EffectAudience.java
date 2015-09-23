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
@Table(name = "rpt_effect_audience", 
		uniqueConstraints={@UniqueConstraint(columnNames={"strategy_id", "creative_id", 
				"creative_unit_id", "platform", "platform_mapping", "ad_unit_id", "day", 
				"conversion_target_id", "removed", "hidden_srv_cost", "category_id", "family_id"})})
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EffectAudience extends BaseEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = -713008948396650889L;
	
	/**
	 * 人群属性id
	 */
	@Index(name="categoryId")
	private int categoryId;
	
	/**
	 * 族群id
	 */
	@Index(name="familyId")
	private long familyId;
}
