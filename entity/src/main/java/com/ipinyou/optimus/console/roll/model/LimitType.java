package com.ipinyou.optimus.console.roll.model;

public enum LimitType {

	Impression("com.ipinyou.optimus.console.roll.model.LimitType.imp"),
	Click("com.ipinyou.optimus.console.roll.model.LimitType.click");
	
	private LimitType(String text){
		this.text = text;
	}
	
	private String text;
	
	public String getText() {
		return text;
	}
}
