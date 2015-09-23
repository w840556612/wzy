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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;

@Data
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"advertiser_id", "category_id"})})
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class ProductCategory extends TimedEntity implements Auditable<ProductCategory> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 所属广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	
	@Column(length=512)
	private String categoryId;
	
	@Column(length=512)
	private String categoryName;
	
	/**
	 * 所属品友分类
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "py_category_id", nullable = true)
	private ProductCategoryIpinyou ipyCategory;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long pyCategoryId;
	
	private boolean changed = false;
	
	private boolean needSegment;
	
	@Override
	public String getEntityName() {
		return "客户分类";
	}
	@Override
	public String getName() {
		return categoryName;
	}
	@Override
	public ProductCategory getOrig() {
		return orig;
	}
	@Override
	public void setOrig(ProductCategory t) {
		this.orig = t;
	}
	@Transient
	private ProductCategory orig;
}
