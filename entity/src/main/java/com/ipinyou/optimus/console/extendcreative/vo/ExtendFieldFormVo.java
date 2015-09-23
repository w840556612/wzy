package com.ipinyou.optimus.console.extendcreative.vo;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ExtendFieldFormVo {
	
	@Valid
	private List<ExtendFieldVo> fields;
}
