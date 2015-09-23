 /**
 * 
 */
package com.ipinyou.optimus.console.creator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Creative.CreativeType;

/**
 * 创意模板
 * @author lijt
 *
 */
@Entity
@Data
@ToString(callSuper = true, exclude = {"orig"})
@EqualsAndHashCode(callSuper = true, exclude = {"orig"})
public class CreatorTemplate extends TimedEntity implements Auditable<CreatorTemplate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8946240622126121590L;
	
	
	public CreatorTemplate(){
		
	}
	
	public CreatorTemplate(Integer width, Integer height){
		this.width = width;
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return "在线制作创意模板";
	}
	
	
	@Column(nullable = false, length = 64)
	private String name;
	
	
    @Column(nullable = false)
    private boolean removed;
	
	
	@Column(nullable = false)
	private Integer height;
	
	
	@Column(nullable = false)
	private Integer width;
	
	@Column(nullable = false,length=50)
	@Enumerated(EnumType.STRING)
	private CreativeType type;
	
	@Index(name="templateId")
	@Column(nullable = false, unique = true, length=50)
	private String templateId;
	
	@JsonIgnore
	@Transient
	private CreatorTemplate orig;	
	
}
