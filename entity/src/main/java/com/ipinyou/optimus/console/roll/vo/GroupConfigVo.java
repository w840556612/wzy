package com.ipinyou.optimus.console.roll.vo;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ipinyou.optimus.console.roll.entity.RollCreativeGroupConfig;
import com.ipinyou.optimus.console.roll.model.LimitType;
import com.ipinyou.optimus.console.roll.model.PeriodType;
import com.ipinyou.optimus.console.roll.model.RollEventType;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class GroupConfigVo {
	
	public GroupConfigVo(){}
	
	public GroupConfigVo(RollCreativeGroupConfig config){
		this.id = config.getId();
		this.name = config.getName();
		this.limitType = config.getLimitType();
		this.limitCount = config.getLimitCount();
		this.sequence = config.getSequence();
		this.eventType = config.getEventType();
		this.eventAdvertiserId = config.getEventAdvertiserId();
		this.eventId = config.getEventId();
		this.nextGroupConfigId = config.getNextGroupConfigId();
		this.period = config.getPeriod();
		this.timeQuantum = config.getTimeQuantum();
	}
	
	@Valid
	@NotNull
	private Long id;
	
	@Valid
	@NotNull
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 64)
	private LimitType limitType;
	
	private Long limitCount;
	
	private Integer sequence;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 64)
	private RollEventType eventType;
	
	private Long eventAdvertiserId;
	
	private Long eventId;
	
	private Long nextGroupConfigId;

	@Enumerated(EnumType.STRING)
	@Column(length = 64)
	private PeriodType period;
	
	private String timeQuantum;
}
