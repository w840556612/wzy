package com.ipinyou.optimus.console.media.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

import com.ipinyou.base.entity.BaseEntity;

/**
 * 媒介资源表
 * @author ke.gu
 * 因为只用来展示，不从这里进行录入，所以只做简单设置
 */
@Entity
@Table(name = "rpt_media_platform_flow")
@Data
@ToString(callSuper = true)
public class MediaPlatform extends BaseEntity{
	
	/*
	 * 域名
	 */
	@NotNull
	private String domain;
	
	/*
	 * 域名
	 */
	private String websiteName;
	
	/*
	 * 类型
	 */
	private String type;
	
	/*
	 * 评级
	 */
	private String content;
	
//	/*
//	 * 类型
//	 */
//	private String classify;
//	
//	/*
//	 * 评级
//	 */
//	private int score;
	
	/*
	 * 日均流量
	 */
	private Long pv;
	
	/*
	 * 支持平台
	 */
	private String platForm;
	
	
	/**
	 * 品友竞价数
	 */
	private Long pinyouStruggle;
	
	/*
	 * 品友竞得数
	 */
	private Long struggleSuccess;
	
	/**
	 * 日期
	 */
	private Date logDate;
	
}
