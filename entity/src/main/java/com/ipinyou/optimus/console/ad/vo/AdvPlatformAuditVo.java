package com.ipinyou.optimus.console.ad.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.model.AdvertiserShowStatus;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvPlatformAuditVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6826184974641460095L;
	
	private Long advId;
	
	private Long companyId;
	
	private List<AdvPlatformAuditStatusVo> platformAuditStatusVos = new ArrayList<>();
		
	private int platformSize = 0;
	
	public AdvPlatformAuditVo(){
		
	}
	
	//不需要平台审核的广告主，也生成审核通过记录
	public AdvPlatformAuditVo(Long advId, Long companyId){
		this.advId = advId;
		this.companyId = companyId;
	}
	
	
	public int getApprovedCount(){
		int approvedCount = 0;
		for(AdvPlatformAuditStatusVo psvo : platformAuditStatusVos) {
			if(psvo != null && psvo.getStatus() == AdvertiserShowStatus.APPROVED){
				++approvedCount;
			}
		}
		return approvedCount;
	}
	
	public int getDisapprovedCount(){
		int disapprovedCount = 0;
		for(AdvPlatformAuditStatusVo psvo : platformAuditStatusVos) {
			if(psvo != null && psvo.getStatus() == AdvertiserShowStatus.DISAPPROVED){
				++disapprovedCount;
			}
		}
		return disapprovedCount;
	}
	
	public boolean allApproved(){
		return platformSize == getApprovedCount();
	}
		
	public boolean allDisapproved(){
		return platformSize == getDisapprovedCount();
	}

}
