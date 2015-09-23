package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "rpt_effect_column")
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "entityName", "creation", "lastModified", "orig" })
public class EffectColumn extends TimedEntity implements Auditable<EffectColumn> {

	private static final long serialVersionUID = -4792173409356451507L;

	@Override
	public String getEntityName() {
		return "广告报表展示列";
	}
	
	@NotNull
	@Index(name="userId")
	@Column(nullable = false, unique = true, length = 20)
	private Long userId;

	/**
	 * imp,click
	 */
	@NotNull
	@Size(min = 1, max = 500)
	@Column(length = 500)
	private String columns;

	@Transient
	private EffectColumn orig;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
