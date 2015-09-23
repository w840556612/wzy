package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;

@Data
public class PoolVo {

	private Long id;
	private String name;

	public PoolVo() {

	}

	public PoolVo(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
