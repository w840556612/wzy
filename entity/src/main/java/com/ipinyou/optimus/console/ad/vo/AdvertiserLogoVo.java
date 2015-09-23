package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.web.multipart.MultipartFile;

import com.ipinyou.base.model.BaseVo;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserLogoVo extends BaseVo {
	
	private static final long serialVersionUID = 5499319434967998069L;

	public AdvertiserLogoVo(){
		
	}
	public AdvertiserLogoVo(MultipartFile file,String name){
		this.file = file;
		this.name = name;
	}
	
	private MultipartFile file;
	
	private String name;
}
