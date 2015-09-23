package com.ipinyou.optimus.console.extendcreative.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Creative;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper = true, exclude = { "creative" })
@EqualsAndHashCode(callSuper = true, exclude = { "creative" })
public class CreativeExtendFile extends TimedEntity {
	
	private static final long serialVersionUID = -5873549878483123819L;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "creative_id", nullable = false)
	private Creative creative;	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long creativeId;
	
	@Column(length = 50, nullable = false)
	private String fileExt=""; 
}
