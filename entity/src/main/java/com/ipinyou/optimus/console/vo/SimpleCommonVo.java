package com.ipinyou.optimus.console.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SimpleCommonVo implements Serializable {

	private static final long serialVersionUID = -8776349164506927029L;

	private Long id;
	private String name;

	public SimpleCommonVo() {
		super();
	}

	public SimpleCommonVo(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
