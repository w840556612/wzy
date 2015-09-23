/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.optimus.console.model.Platform;

/**
 *  
 * 各投放平台URL实例
 * @author shewei.deng
 * 
 */
@Entity
@Table//(uniqueConstraints = { @UniqueConstraint(columnNames = { "width", "height", "platform", "url" }) })
@Data
@ToString
@EqualsAndHashCode(of = { "width", "height", "url" })
@JsonIgnoreProperties(value = { "id" })
public class ExchangeUrl implements com.ipinyou.base.entity.Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 818581857971726814L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * 平台
	 */
	@Column(nullable = false, length=50)
	@Index(name = "platform")
	@Enumerated(EnumType.STRING)
	private Platform platform;

	/**
	 * 广告主宽度
	 */
	@Index(name = "width")
	private int width;

	/**
	 * 广告主高度
	 */
	@Index(name = "height")
	private int height;

	/**
	 * url
	 */
	@Column(nullable = false, length = 500)
	private String url;


	/**
	 * 删除标示字段
	 */
	@NotNull
	@Column(nullable = false)
	@Index(name = "removed")
	private boolean removed = false;

}
