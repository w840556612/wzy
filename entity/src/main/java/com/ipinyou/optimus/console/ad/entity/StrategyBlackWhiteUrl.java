package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.NoIdTimedEntity;
import com.ipinyou.base.entity.UserDefineList;

/**
 * 策略的黑白名单列表信息
 * @author xiaobo.wang
 *
 */

@Entity
@Data
@ToString(callSuper = true, exclude = { "strategy" })
@EqualsAndHashCode(callSuper = true, exclude = { "strategy" })
public class StrategyBlackWhiteUrl extends NoIdTimedEntity implements Auditable<StrategyBlackWhiteUrl>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8586955345149326917L;
	public static final String REFNAME = "blackWhiteUrl";

	@Id
	private Long id;
	
	
	@MapsId 
	@OneToOne(optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "id") 
	private Strategy strategy;
	
	@Transient
	private StrategyBlackWhiteUrl orig;
	
	/**
	 * 域名白名单Id集合
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="black_url_ids",length=16777215))
	private UrlIdList blackUrlIds;
	
	
	/**
	 * 域名黑名单Id集合
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="white_url_ids",length=16777215))
	private UrlIdList whiteUrlIds;
	
	
	/**
	 * 域名黑名单  不使用了
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="black_urls",length=16777215))
	private UrlList blackUrls;
	**/
	
	
	/**
	 * 域名白名单  不使用了	
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="white_urls",length=16777215))
	private UrlList whiteUrls;
	**/
	
	
	/**
	 * 自定义域名黑名单
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="custom_black_urls",length=16777215))
	private UrlList customBlackUrls;
	
	
	
	/**
	 * 自定义域名白名单
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="custom_white_urls",length=16777215))
	private UrlList customWhiteUrls;
	
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class UrlList extends UserDefineList<String>{
		private static final long serialVersionUID = -7427803249699288449L;
		
		public UrlList(){
			super("\n");
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
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class UrlIdList extends UserDefineList<Long>{
		private static final long serialVersionUID = -7427803249699288449L;
		
		public UrlIdList(){
			super(",");
		}	
		
		@Override
		protected Object asObject(String strValue) {
			return Long.valueOf(strValue);
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

	@Override
    public String getEntityName() {
	    return strategy.getEntityName();
    }

	@Override
    public String getPrimaryKey() {
	    return strategy.getPrimaryKey();
    }

	@Override
    public String getName() {
	    return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.StrategyBlackWhiteUrl.1001")/*策略的黑白名单列表*/;
    }
}
