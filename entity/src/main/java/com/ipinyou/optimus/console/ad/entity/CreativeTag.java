/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.util.I18nResourceUtil;

/**
 * 创意标签（分类）
 * @author lijt
 *
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value={"id","version","creation","lastModified","primaryKey"})
public class CreativeTag extends TimedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4415961912379015632L;
	
	
	/**
	 * 分类一级编码
	 */
	@NotNull
	@Column(nullable = false, length = 16)
	private String code;
	
	/**
	 * 分类一级名称
	 */
	@NotNull
	@Column(nullable = false, length = 64)
	private String name;
	
	public String getName(){
		return I18nResourceUtil.getContent(CreativeTag.class,this,"name",name);
	}
	
	/**
	 * 英文名称
	 */
	@Column(length = 64)
	private String nameEn;
	
	/**
	 * 分类二级编码 
	 */
	@NotNull
	@Column(nullable = false, unique = true, length = 16)
	private String codeDetail;
	
	/**
	 * 分类二级名称
	 */
	@NotNull
	@Column(nullable = false, length = 64)
	private String nameDetail;
	
	public String getNameDetail(){
		return I18nResourceUtil.getContent(CreativeTag.class,this,"nameDetail",nameDetail);
	}
	
	/**
	 * 分类二级英文名称
	 */
	@Column(length = 64)
	private String nameDetailEn;
	
	/**
	 * 是否有效
	 */
	@NotNull
	@Column(nullable = false)
	private boolean valid = true;

}
