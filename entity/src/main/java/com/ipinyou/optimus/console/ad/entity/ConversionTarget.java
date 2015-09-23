/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 转化目标
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig", "company", "belongedAdvertiser" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "company","belongedAdvertiser" })
public class ConversionTarget extends TimedEntity implements
		Auditable<ConversionTarget> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4107256306344520838L;

	/**
	 * 关联广告主公司
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private AdvertiserCompany company;
	/**
	 * 关联广告主公司id
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long companyId;

	@NotNull
	@Size(min=2,max = 64)
	@Column(nullable = false, length = 500)
	private String name;
	 /**
     * 备注
     */
    private String remark;
	/**
	 * 创建此代码的广告主 
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "belonged_advertiser_id")
	private Advertiser belongedAdvertiser;
	/**
	 * 创建此代码的广告主id
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long belongedAdvertiserId;
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	/**
	 * E版创建的默认转化
	 */
	private boolean express = false;
	
	/*
	 * 转化目标类型
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private TargetType targetType;
	
	public static enum TargetType {		
		register("optimus.console.ad.entity.ConversionTarget.1001"/*注册*/,1),order("ftl.common.order"/*订单*/,1),buy("optimus.console.ad.entity.ConversionTarget.1002"/*购物车*/,1),partake("optimus.console.ad.entity.ConversionTarget.1003"/*参加活动*/,0),
		firstActivate("optimus.console.ad.entity.ConversionTarget.1004"/*第一次激活*/,0),download("ftl.ad.login.1010"/*下载*/,0),other("optimus.console.ad.entity.ConversionTarget.1005"/*其他*/,0);
		
		private String text;
		private int codeType;//获取转化代码类型的标识
		
		private TargetType(String text,int codeType){
			this.text = text;
			this.codeType = codeType;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
		
		public int getCodeType(){
			return this.codeType;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Auditable#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.ConversionTarget.1006")/*转化目标*/;
	}
	@Transient
	private ConversionTarget orig;
	
	/*
	 * 默认展示转化代码
	 */
	private String defaultCode;
	
	
}
