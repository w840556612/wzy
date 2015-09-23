/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.BaseEntity;
import com.ipinyou.optimus.console.model.Platform;

/**
 * 各平台广告位尺寸
 * 
 * @author lijt
 * 
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "width", "height", "platform" }) })
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, of = { "width", "height", "platform" })
@JsonIgnoreProperties(value = { "id", "version", "active", "dailyAvgImp", "primaryKey" })
public class AdSize extends BaseEntity implements Comparable<AdSize> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8368755383342584257L;

	/**
	 * 广告位宽度
	 */
	@NotNull
	private Integer width;
	/**
	 * 广告位高度
	 */
	@NotNull
	private Integer height;

	private boolean active = true;

	/**
	 * 所属平台
	 */
	@NotNull
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private Platform platform;

	/**
	 * 日平均曝光次数
	 */
	private long dailyAvgImp = 0;

	private Integer otherAdview;
	
	@Transient
	private String dailyAvgImpPercent;

	@Override
	public int compareTo(AdSize s) {
		return (dailyAvgImp > s.getDailyAvgImp()) ? -1 : ((dailyAvgImp == s.getDailyAvgImp()) ? 0 : 1);
	}
}
