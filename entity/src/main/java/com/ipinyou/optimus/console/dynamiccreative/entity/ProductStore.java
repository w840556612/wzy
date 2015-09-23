package com.ipinyou.optimus.console.dynamiccreative.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.NoIdTimedEntity;

/**
 * 商品分仓
 */
@Data
@Table
@Entity
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class ProductStore extends NoIdTimedEntity implements Comparable<ProductStore> {

	private static final long serialVersionUID = 1L;

	/**
	 * 此字段已经不是主键，仅作为一自增列存在，无实际意义
	 */
	private Long id;

	/**
	 * 联合主键
	 */
	@EmbeddedId
	private ProductStorePK pk;
	
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
	 * 促销信息，仅放入一个优先级最高的促销信息
	 */
	@Column(length = 50)
	private String promotion;

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

	@Transient
	private ProductStore orig;

	/**
	 * 这个是记录商品信息更新时间戳，便于审核组排序用，只有api工程更新商品的时候才会更新此字段
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone = "GMT+8")
	@Column(nullable = true)
	private Timestamp infoChangeTime;

	@NotNull
	@Column(nullable = false)
	private boolean soldOut = false;

	@Override
	public int compareTo(ProductStore o) {
		String t1 = this.getPk().toString();
		String t2 = o.getPk().toString();
		return t2.compareTo(t1);
	}
}
