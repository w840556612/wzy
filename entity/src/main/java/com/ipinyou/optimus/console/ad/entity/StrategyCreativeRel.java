package com.ipinyou.optimus.console.ad.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineList;
import com.ipinyou.base.entity.UserDefineMap;
import com.ipinyou.optimus.console.ad.vo.CreativePlatformAuditVo;
import com.ipinyou.optimus.console.ad.vo.StatsClickImpUrl;
import com.ipinyou.optimus.console.model.ApprovalStatus;



/**
 * 策略创意关联，包括点击、到达地址
 * @author lijt
 *
 */
@Entity
@Data
@ToString(callSuper = true, exclude = {"orig","origApprovedInfo","origDisapprovedInfo",
		"creativeAuditStatus", "statsClickImpUrl"})
@EqualsAndHashCode(callSuper = true, exclude = {"orig","origApprovedInfo","origDisapprovedInfo",
		"creativeAuditStatus", "statsClickImpUrl"})
@JsonIgnoreProperties(value={"handler", "hibernateLazyInitializer","strategy","name","primaryKey","creativeId",
		"version","orig","origApprovedInfo","origDisapprovedInfo","statsClickImpUrl"}, ignoreUnknown = true)
public class StrategyCreativeRel extends TimedEntity implements Auditable<StrategyCreativeRel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.StrategyCreativeRel.1001")/*策略创意关联*/;
	}	
	
	@Override
	public String getName() {
		return "Strategy:" + strategy.getName() + "-Creative:" + creative.getName();
	}
	
	public StrategyCreativeRel() {

	}
	public StrategyCreativeRel(long creativeId, String targetUrl, String clickUrl, String trackingUrls) {
		this.creativeId = creativeId;
		this.targetUrl = targetUrl;
		this.clickUrl = clickUrl;
		OrderedUrlList urls = new OrderedUrlList();
		urls.setStrValue(trackingUrls);
		this.trackingUrls = urls;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "strategy_id")
	private Strategy strategy;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long strategyId;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "creative_id")
	private Creative creative;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long creativeId;
	

	@Column(length = 2083)
	@Index(name="clickUrl")
	private String clickUrl;
	
	@Column(nullable = false, length = 2083)
	@Index(name="targetUrl")
	private String targetUrl;
	
	@Column(nullable = false, length = 2083)
	private String extendUrl;
	/**
	 * 开启／关闭
	 */
	private boolean active = true;

	/**
	 * 已删除
	 */
	private boolean removed = false;	
	
	
	/**
	 * 策略创意关联内部审核状态
	 */
	@Column(nullable = false,length=50)
	@Enumerated(EnumType.STRING)
	private ApprovalStatus approvalStatus = ApprovalStatus.NOT_CHECKED;
	
	/*
	 * 创意内部审核通过时间
	 */
	private Timestamp lastApproved;

	/**
	 * 创意内部审核拒绝原因描述
	 */
	@Column(length = 500)
	private String disapprovalDesc;


//	@Lob
//	@Column(length = 65535)
//	private String trackingUrls;	
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="tracking_urls",length=65535))
	private OrderedUrlList trackingUrls;
	

//	@Column(length = 300)
//	private String approvedInfo; 
	@Embedded
	@AttributeOverride(name="strValue",column=@Column(name="approved_info",length=300))
	private ApprovalInfoMap approvedInfo;
	

	@Embedded
	@AttributeOverride(name="strValue",column=@Column(name="disapproved_info",length=300))
	private ApprovalInfoMap disapprovedInfo; //TODO
	
	/**
	 * 动态创意类型rel记录的商品ids
	 */
	@Embedded
	@AttributeOverride(name = "strValue", column = @Column(name = "product_ids", length = 2000))
	private ProductIdList productIds;
	
	/**
	 * 商品ids推荐方式
	 */
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private ProductRecommendType productRecommendType;
	
	/**
	 *暴风平台使用 
	 */
	@Column(length = 2038)
	private String baofengZipPath;
	
	/**
	 * 新浪视频创意Url
	 */
	@Column(length = 255)
	private String sinaCreativeUrl;
	
	@Transient
	private StrategyCreativeRel orig;	
	
	@Transient
	private ApprovalInfoMap origApprovedInfo;
	
	@Transient
	private ApprovalInfoMap origDisapprovedInfo; 
	
	@Transient
	private CreativePlatformAuditVo creativeAuditStatus;
	
	@Transient
	private StatsClickImpUrl statsClickImpUrl;
	
	@Transient
	private String thirdImpUrl;
	
	@Transient
	private List<String> thirdImpUrls;
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class ApprovalInfoMap extends UserDefineMap<String, String> {		
		private static final long serialVersionUID = -8610952695509537180L;

		public ApprovalInfoMap(){
			super(",", "=");
		}
		public ApprovalInfoMap(String defautValue){
			super(",", "=");
			this.setStrValue(defautValue);
		}	
		@Override
		public String getStrValue() {
			return super.getStrValue();
		}
		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
		@Override
		public String mapKeyObject(String key){
			return key;
		}		
		@Override
		public String mapValueObject(String value){
			return value;
		}	
	}
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class OrderedUrlList extends UserDefineList<String>{
		private static final long serialVersionUID = -7427803249699288449L;
		
		public OrderedUrlList(){
			super("\n");
		}	
		@Override
		@Lob
		public String getStrValue() {
			return super.getStrValue();
		}
		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
		@Override
		public boolean needOdered(){
			return true;
		}
	}	
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class ProductIdList extends UserDefineList<String> {
		private static final long serialVersionUID = 6184614428401805340L;

		public ProductIdList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}
	
	public static enum ProductRecommendType {
		
		BuyWeight("optimus.console.ad.entity.StrategyCreativeRel.1002"/*购买权重*/),
		Views("optimus.console.ad.entity.StrategyCreativeRel.1003"/*浏览次数*/),
		Discount("optimus.console.ad.entity.StrategyCreativeRel.1004"/*商品折扣*/);
		
		private String text;
		
		private ProductRecommendType(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(this.text);
		}
	}
}
