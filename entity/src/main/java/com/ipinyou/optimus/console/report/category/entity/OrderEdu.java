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
@Table(name = "rpt_category_order_edu")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrderEdu extends BaseCategoryOrder {
	private static final long serialVersionUID = 8336979169854141245L;
}
