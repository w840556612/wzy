package com.ipinyou.optimus.console.extendcreative.vo;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ExtendCreativeFieldAnalysisVo {
	
	private boolean file;
	
	private int width;
	
	private int height;
	
	private String path;
	
	private int kbCount;
	
	private String text;
}
