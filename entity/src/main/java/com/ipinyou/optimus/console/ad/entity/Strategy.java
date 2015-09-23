package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Index;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineList;
import com.ipinyou.base.entity.UserDefineMap;
import com.ipinyou.optimus.console.ad.model.AgentProduct;
import com.ipinyou.optimus.console.ad.model.IndividualLimitation;
import com.ipinyou.optimus.console.ad.model.Limitation;
import com.ipinyou.optimus.console.ad.model.MobileAttribute;
import com.ipinyou.optimus.console.ad.vo.StrategyAdvertisingStatusVo;

/**
 * 策略
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig", "campaign", "runStatus",
		"strategyStats" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "campaign",
		"runStatus", "strategyStats" ,"regulates"})
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer",
		"campaign", "blackWhiteUrl", "advertisingStatus", "orig" }, ignoreUnknown = true)
public class Strategy extends TimedEntity implements Auditable<Strategy> {

	/**
	 *
	 */
	private static final long serialVersionUID = 2000274666807358793L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("ftl.common.strategy")/*策略*/;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "campaign_id", nullable = false)
	private Campaign campaign;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long campaignId;
	/**
	 * 策略名称
	 */
	@Index(name = "name")
	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 64)
	private String name;

	/**
	 * 开启／关闭
	 */
	@Index(name = "active")
	private boolean active;

	/**
	 * 投放类型,默认为Desktop
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private AdvertisingMode advertisingMode = AdvertisingMode.Desktop;

	public static enum AdvertisingMode {
		Desktop("DSP"),
		Mobile("optimus.console.ad.entity.Strategy.1001"/*移动DSP*/),
		Stats("optimus.console.ad.entity.Strategy.1002"/*第三方监测*/),
		OwnMedia("optimus.console.ad.entity.Strategy.1003"/*自有媒体*/);

		private String text;

		private AdvertisingMode(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	/**
	 * true:表示从未开启过
	 */
	private boolean original = true;
	/**
	 * 已删除
	 */
	private boolean removed = false;

	/**
	 * 策略运行投放状态
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private StrategyStatus runStatus;

	/**
	 * 策略统计信息
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private StrategyStats strategyStats;

	/**
	 * 策略黑白名单信息
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private StrategyBlackWhiteUrl blackWhiteUrl;
	
	/**
	 * 策略媒体分类定向与排除信息
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private StrategyClassifyUrl classifyUrl;
	
	/**
	 * APP黑白名单信息
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private StrategyBlackWhiteApp blackWhiteApp;

//	/**
//	 * 全局黑白名单
//	 */
//	@Column(nullable = true)
//	private Long globalUrlId;
//
//	/**
//	 * 是否开启优质媒体圈定向
//	 */
//	@Transient
//	public boolean isHighQualityMedia() {
//		if (this.globalUrlId != null && this.globalUrlId > 0) {
//			return true;
//		}
//		return false;
//	}
//
//	@Transient
//	public void setHighQualityMedia(boolean highQualityMedia) {
//		if (highQualityMedia) {
//			this.globalUrlId = 1L;
//		} else {
//			this.globalUrlId = null;
//		}
//	}

	/**
	 * 投放优先级[1，2，3，4，5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]，
	 * 必须为正整数，数字越大优先级越高，可为空，为空时由投放判断优先级
	 */
	private Short priority;

	/**
	 * 查看广告策略的优先级设置
	 */
	public final static Short PRIORITY_VIEW_AD = 15;

	/**
	 * 投放开始日期
	 */
	@Index(name = "beginDate")
	private Date beginDate;

	/**
	 * 投放结束日期
	 */
	@Index(name = "endDate")
	private Date endDate;

	/**
	 * 限制
	 */
	@Embedded
	private Limitation limit;

	/**
	 * 单人阈值限制
	 */
	@Embedded
	private IndividualLimitation indvdLimit;

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
	 * 移动人群定向属性
	 */
	@Column(length = 2000)
	private String mobileAudiences;
	
	/**
	 * 秒针人群定向属性
	 */
	@Column(length = 2000)
	private String miaozhenAudiences;
	
	/**
	 * DATA人群定向排除
	 */
	@Column(length = 2000)
	private String audiencesExclude;
	
	
	/**
	 * 人群画像
	 */
	@Column(length = 2000)
	private String audiencesDrawing;

	/**
	 * 地域定向属性
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "areas", length = 2000))
	private StrategyAreaList areas;

	/**
	 * 地域定向排除属性
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "areas_exclude", length = 2000))
	private StrategyAreaList areasExclude;

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
	
	/**
	 * 自动优化开启/关闭
	 */
	private boolean regulateStatus;

	
	@OneToMany(mappedBy = "strategy")
	@OrderBy("lastModified")
	private Set<StrategyRegulate> regulates;
	
	/**
	 * 单品访客找回标识 true-开启;false-关闭
	 */
	private boolean retargetProduct;

	public static enum RetargetType {
		NotEnabled("optimus.console.ad.model.AdvertisingStatus.1007"/*未开启*/), Site("optimus.console.ad.entity.Strategy.1004"/*全站访客*/), Category("optimus.console.ad.entity.Strategy.1005"/*分类访客*/), Conversion("optimus.console.ad.entity.ConversionTarget.1006"/*转化目标*/), Product(
				"optimus.console.ad.entity.Strategy.1006"/*单品访客*/);

		private String text;

		private RetargetType(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	@Transient
	public boolean isVisitorExclude() {
		if ((this.getExcludeCategorys() != null && this.getExcludeCategorys()
				.size() > 0)
				|| (this.getExcludeConversions() != null && this
						.getExcludeConversions().size() > 0)) {
			return true;
		} else {
			return false;
		}
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private DayPacingType dayPacing;

	public static enum DayPacingType {
		NotEnabled("optimus.console.ad.entity.Strategy.1007"/*未启用*/), DayBudget("optimus.console.ad.entity.Strategy.1008"/*每日预算均匀*/), DayImp("optimus.console.ad.entity.Strategy.1009"/*每日曝光均匀*/);

		private String text;

		private DayPacingType(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

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
	 * retargetConversions列表，逗号分隔，访客找回转化id
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "retarget_conversions", length = 1000))
	private ConversionIdList retargetConversions;

	/**
	 * retargetCategorys列表，逗号分隔，访客找回目标value
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "retarget_categorys", length = 1000))
	private RetargetValueList retargetCategorys;

	/**
	 * excludeConversions列表，逗号分隔，排除的访客找回转化id
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "exclude_conversions", length = 1000))
	private ConversionIdList excludeConversions;

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
	 * 商品轮播方式
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private CarouselMode carouselMode;

	public static enum CarouselMode {
		BAba("BAba"),BbAa("BbAa");

		private String text;

		private CarouselMode(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}

	/**
	 * 补充商品分类
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private SupplementCategory supplementCategory;

	public static enum SupplementCategory {
		Related("optimus.console.ad.entity.Strategy.1010"/*相关商品*/), SameHot("optimus.console.ad.entity.Strategy.1011"/*同品热销*/), SameDiscount("optimus.console.ad.entity.Strategy.1012"/*同品最大折扣*/), SameViews(
				"optimus.console.ad.entity.Strategy.1013"/*同品最多访问*/),CollaborateFilter("optimus.console.ad.entity.Strategy.1014"/*协同过滤*/),GoodsCategory("optimus.console.ad.entity.Strategy.1015"/*按商品品类推荐*/);

		private String text;

		private SupplementCategory(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
	
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "goods_category", length = 1000))
	private GoodsCategoryList goodsCategory;
	
	/**
	 * 访客找回-》商品补充分类-》商品品类列表
	 * 
	 * @author ke.gu
	 * 
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class GoodsCategoryList extends UserDefineList<Short> {
		
		private static final long serialVersionUID = -2852915775173580542L;

		public GoodsCategoryList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}	
	
	
	
	
	

	/**
	 * 策略类型
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private StrategyType type = StrategyType.Normal;

	public static enum StrategyType {
		Normal, // 普通
		ViewAd, // 查看广告
		Stats // 第三方监测
	}

	/**
	 * 广告位位置列表
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "ad_locations", length = 1000))
	private SimulateMap adLocations;
	
	
	/**
	 * 广告位频道列表
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "ad_channels", length = 1000))
	private SimulateMap adChannels;

	/**
	 * CPM出价，单位：元
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal cpmBidPrice = new BigDecimal(5);

	/**
	 * 策略媒体广告位设置
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private StrategyAdUnit adUnit;
	

	public static enum ExpectedGoal {
		Na("optimus.console.ad.entity.Strategy.1016"/*未设置*/), 
		Imp("optimus.console.ad.entity.Strategy.1045"/*CPM单价*/), // 页面已删除，枚举留着，兼容老数据
		Click("optimus.console.ad.model.KPIAttribute.1001"/*点击单价*/), Reach("optimus.console.ad.model.KPIAttribute.1002"/*到达单价*/),  TwoJump("optimus.console.ad.model.KPIAttribute.1006"/*二跳单价*/),Convert("optimus.console.ad.model.KPIAttribute.1003"/*转化单价*/); // TODO

		private String text;

		private ExpectedGoal(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private ExpectedGoal expectedGoal = ExpectedGoal.Na;

	public static SimulateMap getDefaultExpectedParams() {
		SimulateMap param = new SimulateMap();
		param.setStrValue("cpc=1.0");
		return param;
	}

	/**
	 * 期望目标的设置参数
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "expected_params", length = 255))
	private SimulateMap expectedParams;

	@Transient
	private String showExpectedParams;

	/** 前端错误信息提示使用 **/
	@Transient
	private String expectedPrice;

	@Column(length = 50)
	private String expectedConvertPoint;
	
	@Transient
	private String expectedAmount;

	public String getShowExpectedParams() {
		if (expectedParams == null || expectedParams.isEmpty()) {
			return null;
		}
		showExpectedParams = "";
		for (Iterator<Map.Entry<String, String>> it = expectedParams.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if ("clicks".equalsIgnoreCase(key)) {
				key = com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1017")/*点击量:*/;
				value += com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1018")/*次*/;
			} else if ("imps".equalsIgnoreCase(key)) {
				key = com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1019")/*曝光量:*/;
				value += com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1020")/*千次*/;
			} else if ("reachs".equalsIgnoreCase(key)) {
				key = com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1021")/*到达量:*/;
				value += com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1018")/*次*/;
			} else if ("converts".equalsIgnoreCase(key)) {
				key = com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1022")/*转化量:*/;
				value += com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1018")/*次*/;
			} else if ("expectedConvertPoint".equalsIgnoreCase(key)){
				key = key + com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1023")/*转化点ID:[*/;
				value += "]";
			} else {
				key = key + com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1024")/*单价:*/;
				value += com.ipinyou.base.util.I18nResourceUtil.getResource("ftl.ad.allocate_center.1007")/*元*/;
			}
			showExpectedParams += key + value + " ";
		}
		return showExpectedParams;
	}

	/**
	 * 策略备注信息
	 */
	@Column(length = 255)
	@Size(max = 255)
	private String remark;

	/**
	 * 算法设置（后台设置，不展示到界面上）
	 */
	@Column(length = 64)
	private String algorithm;

	/**
	 * 界面可选择的算法编码
	 */
	@Column(length = 64)
	private String algorithmCode;
	
	/**
	 * 内部添加的脚本代码信息, 长度改为longtext
	 */
	@Column(length = 16777215)
	private String scriptCode;

	/**
	 * 防作弊开关
	 */
	private boolean antiCheating = true;
	
	/**
	 * 防作弊程度 高-0、中-1、低-2，不设置-NULL,默认为高
	 */
	private Short antiCheatLevel = 0;

	/**
	 * 手工指定策略分布到哪些投放节点
	 */
	private Long hashCode;

	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "pyid_categories", length = 1000))
	private PyidCategoryList pyidCategories;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private AudienceWeight audienceWeight;

	public static enum AudienceWeight {
		Hight("optimus.console.ad.entity.Strategy.1025"/*高*/), Middle("optimus.console.ad.entity.Strategy.1026"/*中*/), Unlimited("ftl.import.spring.1004"/*不限*/);

		private String text;

		private AudienceWeight(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}

	/**
	 * 投放广告位类型定向
	 */
	// @Embedded
	// @AttributeOverride(name = "strValue", column = @Column(name =
	// "ad_unit_types", length = 128))
	// private AdUnitViewTypeMap adUnitViewTypes;
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
	
	
	/*
	 * 是否是preferdeal投放策略
	 */
	private Boolean preferDealStrategy=false;
	

	@Transient
	private Strategy orig;

	public static enum BidMode {
		ByAdvertiser, ByAgency, ByDsp
	};
	
	
	public static enum ClickCookieType {
		Na("optimus.console.ad.entity.Strategy.1016"/*未设置*/), AdvClick("optimus.console.ad.entity.Strategy.1027"/*点击过本广告主广告*/), PinyouClick("optimus.console.ad.entity.Strategy.1028"/*点击过广告*/), OrderClick("optimus.console.ad.entity.Strategy.1029"/*点击过本订单广告*/),
		CampaignClick("optimus.console.ad.entity.Strategy.1030"/*点击过本计划*/);
		
		private String text;
		
		private ClickCookieType(String text){
			this.text = text;
		}
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}

	/**
	 * 投放状态，非持久化，以vo形式存在
	 */
	@Transient
	private StrategyAdvertisingStatusVo advertisingStatus;

	/**
	 * 地域属性，以对象形式存在
	 * 
	 * @author zhyhang
	 * 
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class StrategyAreaList extends UserDefineList<Short> {
		private static final long serialVersionUID = -6200839774554727561L;

		public StrategyAreaList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		protected Short asObject(String value) {
			return Short.valueOf(value);
		}
	}

	/**
	 * 逗号分隔的字符串列表。用于： 浏览器属性； exchange平台
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class BrowsersList extends UserDefineList<AgentProduct> {
		private static final long serialVersionUID = 7396579259085541839L;

		public BrowsersList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		public boolean needOdered() {
			return true;
		}

		@Override
		protected Object asObject(String strValue) {
			return Enum.valueOf(AgentProduct.class, strValue);
		}

	}

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class PlatformList extends UserDefineList<String> {
		private static final long serialVersionUID = 7396579259085541839L;

		public PlatformList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		public boolean needOdered() {
			return true;
		}
	}

	/**
	 * pyid属性自定义类型
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class PyidList extends UserDefineList<String> {
		private static final long serialVersionUID = 6184614428401805340L;

		public PyidList() {
			super("\n");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}

	/**
	 * 广告位第几屏自定义类型
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class SimulateMap extends UserDefineMap<String, String> {
		private static final long serialVersionUID = -8610952695509537180L;

		public SimulateMap() {
			super(";", "=");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		public String mapKeyObject(String key) {
			return key;
		}

		@Override
		public String mapValueObject(String value) {
			return value;
		}

	}

	/**
	 * retargetvalue属性自定义类型
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class RetargetValueList extends UserDefineList<String> {
		private static final long serialVersionUID = 6184614428401805340L;

		public RetargetValueList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}
	
	
	/**
	 * videoChannel属性自定义类型
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class VideoChannelValueList extends UserDefineList<String> {
		private static final long serialVersionUID = 6184614428401805340L;

		public VideoChannelValueList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}

	/**
	 * conversionid属性自定义类型
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class ConversionIdList extends UserDefineList<Long> {
		private static final long serialVersionUID = 6184614428401805340L;

		public ConversionIdList() {
			super(",");
		}

		@Override
		protected Object asObject(String strValue) {
			return Long.valueOf(strValue);
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class PyidCategoryList extends UserDefineList<String> {
		private static final long serialVersionUID = 3967059505310177396L;

		public PyidCategoryList() {
			super("\n");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}


	public static enum AdUnitType {
		Banner("Banner"), // Banner
		Video("optimus.console.ad.entity.Creative.1002"/*视频*/); // 视频

		private String text;

		private AdUnitType(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}

	public static enum AdUnitViewType {
		Fixed("optimus.console.ad.entity.Strategy.1046"/*固定*/), // 固定
		Pop("optimus.console.ad.entity.Strategy.1047"/*弹窗*/), // 弹窗
		Other("optimus.console.ad.model.AgentProduct.1003"/*其它*/); // 其它

		private String text;

		private AdUnitViewType(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class AdUnitViewTypeList extends
			UserDefineList<AdUnitViewType> {

		private static final long serialVersionUID = 202378160443307212L;

		public AdUnitViewTypeList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		protected AdUnitViewType asObject(String strValue) {
			return AdUnitViewType.valueOf(strValue);
		}
	}

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class AdUnitTypeList extends UserDefineList<AdUnitType> {
		private static final long serialVersionUID = 202378160443307212L;

		public AdUnitTypeList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

	}

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class AdUnitViewTypeMap extends
			UserDefineMap<AdUnitType, AdUnitViewTypeList> {

		private static final long serialVersionUID = -2461442154375076275L;

		public AdUnitViewTypeMap() {
			super(";", "=");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		public AdUnitType mapKeyObject(String key) {
			return AdUnitType.valueOf(key);
		}

		@Override
		public AdUnitViewTypeList mapValueObject(String value) {
			AdUnitViewTypeList types = new AdUnitViewTypeList();
			if (StringUtils.isBlank(value)) {
				return types;
			}
			types.setStrValue(value);
			return types;
		}
	}

	/**
	 * 策略的效果目标标示
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private EffectGoal effectGoal = EffectGoal.Na;

	public static enum EffectGoal {
//		<option value="bidSuccessRate">竞价成功率</option>
//		<option value="twoJumpRate">二跳率</option>
//		<option value="threeJumpRate">三跳率</option>
//		<option value="fourJumpRate">四跳率</option>
//		<option value="fiveJumpRate">五跳率</option>
//		<option value="impConversionRate">曝光转化率</option>
//		<option value="cpcPrice">cpc价格</option>
//		<option value="reachPrice">到达单价</option>
//		<option value="twoJumpPrice">二跳单价</option>
//		<option value="threeJumpPrice">三跳单价</option>
//		<option value="fourJumpPrice">四跳单价</option>
//		<option value="fiveJumpPrice">五跳单价</option>
//		<option value="clickCvtPrice">点击转化单价</option>
//		<option value="impCvtPrice">曝光转化单价</option>
//		<option value="roi">点击转化ROI</option>
//		<option value="roiImp">曝光转化ROI</option>
//		<option value="unitPrice">点击转化客单价</option>
//		<option value="unitPriceImp">曝光转化客单价</option>
		
		
//		<option value="clickRate">点击率</option>
//		<option value="reachRate">到达率</option>
//		<option value="clickConversionRate">点击转化率</option>
		Na("optimus.console.ad.entity.Strategy.1016"/*未设置*/), ClickRate("ftl.ad.index.1017"/*点击率*/), ReachRate("optimus.console.ad.entity.DiagnoseCampaign.1002"/*到达率*/), ConvertRate("optimus.console.ad.entity.Strategy.1031"/*转化率*/), ConversionRate("optimus.console.ad.entity.Strategy.1032"/*点转率*/), Convert("optimus.console.ad.model.KPIAttribute.1003"/*转化单价*/),
		ROI("ROI"),
		// 需求 by 彭凌霄
		BidSuccessRate("optimus.console.ad.entity.DiagnoseCampaign.1003"/*竞价成功率*/), TwoJumpRate("optimus.console.ad.entity.Strategy.1033"/*二跳率*/), ThreeJumpRate("optimus.console.ad.entity.Strategy.1034"/*三跳率*/), FourJumpRate("optimus.console.ad.entity.Strategy.1035"/*四跳率*/), FiveJumpRate("optimus.console.ad.entity.Strategy.1036"/*五跳率*/), ImpConversionRate(
		"optimus.console.ad.entity.Strategy.1037"/*曝光转化率*/), CpcPrice("optimus.console.ad.entity.Strategy.1038"/*cpc价格*/), ReachPrice("optimus.console.ad.model.KPIAttribute.1002"/*到达单价*/), TwoJumpPrice("optimus.console.ad.model.KPIAttribute.1006"/*二跳单价*/), ThreeJumpPrice("optimus.console.ad.model.KPIAttribute.1007"/*三跳单价*/), FourJumpPrice("optimus.console.ad.entity.Strategy.1039"/*四跳单价*/), FiveJumpPrice(
		"optimus.console.ad.entity.Strategy.1040"/*五跳单价*/), ClickCvtPrice("optimus.console.ad.model.KPIAttribute.1005"/*点击转化单价*/), ImpCvtPrice("optimus.console.ad.model.KPIAttribute.1004"/*曝光转化单价*/), Roi("optimus.console.ad.entity.Strategy.1041"/*点击转化ROI*/), RoiImp("optimus.console.ad.entity.Strategy.1042"/*曝光转化ROI*/), UnitPrice("optimus.console.ad.entity.Strategy.1043"/*点击转化客单价*/), UnitPriceImp(
		"optimus.console.ad.entity.Strategy.1044"/*曝光转化客单价*/);

		private String text;

		private EffectGoal(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	/**
	 * 策略的效果目标率
	 */
	@Column(nullable = true)
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal effectGoalRate;

	public static enum OSType {
		Android("Android", true), IOS("iOS", true), WindowsPhone(
				"WindowsPhone", true),

		WindowsXP("Windows XP", false), Windows2000("Windows 2000", false), Windows2003(
				"Windows 2003", false), WindowsVista("Windows Vista", false), Windows7(
				"Windows 7", false), Windows8("Windows 8", false),

		MacOSX("Mac OS X", false), Linux("Linux", false), Other("optimus.console.ad.model.AgentProduct.1003"/*其它*/, false);

		private String text;

		private boolean mobile;

		private OSType(String text, boolean mobile) {
			this.text = text;
			this.mobile = mobile;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}

		public boolean isMobile() {
			return mobile;
		}
	}

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class OSTypeList extends UserDefineList<OSType> {
		private static final long serialVersionUID = 7396579259085541839L;

		public OSTypeList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		public OSType asObject(String strValue) {
			return Enum.valueOf(OSType.class, strValue);
		}
	}

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class OSVersionMap extends UserDefineMap<OSType, String> {

		private static final long serialVersionUID = -1199655002777154996L;

		public OSVersionMap() {
			super(";", "=");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		public OSType mapKeyObject(String key) {
			return Enum.valueOf(OSType.class, key);
		}

		@Override
		public String mapValueObject(String value) {
			return value;
		}
	}

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
	
	/**
	 * 流量打底
	 */
	private boolean backing;
	
	/**
	 * 自动截屏
	 */
	private boolean autoScreen=false;
	
	/**
	 * 频道
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "video_channels", length = 2000))
	private VideoChannelValueList videoChannels;
	
	/**
	 * 剧目id或剧目名称
	 */
	@Column(length = 2000)
	private String videoPlayList;
	
	/**
	 * LBS选中的点
	 */
	@Column(length = 2000)
	private String lbsPoints;
	
	/**
	 * LBS每个点覆盖的范围（半径）
	 */
	private Integer lbsDistance;
	
	/**
	 * 浏览器指纹应用
	 */
	private boolean fingerprint=false;
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class ThirdPlatformList extends UserDefineList<String> {
		private static final long serialVersionUID = 7396579259085541839L;

		public ThirdPlatformList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

		@Override
		public boolean needOdered() {
			return true;
		}
	}
	
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "third_platform", length = 50))
	private ThirdPlatformList thirdPlatforms;
	
	private Long rollRuleId;
	
//	@Enumerated(EnumType.STRING)
//	@Column(nullable = true, length = 50)
//	private ThirdPlatform thirdPlatform;
}
