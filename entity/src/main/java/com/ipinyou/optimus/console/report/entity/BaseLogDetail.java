package com.ipinyou.optimus.console.report.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.model.AdLocation;
import com.ipinyou.optimus.console.model.Platform;

/**
 * 广告主日志详细信息基类
 * @author guodong.zhang
 */
@MappedSuperclass
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseLogDetail extends TimedEntity {

	private static final long serialVersionUID = -2059948420079902245L;

	// Action
	private String actionId;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private Platform platform;
	
	private String firstActionId;
	
	@Column(length = 50)
	private String sessionId;
	
	@Index(name = "requestTime")
	private Date requestTime; // 请求时间【不能为空】
	
	private Date responseTime;
	
	private Date firstActionTime;
	
	private Date prevActionTime;
	
	// User
	@Column(length = 30)
	private String pyid;
	
	private Boolean newUser;
	
	// Agent
	@Lob
	private String url;
	
	private String anonymousUrlId;
	
	@Lob
	private String referUrl;
	
	private String agentType;
	
	@Column(length = 200)
	private String userAgent;
	
	private String agentCharset;
	
	private String appId;
	
	// Geo
	@Column(length = 200)
	private String ip;
	
	private Integer timeOffsetMinutes;
	
	private String geoId;
	
	private String thirdPartyGeoId;
	
	private String geoCell;
	
	private String mobileLatitude;
	
	// Device
	private String deviceType;
	
	private String deviceOs;
	
	private String deviceBrand;
	
	private String deviceModel;
	
	private String networkOperator;

	protected Long advertiserCompanyId;

	private Long strategyId;
	
	private Long creativeId;
	
	private Long creativeUnitId;
	
	private String adUnitId;
	
	@Lob
	private String category;
	
	@Column(length = 100)
	private String family;
	
	private String adUnitDomain;
	
	private String adUnitAnonymousUrlId;
	
	private String adUnitAppId;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private AdLocation adUnitLocation;
	
	@Column(length = 20)
	private String adUnitViewType;
	
	private Integer jump;
	
	@Column(length = 10)
	private String paramInvalid;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private Platform platformMapping;


}
