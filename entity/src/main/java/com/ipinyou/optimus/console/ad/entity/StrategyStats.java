package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper = true, exclude = { "strategy" })
@EqualsAndHashCode(callSuper = true, exclude = { "strategy" })
public class StrategyStats extends BaseStats {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3122962741822877408L;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Strategy strategy;

	@Transient
	public BigDecimal getBalance() {
		if (strategy.getLimit()==null || strategy.getLimit().getTotalBudget() == null) {
			return null;
		}
		return strategy.getLimit().getTotalBudget().subtract(getAdvCost());
	}
	//无意义的方法
	@Transient
	public void setBalance(BigDecimal balance){}

}
