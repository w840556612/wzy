/**
 * 
 */
package com.ipinyou.optimus.console.dynamiccreative.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;

/**
 * 热销商品
 * 
 * @author lijt
 * 
 */
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "product_no",
		"advertiser_id", "category_id" }) })
@Entity
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class HotProduct extends TimedEntity implements Auditable<ProductInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8329950531485683873L;
	/**
	 * 所属广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	/**
	 * 商品编号
	 */
	@NotNull
	@Size(min = 1, max = 64)
	@Index(name = "productNo")
	@Column(length = 64, nullable = false)
	private String productNo;

	/**
	 * 在电商网站中的类别id,若本商品同属多个类别，则记录多个类别id，用逗号分隔
	 */
	@Size(max = 64)
	@Column(length = 64)
	private String categoryId;

	/**
	 * 权重
	 */
	private long weight;
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	@Override
	public String getEntityName() {
		return "热销商品";
	}

	@Transient
	private ProductInfo orig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Auditable#getName()
	 */
	@Override
	public String getName() {
		return productNo;
	}
}
