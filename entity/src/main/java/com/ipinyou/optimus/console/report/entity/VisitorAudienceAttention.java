package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.springframework.format.annotation.DateTimeFormat;

import com.ipinyou.base.constant.DateFormat;

@Entity
@Table(name = "rpt_visitor_audience_attention")
@Data
@ToString(callSuper = true)
public class VisitorAudienceAttention{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3507465889700805077L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * 广告主公司ID
	 */
	@NotNull
	@Index(name="companyId")
	private long companyId;

	
	/**
	 * 日期
	 */
	@NotNull
	@DateTimeFormat(pattern = DateFormat.DATE)
	@Index(name="logDate")
	private Date logDate;
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
