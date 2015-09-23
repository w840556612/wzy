package com.ipinyou.optimus.console.ad.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.Pool;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class AgencyProtectHistory extends TimedEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 保护的广告主
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	
	/**
	 * 保护的池
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "protect_pool_id", nullable = false)
	private Pool protectPool;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long protectPoolId;
	
	private String province;

	private String city;
	
	private Timestamp protectStart;

	private Timestamp protectEnd;

	private int protectTimes = 0;
	
	@Column(length = 45)
	private String protectContactName;
	
	@Column(length = 32)
	private String protectCellphone;
	
	@Column(length = 128)
	private String protectUrl;
	
}
