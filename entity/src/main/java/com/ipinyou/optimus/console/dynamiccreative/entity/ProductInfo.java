package com.ipinyou.optimus.console.dynamiccreative.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.NoIdTimedEntity;

/**
 * 商品信息
 */
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "product_no",
		"advertiser_id" }) })
@Entity
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class ProductInfo extends NoIdTimedEntity implements Auditable<ProductInfo> {

	private static final long serialVersionUID = -5873549878483123819L;

	/**
	 * 此字段已经不是主键，仅作为一自增列存在，无实际意义
	 */
	private Long id;
	
	/**
	 * 联合主键
	 */
	@EmbeddedId  
	private ProductInfoPK pk;
	
	/**
	 * 商品名称
	 */
	@NotNull
	@Size(min = 1, max = 128)
	@Column(length = 128, nullable = false)
	private String name;


	/**
	 * 在电商网站中的类别
	 */
	@Size(max = 512)
	@Column(length = 512)
	private String category;

	/**
	 * 在电商网站中的类别id,若本商品同属多个类别，则记录多个类别id，用逗号分隔
	 */
	@Size(max = 512)
	@Column(length = 512)
	private String categoryId;

	/**
	 * 品牌
	 */
	@Size(max = 512)
	@Column(length = 512)
	private String brand;

	/**
	 * 在电商网站中的品牌id
	 */
	@Size(max = 512)
	@Column(length = 512)
	private String brandId;

	/**
	 * 销售价格
	 */
	@NotNull
	@DecimalMin("0")
	private BigDecimal price;

	/**
	 * 原始价格
	 */
	@DecimalMin("0")
	private BigDecimal origPrice;

	/**
	 * 商品简要描述
	 */
	@Column(length = 50)
	private String shortDesc;
	
	/**
	 * 图片比例，宽/高，必填
	 */
	private double picRatio;
	
	/**
	 * 图片中非空白区域与上边框的距离占图片高度的比例，0-1之间的小数，可选
	 */
	private Double paddingTop;
	/**
	 * 图片中非空白区域与左边框的距离占图片宽度的比例，0-1之间的小数，可选
	 */
	private Double paddingLeft;
	/**
	 * 图片中非空白区域与下边框的距离占图片高度的比例，0-1之间的小数，可选
	 */
	private Double paddingBottom;
	/**
	 * 图片中非空白区域与右边框的距离占图片宽度的比例，0-1之间的小数，可选
	 */
	private Double paddingRight;
	
	

	/**
	 * 商品图片地址
	 */
	@NotNull
	@Column(length = 512, nullable = false)
	private String picUrl01;

	/**
	 * 图片地址 (该尺寸图片可能不存在)
	 */
	@Column(length = 512)
	private String picUrl02;

	/**
	 * 图片地址 (该尺寸图片可能不存在)
	 */
	@Column(length = 512)
	private String picUrl03;

	/**
	 * 图片地址 (该尺寸图片可能不存在)
	 */
	@Column(length = 512)
	private String picUrl04;

	/**
	 * 图片地址 (该尺寸图片可能不存在)
	 */
	@Column(length = 512)
	private String picUrl05;

	/**
	 * 图片地址 (该尺寸图片可能不存在)
	 */
	@Column(length = 512)
	private String picUrl06;

	/**
	 * 商品网页链接
	 */
	@NotNull
	@Column(nullable = false, length = 512)
	private String productUrl;



	/**
	 * 点击商店logo之后的到达地址
	 */
	@Column(length = 512)
	private String logoTargetUrl;
	/**
	 * 相关商品内容
	 */
	@Column(length = 32)
	private String relativeContent;

	/**
	 * 相关商品URL
	 */
	@Column(length = 512)
	private String relativeUrl;

	/**
	 * 相关商品编号，至少3个，逗号分隔
	 */
	@Column(length = 128)
	private String relativeProductIds;
	/**
	 * 分类内容
	 */
	@Column(length = 64)
	private String categoryContent;

	/**
	 * 分类Url
	 */
	@Column(length = 512)
	private String categoryUrl;
	/**
	 * 活动内容
	 */
	@Column(length = 64)
	private String activityContent;
	/**
	 * 活动对应Url
	 */
	@Column(length = 512)
	private String activityUrl;

	/**
	 * 促销信息，仅放入一个优先级最高的促销信息
	 */
	@Column(length = 50)
	private String promotion;

	/**
	 * 扩展信息，json格式，可被反序列化为Map<String,Object>
	 */
	@Column(length = 512)
	private String extend;

	/**
	 * 开启／关闭
	 */
	@Column(nullable = false)
	private boolean active = true;

	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	@Override
	public String getEntityName() {
		return "商品信息";
	}

	/**
	 * 日平均访问次数，用于按照最多访问推荐策略
	 */
	private long dailyAvgPv = 0;

	/**
	 * 折扣率，0~1之间的一个数，例如0.5表示五折，用于按照最大折扣推荐策略
	 */
	private double discount;

	/**
	 * 权重，用于投放排序，用于综合考虑多种因素，或者自定义规则推荐策略
	 */
	private long weight;
	
	/**
	 * 禁投平台，用逗号分隔（如：Adx,Tanx）
	 */
	@Column(length = 64)
	private String forbidPlatforms;

	@Transient
	private ProductInfo orig;

	public static enum MappingTargetUrl {
		productUrl("商品链接"), relativeUrl("相关商品链接"), logoTargetUrl("Logo到达地址");

		private String text;

		private MappingTargetUrl(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	public static enum MappingField {
		logoUrl("Logo图片"), name("商品名称"), price("价格"), origPrice("商品原价"), picUrl01(
				"商品图片800x800"), picUrl02("商品图片350x350"), picUrl03("商品图片240x240"), picUrl04(
				"商品图片220x280"), picUrl05("商品图片220x220"), picUrl06("商品图片160x160"), picUrl07(
				"商品图片130x130"), picUrl08("商品图片100x100"), picUrl09("商品图片50x50"), picUrl10(
				"商品图片25x25"), categoryId("商品类别"), shortDesc("商品描述"), relativeContent(
				"相关商品"), promotion("促销信息");

		private String text;

		private MappingField(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	@Transient
	private List<String> picUrl;
	
	@Column(length = 64)
	private String offReason;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 16)
	private DescMachineAudit descMachineAudit = DescMachineAudit.Na;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 16)
	private DescManAudit descManAudit = DescManAudit.Na;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 16)
	private CategoryAudit categoryAudit = CategoryAudit.Na;
	
	public static enum DescMachineAudit {
		PartForbid("部分平台禁投"), FullForbid("全部平台禁投"), Pass("无平台禁投"), Na("未审核");

		private String text;

		private DescMachineAudit(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	public static enum DescManAudit {
		//拒绝：会更新商品全部字段，更新后仍为拒绝（不可投）
		//通过：会更新商品全部字段，更新后仍为通过（可投）
		//未审核：会更新全部字段
		//锁定：所有字段都不会更新
		
		Reject("拒绝"), Pass("通过"), Na("未审核"), AllowUpdate("允许自动更新"), Lock("锁定");

		private String text;

		private DescManAudit(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	public static enum CategoryAudit {
		PartForbid("部分平台禁投"), FullForbid("全部平台禁投"), Pass("无平台禁投"), Na("未审核"), LayBy("搁置");

		private String text;

		private CategoryAudit(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = true)
	private Timestamp descManAuditTime;
	
	/**
	 * 这个是记录商品信息更新时间戳，便于审核组排序用，只有api工程更新商品的时候才会更新此字段
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = true)
	private Timestamp infoChangeTime;
	
	/**
	 * 商品过期时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = true)
	private Timestamp expireTime;
	
	/** 商品名称分词列 */
	@Column(nullable = true)
	private String nameSegmented;
	
	
	@NotNull
	@Column(nullable = false)
	private boolean soldOut = false;
	
	private Long descManAuditUserId;
	
//	@Column(nullable = false, length = 4)
//	private CurrencyCode currencyCode = CurrencyCode.CNY;
//	
//	public static enum CurrencyCode {
//		CNY("人民币"), HKD("港币");
//
//		private String text;
//
//		private CurrencyCode(String text) {
//			this.text = text;
//		}
//
//		public String getText() {
//			return text;
//		}
//	}
	
	@Transient
	private ProductCategory productCategory;

	@Override
	public String getPrimaryKey() {
		return pk.getAdvertiserId()+":"+pk.getProductNo();
	}
}
