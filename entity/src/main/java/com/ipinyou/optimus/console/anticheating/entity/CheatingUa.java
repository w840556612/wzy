package com.ipinyou.optimus.console.anticheating.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CheatingUa extends BaseCheating {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8503849237771300413L;
	

	public static enum CheatingUaType{
		/**
		 * 正则表达式
		 */
		Regex,
		/**
		 * 精确相等
		 */
		Equal;
	}
	@Column(length = 50, nullable=false)
	@Enumerated(EnumType.STRING)
	private CheatingUaType type;
}
