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
@Table(name = "rpt_effect_geo", 
		uniqueConstraints={@UniqueConstraint(columnNames={"strategy_id", "creative_id", 
				"creative_unit_id", "platform", "platform_mapping", "ad_unit_id", "day", 
				"conversion_target_id", "removed", "hidden_srv_cost", "city_id", "province_id"})})
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EffectGeo extends BaseEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8161506211878033507L;

	/**
	 * 地级市id
	 */
	@Index(name="cityId")
	private short cityId;
	
	/**
	 * 省id
	 */
	@Index(name="provinceId")
	private short provinceId;
	
}
