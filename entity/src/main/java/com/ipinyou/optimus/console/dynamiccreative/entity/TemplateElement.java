package com.ipinyou.optimus.console.dynamiccreative.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 模板元素
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class TemplateElement extends TimedEntity implements Auditable<TemplateElement> {

	/***
	 * 
	 */
	private static final long serialVersionUID = -5873549878483123819L;

	/**
	 * 名称
	 */
	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, length = 64)
	private String name;

	/**
	 * 变量名称，freemarker模板中使用
	 */
	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, length = 64)
	private String varName;

	/**
	 * 是否主要部分
	 */
	@NotNull
	private boolean main;

	/**
	 * 所属创意模板
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", nullable = false)
	private CreativeTemplate template;	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long templateId;

	/**
	 * 类型
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private MaterialType type;

	public static enum MaterialType {
		Picture("图片"), Text("文字");

		private String text;

		private MaterialType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	/**
	 * 宽度
	 */
	@NotNull
	private int width;

	/**
	 * 高度
	 */
	@NotNull
	private int height;

	/**
	 * 离左侧的内边距
	 */
	private int paddingLeft;

	/**
	 * 离上侧的内边距
	 */
	private int paddingTop;

	/**
	 * 要求
	 */
	@Column(length = 255, nullable = true)
	private String requirement;

	@Override
	public String getEntityName() {
		return "模板元素";
	}

	@Transient
	private TemplateElement orig;
	
	@Transient
	private int ownedNumber = 0;

}
