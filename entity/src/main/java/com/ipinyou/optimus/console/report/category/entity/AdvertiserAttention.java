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
@Table(name = "rpt_category_advertiser_attention")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserAttention extends BaseCategory {
	private static final long serialVersionUID = 8718235720366453229L;
}
