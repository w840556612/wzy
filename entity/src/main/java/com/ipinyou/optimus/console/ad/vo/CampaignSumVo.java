/**
 * 
 */
package com.ipinyou.optimus.console.ad.vo;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;

/**
 * @author lijt
 * 
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CampaignSumVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2506283836564319028L;

	private BigDecimal totalBudget;

	private BigDecimal advCost;

	private BigDecimal balance;

	private long impSum;

	private long clickSum;

	private double clickRate;
	
	public CampaignSumVo(BigDecimal totalBudget, BigDecimal cost, Long impSum,
			Long clickSum) {
		super();
		if (cost == null) {
			empty = true;
			return;
		}
		this.totalBudget = totalBudget;
		if(cost!=null){
			this.advCost = cost;
		}
		if(totalBudget!=null){
			this.balance = totalBudget.subtract(advCost);
		}
		this.impSum = impSum;
		this.clickSum = clickSum;
		if (impSum != 0) {
			this.clickRate = clickSum / impSum;
		}
	}

	public CampaignSumVo() {
		super();
	}

}
