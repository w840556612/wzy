package com.ipinyou.optimus.console.media.entity;

import lombok.Data;

@Data
public class AdUnitVo {

	private Long id;
	private String name;
	private String type;
	private String title;
	
	public AdUnitVo(Long id , String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
		
	}
}
