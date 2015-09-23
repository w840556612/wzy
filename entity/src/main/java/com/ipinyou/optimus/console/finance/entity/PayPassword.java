package com.ipinyou.optimus.console.finance.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.User;
import com.ipinyou.optimus.console.user.entity.User.passwordChecks;


/**
 * 充值确认密码
 * @author yaohl
 *
 */
@Entity
@Table(name = "pay_password",uniqueConstraints={@UniqueConstraint(columnNames={"user_id"})})
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PayPassword extends TimedEntity implements Auditable<PayPassword> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7350874201578512955L;
	/*
	 * 所属的用户
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long userId;

	// @NotNull
	// @Size(min = 36, max = 150)
	@Column(length = 150, nullable = false)
	private String password;

	/**
	 * 用户输入的原始密码，初次设置时使用
	 */
	@Transient
	@NotNull(groups = passwordChecks.class)
	@Size(min = 6, max = 200)
	private String oldPassword;
	
	
	/**
	 * 用户输入的原始密码，初次设置时使用
	 */
	@Transient
	@NotNull(groups = passwordChecks.class)
	@Size(min = 6, max = 200)
	private String plainPassword;

	/**
	 * 用户输入的确认密码，初次设置时使用
	 */
	@Transient
	@NotNull(groups = passwordChecks.class)
	@Size(min = 6, max = 200)
	private String confirmPassword;

	@Column(length = 20, nullable = false)
	private String salt;

	@Transient
	private PayPassword orig;

	@Override
	@Transient
	public String getName() {
		return user.getName();
	}
	
	@Override
	@Transient
	public String getEntityName() {
		
		return "充值密码";
	}

}
