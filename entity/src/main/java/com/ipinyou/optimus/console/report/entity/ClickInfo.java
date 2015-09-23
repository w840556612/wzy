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
 * 广告主、转化日志 关联的点击日志信息
 * @author ganwenlong 2013-2-21 上午11:36:59
 */
@Embeddable
@Data
public class ClickInfo {

	//Action
	@Column(name = "clk_action_id")
	private String actionId;
	@Column(name = "clk_platform", length=50)
	@Enumerated(EnumType.STRING)
	private Platform platform;
	@Column(name = "clk_platform_mapping", length=50)
	@Enumerated(EnumType.STRING)
	private Platform platformMapping;
	@Column(name = "clk_first_action_id")
	private String firstActionId;
	@Column(name = "clk_session_id", length = 50)
	private String sessionId;
	@Column(name = "clk_request_time")
	private Date requestTime;
	@Column(name = "clk_response_time")
	private Date responseTime;
	@Column(name = "clk_first_action_time")
	private Date firstActionTime;
	@Column(name = "clk_prev_action_time")
	private Date prevActionTime;

	// User
	@Column(name = "clk_pyid", length = 30)
	private String pyid;
	@Column(name = "clk_new_user")
	private Boolean newUser;

	// Agent
	@Lob
	@Column(name = "clk_url")
	private String url;
	@Column(name = "clk_anonymous_url_id")
	private String anonymousUrlId;
	@Lob
	@Column(name = "clk_refer_url")
	private String referUrl;
	@Column(name = "clk_agent_type")
	private String agentType;
	@Column(name = "clk_user_agent", length = 200)
	private String userAgent;
	@Column(name = "clk_agent_charset")
	private String agentCharset;
	@Column(name = "clk_app_id")
	private String appId;
	
	// Geo
	@Column(name = "clk_ip", length = 200)
	private String ip;
	@Column(name = "clk_time_offset_minutes")
	private Integer timeOffsetMinutes;
	@Column(name = "clk_geo_id")
	private String geoId;
	@Column(name = "clk_third_party_geo_id")
	private String thirdPartyGeoId;
	@Column(name = "clk_geo_cell")
	private String geoCell;
	@Column(name = "clk_mobile_latitude")
	private String mobileLatitude;
	
	// Device
	@Column(name = "clk_device_type")
	private String deviceType;
	@Column(name = "clk_device_os")
	private String deviceOs;
	@Column(name = "clk_device_brand")
	private String deviceBrand;
	@Column(name = "clk_device_model")
	private String deviceModel;
	@Column(name = "clk_network_operator")
	private String networkOperator;

	// ID
	@Column(name = "clk_advertiser_id")
	private Long advertiserId;
	@Column(name = "clk_order_id")
	private Long orderId;
	@Column(name = "clk_campaign_id")
	private Long campaignId;
	@Column(name = "clk_strategy_id")
	private Long strategyId;
	@Column(name = "clk_creative_id")
	private Long creativeId;
	@Column(name = "clk_creative_mapping_id")
	private Long creativeMappingId;
	@Column(name = "clk_sc_rel_id")
	private Long scRelId;
	@Column(name = "clk_creative_unit_id")
	private Long creativeUnitId;
	@Column(name = "clk_creative_unit_mapping_id")
	private Long creativeUnitMappingId;
	@Column(name = "clk_channel_id")
	private Long channelId;
	@Column(name = "clk_website_id")
	private Long websiteId;
	@Column(name = "clk_label_id")
	private Long labelId;
	@Column(name = "clk_ad_unit_id")
	private String adUnitId;
	@Column(name = "clk_advertiser_company_id")
	private Long advertiserCompanyId;

	//ADUnit
	@Column(name = "clk_ad_unit_width")
	private Integer adUnitWidth;
	@Column(name = "clk_ad_unit_height")
	private Integer adUnitHeight;
	@Column(name = "clk_ad_unit_location", length=50)
	@Enumerated(EnumType.STRING)
	private AdLocation adUnitLocation;
	@Column(name = "clk_ad_unit_view_type", length=20)
	private String adUnitViewType;
	@Column(name = "clk_ad_unit_type", length=20)
	private String adUnitType;
	@Column(name = "clk_ad_unit_floor_price")
	private Long adUnitFloorPrice;

	// CREATIVE
	@Column(name = "clk_creative_type", length=20)
	private String creativeType;
	@Column(name = "clk_creative_path", length = 200)
	private String creativePath;
	@Lob
	@Column(name = "clk_click_url")
	private String clickUrl;
	// ARITHMETIC
	@Lob
	@Column(name = "clk_category")
	private String category;
	@Column(name = "clk_family", length = 100)
	private String family;
	@Column(name = "clk_bid_policy_data")
	private String bidPolicyData;
	@Column(name = "clk_bid_rank_data",length=40)
	private String bidRankData;

}
