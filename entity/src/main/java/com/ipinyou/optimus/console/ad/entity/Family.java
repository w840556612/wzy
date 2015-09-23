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
import com.ipinyou.base.entity.BaseEntity;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 族群（用户自定义自己的人群属性打包）
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = {"orig"})
@EqualsAndHashCode(callSuper = true, exclude = {"orig"})
public class Family extends BaseEntity implements Auditable<Family> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2279110251775422272L;

	/* (non-Javadoc)
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.Family.1001")/*用户人群属性*/;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long userId;
	
	@Column(nullable = false, length = 64)
	private String name;
	
	
	@Column(nullable = false, length = 2000)
	private String audienceProperty;

	
	@Transient
	private Family orig;
	
}
