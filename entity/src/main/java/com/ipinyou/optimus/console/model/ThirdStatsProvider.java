package com.ipinyou.optimus.console.model;

/**
 * 第三方监测提供商
 */
public enum ThirdStatsProvider {
	
	Miaozhen("秒针"), 
	Admaster("AdMaster");

	private ThirdStatsProvider(String text) {
		this.text = text;
	}

	private String text;

	public String getText() {
		return text;
	}
}
