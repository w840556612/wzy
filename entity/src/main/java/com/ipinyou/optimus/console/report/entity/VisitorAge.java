package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "rpt_visitor_age")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VisitorAge extends BaseVisitor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4493144376633228067L;

	/**
	 * 年龄ID
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
}
