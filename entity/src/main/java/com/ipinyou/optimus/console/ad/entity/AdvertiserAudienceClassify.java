package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;


/**
 * 
 * @author xiaobo.wang
 *
 */

@Data
@Entity
@ToString(callSuper = true, exclude = {"orig"})
@EqualsAndHashCode(callSuper = true, exclude = {"orig"})
public class AdvertiserAudienceClassify extends TimedEntity implements Auditable<AdvertiserAudienceClassify> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1225752055241225271L;
	
	
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "company_id", nullable = false)
	private AdvertiserCompany company;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long companyId;
	
	
	@Column(nullable = false, length = 64)
	private String name;
	
	@Column(nullable = false, length = 64)
	private String uniqueCode;
	
	
	@Column(nullable = false)
	private boolean active = true;
	
	
	@Column(nullable = false)
	private boolean removed = false;
	
	
	@Column(length = 255)
	private String remark;
	
	@Column(nullable = true)
	private Boolean tidFlag = false;

	@Column(length = 64)
	private String platform;

	@Transient
	private AdvertiserAudienceClassify orig;
	
	@Override
	@Transient
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.AdvertiserAudienceClassify.1001")/*广告主人群分类*/;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
