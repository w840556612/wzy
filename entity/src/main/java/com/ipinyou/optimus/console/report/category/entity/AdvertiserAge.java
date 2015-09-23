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
@Table(name = "rpt_category_advertiser_age")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserAge extends BaseCategory {
	private static final long serialVersionUID = -3113526748189175275L;
}
