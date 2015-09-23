package com.ipinyou.optimus.console.ad.vo;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.Creative;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel.OrderedUrlList;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StatsCreativeForm extends BaseVo{
	
	private static final long serialVersionUID = -1525892689137979464L;
	
	@NotNull
	@Size(min = 1, max = 64)
	String theme;
	
	@NotNull
	Integer width;
	
	@NotNull
	Integer height;
	
	@NotNull
	@Size(min = 1, max = 2083)
	String clickUrl;
	
	@Embedded
	OrderedUrlList trackingUrls;
	
	public StatsCreativeForm(){
		
	}
	
	public StatsCreativeForm(Creative creative, StrategyCreativeRel rel){
		this.theme = creative.getTheme();
		this.width = creative.getWidth();
		this.height=creative.getHeight();
		
		this.clickUrl=rel.getClickUrl();
		this.trackingUrls = rel.getTrackingUrls();
	}
}
