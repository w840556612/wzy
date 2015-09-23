package com.ipinyou.optimus.console.ad.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel.OrderedUrlList;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel.ProductIdList;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel.ProductRecommendType;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"rel"})
public class StrategyCreativeVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 323562702616538155L;
	
	private Long creativeId;
	
	@NotNull
	@Size(min = 1, max = 64)
	private String creativeTheme;

	@Size(min = 1, max = 20)
	private String tags;
	
	@Size(min = 1, max = 1200)
	private String clickUrl;
	@NotNull
	@Size(min = 1, max = 1200)
	private String targetUrl;
	
	@Size(min = 1, max = 3)
	private OrderedUrlList trackingUrls;
	
	private ProductIdList productIds;
	
	private ProductRecommendType productRecommendType;
	
	private StrategyCreativeRel rel;
	
	public StrategyCreativeVo(){
		
	}
	
	public StrategyCreativeVo(StrategyCreativeRel rel, Long creativeId, String creativeTheme, 
			String tags, String targetUrl, String clickUrl, OrderedUrlList trackingUrls){
		this.rel = rel;
		this.creativeId = creativeId;
		this.creativeTheme = creativeTheme;
		this.tags = tags;
		this.targetUrl = targetUrl;
		this.clickUrl = clickUrl;
		this.trackingUrls = trackingUrls;
	}	
}
