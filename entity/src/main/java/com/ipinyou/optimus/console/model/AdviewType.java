package com.ipinyou.optimus.console.model;

public enum AdviewType {
	Mobile("mobile");

	private String text;

	private AdviewType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}