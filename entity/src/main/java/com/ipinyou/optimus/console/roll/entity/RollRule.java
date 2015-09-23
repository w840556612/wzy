package com.ipinyou.optimus.console.roll.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.listener.PinyinListener;
import com.ipinyou.optimus.console.roll.vo.DisplayAdvertiserVo;
/**
 * 创意轮播规则
 * 
 * @author baozhu.cao
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig", "advertisers"})
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "advertisers"})
@JsonIgnoreProperties(value = { "version", "lastModified", "orig", "advertisers"})
@EntityListeners({ PinyinListener.class })
public class RollRule extends TimedEntity implements Auditable<RollRule>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.roll.entity.RollRull.1001")/*创意轮播规则*/;
	}

	@Size(min = 2, max = 64)
	@Column(length = 64)
	@NotNull
	@Index(name = "name")
	private String name;
	
	@NotNull
	private boolean removed = false;
	
	@NotNull
	private boolean active = false;
	
	@NotNull
	private boolean original = true;
	
	@Transient
	private RollRule orig;
	
	@Transient
	private List<DisplayAdvertiserVo> advertisers; 
}
