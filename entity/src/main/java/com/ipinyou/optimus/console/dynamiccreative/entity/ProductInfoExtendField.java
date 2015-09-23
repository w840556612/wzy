package com.ipinyou.optimus.console.dynamiccreative.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;

/**
 * 商品信息扩展字段
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class ProductInfoExtendField extends TimedEntity implements Auditable<ProductInfoExtendField> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873549878483123819L;

	/**
	 * 所属广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;

	@Override
	public String getEntityName() {
		return "商品信息扩展字段";
	}

	/**
	 * 字段名称 
	 */
	@Size(max = 64)
	@Column(length = 64, nullable=false)
	private String name;

	@Transient
	private ProductInfoExtendField orig;

}
