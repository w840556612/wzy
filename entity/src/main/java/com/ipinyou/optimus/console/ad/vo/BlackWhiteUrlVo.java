package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;

@Data
public class BlackWhiteUrlVo {
	
	private Long strategyId;
	
	private Integer blackUrlIdLen;
	
	private Integer customBlackUrlLen;
	
	private Integer whiteUrlIdLen;
	
	private Integer customWhiteUrlLen;
	
	
	public BlackWhiteUrlVo(Long strategyId, Integer blackUrlIdLen, Integer customBlackUrlLen, 
			Integer whiteUrlIdLen, Integer customWhiteUrlLen){
		this.strategyId = strategyId;
		this.blackUrlIdLen = blackUrlIdLen;
		this.customBlackUrlLen = customBlackUrlLen;
		this.whiteUrlIdLen = whiteUrlIdLen;
		this.customWhiteUrlLen = customWhiteUrlLen;
	}

}
