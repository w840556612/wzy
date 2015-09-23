package com.ipinyou.optimus.console.diagnose;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.ipinyou.base.entity.TimedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 */
@Entity
@Table(name = "diagnose_campaign_cost")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, of = { "campaignId", "day" })
@Data
public class DiagnoseCampaignCost extends TimedEntity {

	public DiagnoseCampaignCost() {
	}

	public DiagnoseCampaignCost(DiagnoseStrategyCost strategyCost) {
		campaignId = strategyCost.getCampaignId();
		day = strategyCost.getDay();
		advCost = strategyCost.getAdvCost();
		strategyDailyBudget = strategyCost.getStrategyDailyBudget();
		campaignDailyBudget = strategyCost.getCampaignDailyBudget();
	}

	private static final long serialVersionUID = -5212987269046960319L;

	@Column(updatable = false, nullable = false)
	private Long campaignId;

	@Column(updatable = false, nullable = false)
	private Long advertiserId;

	private boolean dasuanpan;
	
	private Date day;

	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 64)
	private String campaignName;

	private BigDecimal strategyDailyBudget;

	private BigDecimal campaignDailyBudget;

	private BigDecimal advCost;

}
