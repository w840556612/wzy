/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


/**
 * @author lijt
 * 
 */
@Entity
@Data
public class TopDomain implements com.ipinyou.base.entity.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2387567696497911797L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 128, unique=true)
	private String domain;

	private Short categoryId;

	private boolean removed;

}
