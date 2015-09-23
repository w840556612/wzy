package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;



@Data
public class VideoChannelVo {
	private String id;
	
	private String name;
	
	
	public VideoChannelVo(String id,String name){
		this.id = id;
		this.name = name;
	}
}
