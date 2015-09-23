package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

/**
 * 我的访客-个人月收入分析
 * 
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_visitor_income")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VisitorIncome extends BaseVisitor {

	private static final long serialVersionUID = 1106318957593331242L;

	/**
	 * 月收入ID
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
