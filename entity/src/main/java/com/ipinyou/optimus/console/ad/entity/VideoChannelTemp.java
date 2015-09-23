/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

import org.hibernate.annotations.Index;

@Entity
@Data
public class VideoChannelTemp implements com.ipinyou.base.entity.Entity {

	private static final long serialVersionUID = -5611760045444004242L;

	/**
	 * 主键
	 */
	@Id
	@Index(name="id")
	private Long id;

	private String pyChannel;
	
	private String platform;
	
	private String platformChannel;
	
	

}
