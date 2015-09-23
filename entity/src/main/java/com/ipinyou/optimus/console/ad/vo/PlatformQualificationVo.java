package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author kg.gu
 * 
 */
@Data
@ToString(callSuper = true)
public class PlatformQualificationVo {

	private String platform;
	private String qualification;
	private String advertiserAuditName;
	private String tanxCustom;
	private String advertiserType;
	
	public PlatformQualificationVo(String platform, String qualification){
		this.platform = platform;
		this.qualification = qualification;
	}
	
	public PlatformQualificationVo(){}
	
	
	@Override
	public String toString(){
		return this.platform+"#"+this.qualification;
	}
	
}
