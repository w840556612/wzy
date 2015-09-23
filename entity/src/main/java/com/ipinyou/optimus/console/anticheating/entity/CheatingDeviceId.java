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
public class CheatingDeviceId extends BaseCheating {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6128017314108194671L;

	
	public static enum DigestType{
		/**
		 * 原始值
		 */
		Plain,
		/**
		 * sha1摘要 
		 */
		Sha1,
		/**
		 * md5摘要
		 */
		Md5;
	}
	
	/**
	 * 摘要类型，例如Plain, Sha1, Md5
	 */
	@Column(length = 50, nullable=false)
	@Enumerated(EnumType.STRING)
	private DigestType digestType;
	
	public static enum DeviceIdType{
		Unknown,
		Idfa,
		Udid,
		AndroidId,
		DeviceId,//Imei
		Mac,
		Imsi
	}
	
	
	/**
	 * 设备id类型，例如Idfa,AndroidId...
	 */
	@Column(length = 50, nullable=false)
	@Enumerated(EnumType.STRING)
	private DeviceIdType deviceIdType;
}
