package com.ipinyou.optimus.console.ad.vo;

import java.sql.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.model.ApprovalStatus;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreativeAuditFilterVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3235627026165381588L;
	
	@DateTimeFormat(pattern = DateFormat.DATE)
	private Date startDate;
	@DateTimeFormat(pattern = DateFormat.DATE)
	private Date endDate;
	private String advertiserName;
	private String strategyName;
	private String creativeName;
	private String adSize;
	private String type;
	private InternalAuditStatus innerAuditStatus;
	private CombinedAuditStatus combinedAuditStatus;
	private Boolean dasuanpan;
	private String targetUrl;
	
	public Integer getWidth() {
		String[] dimonsion = StringUtils.split(getAdSize(), "*");
		if (ArrayUtils.isNotEmpty(dimonsion) && dimonsion.length == 2 && NumberUtils.isNumber(dimonsion[0])) {
			return Integer.parseInt(dimonsion[0]);
		}
		return null;
	}

	public Integer getHeight() {
		String[] dimonsion = StringUtils.split(getAdSize(), "*");
		if (ArrayUtils.isNotEmpty(dimonsion) && dimonsion.length == 2 && NumberUtils.isNumber(dimonsion[1])) {
			return Integer.parseInt(dimonsion[1]);
		}
		return null;
	}
	
	public static enum CombinedAuditStatus {
		ALLNOTCHECK("全部未审核"), 
		APPROVEDNODISAPPROVED("有通过无拒绝"), 
		APPROVEDANDDISAPPROVED("有通过有拒绝"), 
		ALLDISAPPROVED("全部拒绝");
		
		private String text;
		private CombinedAuditStatus(String text) {
			this.text = text;
		}
		public String getText() {
			return text;
		}
	}
	
	public static enum InternalAuditStatus {
		// 用于审核搜索页面
		NOT_CHECKED("未审核"), APPROVED("审核通过"), DISAPPROVED("审核拒绝");

		private String text;

		private InternalAuditStatus(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	public ApprovalStatus getApprovalStatus() {
		if (innerAuditStatus == null) {
			return null;
		}
		switch (innerAuditStatus) {
		case NOT_CHECKED:
			return ApprovalStatus.NOT_CHECKED;
		case APPROVED:
			return ApprovalStatus.APPROVED;
		case DISAPPROVED:
			return ApprovalStatus.DISAPPROVED;
		default:
			return null;
		}
	}
}
