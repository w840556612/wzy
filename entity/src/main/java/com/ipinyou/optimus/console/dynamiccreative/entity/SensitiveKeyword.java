package com.ipinyou.optimus.console.dynamiccreative.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineList;
import com.ipinyou.optimus.console.dynamiccreative.entity.ProductCategoryIpinyou.IpinyouCategoryType;
import com.ipinyou.optimus.console.model.Platform;

/**
 * 敏感词分类
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class SensitiveKeyword extends TimedEntity implements Auditable<SensitiveKeyword> {

	private static final long serialVersionUID = 1L;
	/**
	 * 敏感词分类名
	 */
	@Column(length=512)
	private String categoryName;
	
	@Column(length=10240)
	private String keyword;
	/**
	 * 禁投平台
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "forbid_platforms", length = 1000))
	private ForbidPlatforms forbidPlatforms;

	
	@Override
	public String getEntityName() {
		return "敏感词分类";
	}
	@Override
	public String getName() {
		return categoryName;
	}
	@Override
	public SensitiveKeyword getOrig() {
		return orig;
	}
	@Override
	public void setOrig(SensitiveKeyword t) {
		this.orig = t;
	}
	@Transient
	private SensitiveKeyword orig;
	
	@Column(length = 16)
	@Enumerated(EnumType.STRING)
	private KeywordType keywordType = KeywordType.partPlatform;
	
	public static enum KeywordType {
		allReject("全部禁投"), partPlatform("部分平台禁投");
		private String text;

		private KeywordType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	
	/**
	 * 逗号分隔的字符串列表。用于： 禁投平台
	 */
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class ForbidPlatforms extends UserDefineList<Platform> {
		private static final long serialVersionUID = 7396579259085541839L;

		public ForbidPlatforms() {
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

		@Override
		public boolean needOdered() {
			return true;
		}

		@Override
		protected Object asObject(String strValue) {
			return Enum.valueOf(Platform.class, strValue);
		}

	}
}
