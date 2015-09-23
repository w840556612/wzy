package com.ipinyou.optimus.console.extendcreative.vo;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.ipinyou.optimus.console.extendcreative.entity.CreativeExtendField.CreativeMimeType;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ExtendFieldVo {
	
	@Valid
	@NotNull
	private Long fieldId;
	

	private boolean required;
	
	
	private String name;
	

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private CreativeMimeType type;
	
	private MultipartFile file;
	
	private String text;
	
	private String requirement;
}
