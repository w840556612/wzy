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
@Table(name = "rpt_category_campaign_attention")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CampaignAttention extends BaseCategoryCampaign {
	private static final long serialVersionUID = -6433170348082090726L;
}
