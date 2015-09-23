package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.ipinyou.base.entity.TimedEntity;


@Entity
@Data
public class CreativeTemplateLogo extends TimedEntity{
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;

	/**
	 * 文件名
	 */
	@Column(length = 200)
	private String fileName;
	
	/**
	 * 宽
	 */
	private int width;
	
	/**
	 * 高
	 */
	private int height;
	
	/**
	 * 文件后缀
	 */
	@Column(length = 40)
	private String suffix;
	
	
	/**
	 * 文件路径
	 */
	@Column(length = 200)
	private String path;
	
	
	
	/**
	 * 图片唯一摘要MD5签名
	 */
	@Column(length = 32)
	private String contentDigest;
	
	
	/**
	 * 删除标记
	 */
	private boolean removed=false;
	
}
