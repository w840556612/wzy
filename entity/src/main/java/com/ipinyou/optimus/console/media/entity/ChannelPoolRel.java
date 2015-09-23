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
import com.ipinyou.optimus.console.user.entity.Pool;

/**
 * 媒体管理-渠道和池的关联表
 * @author guodong.zhang
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "version", "lastModified", "creator" })
public class ChannelPoolRel extends TimedEntity implements
		Auditable<ChannelPoolRel> {

	private static final long serialVersionUID = -5658874531527066785L;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id", nullable = false, insertable = false, updatable = false)
	private Channel channel;

	@Column(insertable = true, updatable = true, nullable = false)
	private Long channelId;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "pool_id", nullable = false, insertable = false, updatable = false)
	private Pool pool;

	@Column(insertable = true, updatable = true, nullable = false)
	private Long poolId;

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
	private ChannelPoolRel orig;

	@Override
	public String getName() {
		return "Channel:" + channel.getName() + "-Pool:" + pool.getName();
	}

	@Override
	@Transient
	public String getEntityName() {
		return "渠道池关联";
	}

}
