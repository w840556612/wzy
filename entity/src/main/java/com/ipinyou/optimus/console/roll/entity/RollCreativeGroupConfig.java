package com.ipinyou.optimus.console.roll.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.listener.PinyinListener;
import com.ipinyou.optimus.console.roll.model.LimitType;
import com.ipinyou.optimus.console.roll.model.PeriodType;
import com.ipinyou.optimus.console.roll.model.RollEventType;

/**
 * 创意轮播-分组属性
 * 
 * @author baozhu.cao
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig","rollRule"})
@EqualsAndHashCode(callSuper = true, exclude = { "orig","rollRule"})
@JsonIgnoreProperties(value = { "version", "lastModified","rollRule"})
@EntityListeners({ PinyinListener.class })
public class RollCreativeGroupConfig extends TimedEntity implements Auditable<RollCreativeGroupConfig>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.roll.entity.RollCreativeGroupConfig.1001")/*创意轮播-分组属性*/;
	}

	@Size(min = 2, max = 64)
	@Column(length = 64)
	@NotNull
	@Index(name = "name")
	private String name;
	
	@NotNull
	private boolean removed = false;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "roll_rule_id", nullable = false)
	private RollRule rollRule;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long rollRuleId;
	
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
	
	@Transient
	private RollCreativeGroupConfig orig; 
}
