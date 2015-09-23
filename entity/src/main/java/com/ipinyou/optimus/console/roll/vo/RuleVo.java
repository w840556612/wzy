package com.ipinyou.optimus.console.roll.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RuleVo {
	
	@Valid
	@NotNull
	private Long id;
	
	@Valid
	@NotNull
	private String name;
	
	@Valid
	private List<GroupConfigVo> config; 
}
