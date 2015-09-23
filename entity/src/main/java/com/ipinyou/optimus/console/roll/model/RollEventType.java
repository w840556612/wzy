package com.ipinyou.optimus.console.roll.model;

public enum RollEventType {

	Click("com.ipinyou.optimus.console.roll.model.RollEventType.click"),
	RetargetAll("com.ipinyou.optimus.console.roll.model.RollEventType.retargetAll"),
	RetargetSingle("com.ipinyou.optimus.console.roll.model.RollEventType.retargetSingle"),
	RetargetCategory("com.ipinyou.optimus.console.roll.model.RollEventType.retargetCategory"),
	Conversion("com.ipinyou.optimus.console.roll.model.RollEventType.conversion");
	
	private RollEventType(String text){
		this.text = text;
	}
	
	private String text;
	
	public String getText() {
		return text;
	}
}
