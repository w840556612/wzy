package com.ipinyou.optimus.console.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.constant.Regex;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;


/**
 * 发票信息，仅在E版使用
 * @author lijt
 *
 */
@Entity
@Table(name = "usr_invoice")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Invoice extends TimedEntity implements Auditable<Invoice> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1734668643242363446L;
	
	public static enum InvoiceType {
		TechincalService("技术服务费"), Advertising("广告发布费");// 顺序固定，不能调整//技术服务和广告发布
		private String text;

		private InvoiceType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	public static enum InvoiceCategory{
		OrdinaryVAT("增值税专用发票"),DedicatedVAT("增值税普通发票");
		private String text;
		
		private InvoiceCategory(String text){
			this.text = text;
		}
		
		public String getText(){
			return text;
		}
	}
	
	@Override
	@Transient
	public String getEntityName() {
		return "发票信息";
	}
	/*
	 * 所属的用户
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long userId;
	/**
	 * 发票抬头
	 */
	@NotNull
	@Size(min =2, max = 64)
	@Column(nullable = false, length = 64)
	private String name;
	
	public static void main(String[] args){
		System.out.println("0123456789012的345d".matches(Regex.TAXPAYER));
		
		
		
	}
	/**
	 * 寄送地址
	 */
	@NotNull
	@Size(max = 128)
	@Column(nullable = false, length = 128)
	private String address;
	
	
	/**
	 * 联系人名称
	 */
	@NotNull 
	@Size(max = 64)
	@Column(nullable = false, length = 64)
	private String contactName;
	
	/**
	 * 发票种类
	 */	
	@NotNull
	@Column(nullable=false,length=50)
	@Enumerated(EnumType.STRING)
	private InvoiceCategory category;
	
	
	/**
	 * 类型
	 */	
	@NotNull
	@Column(nullable=false,length=50)
	@Enumerated(EnumType.STRING)
	private InvoiceType type;
	
	/**
	 * 手机号
	 */
	@NotNull
	@Pattern(regexp=Regex.CELLPHONE)
	@Column(nullable=false,length = 11)
	private String cellphone;
	
	/**
	 * 邮政编码
	 */
	@Column(length = 10)
	@Size(max = 10)
	private String zipCode;
	
	@Transient
	private Invoice orig;
	
	/**
	 * 手机号
	 */
	@Pattern(regexp=Regex.TAXPAYER,message="请输入15~20个字符")
	@Column(nullable=false,length = 20)
	private String taxpayer;
	
	/**
	 * 电话号码
	 */
	@Pattern(regexp=Regex.PHONE)
	@Column(nullable=false,length = 15)
	private String phone;
	
	/**
	 * 开户银行
	 */
	@Size(max = 128)
	@Column(nullable = false, length = 128)
	private String bankName;
	
	/**
	 * 银行卡号
	 */
	@Pattern(regexp="^\\d*")
	@Column(nullable = false, length = 32)
	private String bankcard;
	
	@Column(length = 32)
	private String proofFile;
}
