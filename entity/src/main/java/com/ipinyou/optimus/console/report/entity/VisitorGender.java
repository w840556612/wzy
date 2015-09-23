package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

/**
 * 我的访客-性别分析
 * 
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_visitor_gender")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VisitorGender extends BaseVisitor {

	private static final long serialVersionUID = 1474380712121101105L;

	/**
	 * 性别ID
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
