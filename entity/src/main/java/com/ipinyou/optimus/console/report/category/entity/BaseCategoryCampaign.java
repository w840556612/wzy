package com.ipinyou.optimus.console.report.category.entity;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class BaseCategoryCampaign extends BaseCategoryOrder {
	
	private static final long serialVersionUID = 3851504885035881632L;
	
	/**
	 * 计划id
	 */
	@Index(name="campaignId")
	protected long campaignId;
	
}
