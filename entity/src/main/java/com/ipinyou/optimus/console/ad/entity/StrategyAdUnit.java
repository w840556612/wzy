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
 * 策略的自有媒体广告位id列表信息
 * 
 * @author shewei.deng
 * 
 */

@Entity
@Data
@ToString(callSuper = true, exclude = { "strategy" })
@EqualsAndHashCode(callSuper = true, exclude = { "strategy" })
public class StrategyAdUnit extends NoIdTimedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8586955345149326917L;

	@Id
	private Long id;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Strategy strategy;

	/**
	 * 
	 */
	@Embedded
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "black_ids", length = 65536))
	private AdUnitIdList blackIds;

	/**
	 * 
	 */
	@Embedded
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "white_ids", length = 65536))
	private AdUnitIdList whiteIds;
	
	
	/*
	 * 白名单广告组
	 */
	@Embedded
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "white_group_ids", length = 65536))
	private AdUnitIdList whiteGroupIds;
	
	/*
	 * 黑名单广告组
	 */
	@Embedded
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "black_group_ids", length = 65536))
	private AdUnitIdList blackGroupIds;
	
	/*
	 * 用户白名单
	 */
	@Embedded
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "custom_white_ids", length = 65536))
	private AdUnitIdList customWhiteIds;

	/*
	 * 用户黑名单
	 */
	@Embedded
	@Lob
	@AttributeOverride(name = "strValue", column = @Column(name = "custom_black_ids", length = 65536))
	private AdUnitIdList customBlackIds;
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class AdUnitIdList extends UserDefineList<String> {
		private static final long serialVersionUID = 6184614428401805340L;

		public AdUnitIdList() {
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
	}


}
