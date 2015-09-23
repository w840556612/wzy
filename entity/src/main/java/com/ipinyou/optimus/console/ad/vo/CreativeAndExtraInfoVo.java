package com.ipinyou.optimus.console.ad.vo;

import java.math.BigDecimal;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.ipinyou.optimus.console.model.InteractiveStyle;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreativeAndExtraInfoVo {
	
	@NotBlank
	private String adviewType;
	
	private Long creativeId;
	
	private int interactive=InteractiveStyle.NA.getCode();
	
	private String telNo;
	
	private String downloadUrl;
	
	private String appName;
	
	private String appDesc;
	
	private BigDecimal appPackageSize;
	
	private MultipartFile file;
}
