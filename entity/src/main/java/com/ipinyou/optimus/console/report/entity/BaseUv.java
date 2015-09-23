/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.annotations.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.Entity;

/**
 * @author lijt
 * 
 */
@Data
@MappedSuperclass
public class BaseUv implements Entity {

	private static final long serialVersionUID = -5973532631230001106L;

	@Transient
	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * 池id
	 */
	@Index(name = "poolId")
	protected long poolId;

	/**
	 * 广告主id
	 */
	@Index(name = "advertiserId")
	protected long advertiserId;

	/**
	 * 日期，年-月-日
	 */
	@Index(name = "day")
	@NotNull
	protected Date day;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone = "GMT+8")
	@Column(updatable = false, nullable = false)
	protected Timestamp creation = new Timestamp(System.currentTimeMillis());
	
	/**
	 * 竞价pv
	 */
	protected long bidPv;
	
	/**
	 * 竞价uv
	 */
	protected long bidUv;
	
	/**
	 * 曝光pv
	 */
	protected long impPv;

	/**
	 * 曝光uv
	 */
	protected long impUv;

	/**
	 * 点击pv
	 */
	protected long clickPv;

	/**
	 * 点击uv
	 */
	protected long clickUv;

	/**
	 * 到达pv，按品友统计统计标准（同时也是一跳）
	 */
	protected long reachPv;

	/**
	 * 到达uv，按品友统计统计标准
	 */
	protected long reachUv;

	/**
	 * 二跳pv
	 */
	protected long twoJumpPv;
	
	/**
	 * 二跳uv
	 */
	protected long twoJumpUv;

	/**
	 * 三跳pv
	 */
	protected long threeJumpPv;

	/**
	 * 三跳uv
	 */
	protected long threeJumpUv;

	/**
	 * 四跳pv
	 */
	protected long fourJumpPv;
	
	/**
	 * 四跳uv
	 */
	protected long fourJumpUv;

	/**
	 * 五跳pv
	 */
	protected long fiveJumpPv;
	
	/**
	 * 五跳uv
	 */
	protected long fiveJumpUv;
	
	/**
	 * 曝光转化pv
	 */
	protected long impConversionPv;

	/**
	 * 曝光转化uv
	 */
	protected long impConversionUv;

	/**
	 * 点击转化pv
	 */
	protected long clickConversionPv;

	/**
	 * 点击转化uv
	 */
	protected long clickConversionUv;

}
