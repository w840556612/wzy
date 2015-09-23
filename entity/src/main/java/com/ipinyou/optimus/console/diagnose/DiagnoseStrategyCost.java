package com.ipinyou.optimus.console.diagnose;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Strategy.AdUnitTypeList;
import com.ipinyou.optimus.console.ad.entity.Strategy.AudienceWeight;
import com.ipinyou.optimus.console.ad.entity.Strategy.BrowsersList;
import com.ipinyou.optimus.console.ad.entity.Strategy.ClickCookieType;
import com.ipinyou.optimus.console.ad.entity.Strategy.DayPacingType;
import com.ipinyou.optimus.console.ad.entity.Strategy.EffectGoal;
import com.ipinyou.optimus.console.ad.entity.Strategy.OSTypeList;
import com.ipinyou.optimus.console.ad.entity.Strategy.OSVersionMap;
import com.ipinyou.optimus.console.ad.entity.Strategy.PlatformList;
import com.ipinyou.optimus.console.ad.entity.Strategy.PyidCategoryList;
import com.ipinyou.optimus.console.ad.entity.Strategy.PyidList;
import com.ipinyou.optimus.console.ad.entity.Strategy.RetargetValueList;
import com.ipinyou.optimus.console.ad.entity.Strategy.SimulateMap;
import com.ipinyou.optimus.console.ad.entity.Strategy.StrategyAreaList;
import com.ipinyou.optimus.console.ad.entity.StrategyAdUnit.AdUnitIdList;
import com.ipinyou.optimus.console.ad.entity.StrategyBlackWhiteUrl.UrlIdList;
import com.ipinyou.optimus.console.ad.entity.StrategyBlackWhiteUrl.UrlList;
import com.ipinyou.optimus.console.ad.model.IndividualLimitation;
import com.ipinyou.optimus.console.ad.model.Limitation;
import com.ipinyou.optimus.console.ad.model.MobileAttribute;

/**
 */
@Entity
@Table(name = "diagnose_strategy_cost")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, of = { "strategyId", "day" })
@Data
public class DiagnoseStrategyCost extends TimedEntity {

	private static final long serialVersionUID = -5212987269046960319L;

	@Column( updatable = false, nullable = false)
	private Long strategyId;

	@Column(updatable = false, nullable = false)
	private Long campaignId;

	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 64)
	private String strategyName;

	private BigDecimal strategyDailyBudget;

	private BigDecimal campaignDailyBudget;

	private Date day;

	private BigDecimal advCost;
	
	private boolean dasuanpan;
	
	/**
	 * 所属计划总预算，单位：元 code from 140826
	 */
	private BigDecimal campaignTotalBudget;
	
	/**
	 * 限制 code from 140826
	 */
	@Embedded
	private Limitation limit;

	/**
	 * 单人阈值限制 code  from 140826
	 */
	@Embedded
	private IndividualLimitation indvdLimit;
	
	/**
	 * CPM出价，单位：元code  from 140826
	 */
	private BigDecimal cpmBidPrice = new BigDecimal(5);

	
	/**
	 * 竞价 code from 140829
	 */
	private long bid;

	/**
	 * 曝光 code from 140829
	 */
	private long imp;

	/**
	 * 域名白名单Id集合
	 */
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "black_url_ids", length = 16777215))
	private UrlIdList blackUrlIds;

	/**
	 * 域名黑名单Id集合
	 */
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "white_url_ids", length = 16777215))
	private UrlIdList whiteUrlIds;

	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "custom_black_urls", length = 16777215))
	private UrlList customBlackUrls;

	/**
	 * 自定义域名白名单
	 */
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "custom_white_urls", length = 16777215))
	private UrlList customWhiteUrls;

	/**
	 * 移动APPId黑名单
	 */
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "black_app_ids", length = 16777215))
	private UrlList blackAppIds;

	/**
	 * 移动APPId白名单
	 */
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "white_app_ids", length = 16777215))
	private UrlList whiteAppIds;
	/**
	 * 全局黑白名单
	 */
	@Column(nullable = true)
	private Long globalUrlId;

	/**
	 * 
	 */
	@Embedded
	private MobileAttribute mobileAttr;

	/**
	 * 人群定向属性
	 */
	@Column(length = 2000)
	private String audiences;

	/**
	 * 百度人群定向属性
	 */
	@Column(length = 2000)
	private String baiduAudiences;

	/**
	 * DATA人群定向排除
	 */
	@Column(length = 2000)
	private String audiencesExclude;

	/**
	 * 地域定向属性
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "areas", length = 2000))
	private StrategyAreaList areas;

	/**
	 * 投放时间段,目前暂时为每周排期表，后续若增强该功能，将修改映射字段
	 */
	@Column(length = 1000, name = "weekly_schedule")
	private String dayParting;

	/**
	 * 浏览器定向属性
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "browsers", length = 1000))
	private BrowsersList browsers;

	/**
	 * 全站访客找回,true表示开启针对广告主的全站访客找回，此时retargetChannelIds的设置不起作用，false表示不开启全站访客找回
	 */
	private boolean retargetSite;

	private boolean highQualityMedia;

	/**
	 * 单品访客找回标识 true-开启;false-关闭
	 */
	private boolean retargetProduct;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private DayPacingType dayPacing;

	/**
	 * ad-exchange列表，逗号分隔
	 * 
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "exchanges", length = 1000))
	private PlatformList exchanges;

	/**
	 * cookie定向pyid列表，使用\n分隔，一行一个
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "pyids", length = 1000))
	private PyidList pyids;

	/**
	 * excludeCategorys列表，逗号分隔，排除的访客找回目标value
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "exclude_categorys", length = 1000))
	private RetargetValueList excludeCategorys;

	/**
	 * 访客找回周期 1-90天
	 */
	private Short visitorsCycle;

	/**
	 * 广告位位置列表
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "ad_locations", length = 1000))
	private SimulateMap adLocations;

	@Embedded
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "black_ids", length = 65536))
	private AdUnitIdList blackIds;

	/**
	 * 
	 */
	@Embedded
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "white_ids", length = 65536))
	private AdUnitIdList whiteIds;

	/**
	 * 防作弊开关
	 */
	private boolean antiCheating = true;

	/**
	 * 防作弊程度 高-0、中-1、低-2，不设置-NULL,默认为高
	 */
	private Short antiCheatLevel = 0;

	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "pyid_categories", length = 1000))
	private PyidCategoryList pyidCategories;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private AudienceWeight audienceWeight;

	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "ad_unit_types", length = 128))
	private AdUnitTypeList adUnitTypes;

	/**
	 * 点击Cookie找回方式
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private ClickCookieType clickCookieType = ClickCookieType.Na;

	/**
	 * preferDeal方式DSP投放标识信息，可以包含多个广告位或者多个其它标识，以逗号,分割
	 */
	@Column(length = 128)
	private String preferDeal;

	/**
	 * 策略的效果目标标示
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private EffectGoal effectGoal = EffectGoal.Na;

	/**
	 * 操作系统类型
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "os_types", length = 256))
	private OSTypeList osTypes;

	/**
	 * 操作系统及版本信息 格式：Android=2.0,3.0,4.0;iOS=6.0,7.0
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "os_versions", length = 512))
	private OSVersionMap osVersions;

}
