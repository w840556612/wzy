package com.ipinyou.optimus.console.third.entitiy;

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

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Strategy;
import com.ipinyou.optimus.console.third.model.ThirdMappingStatus;

/**
 * @author baozhu.cao
 * 
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ThirdStrategyMapping extends TimedEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Index(name="platform")
	@NotNull
	@Column(nullable = false, length = 16)
	private String platform;
	
	@Column(nullable = false, length=32)
	@Enumerated(EnumType.STRING)
	@Index(name="status")
	private ThirdMappingStatus status = ThirdMappingStatus.NOT_MAP;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "strategy_id", nullable = false)
	private Strategy strategy;
	
	@Index(name="strategyId")
	@Column(insertable = false, updatable = false, nullable = false)
	private  Long strategyId;
	
	@Index(name="externalId")
	@Column(nullable = true, length = 64)
	private  String externalId;
	
	@Transient
	private String extendSuperId;
}
