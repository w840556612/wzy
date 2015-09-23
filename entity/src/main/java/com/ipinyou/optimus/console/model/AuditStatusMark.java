package com.ipinyou.optimus.console.model;

public enum AuditStatusMark {
	INTERNAL_DISAPPROVED("内部拒绝");

	private String text;

	private AuditStatusMark(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}