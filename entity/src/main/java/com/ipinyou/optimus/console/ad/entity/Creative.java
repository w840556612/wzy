package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;
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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineMap;
import com.ipinyou.optimus.console.ad.entity.Strategy.PlatformList;
import com.ipinyou.optimus.console.dynamiccreative.entity.AdvertiserTemplatePropsRel;
import com.ipinyou.optimus.console.dynamiccreative.entity.CreativeTemplate;
import com.ipinyou.optimus.console.extendcreative.entity.CreativeExtend;

/**
 * 创意（旧称：物料）
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer",
		"advertiser", "advertiserId", "creativeTemplate", "creativeTemplateId",
		"extend", "extendId", "audienceCategory", "orig", "version",
		"primaryKey", "creativeCategoryRel", "creativeKeyword" ,"advertiserTemplatePropsRel"}, ignoreUnknown = true)
@ToString(callSuper = true, exclude = { "orig", "creativeCategoryRel",
		"creativeKeyword" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "creativeCategoryRel",
		"creativeKeyword", "advertiserTemplatePropsRel" })
public class Creative extends TimedEntity implements Auditable<Creative> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873549878483123819L;

	public Creative() {

	}

	public Creative(Long id, String theme, int width, int height,
			CreativeType type, String path) {
		this.id = id;
		this.theme = theme;
		this.width = width;
		this.height = height;
		this.type = type;
		this.path = path;
	}

	public static enum CreativeType {
		Unknown("未知"), Picture("图片"), Flash("Flash"), Video("视频"), TextLink(
				"文字链"), Javascript("JS型创意"), HtmlSnippet("页面代码片段"), InteractiveFlash(
				"交互式flash"), StaticSingleLinkPage("静态单链接页面"), StaticMultipleLinkPage(
				"静态多链接页面"), DynamicSingleLinkPage("动态单链接页面"), DynamicMultipleLinkPage(
				"动态多链接页面"), ProductRetargettingSingleLinkPage("单链接单品访客找回"), ProductRetargettingMultipleLinkPage(
				"动态创意"), Stats("第三方监测创意"), TopNews("新闻创意"), Extensible("扩展创意");

		private String text;

		private CreativeType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	public static enum ExtendType {
		General, MultiFrame, Expand, DynamicCreative // ,DynamicMultiFrame
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return "广告创意";
	}

	@Override
	public String getName() {
		return this.theme;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "creative_tag_rel", inverseJoinColumns = @JoinColumn(name = "tag_id"), joinColumns = @JoinColumn(name = "creative_id"))
	private Set<CreativeTag> tags;

	@Size(max = 64)
	@Column(length = 64)
	private String theme;

	@NotNull
	private int width;

	@NotNull
	private int height;

	/*
	 * 创意文件大小
	 */
	private Long bytes;

	/**
	 * 1-图片; 2-Flash; 3-视频
	 */
	@NotNull
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private CreativeType type;

	/**
	 * 扩展类型，例如多帧、扩展型
	 */
	@NotNull
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private ExtendType extendType;

	@NotNull
	@Column(length = 512)
	private String path;

	/**
	 * 创意缩略图片路径
	 */
	@Column(length = 512)
	private String thumbnail;

	/**
	 * 文字链/js型广告/html片段广告 内容
	 */
	@Lob
	@Column(length = 65535)
	private String content;

	/**
	 * 今日头条类型创意来源信息
	 */
	@Column(length = 64)
	private String origin;

	/**
	 * 开启／关闭
	 */
	@NotNull
	@Column(nullable = false)
	private boolean active = true;

	/**
	 * 已删除
	 */
	private boolean removed = false;

	/**
	 * 是否为多创意单元，该属性在设置创意类型时设置，不在前台展示
	 */
	private boolean multipleUnit = false;

	/**
	 * 视频物料的播放时长，单位为毫秒
	 */
	private int durationMillis = 0;

	/**
	 * 是否开启创意监播 最初只有视频，所以叫video，后来所有创意都支持，但是字段暂时不改了
	 */
	private boolean videoMonitor = false;

	/**
	 * 物料内容唯一摘要MD5签名
	 */
	@Column(length = 32)
	private String contentDigest;

	/**
	 * 影子创意
	 */
	private Long mappingId;

	/**
	 * 创意类别
	 */
	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private CreativeCategoryRel creativeCategoryRel;

	@org.hibernate.annotations.LazyToOne(org.hibernate.annotations.LazyToOneOption.NO_PROXY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private CreativeKeyword creativeKeyword;

	/**
	 * 动态创意模版信息
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "creative_template_id", nullable = true)
	private CreativeTemplate creativeTemplate;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long creativeTemplateId;

	@NotNull
	private Integer tanxAdLevel = 1;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "extend_id", nullable = true)
	private CreativeExtend extend;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long extendId;

	@Column(length = 64)
	private String mimeType;

	@Lob
	@Column(length = 65535)
	private String extendContent;

	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "black_platforms", length = 1000))
	private PlatformList blackPlatforms;

	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "black_platform_reasons", length = 5000))
	private ReasonSimulateMap blackPlatformReasons;

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class ReasonSimulateMap extends UserDefineMap<String, String> {
		private static final long serialVersionUID = -5873549878483123819L;

		public ReasonSimulateMap() {
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

	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "adview_type", length = 64))
	private ReasonSimulateMap adviewType;

	@Column(length = 24)
	private String telNo;

	@Column(length = 2056)
	private String downloadUrl;

	@Column(length = 64)
	private String appName;

	private BigDecimal appPackageSize;

	// @Embedded
	// @AttributeOverride(name = "strValue", column = @Column(name =
	// "product_ids", length = 2000))
	// private ProductIdList productIds;
	//
	// /**
	// * 动态创意带宏的点击地址
	// */
	// @Column(length = 2038)
	// private String clickUrlMacro;

	@Column(length = 1000)
	private String pathPlatform;

	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "external_audit_categories", length = 2000))
	private SplitMap externalAuditCategories;

	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class SplitMap extends UserDefineMap<String, String> {
		private static final long serialVersionUID = -5873549878483123819L;

		public SplitMap() {
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
	 * 广告主模板关联
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "adv_templ_rel_id", insertable = false, updatable = false, nullable = false)
	private AdvertiserTemplatePropsRel advertiserTemplatePropsRel;
	private Long advTemplRelId=0L;

	/**
	 * 用户模板审核状态
	 */
	private boolean templateAuditStatus = true;

	@Transient
	private Creative orig;

	@Transient
	private String size;

	@Transient
	private String templatePriviewPic;

	@Transient
	private Long showCreativeTemplateId;

	@Transient
	private String showCreativeTemplateType;

}
