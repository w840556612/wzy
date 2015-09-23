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
@Table(name = "diagnose_advertiser_cost")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, of = { "advertiserId", "day" })
@Data
public class DiagnoseAdvertiserCost extends TimedEntity {

	public DiagnoseAdvertiserCost() {

	}

	public DiagnoseAdvertiserCost(DiagnoseCampaignCost campaignCost) {
		advertiserId = campaignCost.getAdvertiserId();
		day = campaignCost.getDay();
		actualDailyBudget = campaignCost.getCampaignDailyBudget();
		if (campaignCost.getStrategyDailyBudget() != null
				&& (actualDailyBudget == null || actualDailyBudget.compareTo(campaignCost.getStrategyDailyBudget()) > 0)) {
			actualDailyBudget = campaignCost.getStrategyDailyBudget();
		}
		advCost = campaignCost.getAdvCost();
	}

	private static final long serialVersionUID = -5212987269046960319L;

	@Column(updatable = false, nullable = false)
	private Long advertiserId;

	private Date day;

	private boolean dasuanpan;

	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 64)
	private String advertiserName;

	private BigDecimal actualDailyBudget;

	private BigDecimal advCost;

}
