package com.ipinyou.optimus.console.extendcreative.vo;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ExtendCreativeAnalysisVo {
	
	private int fileCount;
	
	private int textCount;
	
	private List<ExtendCreativeFieldAnalysisVo> fieldInfos;
}
