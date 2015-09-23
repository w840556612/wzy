package com.ipinyou.optimus.console.report.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import lombok.Data;

import com.ipinyou.optimus.console.model.AdLocation;
import com.ipinyou.optimus.console.model.Platform;

/**
 * 曝光转化日志关联的曝光日志信息
 * @author guodong.zhang
 */
@Embeddable
@Data
public class ImpInfo {

	// Action
	@Column(name = "imp_action_id")
	private String actionId;
	
	@Column(name = "imp_platform", length = 50)
	@Enumerated(EnumType.STRING)
	private Platform platform;
	
	@Column(name = "imp_platform_mapping", length = 50)
	@Enumerated(EnumType.STRING)
	private Platform platformMapping;
	
	@Column(name = "imp_first_action_id")
	private String firstActionId;
	
	@Column(name = "imp_session_id", length = 50)
	private String sessionId;
	
	@Column(name = "imp_request_time")
	private Date requestTime;
	
	@Column(name = "imp_response_time")
	private Date responseTime;
	
	@Column(name = "imp_first_action_time")
	private Date firstActionTime;
	
	@Column(name = "imp_prev_action_time")
	private Date prevActionTime;

	// User
	@Column(name = "imp_pyid", length = 30)
	private String pyid;
	
	@Column(name = "imp_new_user")
	private Boolean newUser;

	// Agent
	@Lob
	@Column(name = "imp_url")
	private String url;
	
	@Column(name = "imp_anonymous_url_id")
	private String anonymousUrlId;
	
	@Lob
	@Column(name = "imp_refer_url")
	private String referUrl;
	
	@Column(name = "imp_agent_type")
	private String agentType;
	
	@Column(name = "imp_user_agent", length = 200)
	private String userAgent;
	
	@Column(name = "imp_agent_charset")
	private String agentCharset;
	
	@Column(name = "imp_app_id")
	private String appId;

	// Geo
	@Column(name = "imp_ip", length = 200)
	private String ip;
	
	@Column(name = "imp_time_offset_minutes")
	private Integer timeOffsetMinutes;
	
	@Column(name = "imp_geo_id")
	private String geoId;
	
	@Column(name = "imp_third_party_geo_id")
	private String thirdPartyGeoId;
	
	@Column(name = "imp_geo_cell")
	private String geoCell;
	
	@Column(name = "imp_mobile_latitude")
	private String mobileLatitude;

	// Device
	@Column(name = "imp_device_type")
	private String deviceType;
	
	@Column(name = "imp_device_os")
	private String deviceOs;
	
	@Column(name = "imp_device_brand")
	private String deviceBrand;
	
	@Column(name = "imp_device_model")
	private String deviceModel;
	
	@Column(name = "imp_network_operator")
	private String networkOperator;

	// ID
	@Column(name = "imp_advertiser_id")
	private Long advertiserId;
	
	@Column(name = "imp_order_id")
	private Long orderId;
	
	@Column(name = "imp_campaign_id")
	private Long campaignId;
	
	@Column(name = "imp_strategy_id")
	private Long strategyId;
	
	@Column(name = "imp_creative_id")
	private Long creativeId;
	
	@Column(name = "imp_creative_mapping_id")
	private Long creativeMappingId;
	
	@Column(name = "imp_sc_rel_id")
	private Long scRelId;
	
	@Column(name = "imp_creative_unit_id")
	private Long creativeUnitId;
	
	@Column(name = "imp_creative_unit_mapping_id")
	private Long creativeUnitMappingId;
	
	@Column(name = "imp_channel_id")
	private Long channelId;
	
	@Column(name = "imp_website_id")
	private Long websiteId;
	
	@Column(name = "imp_label_id")
	private Long labelId;
	
	@Column(name = "imp_ad_unit_id")
	private String adUnitId;
	
	@Column(name = "imp_advertiser_company_id")
	private Long advertiserCompanyId;

	// ADUnit
	@Column(name = "imp_ad_unit_width")
	private Integer adUnitWidth;
	
	@Column(name = "imp_ad_unit_height")
	private Integer adUnitHeight;
	
	@Column(name = "imp_ad_unit_location", length = 50)
	@Enumerated(EnumType.STRING)
	private AdLocation adUnitLocation;
	
	@Column(name = "imp_ad_unit_view_type", length = 20)
	private String adUnitViewType;
	
	@Column(name = "imp_ad_unit_type", length = 20)
	private String adUnitType;
	
	@Column(name = "imp_ad_unit_floor_price")
	private Long adUnitFloorPrice;

	// CREATIVE
	@Column(name = "imp_creative_type", length = 20)
	private String creativeType;
	
	@Column(name = "imp_creative_path", length = 200)
	private String creativePath;
	
	@Lob
	@Column(name = "imp_url", insertable = false, updatable = false)
	private String impUrl;
	
	// ARITHMETIC
	@Lob
	@Column(name = "imp_category")
	private String category;
	
	@Column(name = "imp_family", length = 100)
	private String family;
	
	@Column(name = "imp_bid_policy_data")
	private String bidPolicyData;
	
	@Column(name = "imp_bid_rank_data",length=40)
	private String bidRankData;

}
