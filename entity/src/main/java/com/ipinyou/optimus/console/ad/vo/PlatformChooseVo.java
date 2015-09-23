package com.ipinyou.optimus.console.ad.vo;

import com.ipinyou.optimus.console.model.Platform;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class PlatformChooseVo {

	private boolean choose;
	
	private Platform platform;

	private String reason;
}
