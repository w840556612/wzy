package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.StrategyBlackWhiteUrl.UrlList;

@Entity
@Data
@ToString(callSuper = true, exclude = {"orig","advertiser"})
@EqualsAndHashCode(callSuper = true, exclude = {"orig","advertiser"})
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","advertiser","entityName",
		"primaryKey","creation","lastModified","version","orig"}, ignoreUnknown = true)
public class GlobalBlackWhiteUrl extends TimedEntity implements Auditable<GlobalBlackWhiteUrl>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4597217879133524098L;
	
	public static enum UrlType {		
		HighQuality("optimus.console.ad.entity.GlobalBlackWhiteUrl.1001"/*优质媒体域名*/),
		General("optimus.console.ad.entity.GlobalBlackWhiteUrl.1002"/*一般媒体域名*/),
		Custom("optimus.console.ad.entity.GlobalBlackWhiteUrl.1003"/*自定义域名*/),
		Classify("optimus.console.ad.entity.GlobalBlackWhiteUrl.1004"/*媒体分类域名*/);
		
		private String text;
		
		private UrlType(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id")
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false)
	@Index(name="idx_aid")
	private Long advertiserId;
	
	
	@NotNull
	@Column(nullable = false, length = 64)
	@Index(name="name")
	private String name;
	
	
	@NotNull
	@Column(nullable = false)
	private boolean isWhiteUrl = true;
	
	@NotNull
	@Column(nullable = false)
	private boolean active = true;
	
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	@NotNull
	@Column(nullable = false, length=50)
	@Enumerated(EnumType.STRING)
	private UrlType type;
	
	
	/**
	 * 全局域名Urls
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="domain_urls",length=16777215))
	private UrlList domainUrls;
	
	
	@Transient
	private GlobalBlackWhiteUrl orig;


	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.GlobalBlackWhiteUrl.1005")/*黑白名单*/;
	}

}
