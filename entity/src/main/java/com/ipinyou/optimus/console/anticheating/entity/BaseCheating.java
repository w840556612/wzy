package com.ipinyou.optimus.console.anticheating.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ipinyou.base.entity.TimedEntity;

@Data
@MappedSuperclass()
@EqualsAndHashCode(callSuper=true)
public class BaseCheating extends TimedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4042011911183968483L;

	private boolean removed;

	/**
	 * domain、ip、ua、app id或者pyid
	 */
	@Index(name="content")
	@Column(length = 255, nullable = false)
	private String content;

	/**
	 * 原因
	 */
	@Column(length = 255)
	private String reason;
//	public static enum CheatingLevel {
//		/**
//		 * 轻度嫌疑
//		 */
//		LowSuspicion,
//		/**
//		 * 中度嫌疑
//		 */
//		MediumSuspicion, 
//		/**
//		 * 高度嫌疑
//		 */
//		HighSuspicion, 
//		/**
//		 * 人工确认
//		 */
//		ManualConfirm;
//	}
	
	/**
	 * 作弊嫌疑级别, 1表示轻度嫌疑，2表示中度嫌疑，3表示高度嫌疑 9表示人工确认
	 */
	private int level;
	
	/**
	 * 作弊系数
	 */
	private Double factor;
}
