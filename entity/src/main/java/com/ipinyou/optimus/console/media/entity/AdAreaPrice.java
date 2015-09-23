package com.ipinyou.optimus.console.media.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.listener.PinyinListener;
import com.ipinyou.optimus.console.ad.entity.Advertiser;


@Entity
@Data
@JsonIgnoreProperties(value = { "version", "lastModified", "creator","prediction" })
@EntityListeners({ PinyinListener.class })
public class AdAreaPrice extends TimedEntity implements Auditable<AdAreaPrice> {
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "adunit_id", nullable = false)
	private AdUnit adunit;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long adunitId;

	
	/**
	 * 地区ID
	 */
	private String areaIds;
	
	
	/**
	 * 地区列表
	 */
	private String areaList;
	
	/*
	 * 区域定价
	 */
	private double price;
	
	/**
	 * 已删除
	 */
	private boolean removed = false;
	
	
	
	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "区域定价";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "AdAreaPrice";
	}

	@Override
	public AdAreaPrice getOrig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrig(AdAreaPrice t) {
		// TODO Auto-generated method stub
		
	}

}
