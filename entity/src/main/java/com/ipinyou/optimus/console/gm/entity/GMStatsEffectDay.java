package com.ipinyou.optimus.console.gm.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.TimedEntity;

/**
 * GM秒针监测广告报表
 */
@Entity
@Table(name = "gm_stats_effect_day")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = { "version", "creation", "lastModified" })
public class GMStatsEffectDay extends TimedEntity {

	private static final long serialVersionUID = 3161795833527614009L;

	/**
	 * 日期，年-月-日
	 */
	@Column(nullable = false)
	private Date day;

	/**
	 * 品友广告ID，在GM秒针中对应订单ID
	 */
	@Column(nullable = false)
	private Long orderId;

	/**
	 * 品友广告位标识
	 */
	@Column(nullable = false)
	private String adUnitCode;

	@Column(nullable = false)
	private boolean removed = false;

	@Column(nullable = false)
	private long imp;

	@Column(nullable = false)
	private long click;

	@Column(nullable = true)
	private long impUv;

	@Column(nullable = true)
	private long clickUv;

}
