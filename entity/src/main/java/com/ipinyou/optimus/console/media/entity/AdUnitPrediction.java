package com.ipinyou.optimus.console.media.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.NoIdTimedEntity;
import com.sun.istack.Nullable;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = { "version", "lastModified" ,"adunit"})
public class AdUnitPrediction extends NoIdTimedEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2300078049564878222L;

	@Id
	protected Long id;
	
	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private AdUnit adunit;
	
	/**
	 * 曝光量
	 */
	@Column(nullable = true)
	private Long exposure;
	
	/**
	 * 点击率
	 */
	@Column(nullable = true)
	private Double clickRatio;
	
}
