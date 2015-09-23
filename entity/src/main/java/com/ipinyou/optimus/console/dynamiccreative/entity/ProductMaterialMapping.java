package com.ipinyou.optimus.console.dynamiccreative.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;

/**
 * 商品素材映射
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class ProductMaterialMapping extends TimedEntity implements Auditable<ProductMaterialMapping> {

	/***
	 * 
	 */
	private static final long serialVersionUID = -5873549878483123819L;

	/**
	 * 商品字段
	 */
	@NotNull
	@Size(min = 1, max = 64)
	@Column(length = 64, nullable = false)
	private String field;
	
	/**
	 * 所属广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;

	/**
	 * 是否为扩展字段
	 */
	private boolean extend;

	/**
	 * 对应模板元素
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "element_id", nullable = true)
	private TemplateElement element;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long elementId;

	/**
	 * 到达地址映射
	 */
//	private boolean targetUrlMapping;
	@Column(length = 64)
	private String targetUrlField;

	@Override
	public String getEntityName() {
		return "商品素材映射";
	}

	@Override
	public String getName() {
		return getEntityName() + getId();
	}

	@Transient
	private ProductMaterialMapping orig;

}
