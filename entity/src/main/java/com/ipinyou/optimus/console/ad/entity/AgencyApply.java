package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.constant.Regex;
import com.ipinyou.base.entity.TimedEntity;

@Entity
@Table(name = "agency_apply")
@Data
@ToString(callSuper = false, of = { "address", "name", "contactName", "email", "cellphone" })
@EqualsAndHashCode(callSuper = true)
public class AgencyApply extends TimedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 公司所在地
	 */
	@NotNull
	@Size(max = 128)
	@Column(nullable = false, length = 128)
	private String address;

	/*
	 * 公司名称
	 */
	@NotNull
	@Size(max = 20)
	@Column(length = 20, nullable = false)
	private String name;

	/*
	 * 公司网址
	 */
	@Pattern(regexp = Regex.URL)
	@Size(max = 50)
	@Column(length = 50, nullable = true)
	private String website;

	/*
	 * 联系人
	 */
	@NotNull
	@Size(max = 40)
	@Column(nullable = false, length = 40)
	private String contactName;

	/*
	 * Email
	 */
	@Pattern(regexp = Regex.EMAIL)
	@Size(max = 40)
	@Column(nullable = true, length = 40)
	private String email;

	/*
	 * QQ
	 */
	@Size(max = 40)
	@Column(length = 40)
	private String qq;

	/*
	 * 手机号码
	 */
	@NotNull
	@Pattern(regexp = Regex.CELLPHONE)
	@Size(max = 11)
	@Column(nullable = false, length = 11)
	private String cellphone;

}
