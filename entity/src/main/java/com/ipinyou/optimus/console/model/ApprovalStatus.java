package com.ipinyou.optimus.console.model;

public enum ApprovalStatus {
	NOT_SUBMITED("未提交平台审核"), NOT_CHECKED("审核中"), APPROVED("审核通过"), DISAPPROVED("审核拒绝"), MORE_SUBMIT("准备再次提交平台审核");

	private String text;

	private ApprovalStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}