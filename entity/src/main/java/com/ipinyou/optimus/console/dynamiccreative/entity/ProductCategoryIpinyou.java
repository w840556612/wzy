package com.ipinyou.optimus.console.dynamiccreative.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.dynamiccreative.entity.SensitiveKeyword.ForbidPlatforms;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "forbidPlatforms","ipinyouCategoryType","orig","version","creation","lastModified"})
public class ProductCategoryIpinyou extends TimedEntity implements Auditable<ProductCategoryIpinyou> {

	private static final long serialVersionUID = 1L;
	
	@Column(length=512)
	private String categoryId;
	@Column(length=512)
	private String categoryName;
	/**
	 * 禁投平台
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "forbid_platforms", length = 1000))
	private ForbidPlatforms forbidPlatforms;
	
	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private IpinyouCategoryType ipinyouCategoryType = IpinyouCategoryType.manualConfig;
	
	@Override
	public String getEntityName() {
		return "品友分类";
	}
	@Override
	public String getName() {
		return categoryName;
	}
	@Override
	public ProductCategoryIpinyou getOrig() {
		return orig;
	}
	@Override
	public void setOrig(ProductCategoryIpinyou t) {
		this.orig = t;
	}
	@Transient
	private ProductCategoryIpinyou orig;
	
	
	public static enum IpinyouCategoryType {
		defaultPass("默认分类通过"), defaultNotPass("默认分类不通过"), manualConfig("手工配置"), layBy("搁置");
		private String text;

		private IpinyouCategoryType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
}
