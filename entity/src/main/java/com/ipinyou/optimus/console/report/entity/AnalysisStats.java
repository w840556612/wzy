package com.ipinyou.optimus.console.report.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.Entity;


/**
 * 日志分析状态表
 * @author ganwenlong
 * 2013-10-31
 */
@Data
@javax.persistence.Entity
@Table(name = "analysis_stats")
public class AnalysisStats implements Entity {

	private static final long serialVersionUID = -7699980480913534710L;

	@Id
	private String analysisName;
	
	/**
	 * 最后分析时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone = "GMT+8")
	@Column(updatable = true, nullable = false)
	protected Timestamp lastAnalyzed = new Timestamp(System.currentTimeMillis());
	
	/**
	 * 最后修改时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone = "GMT+8")
	@Column(updatable = true, nullable = false)
	protected Timestamp lastModified = new Timestamp(System.currentTimeMillis());

}
