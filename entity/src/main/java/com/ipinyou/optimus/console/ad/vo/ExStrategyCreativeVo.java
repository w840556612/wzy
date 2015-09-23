package com.ipinyou.optimus.console.ad.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel.OrderedUrlList;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel.ProductIdList;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ExStrategyCreativeVo extends BaseVo {
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
	
	@Size(min = 1, max = 1000)
	private String clickUrl;
	@NotNull
	@Size(min = 1, max = 1000)
	private String targetUrl;
	
	@Size(min = 1, max = 3)
	private OrderedUrlList trackingUrls;
	
	private ProductIdList productIds;
	
	
}
