package com.ipinyou.optimus.console.media.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.constant.Regex;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.listener.PinyinListener;

/**
 * 媒体管理-网站表
 * @author guodong.zhang
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "version", "lastModified", "creator" })
@EntityListeners({ PinyinListener.class })
public class Website extends TimedEntity implements Auditable<Website> {

	private static final long serialVersionUID = 7100807951773786251L;

	/**
	 * 名称
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	@NotNull
	@Index(name = "name")
	private String name;
	
	/**
	 * 名称
	 * 名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 1000)
	private String namePinyin;
	
	/**
	 * 域名
	 */
	@Column(length = 2083)
	@Index(name="domain")
	private String domain;
	
	/**
	 * 所属渠道ID
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long channelId;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id", nullable = false)
	private Channel channel;
	
	/**
	 * 联系人邮箱
	 */
	@NotNull
	@Size(min = 3, max = 50)
	@Pattern(regexp = Regex.EMAIL)
	@Column(nullable = false, length = 100)
	@Index(name = "email")
	private String email;

	/**
	 * 联系方式
	 */
	@NotNull
	@Size(min = 11, max = 32)
	@Column(nullable = false, length = 32)
	private String cellphone;
	
	/**
	 * 联系人名称
	 */
	@NotNull
	@Size(min = 2, max = 45)
	@Column(length = 45)
	private String contactName;
	
	/**
	 * 联系人名称
	 * 联系人名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 1000)
	private String contactNamePinyin;
	
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	/**
	 * 广告投放时，创意是否携带品友角标  
	 * add by wujun 20131021
	 */
	private boolean showLogo = true;
	
	
	/**
	 * 备注
	 */
	@Column(length = 255)
	@Size(max = 255)
	private String remark;
	
	@Transient
	private Website orig;
	
	@Override
	@Transient
	public String getEntityName() {
		return "网站";
	}

}
