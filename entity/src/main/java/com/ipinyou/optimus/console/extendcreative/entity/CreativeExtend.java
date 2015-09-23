package com.ipinyou.optimus.console.extendcreative.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.model.Platform;

@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
public class CreativeExtend extends TimedEntity implements Auditable<CreativeExtend>{
	
	private static final long serialVersionUID = -5873549878483123819L;
	
	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, length = 64)
	private String name;
	
	@NotNull
	private boolean removed=false;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private ExtendCreativeType type=ExtendCreativeType.PC;
	

	public static enum ExtendCreativeType{
		PC("PC"),Mobile("移动");
		
		private String text;
		
		private ExtendCreativeType(String text){
			this.text=text;
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
	 * 预览图片
	 */
	@Column(length = 50, nullable = false)
	private String priviewPicExt="";
	
	@NotNull
	@Lob
	private String template;

	@Lob
	private String auditTemplate;
	
	/**
	 * 所属平台
	 */
	@Column(nullable = true, length = 50)
	@Enumerated(EnumType.STRING)
	private Platform platform;
	
	@Size(min = 2, max = 64)
	@Column(nullable = true, length = 64)
	private String tcreativestyle;
	
	@Override
	public String getEntityName() {
		return "拓展创意模板";
	}
	
	@Transient
	private CreativeExtend orig;
	
}
