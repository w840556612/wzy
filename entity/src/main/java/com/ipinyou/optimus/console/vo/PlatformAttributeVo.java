package com.ipinyou.optimus.console.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PlatformAttributeVo implements Serializable {

	private static final long serialVersionUID = -8776349164506927029L;

	private String name;
	private String enumStr;
	
	public PlatformAttributeVo() {
		super();
	}

	public PlatformAttributeVo(String enumStr, String name) {
		super();
		this.enumStr = enumStr;
		this.name = name;
	}
}
