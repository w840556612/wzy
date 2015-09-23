/**
 * 
 */
package com.ipinyou.optimus.console.ad.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.model.ApprovalStatus;
import com.ipinyou.optimus.console.model.CreativeShowStatus;

/**
 * @author lijt
 * 
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreativeApprovalStatusVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2506283836564319028L;

	private String disapprovalDesc;
	
	private ApprovalStatus approvalStatus = null;
	
	private List<CreativePlatformAuditStatusVo> platformAuditStatusVos = new ArrayList<>();

	private List<CreativePlatformAuditStatusVo> allPlatformAuditStatusVos = new ArrayList<>();;
	
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
	
	public CreativeApprovalStatusVo() {
		super();
	}

}
