/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 创意单元（多帧广告和多个点击地址的创意使用该模型）
 * @author lijt
 *
 */
@Entity
@Data
@ToString(callSuper = true, exclude = {"orig"})
@EqualsAndHashCode(callSuper = true, exclude = {"orig"})
public class CreativeUnit extends TimedEntity implements Auditable<CreativeUnit> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7883565242365668211L;

	/* (non-Javadoc)
	 * @se e com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.CreativeUnit.1001")/*创意单元*/;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "creative_id", nullable = false)
	private Creative creative;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long creativeId;
	/**
	 * 创意点击地址
	 */
	@Column(length = 2083)
	private String clickUrl;
	
	/**
	 * 创意到达地址
	 */
	@Column(nullable = false, length = 2083)
	private String targetUrl;

	/**
	 * 影子创意单元
	 */
	private Long mappingId;
	
	@Override
	public String getName() {
		return getPrimaryKey();
	}
	
	@Transient
	private CreativeUnit orig;

	
}
