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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.NoIdTimedEntity;
import com.ipinyou.base.entity.UserDefineList;

/**
 * 策略的媒体分类定向
 * @author baozhu.cao
 *
 */

@Entity
@Data
@ToString(callSuper = true, exclude = { "strategy" })
@EqualsAndHashCode(callSuper = true, exclude = { "strategy" })
public class StrategyClassifyUrl extends NoIdTimedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8586955345149326917L;

	@Id
	private Long id;
	
	
	@MapsId 
	@OneToOne(optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "id") 
	private Strategy strategy;
	
	/**
	 * 媒体分类定向Id集合
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="classify_ids",length=16777215))
	private UrlIdList classifyIds;
	
	
	/**
	 * 媒体分类排除Id集合
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="exclude_classify_ids",length=16777215))
	private UrlIdList excludeClassifyIds;
	
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
	
}
