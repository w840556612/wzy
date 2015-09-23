package com.ipinyou.optimus.console.media.entity;

import java.util.Set;

import lombok.Data;

@Data
public class LabelVo {

	private Long id;
	private String name;
	private String title="";
	private String type;
	private Set<AdUnitVo> children;
	
	public LabelVo(Long id , String name) {
		this.id = id;
		this.name = name;
	}
}
