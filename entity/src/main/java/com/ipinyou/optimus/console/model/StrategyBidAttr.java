package com.ipinyou.optimus.console.model;

public enum StrategyBidAttr {
	cpmBidPrice("最高出价"),
	expectedGoal("价格目标"),
	effectGoal("效果目标"),
	scriptCode("内部设置"),
	remarks("备注");
	
	private StrategyBidAttr(String text){
		this.text = text;
	}
	private String text;
	
	public String getText() {
		return text;
	}
}
