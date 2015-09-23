package com.ipinyou.optimus.console.extendcreative.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.ipinyou.optimus.console.extendcreative.entity.CreativeExtend;
import com.ipinyou.optimus.console.extendcreative.entity.CreativeExtendField;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ExtendcreativeForm {
	
	@Valid
	@NotNull
	private CreativeExtend creativeExtend;
	
	@NotNull
	private MultipartFile priviewPic;
	
	@Valid
	@NotNull
	private CreativeExtendField mainField;
	
	@Valid
	private List<CreativeExtendField> otherFields;
	
}
