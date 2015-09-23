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
@Table(name = "advertiser_apply")
@Data
@ToString(callSuper = false, of = { "address", "name", "tel", "website", "email" })
@EqualsAndHashCode(callSuper = true)
public class AdvertiserApply extends TimedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 姓名
	 */
	@NotNull
	@Size(max = 20)
	@Column(length = 20, nullable = false)
	private String name;
	/**
	 * 手机号码
	 */
	@NotNull
	@Size(max = 30)
	@Column(nullable = false, length = 30)
	private String tel;
	/**
	 * 公司所在地
	 */
	@NotNull
	@Size(max = 128)
	@Column(nullable = false, length = 128)
	private String address;
	/**
	 * 广告主网址
	 */
	@NotNull
	@Size(min = 10, max = 128)
	@Pattern(regexp = Regex.URL, message = "格式不正确")
	@Column(nullable = false, length = 128)
	private String website;

	/**
	 * 公司网址
	 */
	@Size(max = 10)
	@Column(length = 10, nullable = true)
	private String zipcode;

	/**
	 * Email
	 */
	@Pattern(regexp = Regex.EMAIL, message = "格式不正确")
	@Size(max = 40)
	@Column(nullable = true, length = 40)
	private String email;

	/**
	 * 公司
	 */
	@Size(max = 100)
	@Column(length = 100)
	private String company;
	/**
	 * 部门
	 */
	@Size(max = 100)
	@Column(length = 100)
	private String department;
	/**
	 * 职位
	 */
	@Size(max = 100)
	@Column(length = 100)
	private String position;
	/**
	 * 公司规模和业务描述
	 */
	@Size(max = 100)
	@Column(length = 200)
	private String companyDesc;
	/**
	 * 申请原因
	 */
	@Size(max = 100)
	@Column(length = 200)
	private String reason;
	/**
	 * 申请来源地址
	 */
	@Size(max = 100)
	@Column(length = 200)
	private String source;

}
