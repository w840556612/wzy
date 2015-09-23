package com.ipinyou.optimus.console.third.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.TimedEntity;

/**
 * @author baozhu.cao
 * 
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ThirdPoolMapping extends TimedEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Index(name="platform")
	@NotNull
	@Column(nullable = false, length = 16)
	private String platform;
	
	@Index(name="poolId")
	@NotNull
	@Column
	private  Long poolId;
	
	@Index(name="externalId")
	@Column(nullable = true, length = 64)
	private  String externalId;
}
