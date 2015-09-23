package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 我的访客-访客人数分析
 * 
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_visitor_count")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VisitorCount extends BaseVisitor {

	private static final long serialVersionUID = 8229289441908767762L;

	/**
	 * logDate当天新增访问次数
	 */
	@NotNull
	private long additionPv;

	/**
	 * logDate当天新增访问人数
	 */
	@NotNull
	private long additionUv;

	/**
	 * 截止logDate，累计访问次数
	 */
	@NotNull
	private long sumPv;

	/**
	 * 截止logDate，累计访问人数
	 */
	@NotNull
	private long sumUv;
}
