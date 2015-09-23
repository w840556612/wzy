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
@Table(name = "rpt_category_strategy_income")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StrategyIncome extends BaseCategoryStrategy {
	private static final long serialVersionUID = -1437526201494855812L;
}
