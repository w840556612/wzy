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
 * GM秒针监测地址表
 */
@Entity
@Table(name = "gm_stats_url")
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "version", "creation", "lastModified", "orig" })
public class GMStatsUrl extends TimedEntity implements
		Auditable<GMStatsUrl> {

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
	 * 品友广告位标识
	 */
	@NotNull
	private String adUnitCode;
	
	/**
	 * 秒针广告ID
	 */
	@NotNull
	private String otherAdId;

	/**
	 * 秒针spot id
	 */
	private String otherId;
	
	/**
	 * 秒针spot id str
	 */
	private String otherIdStr;
	
	/**
	 * 第三方监测扩展ID
	 */
	private String otherExtendId;
	
	/**
	 * 品友网站名称
	 */
	@NotNull
	private String websiteName;
	
	/**
	 * 品友栏目名称
	 */
	@NotNull
	private String labelName;
	
	/**
	 * 品友广告位名称
	 */
	@NotNull
	private String adUnitName;
	
	@Column(nullable = false, length = 2083)
	@Index(name="targetUrl")
	private String targetUrl;
	
	@Column(length = 2083)
	@Index(name="impUrl")
	private String impUrl;
	
	@Column(length = 2083)
	@Index(name="clickUrl")
	private String clickUrl;

	@Override
	public String getEntityName() {
		return "第三方监测广告";
	}

	@Override
	public String getName() {
		return websiteName + "\n" + labelName + "\n" + adUnitName;
	}

	@Transient
	private GMStatsUrl orig;

}
