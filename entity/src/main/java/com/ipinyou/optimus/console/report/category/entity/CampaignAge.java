package com.ipinyou.optimus.console.report.category.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author zhyhang
 *
 */
@Entity
@Table(name = "rpt_category_campaign_age")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CampaignAge extends BaseCategoryCampaign {
	private static final long serialVersionUID = -2529658104220920560L;
}
