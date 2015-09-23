package com.ipinyou.optimus.console.report.category.entity;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class BaseCategoryOrder extends BaseCategory {
	private static final long serialVersionUID = 3446952719526751373L;
	
	/**
	 * 订单id
	 */
	@Index(name="orderId")
	protected long orderId;
	
}
