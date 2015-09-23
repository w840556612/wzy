package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.NoIdTimedEntity;

/**
 * 
 * @author xiaobo.wang
 *
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "advertiser" })
@EqualsAndHashCode(callSuper = true, exclude = { "advertiser" })
public class AdvertiserConverateIndex extends NoIdTimedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2237395938509237445L;
	
	@Id
	private Long id;
	
	@MapsId
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Advertiser advertiser;

	/**
	 * 记录是否有效 
	 */
	private boolean valid;
	
	/**
	 * 记录是否删除
	 */
	private boolean removed;
	
}
