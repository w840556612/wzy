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
@Table(name = "rpt_category_campaign_income")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CampaignIncome extends BaseCategoryCampaign {
	private static final long serialVersionUID = 6538791704414580641L;
}
