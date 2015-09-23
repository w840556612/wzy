package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "rpt_visitor_audience_trend")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VisitorAudienceTrend extends BaseVisitor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1772647032801799624L;

	/**
	 * ID
	 */
	@NotNull
	@Index(name = "audienceCategoryId")
	private short audienceCategoryId;

	/**
	 * 访问次数
	 */
	@NotNull
	private long pv;

	/**
	 * 访问人数
	 */
	@NotNull
	private long uv;
	
	/**
	 * 访问次数
	 */
	@NotNull
	private long allPv;

	/**
	 * 访问人数
	 */
	@NotNull
	private long allUv;
	
}
