package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 曝光对搜索影响分析
 * 
 * @author dengsw
 */
@Entity
@Table(name = "rpt_imp_search_affect")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = { "version", "creation", "lastModified", "primaryKey" })
public class ImpressionSearchAffect extends TimedEntity {

	private static final long serialVersionUID = 8229289441908767762L;

	@NotNull
	private Long advertiserCompanyId;

	@NotNull
	private Date day;

	@NotNull
	private long pinyouUv;

	@NotNull
	private long searchUv;

	@NotNull
	private long pinyouPv;

	@NotNull
	private long searchPv;

	@NotNull
	private boolean removed = false;

}
