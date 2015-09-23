package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;



@Data
public class GeoVo {
	private Short id;
	
	private String name;
	
	
	public GeoVo(Short geoId,String name){
		this.id = geoId;
		this.name = name;
	}
}
