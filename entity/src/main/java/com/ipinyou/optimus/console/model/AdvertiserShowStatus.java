package com.ipinyou.optimus.console.model;

public enum AdvertiserShowStatus {
	NOT_SELECT("optimus.console.model.AdvertiserShowStatus.1000"/*未选择*/), NOT_CHECKED("optimus.console.model.AdvertiserShowStatus.1001"/*审核中*/), APPROVED("optimus.console.model.AdvertiserShowStatus.1002"/*审核通过*/), DISAPPROVED("optimus.console.model.AdvertiserShowStatus.1003"/*审核拒绝*/);

	private String text;

	private AdvertiserShowStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
	}
}
