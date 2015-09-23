package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;

/**
 * @author guodong.zhang
 * 
 */
@Entity
@Table(name = "rpt_effect_simple_domain")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EffectSimpleDomain extends BaseEffectMeasure {
	
	private static final long serialVersionUID = -3303156425767918807L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * 池id
	 */
//	@Index(name = "poolId")
//	protected long poolId;

	/**
	 * 广告主id
	 */
	protected long advertiserId;

	/**
	 * 日期，年-月-日
	 */
	@Column(nullable = false)
	protected Date day;

	/**
	 * 域名
	 */
	private String domain;

	/**
	 * 域名分类
	 */
	private short domainCategory;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone = "GMT+8")
	@Column(updatable = false, nullable = false)
	protected Timestamp creation = new Timestamp(System.currentTimeMillis());

	/**
	 * 已删除
	 */
//	private boolean removed = false;
	
}
