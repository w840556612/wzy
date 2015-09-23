package com.ipinyou.optimus.console.ad.model;

public enum AgentProduct {
	IE6("IE6"),
	IE7("IE7"),
	IE8("IE8"), 
	IE9("IE9"), 
	IE10("IE10"), 
	IE11("IE11"), 
	Chrome("Chrome"), 		
	Firefox("Firefox"), 	
	Safari("Safari"), 
	Opera("Opera"), 
	Maxthon("optimus.console.ad.model.AgentProduct.1001"/*遨游*/), 
	TheWorld("TheWorld"), 
	Sogou("Sogou"), 
	Baidu("Baidu"), 
	Liebao("optimus.console.ad.model.AgentProduct.1002"/*猎豹*/), 
	QQ("QQ"), 			
	Qihu("360"), 	
	Na("optimus.console.ad.model.AgentProduct.1003"/*其它*/);		
	
	private String text;
	private AgentProduct(String text) {
		this.text = text;
	}
	public String getText() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
	}
	
}
