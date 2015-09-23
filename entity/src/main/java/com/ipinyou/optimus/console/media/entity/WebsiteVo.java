package com.ipinyou.optimus.console.media.entity;

import java.util.Set;

import lombok.Data;

@Data
public class WebsiteVo {

	private Long id;
	private String name;
	private String type;
	private String title="";
	private Set<LabelVo> children;
	
	public WebsiteVo(Long id , String name) {
		this.id = id;
		this.name = name;
	}
}
