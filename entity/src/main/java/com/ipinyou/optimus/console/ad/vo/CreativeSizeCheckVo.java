package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class CreativeSizeCheckVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3852042511556006209L;
	private String creativeTheme;
	private String msg;
	
	public CreativeSizeCheckVo(String creativeTheme, String msg) {
		super();
		this.creativeTheme = creativeTheme;
		this.msg = msg;
	}
	
}
