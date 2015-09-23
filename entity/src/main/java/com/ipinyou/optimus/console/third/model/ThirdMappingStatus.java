package com.ipinyou.optimus.console.third.model;

public enum ThirdMappingStatus {

	NOT_MAP("未映射"), MAPPING("映射"), MAPPED("映射完成"), HARVEST("收获");
	
	private ThirdMappingStatus(String text){
		this.text = text;
	}
	
	private String text;
	
	public String getText() {
		return text;
	}
}
