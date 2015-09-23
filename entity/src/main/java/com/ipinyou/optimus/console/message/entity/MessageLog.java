package com.ipinyou.optimus.console.message.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.User;

@Entity
@Data
@JsonIgnoreProperties(value={"recipient","version", "lastModified"})
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
public class MessageLog extends TimedEntity implements Auditable<MessageLog>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9076515591598358543L;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "recipient_id")
	private User recipient;

	@Column(name = "recipient_id", insertable = false, updatable = false, nullable = false)
	private Long recipientId;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id")
	private Message message;

	@Column(name = "message_id", insertable = false, updatable = false, nullable = false)
	private Long messageId;
	
	@Column(nullable = true)
    private boolean removed = false;
	
	/**
	 * 消息阅读状态
	 * true:未读
	 */
	@Column(nullable = false)
	private boolean readStatus = true;

	@Override
	public String getEntityName() {
		
		return "消息记录";
	}
	
	@Transient
	private MessageLog orig;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
