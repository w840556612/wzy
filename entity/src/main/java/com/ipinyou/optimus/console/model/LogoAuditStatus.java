package com.ipinyou.optimus.console.model;

public enum LogoAuditStatus {
	NOT_MAP("未映射"),NOT_SUBMITED("未提交"), NOT_CHECKED("审核中"), APPROVED("审核通过"), 
	DISAPPROVED("审核拒绝"), MORE_SUBMIT("准备再次提交");

	private String text;

	private LogoAuditStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}