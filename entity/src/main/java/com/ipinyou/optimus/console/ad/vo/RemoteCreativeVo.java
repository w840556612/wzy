package com.ipinyou.optimus.console.ad.vo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.Creative.CreativeType;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RemoteCreativeVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 323562702616538166L;
	
	public RemoteCreativeVo(){
	}
	private String creativePath;
	
	private String creativeSize;
	
	@Enumerated(EnumType.STRING)
	private CreativeType creativeType;
	
	//视频创意的播放长度，单位：秒
	private Double durationMillis ;
}
