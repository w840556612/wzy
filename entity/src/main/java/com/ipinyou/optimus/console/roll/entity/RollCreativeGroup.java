package com.ipinyou.optimus.console.roll.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import com.ipinyou.base.entity.listener.PinyinListener;
import com.ipinyou.optimus.console.ad.entity.Creative;

/**
 * 创意轮播-创意分组下创意
 * 
 * @author baozhu.cao
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig"})
@EqualsAndHashCode(callSuper = true, exclude = { "orig"})
@JsonIgnoreProperties(value = { "version", "lastModified"})
@EntityListeners({ PinyinListener.class })
public class RollCreativeGroup extends TimedEntity implements Auditable<RollCreativeGroup>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.roll.entity.RollCreativeGroup")/*创意分组下创意*/;
	}
	
	@NotNull
	private boolean removed = false;
	
	@Override
	public String getName() {
		return "CreativeGroupConfig:" + groupConfig.getName() + "-Creative:" + creative.getName();
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "group_config_id")
	private RollCreativeGroupConfig groupConfig;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long groupConfigId;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "creative_id")
	private Creative creative;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long creativeId;
	
	@Transient
	private RollCreativeGroup orig; 
}
