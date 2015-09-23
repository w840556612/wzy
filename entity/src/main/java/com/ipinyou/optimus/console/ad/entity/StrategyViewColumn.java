package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.TimedEntity;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"creation", "lastModified"})
public class StrategyViewColumn extends TimedEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2724463593148446402L;


	@NotNull
	@Index(name="userId")
	@Column(nullable = false, unique = true)
	private Long userId;
	
	
	@NotNull
	@Column(length = 500)
	private String columns;	

}
