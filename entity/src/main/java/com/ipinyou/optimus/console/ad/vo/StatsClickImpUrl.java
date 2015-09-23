package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StatsClickImpUrl extends BaseVo{
	
	private static final long serialVersionUID = -1525892689137979465L;
	
	String clickUrl;
	String impUrl;
}
