package com.ipinyou.optimus.console.ad.vo;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;

@Data
public class StrategyCreativeListForm {
	@Valid
	private List<StrategyCreativeVo> strtgyCrtvs;
	
}
