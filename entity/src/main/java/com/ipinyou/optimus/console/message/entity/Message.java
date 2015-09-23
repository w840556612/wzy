package com.ipinyou.optimus.console.message.entity;


import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * @author yaohl
 * 
 */
@Entity
@Data
@JsonIgnoreProperties(value={"sender","version", "lastModified"})
@ToString(callSuper = true, exclude = { "orig" ,"sender"})
@EqualsAndHashCode(callSuper = true, exclude = {"sender", "orig" })
public class Message extends TimedEntity implements Auditable<Message> {
	/**
     * 
     */
	private static final long serialVersionUID = 3323684946924666436L;

	/*
     * (non-Javadoc)
     * 
     * @see com.ipinyou.base.entity.Entity#getEntityName()
     */
    @Override
	public String getEntityName() {
		return "消息";
	}

    @ManyToOne(cascade = { CascadeType.REFRESH },fetch=FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(name="sender_id", insertable = false, updatable = false, nullable = false)
    private Long senderId;

    @Column(name="pool_id", nullable = true)
    private Long poolId;
    
    
	public static enum MessageType {
        System("菲菲消息"), Advertising("审核消息"),Register("注册消息");
        
        private String text;

		private MessageType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
    }
	@Column(nullable=true,length=50)
	@Enumerated(EnumType.STRING)
	private MessageType type;
	
	public static enum SendType {
        PublicMessage("公共消息"), PrivateMessage("私信");
        
        private String text;

		private SendType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
    }
	
	@Column(nullable=true,length=50)
	@Enumerated(EnumType.STRING)
	private SendType sendType;
	
	
	/**
	 * 消息内容
	 */
	@Column(length = 500,nullable = false)
	private String content;
	
	@Index(name="expiry_date")
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = false)
	private Timestamp expiryDate;
	
	@Column
	private String title;
	
	@Column(name="user_type")
	private String userType;

	@Transient
	private Message orig;

	@Override
	public String getName() {
		return this.getContent();
	}
}
