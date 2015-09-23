package com.ipinyou.optimus.console.media.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;

/**
 * 媒体管理-渠道和广告主的关联表
 * @author guodong.zhang
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "version", "lastModified", "creator" })
public class ChannelAdvertiserRel extends TimedEntity implements
		Auditable<ChannelAdvertiserRel> {

	private static final long serialVersionUID = -3207894097426055491L;

	/**
	 * 渠道
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id")
	private Channel channel;

	/**
	 * 渠道ID
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long channelId;

	/**
	 * 广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id")
	private Advertiser advertiser;

	/**
	 * 广告主ID
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;

	/**
	 * 开启/关闭
	 */
	@NotNull
	@Column(nullable = false)
	private boolean active = true;

	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	@Transient
	private ChannelAdvertiserRel orig;

	@Override
	public String getName() {
		return "Channel:" + channel.getName() + "-Pool:" + advertiser.getName();
	}

	@Override
	@Transient
	public String getEntityName() {
		return "渠道广告主关联";
	}

}
