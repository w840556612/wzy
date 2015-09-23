package com.ipinyou.optimus.console.message.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.User;

@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
public class MessageSetting extends TimedEntity implements Auditable<MessageSetting>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6640042362661944857L;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "user_id", insertable = false, updatable = false, nullable = false)
	private Long userId;

	@Override
	public String getEntityName() {
		
		return "用户消息设置";
	}
	
	/**
	 * 上次接收信息时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(updatable = false, nullable = false)
	private Timestamp lastRecievedTime;
	
	/**
	 * 系统消息开关
	 */
	@Column(nullable = true)
	private boolean systemSetting = true;
	
	/**
	 * 注册消息开关
	 */
	@Column(nullable = true)
	private boolean registerSetting = true;
	
	/**
	 * 审核消息开关
	 */
	@Column(nullable = true)
	private boolean auditSetting = true;
	

	@Override
	public String getName() {
		
		return null;
	}

	

	@Transient
	private MessageSetting orig;

	
}
