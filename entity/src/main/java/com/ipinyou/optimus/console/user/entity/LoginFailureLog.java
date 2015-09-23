package com.ipinyou.optimus.console.user.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.BaseEntity;

@Entity
@Table(name = "usr_login_failure_log")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoginFailureLog extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7655915987938166460L;

	@Column(length = 200, nullable = false)
	@Index(name="account")
	private String account;
	/**
	 * 登录Ip
	 */
	@Column(length = 200, nullable = false)
	@Index(name="ip")
	private String ip;
	/**
	 * 登录时间
	 */	
	@Column(nullable = false)
	@Index(name="timestamp")
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
}
