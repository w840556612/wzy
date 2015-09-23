package com.ipinyou.optimus.console.media.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Index;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineList;
import com.ipinyou.base.entity.listener.PinyinListener;
import com.ipinyou.optimus.console.ad.entity.AdSize;
import com.ipinyou.optimus.console.gm.entity.AdUnitSchedule;
import com.ipinyou.optimus.console.model.AdLocation;

/**
 * 媒体管理-广告位表
 * 
 * @author guodong.zhang
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig","prediction" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig","prediction" })
@JsonIgnoreProperties(value = { "version", "lastModified", "creator","prediction"  })
@EntityListeners({ PinyinListener.class })
public class AdUnit extends TimedEntity implements Auditable<AdUnit> {

	private static final long serialVersionUID = 5018057196704427919L;
	
	/**
	 * 加密后的广告位ID，加密方法see @AdUnitController.encodeAdUnitId
	 */
	@Transient
	private String idEncoded;
	
	/**
	 * 名称
	 */
	@Size(min = 2, max = 100)
	@Column(length = 100)
	@NotNull
	@Index(name = "name")
	private String name;
	
	/**
	 * 在一些场景下，多个广告位需要合并为一个广告位显示，比如CPD报表中。
	 * merge_name存储的就是合并为什么名字。
	 */
	@Size(min = 0, max = 100)
	@Column(length = 100)
	private String mergeName;
	/**
	 * 在gm排期导入时，通过这个名字去跟数据库中的广告位id进行映射关联。
	 * schedule_name存储是gm排期导入的名字
	 */
	@Size(min = 0, max = 100)
	@Column(length = 100)
	private String scheduleName;
	/**
	 * 名称
	 * 名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 1000)
	private String namePinyin;
	
	/**
	 * 所属栏目ID
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long labelId;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "label_id", nullable = false)
	private Label label;

	/**
	 * 尺寸ID
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long adSizeId;

	/**
	 * 尺寸
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ad_size_id", nullable = false)
	private AdSize adSize;

	/**
	 * 展现类型（固定、浮起）
	 */
	public static enum DisplayType {
		Fixed("固定"), Pop("浮起");
		// Float("漂浮"), Background("背投");

		private String text;

		private DisplayType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	@NotNull
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private DisplayType displayType;

	/**
	 * 展现页面（首页、内文页、首页和内文页）
	 */
	public static enum DisplayPage {
		Home("首页"), Content("内文页"), HomeAndContent("首页和内文页");

		private String text;

		private DisplayPage(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	@NotNull
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private DisplayPage displayPage;

	/**
	 * 广告位位置
	 */
	@NotNull
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private AdLocation adLocation;

	/**
	 * 开启打底广告
	 */
	@NotNull
	@Column(nullable = false)
	private boolean openDefaultAd = false;

	/**
	 * 开启/关闭
	 */
	@NotNull
	@Column(nullable = false)
	private boolean active = true;

	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	/**
	 * 是否开启预测点击率优化
	 */
	private boolean optimizeFlag = true;
	
	/**
	 * 是否视频广告位
	 * add by wujun 2013-10-17
	 */
	private boolean videoType = false;
	
	
	/**
	 * 是否开启区域价格
	 */
	private boolean openAreaPrice = false;
	
	/**
	 * 视频广告位网站类型
	 */
	@Column(length = 64)
	@Enumerated(EnumType.STRING)
	private VideoSite videoSite;
	
	
	public static enum VideoSite {
		//fengxing("风行网"), baofeng("暴风网"), 
		other("其他","other"),m1905("电影网","other"),ku6("ku6网","other"),w56("56网","flash"),baomihua("爆米花网","flash"),v1("第一视频","other"), boosj("播视网","other"),
		letv("乐视","flash"),iqiyiVast("爱奇艺VAST","VAST"),ifengVast("凤凰视频VAST","VAST"),letvRTB("乐视RTB", "other"),
		youkuVast("优酷VAST","VAST"),pptvVast("PPTV VAST","VAST"),funadxvast("风行VAST","VAST");
		

		private String text;
		
		private String codeType;

		private VideoSite(String text,String codeType) {
			this.text = text;
			this.codeType = codeType;
		}
		
		public String getCodeType(){
			return codeType;
		}

		public String getText() {
			return text;
		}
	}
	
	/**
	 * 广告位代码类型
	 */
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private CodeType codeType = CodeType.other;
	
	public static enum CodeType {
		other("other"),flash("flash"),VAST("VAST");
		
		private String text;
		
		private CodeType(String text){
			this.text = text;
		}
	}
	
	/**
	 * 广告位优化方式
	 */
	@Column(length = 64)
	@Enumerated(EnumType.STRING)
	private OptimizeGoal optimizeGoal;
	
	public static enum OptimizeGoal {
		CTR("CTR最大化"),Profit("收益最大化"),KPI("效果最大化"),None("不优化");

		private String text;

		private OptimizeGoal(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	/**
	 * 出价方式
	 */
	@Column(length = 64)
	@Enumerated(EnumType.STRING)
	private ValuationType valuationType;
	
	public static enum ValuationType {
		CPM("CPM"),CPD("CPD");

		private String text;

		private ValuationType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	/**
	 * 默认出价，如果是CPD方式，则为买断价格
	 */
	private BigDecimal defaultPrice;
	
	@Column(length = 128)
	private String externalId;
	
	/**
	 * 广告位曝光点击预测CPM
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private AdUnitPrediction prediction;
	
	/**
	 * 今日头条 的广告类型增加广告位能支持的创意类型,多个以,号分割，为空表示都支持或者不受限制
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "supported_creative_types", length = 255))
	private CreativeTypeList supportedCreativeTypes;
	
	@Transient
	private AdUnit orig;	

	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class CreativeTypeList extends UserDefineList<String> {
		private static final long serialVersionUID = 202378160443307212L;
	
		public CreativeTypeList() {
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
	
	@Override
	@Transient
	public String getEntityName() {
		return "广告位";
	}
	
	
	//投放类型
	@NotNull
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private AdUnitType adUnitType;

	/*
	 * 投放类型
	 */
	public static enum AdUnitType {
		NORMAL("普通投放"),RTB("RTB"),SIMLLARRTB("类RTB"),PREFERDEAL("Prefer Deal");

		private String text;

		private AdUnitType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	/*
	 * prefer deal id
	 */
	@Column(length = 64)
	private String preferDealId;
	
	//视频时长
	private int videoLength;
	
	//投放类型
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private AdUnitPlatform adUnitPlatform=AdUnitPlatform.Na;
	
	
	/*
	 * 支持Prefer Deal的平台
	 */
	public static enum AdUnitPlatform {
		Na("未设置", false,"other"),Sina("新浪PC", true,"other"),//SinaWap("手机新浪网",true),SinaApp("新浪APP"), 
		AutoHome("汽车之家",false,"other"),TouTiao("今日头条",false,"other"),
		Youku("优酷", true,"other"),Xunlei("迅雷",true,"VAST"),Tencent("腾讯", false,"VAST"),TencentPD("腾讯PreferDeal",true,"other"),
		BaoFeng("暴风",true,"VAST"),Iqiyi("爱奇艺",true,"other"), Sohu("搜狐",true,"other"),
		Ifeng("凤凰",false,"other"),PCAuto("太平洋汽车",false,"other"),Adx("谷歌",true,"other"),AdView("adView",true,"other"),Funadx("风行",true,"other"),
		_163("网易",false,"other"),BitAuto("易车网",false,"other"), Letv("乐视", true, "other"),Baidu("百度PreferDeal",true,"other");
		private String text;

		/**
		 * 是否需要生成审核记录
		 * */
		private boolean audit;
		
		/**
		 * 代码下载类型
		 */
		private String codeType;
		
		private AdUnitPlatform(String text, boolean audit,String codeType) {
			this.text = text;
			this.audit = audit;
			this.codeType = codeType;
		}

		public String getText() {
			return text;
		}
		
		public String getCodeType() {
			return codeType;
		}
	}
	
	@Column(length = 50)
	private String utmSource;
	
	/*
	 * 备注，目前用于初步的排期记录，但是没有任何校验
	 */
	@Column(length = 3072)
	private String remark;
	
	
	@OneToMany(mappedBy="adUnit", fetch = FetchType.LAZY)
	private Set<AdUnitSchedule> adUnitSchedule = new HashSet<AdUnitSchedule>();
	
	/**
	 * 排期小时，从scheduleHour:scheduleMinute开始投放至次日此时
	 */
	private Integer scheduleHour;
	/**
	 * 排期分钟，从scheduleHour:scheduleMinute开始投放至次日此时
	 */
	private Integer scheduleMinute;
	
	/**
	 * 外部广告位ID是否重复
	 */
	private boolean repeatable;

}
