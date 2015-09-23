/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.util.I18nResourceUtil;

/**
 * 广告主行业
 * @author caobaozhu
 *
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value={"version","creation","lastModified","primaryKey"})
public class AdvertiserVerticalTag extends TimedEntity {

	private static final long serialVersionUID = 4415961912379015632L;
	
	public AdvertiserVerticalTag(){
		super();
	}
	
	public AdvertiserVerticalTag(Long id,String nameDetail){
		this.id=id;
		this.nameDetail=nameDetail;
	}
	
	
	public AdvertiserVerticalTag(Long id,String nameDetail,String nameDetailEn){
		this.id=id;
		this.nameDetail=nameDetail;
		this.nameDetailEn=nameDetailEn;
	}
	
	
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
		return I18nResourceUtil.getContent(AdvertiserVerticalTag.class,this,"name",name);
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
	
	private String nameDetailEn;
	
	public String getNameDetail() {
		return I18nResourceUtil.getContent(AdvertiserVerticalTag.class,this,"nameDetail",nameDetail);
	}
	
	/**
	 * 行业人群推荐表达式
	 */
	@Column(length = 500)
	private String commendedAudiences;
	
	/**
	 * 是否有效
	 */
	@NotNull
	@Column(nullable = false)
	@Getter
	private boolean valid = true;

}
