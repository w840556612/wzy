package com.ipinyou.optimus.console.model;

public enum CreativeShowStatus {
	NOT_CHECKED("审核中"), APPROVED("审核通过"), DISAPPROVED("审核拒绝");

	private String text;

	private CreativeShowStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}