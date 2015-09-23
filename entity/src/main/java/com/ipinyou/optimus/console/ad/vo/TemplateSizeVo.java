package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class TemplateSizeVo {
	private String width;
	private String height;
	
	public TemplateSizeVo(int width, int height){
		this.width = String.valueOf(width);
		this.height = String.valueOf(height);
	}
}
