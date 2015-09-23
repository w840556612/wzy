package com.ipinyou.optimus.console.gm.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.media.entity.AdUnit;

/**
 * GM排期表
 */
@Entity
@Table(name = "ad_unit_schedule")
@Data
@ToString(callSuper = true, exclude = { "orig","adUnit" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig","adUnit" })
@JsonIgnoreProperties(value = { "version", "creation", "lastModified", "orig", "adUnit"})
public class AdUnitSchedule extends TimedEntity implements
		Auditable<AdUnitSchedule> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3002995609183818162L;

	/**
	 * 是否已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	/**
	 * 广告位ID
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long adUnitId;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ad_unit_id", nullable = false)
	private AdUnit adUnit;
	
	/**
	 * 广告位相关信息
	 */
	private Long adPositionId;
	
	/**
	 * 排期
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = false)
	private Date scheduleDate;
	
	@Transient
	private String scheduleDateStr;
	
	/**
	 * 几轮播
	 */
	@NotNull
	private int multiple;
	
	/**
	 * 是否为补量 
	 */
	@NotNull
	@Column(nullable = false)
	private boolean extra = false;

	@Transient
	private String extraStr="";
	
	/**
	 * 补量订单ID，多个ID之间以英文逗号分隔。只有extra=1时，extra_order_ids的值才生效
	 */
	private String extraOrderIds;
	
	@Transient
	private String orderName;
	
	@Override
	public String getEntityName() {
		return "广告位排期表";
	}

	@Override
	public String getName() {
		return null;
	}

	@Transient
	private AdUnitSchedule orig;

	 

}
