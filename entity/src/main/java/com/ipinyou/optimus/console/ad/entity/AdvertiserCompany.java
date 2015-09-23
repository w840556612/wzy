/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.constant.Regex;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineMap;

/**
 * 广告主
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig",})
@EqualsAndHashCode(callSuper = true, exclude = { "orig",})
@JsonIgnoreProperties(value = { "version", "lastModified"})
public class AdvertiserCompany extends TimedEntity implements Auditable<AdvertiserCompany> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434403672512559697L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	@Transient
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.AdvertiserCompany.1001")/*广告主所属公司*/;
	}
	
	@Override
	@Transient
	public String getName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.AdvertiserCompany.1002")/*AdvertiserCompany：*/+registerName;
	}
	
	/**
	 * 广告主公司注册名称，Express版等于name；Full版页面填入
	 */
	@Size(min = 2, max = 64)
	@Column(nullable = false, unique = true, length = 64)
	private String registerName;

//	@Enumerated(EnumType.STRING)
//	@Column(nullable = true, length = 50)
//	private Vertical vertical;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true,length=50)
	private Advertiser.AdvertiserType type;
	
	/**
	 * 广告主网址
	 */
	@NotNull
	@Size(min = 10, max = 128)
	@Pattern(regexp = Regex.URL)
	@Column(nullable = false, length = 128)
	private String website = "http://";

//	
//	/**
//	 * 身份证、广告主企业营业执照复印件文件名
//	 */
//	@Column(length = 64)
//	private String licenseFilename;
//
//	/**
//	 * 企业法人身份证复印件文件名
//	 */
//	@Column(length = 64)
//	private String legalPersonIdFilename;
//	
//	/**
//	 * ICP证复印件文件名
//	 */
//	@Column(length = 64)
//	private String icpFilename;	
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "vertical_tag_id")
	private AdvertiserVerticalTag verticalTag;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long verticalTagId;
	
	/**
	 * 组织机构代码证号
	 * */
	@Size(max = 128)
	@Column(length = 128)
	private String orgCodeNo;
	
	/**
	 * 到达是否需要匹配URL
	 */
	private boolean reachMatchUrl;
	
	/**
	 * 是否收集了网站访客信息
	 */
	private boolean collectVisit;
	
	/**
	 * 是否收集了网站订单信息
	 */
	private boolean collectOrder;
	
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "noselect_platform_reasons", length = 5000))
	private NoselectResasonSimulateMap noselectPlatformReasons;
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class NoselectResasonSimulateMap extends UserDefineMap<String, String> {
		private static final long serialVersionUID = -5873549878483123819L;

		public NoselectResasonSimulateMap() {
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
	
	@Transient
	private AdvertiserCompany orig;
}
