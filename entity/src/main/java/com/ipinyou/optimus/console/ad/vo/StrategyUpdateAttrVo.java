package com.ipinyou.optimus.console.ad.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Enumerated;

import lombok.Data;

import com.ipinyou.optimus.console.ad.entity.Strategy.AdUnitTypeList;
import com.ipinyou.optimus.console.ad.entity.Strategy.AudienceWeight;
import com.ipinyou.optimus.console.ad.entity.Strategy.BrowsersList;
import com.ipinyou.optimus.console.ad.entity.Strategy.CarouselMode;
import com.ipinyou.optimus.console.ad.entity.Strategy.ClickCookieType;
import com.ipinyou.optimus.console.ad.entity.Strategy.ConversionIdList;
import com.ipinyou.optimus.console.ad.entity.Strategy.DayPacingType;
import com.ipinyou.optimus.console.ad.entity.Strategy.EffectGoal;
import com.ipinyou.optimus.console.ad.entity.Strategy.ExpectedGoal;
import com.ipinyou.optimus.console.ad.entity.Strategy.GoodsCategoryList;
import com.ipinyou.optimus.console.ad.entity.Strategy.OSTypeList;
import com.ipinyou.optimus.console.ad.entity.Strategy.OSVersionMap;
import com.ipinyou.optimus.console.ad.entity.Strategy.PlatformList;
import com.ipinyou.optimus.console.ad.entity.Strategy.PyidCategoryList;
import com.ipinyou.optimus.console.ad.entity.Strategy.PyidList;
import com.ipinyou.optimus.console.ad.entity.Strategy.RetargetValueList;
import com.ipinyou.optimus.console.ad.entity.Strategy.SimulateMap;
import com.ipinyou.optimus.console.ad.entity.Strategy.StrategyAreaList;
import com.ipinyou.optimus.console.ad.entity.Strategy.SupplementCategory;
import com.ipinyou.optimus.console.ad.entity.StrategyAdUnit;
import com.ipinyou.optimus.console.ad.entity.StrategyBlackWhiteApp;
import com.ipinyou.optimus.console.ad.entity.StrategyBlackWhiteUrl;
import com.ipinyou.optimus.console.ad.entity.StrategyClassifyUrl;
import com.ipinyou.optimus.console.ad.model.IndividualLimitation;
import com.ipinyou.optimus.console.ad.model.Limitation;
import com.ipinyou.optimus.console.ad.model.MobileAttribute;



@Data
public class StrategyUpdateAttrVo {
	/**
	 * 基本设置
	*/
	//周期
	private Date beginDate;
	private Date endDate;
	
	private Short priority;
	
	//总预算、每日预算、曝光总上限、点击总上限、每日曝光上限、每日点击上限
	@Embedded
	private Limitation limit;
	
	//单人曝光频次、单人点击频次
	@Embedded
	private IndividualLimitation indvdLimit;
	
	/**
	 * 高级设置
	*/
	//人群定向
	private String audiences;
	
	//移动人群定向
	private String mobileAudiences;
	
	//百度人群
	private String baiduAudiences;
	//秒针人群
	private String miaozhenAudiences;
	//人群排除
	private String audiencesExclude;
	//人群画像
	private String audiencesDrawing;
	
	private boolean retargetSite;
	
	/**
	 * 单品访客找回标识 true-开启;false-关闭
	 */
	private boolean retargetProduct;
	
	private Short visitorsCycle;
	
	private CarouselMode carouselMode;
	
	private SupplementCategory supplementCategory;
	
	//商品品类列表
	private GoodsCategoryList goodsCategory;
	
	public boolean isVisitorExclude() {
		if ((this.getExcludeCategorys() != null && this.getExcludeCategorys().size() > 0)
				|| (this.getExcludeConversions() != null && this.getExcludeConversions().size() > 0)) {
			return true;
		} else {
			return false;
		}
	}
	//访客排除
	//排除的访客找回转化id，访客找回目标value
	@Enumerated
	private ConversionIdList retargetConversions;
	@Enumerated
	private RetargetValueList retargetCategorys;
	//排除的访客找回转化id，访客找回目标value
	@Enumerated
	private ConversionIdList excludeConversions;
	@Enumerated
	private RetargetValueList excludeCategorys;
	
	//人群分类定向
	@Enumerated
	private PyidCategoryList pyidCategories;
	
	//人群关注度
	@Enumerated
	private AudienceWeight audienceWeight;
	
	//Cookie定向
	@Enumerated
	private PyidList pyids;

	//地域定向
	@Enumerated
	private StrategyAreaList areas;
	
	//地域定向
	@Enumerated
	private StrategyAreaList areasExclude;
	
	//时段定向
	private String dayParting;
	
	//平台定向
	@Enumerated
	private PlatformList exchanges;
	
	//广告位位置列表
	@Enumerated
	private SimulateMap adLocations;
	
	//频道定向
	@Enumerated
	private SimulateMap adChannels;
	
	//操作系统类型、操作系统及版本信息、移动网络、移动设备、移动设备品牌、App类型定向
	@Enumerated
	private MobileAttribute mobileAttr;
	
	private OSTypeList osTypes;
	
	private OSVersionMap osVersions;
	
	//浏览器定向
	@Enumerated
	private BrowsersList browsers;
	
	//黑白名单
	private StrategyBlackWhiteUrl blackWhiteUrl;
	
	//优质媒体定向
//	private Long globalUrlId;
//	
//	public boolean isHighQualityMedia(){
//		if(this.globalUrlId != null && this.globalUrlId > 0){
//			return true;
//		}
//		return false;
//	} 	
//	public void setHighQualityMedia(boolean highQualityMedia){
//		if(highQualityMedia){
//			this.globalUrlId = 1L;
//		}else {
//			this.globalUrlId = null;
//		}
//	}
	//广告形式
	@Embedded
	private AdUnitTypeList adUnitTypes;
	
	//点击找回方式
	@Enumerated
	private ClickCookieType clickCookieType;
	
	//防作弊
	private boolean antiCheating = true;
	
	//防作弊程度 高-0、中-1、低-2，不设置-NULL
	private Short antiCheatLevel = 0;
	
	//均匀投放
	@Enumerated
	private DayPacingType dayPacing;
	
	//广告位ID
	//@Enumerated
	//private AdUnitIdList adUnitIds;
	
	//广告位黑白名单
	private StrategyAdUnit adUnit;
	
	//最高出价
	private BigDecimal cpmBidPrice;
	
	//价格目标
	@Enumerated
	private ExpectedGoal expectedGoal= ExpectedGoal.Na;
	@Enumerated
	private SimulateMap expectedParams;
	
	/** 前端错误信息提示使用 **/
	private String expectedPrice;	
	
	private String expectedAmount;
	
	private String showExpectedParams;

	public String getShowExpectedParams() {
		if (expectedParams == null || expectedParams.isEmpty()) {
			return null;
		}
		showExpectedParams = "";
		for (Iterator<Map.Entry<String, String>> it = expectedParams.entrySet()
				.iterator(); it.hasNext();) {
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
			} else {
				key = key + com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Strategy.1024")/*单价:*/;
				value += com.ipinyou.base.util.I18nResourceUtil.getResource("ftl.ad.allocate_center.1007")/*元*/;
			}
			showExpectedParams += key + value + " ";
		}
		return showExpectedParams;
	}
	//效果目标
	@Enumerated
	private EffectGoal effectGoal=EffectGoal.Na;
	private BigDecimal effectGoalRate;
	
	//内部设置
	private String scriptCode;
	
	//备注
	private String remark;
	
	private String preferDeal;	
	
	private StrategyBlackWhiteApp blackWhiteApp;
	
	private StrategyClassifyUrl classifyUrl;
	
	private boolean fingerprint;
}
