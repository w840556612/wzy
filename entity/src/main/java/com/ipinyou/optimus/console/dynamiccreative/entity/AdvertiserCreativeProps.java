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
 * 广告主动态创意属性
 * @author lijt
 *
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "advertiser", "template","advertiserTemplatePropsRel" })
@ToString(callSuper = true, exclude = { "orig", "advertiser", "template" })
public class AdvertiserCreativeProps extends TimedEntity implements Auditable<AdvertiserCreativeProps> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2870250478230468353L;
	
	/**
	 * 所属广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@NotNull
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;

	/**
	 * 动态创意模版
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "template_id", nullable = true)
	private CreativeTemplate template;
	
	@NotNull
	@Column(insertable = false, updatable = false, nullable = true)
	private Long templateId;
	
	/**
	 * 配置name
	 */
	@NotNull
	@Size(min = 1, max = 64)
	@Column(length = 64, nullable = false)
	private String name;
	
	/**
	 * 配置value
	 */
	@Size(min = 1, max = 2000)
	@Column(length = 2000)
	private String value;
	

	@Transient
	private AdvertiserCreativeProps orig;

	@Override
	public String getEntityName() {
		return "广告主动态创意属性";
	}
	
	/**
	 * 广告主模板关系表id
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "adv_templ_rel_id", insertable = false, updatable = false, nullable = false)
	private AdvertiserTemplatePropsRel advertiserTemplatePropsRel;
	private Long advTemplRelId=0L;
	
	private Long logoId;
	
	/**
	 * 删除标志
	 */
	private boolean removed=false;
/*
	@Override
	public String getName() {
		return String.valueOf(advertiserId)+"-"+templateId+": "+name;
	}*/
}
