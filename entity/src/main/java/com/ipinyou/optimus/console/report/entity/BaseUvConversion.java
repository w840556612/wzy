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
 * 转化UV
 * @author guodong.zhang
 */
@Data
@MappedSuperclass
public class BaseUvConversion implements Entity {

	private static final long serialVersionUID = 3543253599349509122L;
	
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
	 * 转化目标id
	 */
	@Index(name="conversionTargetId")
	protected long conversionTargetId;
	
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
