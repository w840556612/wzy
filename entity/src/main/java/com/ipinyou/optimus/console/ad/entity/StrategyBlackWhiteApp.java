package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
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
import com.ipinyou.optimus.console.ad.entity.StrategyBlackWhiteUrl.UrlList;

/**
 * 策略的移动APP黑白名单列表信息
 * @author xiaobo.wang
 *
 */

@Entity
@Data
@ToString(callSuper = true, exclude = { "strategy" })
@EqualsAndHashCode(callSuper = true, exclude = { "strategy" })
public class StrategyBlackWhiteApp extends NoIdTimedEntity implements Auditable<StrategyBlackWhiteApp>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8586955345149326917L;
	
	public static final String REFNAME = "blackWhiteApp";

	@Id
	private Long id;
	
	
	@MapsId 
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id") 
	private Strategy strategy;
	
	/**
	 * 移动APPId黑名单
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="black_app_ids", length=16777215))
	private UrlList blackAppIds;
	
	@Transient
	private StrategyBlackWhiteApp orig;
	
	/**
	 * 移动APPId白名单
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="white_app_ids", length=16777215))
	private UrlList whiteAppIds;

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
	    return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.StrategyBlackWhiteApp.1001")/*策略的移动APP黑白名单*/;
    }
}
