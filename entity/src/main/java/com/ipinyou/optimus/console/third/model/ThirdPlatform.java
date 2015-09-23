package com.ipinyou.optimus.console.third.model;

public enum ThirdPlatform {

	Admaster("Admaster"),
	Youku("Youku"),
	Kubao("Kubao");
	
	private ThirdPlatform(String text){
		this.text = text;
	}
	
	private String text;
	
	public String getText() {
		return text;
	}
}
