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
public class CheatingIp extends BaseCheating {/**
	 * 
	 */
	private static final long serialVersionUID = 7284231273933402748L;

	public static enum CheatingIpType{
		/**
		 * C网IP，主要针对google adx
		 */
		Cnet,
		/**
		 * 单个ip 
		 */
		Single;
	}
	@Column(length = 50, nullable=false)
	@Enumerated(EnumType.STRING)
	private CheatingIpType type;
}
