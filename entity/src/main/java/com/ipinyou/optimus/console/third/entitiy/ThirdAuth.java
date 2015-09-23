package com.ipinyou.optimus.console.third.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.TimedEntity;

/**
 * @author baozhu.cao
 * 
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ThirdAuth extends TimedEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Index(name="platform")
	@NotNull
	@Column(nullable = false, length = 16)
	private String platform;
	
	@Index(name="userId")
	@NotNull
	@Column(nullable = false, length = 64)
	private  String userId;
	
	@Column(length = 64)
	private  String secret;
	
	@Column(length = 64)
	private  String email;
	
	@Column(length = 64)
	private  String password;
	
	@Column(length = 125)
	private  String accessToken;
	
	private Long expiresTimeMilli;
	
	@Column(length = 125)
	private  String refreshToken;
	
	@Transient
	private  String grantType; 
}
