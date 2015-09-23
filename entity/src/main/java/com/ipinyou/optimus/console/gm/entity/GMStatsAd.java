package com.ipinyou.optimus.console.gm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * GM秒针监测广告表
 */
@Entity
@Table(name = "gm_stats_ad")
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "version", "creation", "lastModified", "orig" })
public class GMStatsAd extends TimedEntity implements
		Auditable<GMStatsAd> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4546687525219773551L;

	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	/**
	 * 品友订单ID
	 */
	@NotNull
	@Index(name="orderId")
	private Long orderId;

	/**
	 * 品友订单名称
	 */
	@NotNull
	private String otherName;

	/**
	 * 秒针广告ID
	 */
	private String otherId;

	@Override
	public String getEntityName() {
		return "第三方监测广告";
	}

	@Override
	public String getName() {
		return otherName;
	}

	@Transient
	private GMStatsAd orig;

}
