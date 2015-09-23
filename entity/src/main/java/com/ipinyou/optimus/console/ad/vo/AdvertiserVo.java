package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;



@Data
public class AdvertiserVo {
	private Long advertiserId;
	
	private String name;
	
	
	public AdvertiserVo(Long advertiserId,String name){
		this.advertiserId = advertiserId;
		this.name = name;
	}
}
