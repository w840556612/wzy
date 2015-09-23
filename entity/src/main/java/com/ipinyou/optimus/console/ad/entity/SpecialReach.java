package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.BaseEntity;

/**
 * 处理特殊到达的规则
 * 
 * @author ganwenlong 2013-3-1 下午4:26:47
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "advertiserCompany", "campaign", "creative" })
@EqualsAndHashCode(callSuper = true, exclude = { "advertiserCompany", "campaign",
		"creative" })
public class SpecialReach extends BaseEntity {

	private static final long serialVersionUID = -5297883413329346271L;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_company_id", nullable = false)
	private AdvertiserCompany advertiserCompany;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserCompanyId;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "campaign_id", nullable = true)
	private Campaign campaign;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long campaignId;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "creative_id", nullable = true)
	private Creative creative;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long creativeId;

	@Column(nullable = true, length = 2000)
	private String reachUrl;
	@Column(nullable = true, length = 200)
	private String reachRule;

}
