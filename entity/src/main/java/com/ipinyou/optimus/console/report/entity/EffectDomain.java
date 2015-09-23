/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.optimus.console.model.AdLocation;

/**
 * @author lijt
 * 
 */
@Entity
@Table(name = "rpt_effect_domain", 
		uniqueConstraints={@UniqueConstraint(columnNames={"strategy_id", "creative_id", 
				"creative_unit_id", "platform", "platform_mapping", "ad_unit_id", "day", 
				"conversion_target_id", "removed", "hidden_srv_cost", "domain", "domain_category", "location"})})
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EffectDomain extends BaseEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2065007154946007575L;

	/**
	 * 域名
	 */
	@Index(name="domain")
	private String domain;

	/**
	 * 域名分类
	 */
	@Index(name="domainCategory")
	private short domainCategory;


	/**
	 * 广告位置
	 */
	@Index(name="location")
	@Column(length=50)
	@Enumerated(EnumType.STRING)
	private AdLocation location;
}
