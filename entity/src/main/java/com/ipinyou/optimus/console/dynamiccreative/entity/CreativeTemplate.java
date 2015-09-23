package com.ipinyou.optimus.console.dynamiccreative.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 创意模板
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" ,"group"})
@EqualsAndHashCode(callSuper = true, exclude = { "orig","group" })
@JsonIgnoreProperties(value = { "group" }, ignoreUnknown = true)
public class CreativeTemplate extends TimedEntity implements Auditable<CreativeTemplate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873549878483123819L;
	
	
	public CreativeTemplate(){
		
	}
	
	public CreativeTemplate(int width, int height){
		this.width = width;
		this.height = height;
	}

	/**
	 * 名称
	 */
	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, length = 64)
	private String name;
	
	
	
	/**
	 * 是否通用模板
	 * 2015-03-30修改，这个字段标注的是该模板是否可以配置属性，按照新版动态创意的逻辑，所有模板的属性都是可配置的，所以默认值改为true
	 */
	private boolean universal = true;
	/**
	 * 代号
	 */
	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, length = 64)
	private String symbol;

	
	/**
	 * 需要的设置属性，对应 AdvertiserCreativeProps中的记录
	 */
	@Column(nullable = false, length = 255)
	private String requireProps;
	
	/**
	 * 所属的模板组
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = true, insertable = true, updatable = true)
	private TemplateGroup group;

	@Column(insertable = false, updatable = false, nullable = true)
	private Long groupId;
	
	/**
	 * 类型
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private TemplateType type;

	public static enum TemplateType {
		DynamicCreativeBaseProduct("动态创意(基于商品)"),
		DynamicCreativeBaseMaterial("动态创意(基于素材)");

		private String text;

		private TemplateType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	/**
	 * 宽度
	 */
	@Min(value=1)
	@NotNull
	private int width;

	/**
	 * 高度
	 */
	@Min(value=1)
	@NotNull
	private int height;
	
	/**
	 * Logo宽
	 */
	@Min(value=1)
	private int logoWidth;
	
	/**
	 * Logo高
	 */
	@Min(value=1)
	private int logoHeight;
	
	/**
	 * Logo容差值，默认为1
	 */
	@Min(value=0)
	private int logoTolerance=1;

	/**
	 * 是否多帧
	 */
	@NotNull
	@Column(nullable = false)
	private boolean multiUnit;

	/**
	 * 最小帧数，若为空表示不限制最小帧数
	 */
	private Integer minUnit;

	/**
	 * 最大帧数，若为空表示不限制最大帧数
	 */
	private Integer maxUnit;

	/**
	 * 预览图片扩展名
	 */
	@Column(length = 50, nullable = false)
	private String priviewPicExt="";

	/**
	 * 代码模板，使用Freemarker语法
	 */
	@Lob
	private String codeTemplate;

	/**
	 * 是否使用创意作为素材投放
	 */
	@NotNull
	@Column(nullable = false)
	private boolean useCreative;

	/**
	 * 模板创意的宽度
	 */
	private Integer useCreativeWidth;

	/**
	 * 模板创意的高度
	 */
	private Integer useCreativeHeight;

	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	
	/*
	 * 模板版本
	 */
	@NotNull
	@Column(nullable = false)
	private int templateVersion=2;

	@Override
	public String getEntityName() {
		return "创意模板";
	}
		
	public static enum TemplateStyle {
		Banner("横幅类"),
		Rectangle("矩形类"),
		Vertical("竖幅类");
		private String text;

		private TemplateStyle(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
	@Transient
	private int dimWidth;
	
	@Transient
	private int dimHeight;
	
	@Transient
	private CreativeTemplate orig;

}
