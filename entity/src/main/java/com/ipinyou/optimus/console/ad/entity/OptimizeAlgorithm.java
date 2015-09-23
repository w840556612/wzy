package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.util.I18nResourceUtil;


@Entity
@Table(name = "sys_optimize_algorithm")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OptimizeAlgorithm extends TimedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2331661559969893560L;
	
	@Column(length = 64, nullable = false)
	private String name;
	
	private String nameEn;
	
	public String getName() {
		return I18nResourceUtil.getContent(this.getClass(), this, "name",name);
	}
	
	@Column(length = 64, nullable = false)
	private String code;
	
	/**
	 * 拥有能看此算法的权限列表，可以有多个，以|分割，表示有其中一个权限就能看到此算法。为空表示不用权限也能看到
	 */
	@Column(length = 128)
	private String perms;  
	
	private boolean removed = false;
	
	@Column(length = 255)
	private String remark;

	/**
	 * 需要隐藏的元素id，以|分割。例：priceGoalTR|priceGoalTR
	 */
	@Column(length = 255)
	private String hideFields;

	/**
	 * 字段名id,默认值,可修改,后台强校验每次提交都为默认值。用|分割。 例：cpmBidPrice,20.00,false,false|cpc,0.5,true,false
	 */
	@Column(length = 255)
	private String defaultValues;
}
