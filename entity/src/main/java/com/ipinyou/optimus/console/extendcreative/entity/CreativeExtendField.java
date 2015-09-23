package com.ipinyou.optimus.console.extendcreative.entity;

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

@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class CreativeExtendField extends TimedEntity implements Auditable<CreativeExtendField>{
	
	private static final long serialVersionUID = -5873549878483123819L;
	
	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, length = 64)
	private String name;
	
	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, length = 64)
	private String field;
	
	/**
	 * 所属拓展创意模板
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "extend_id", nullable = false)
	private CreativeExtend extend;	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long extendId;
	
	@NotNull
	private boolean main;
	
	@NotNull
	private boolean required;
	
	@NotNull
	private boolean removed=false;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private CreativeMimeType type;
	
	public static enum CreativeMimeType{
		Picture("图片"),Text("文字"),Video("视频"),Flash("flash");
		
		private String text;
		
		private CreativeMimeType(String text){
			this.text=text;
		}
		
		public String getText() {
			return text;
		}
	}
	
	private Integer width;
	
	private Integer height;
	
	private Integer minLenght;
	
	private Integer maxLenght;
	
	@Column(length = 256, nullable = true)
	private String requirement;
	
	@Column(length = 128, nullable = true)
	private String fieldExtNames;
	
	@Override
	public String getEntityName() {
		return "拓展创意模板单元";
	}
	
	@Transient
	private CreativeExtendField orig;
	
}
