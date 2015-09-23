package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.TimedEntity;


@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TopDomainFlow extends TimedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8966466217467681340L;
	
	@Column(length=255, nullable=false)
	private String domain;
	
	
	private long flow;
	
	
	@Column(precision=12, scale=8, nullable = false)	
	private BigDecimal rate;
	
	
	private boolean mobile;
	
	
	private boolean removed;

}
