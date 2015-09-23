package com.ipinyou.optimus.console.roll.model;

public enum PeriodType {

	Daily("com.ipinyou.optimus.console.roll.model.PeriodType.daily"),
	Weekly("com.ipinyou.optimus.console.roll.model.PeriodType.weekly"),
	AdvertisingCycle("com.ipinyou.optimus.console.roll.model.PeriodType.advertisingCycle"),
	Custom("com.ipinyou.optimus.console.roll.model.PeriodType.custom");
	
	private PeriodType(String text){
		this.text = text;
	}
	
	private String text;
	
	public String getText() {
		return text;
	}
}
