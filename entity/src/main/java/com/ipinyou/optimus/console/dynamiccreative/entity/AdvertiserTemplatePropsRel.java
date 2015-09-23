package com.ipinyou.optimus.console.dynamiccreative.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;

@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
public class AdvertiserTemplatePropsRel extends TimedEntity implements Auditable<AdvertiserTemplatePropsRel>  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2870250478230468353L;
	
	public AdvertiserTemplatePropsRel() {

	}
	
	public AdvertiserTemplatePropsRel(String name) {
		this.name = name;
	}
	
	/**
	 * 广告主id
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id",insertable = false, updatable = false)
	private Advertiser advertiser;
	private Long advertiserId;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", insertable = false, updatable = false, nullable = false)
	private TemplateGroup templateGroup;
	
	
	@OneToMany(mappedBy = "advertiserTemplatePropsRel")
	private Set<AdvertiserCreativePropsMiddle> advertiserCreativePropsMiddles;
	
	/**
	 * 母板集合id
	 */
	private Long groupId;
	
	/**
	 * 模板名称
	 */
	private String name;
	
	/**
	 * 母板状态
	 */
	@Enumerated(EnumType.STRING)
	private TemplateStatus templateStatus;
	
	public static enum TemplateStatus {
		RefuseToReview("拒审"),
		ThroughTheReview("通过"),
		InTheReview("审核中");

		private String text;

		TemplateStatus(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	@Override
	public String getEntityName() {
		return "广告主创意模板关系";
	}
	
	@Transient
	private AdvertiserTemplatePropsRel orig;
	
	/**
	 * 删除标志
	 */
	private boolean removed=false;

	
	
}
