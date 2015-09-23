package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 公共配置表
 */
@Entity
@Table(name = "common_config")
@Data
@ToString(callSuper = true, exclude = {"keyName","keyValue"})
@EqualsAndHashCode(callSuper = true, exclude = {"keyName"})
@JsonIgnoreProperties(value = {"version", "creation", "lastModified"})
public class CommonConfig extends TimedEntity implements
		Auditable<CommonConfig> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7260232716826692095L;

	/**
	 * 是否已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
 
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 键
	 */
	@Column(nullable = false,name = "key_name")
	private String keyName;
	
	/**
	 * 键值
	 */
	@Column(nullable = false,name = "key_value")
	private String keyValue;
	
	/**
	 * 描述
	 */
	private String remark;
	
	@Column(nullable = false,name = "key_type")
	@Enumerated(EnumType.STRING)
	private KeyType keyType;
	
	public static enum KeyType{
		Unknown("optimus.console.model.Platform.1000"/*未知*/), doc("ftl.ad.top.1009"/*审核文档*/), common("ftl.ad.top.1033"/*常用配置*/);

		private String text;

		private KeyType(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
	
 
	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.CommonConfig.1001")/*公共配置表*/;
	}

	@Override
	public String getName() {
		return null;
	}

	@Transient
	private CommonConfig orig;

	 

}
