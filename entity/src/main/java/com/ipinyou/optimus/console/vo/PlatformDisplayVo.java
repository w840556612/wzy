package com.ipinyou.optimus.console.vo;

import com.ipinyou.optimus.console.model.Platform;

import lombok.Data;

@Data
public class PlatformDisplayVo {
	private Platform platform;
	private boolean display;
	
	public PlatformDisplayVo(){
		
	}
	public PlatformDisplayVo(Platform platform, boolean display){
		this.platform = platform;
		this.display = display;
	}
}
