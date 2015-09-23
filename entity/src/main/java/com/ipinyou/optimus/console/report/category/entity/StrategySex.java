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
@Table(name = "rpt_category_strategy_sex")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StrategySex extends BaseCategoryStrategy {
	private static final long serialVersionUID = 299183344306005594L;
}
