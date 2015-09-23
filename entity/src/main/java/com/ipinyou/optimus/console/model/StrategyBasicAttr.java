package com.ipinyou.optimus.console.model;

public enum StrategyBasicAttr {
	
	beginEndDate("周期"),
	//adUnitTypes("广告形式"),
	priority("优先级别"),
	totalBudget("总预算"),
	dailyBudget("每日预算"),
	impTotalLimit("曝光总上限"),
	clickTotalLimit("点击总上限"),
	impDailyLimit("每日曝光上限"),
	clickDailyLimit("每日点击上限"),
	indvdImpLimit("单人曝光频次"),
	indvdClickLimit("单人点击频次");
	
	private StrategyBasicAttr(String text){
		this.text = text;
	}
	private String text;
	
	public String getText() {
		return text;
	}
}
