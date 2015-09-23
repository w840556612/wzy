package com.ipinyou.optimus.console.model;

public enum AdvertiserPlatformOperateStatus {
	DELETE("删除"), RECOVER("恢复"), COMPLETE("完成");

	private String text;

	private AdvertiserPlatformOperateStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}