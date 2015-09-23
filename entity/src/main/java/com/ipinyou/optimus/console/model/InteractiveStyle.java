package com.ipinyou.optimus.console.model;

public enum InteractiveStyle {
	NA("无",0);
	//Phone("电话直拨",1),Download("点击下载",2);

	private String text;

	private int code;
	
	private InteractiveStyle(String text,int code) {
		this.text = text;
		this.code = code;
	}

	public String getText() {
		return text;
	}
	
	public int getCode(){
		return code;
	}
}