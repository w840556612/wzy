package com.ipinyou.optimus.console.ad.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.model.AdvertiserShowStatus;
import com.ipinyou.optimus.console.model.CreativeShowStatus;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreativePlatformAuditVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6826184974641460095L;
	
	private Long creativeId;
	
	private boolean showAllPlatformAuditStatus;
	
	private List<CreativePlatformAuditStatusVo> platformAuditStatusVos = new ArrayList<>();
	
	private List<CreativePlatformAuditStatusVo> allPlatformAuditStatusVos = new ArrayList<>();
	
	public CreativePlatformAuditVo(){
		
	}
	
	public CreativePlatformAuditVo(Long creativeId){
		this.creativeId = creativeId;
	}
	
	public int getPlatformAuditSize(){
		return platformAuditStatusVos.size();
	}
	
	public int getAllPlatformAuditSize(){
		return allPlatformAuditStatusVos.size();
	}
	
	public int getApprovedCount(){
		int approvedCount = 0;
		for(CreativePlatformAuditStatusVo psvo : platformAuditStatusVos) {
			if(psvo != null && psvo.getStatus() == CreativeShowStatus.APPROVED){
				++approvedCount;
			}
		}
		return approvedCount;
	}
	
	public int getDisapprovedCount(){
		int disapprovedCount = 0;
		for(CreativePlatformAuditStatusVo psvo : platformAuditStatusVos) {
			if(psvo != null && psvo.getStatus() == CreativeShowStatus.DISAPPROVED){
				++disapprovedCount;
			}
		}
		return disapprovedCount;
	}
	
	public int getAllApprovedCount(){
		int approvedCount = 0;
		for(CreativePlatformAuditStatusVo psvo : allPlatformAuditStatusVos) {
			if(psvo != null && psvo.getStatus() == CreativeShowStatus.APPROVED){
				++approvedCount;
			}
		}
		return approvedCount;
	}
	
	public int getAllDisapprovedCount(){
		int disapprovedCount = 0;
		for(CreativePlatformAuditStatusVo psvo : allPlatformAuditStatusVos) {
			if(psvo != null && psvo.getStatus() == CreativeShowStatus.DISAPPROVED){
				++disapprovedCount;
			}
		}
		return disapprovedCount;
	}
}
