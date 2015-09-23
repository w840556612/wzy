package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class AdvertiserLogo extends TimedEntity implements	Auditable<AdvertiserLogo> {

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
	
	@Transient
	private AdvertiserLogo orig;
	
	private int width;
	
	private int height;
	
	@Transient
	private int dimWidth;
	
	@Transient
	private int dimHeight;
	
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.AdvertiserLogo.1001")/*广告主Logo*/;
	}
}
