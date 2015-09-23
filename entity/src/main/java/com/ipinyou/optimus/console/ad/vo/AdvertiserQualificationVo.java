package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.web.multipart.MultipartFile;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.AdvertiserQualification.QualificationType;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserQualificationVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4233818533703099774L;
	
	public AdvertiserQualificationVo(){
		
	}
	public AdvertiserQualificationVo(MultipartFile file, QualificationType type,String name){
		this.file = file;
		this.type = type;
		this.name = name;
	}
	
	private MultipartFile file;
	
	private QualificationType type;
	
	private String name;
}
