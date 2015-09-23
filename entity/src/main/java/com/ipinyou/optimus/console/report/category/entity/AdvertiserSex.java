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
@Table(name = "rpt_category_advertiser_sex")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserSex extends BaseCategory {
	private static final long serialVersionUID = 541155750906647294L;
}
