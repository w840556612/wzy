package com.ipinyou.optimus.console.model;

public enum EffectiveStatus {
	EXPIRED("已过期"), NOT_EFFECTIVE("待生效"), EFFECTIVE("生效中"), EXPIRING("即将到期");

	private String text;

	private EffectiveStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}