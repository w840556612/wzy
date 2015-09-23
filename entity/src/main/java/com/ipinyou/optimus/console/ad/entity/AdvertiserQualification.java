package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;


@Entity
@Data
@ToString(callSuper = true, exclude={"orig","advertiser"})
@EqualsAndHashCode(callSuper = true, exclude={"orig","advertiser"})
@JsonIgnoreProperties(value = { "advertiser", "orig", "version", "creation", "lastModified", "entityName", "primaryKey" })
public class AdvertiserQualification extends TimedEntity implements	Auditable<AdvertiserQualification> {

	private static final long serialVersionUID = 4111641380249730016L;
	
	
	@Column(length = 64)
	private String name;
	
	@NotNull
	@Column(length = 64, nullable = false)
	private String fileName;
		
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name="advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;	
	
	
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)	
	private QualificationType type;
	

	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	public static enum QualificationType{		
		ICP("optimus.console.ad.entity.AdvertiserQualification.1001"/*ICP证*/),
		License("optimus.console.ad.entity.AdvertiserQualification.1002"/*企业营业执照*/),
		LegalId("optimus.console.ad.entity.AdvertiserQualification.1003"/*法人代表身份证*/),
		Product("optimus.console.ad.entity.AdvertiserQualification.1004"/*创意资质*/),
		OrgCode("optimus.console.ad.entity.AdvertiserQualification.1005"/*组织机构代码证*/),
		Other("optimus.console.ad.entity.AdvertiserQualification.1006"/*其他广告主资质*/);
		
		private String text;		
				
		private QualificationType(String text){
			this.text = text;
		}
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}		
	}
	
	@Transient
	private AdvertiserQualification orig;
	
	@Transient
	private int width;
	
	@Transient
	private int height;
	
	@Transient
	private int dimWidth;
	
	@Transient
	private int dimHeight;

	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.AdvertiserQualification.1007")/*广告主资质*/;
	}
}
