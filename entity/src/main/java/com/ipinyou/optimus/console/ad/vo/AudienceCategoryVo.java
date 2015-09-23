package com.ipinyou.optimus.console.ad.vo;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.optimus.console.ad.entity.AudienceCategory;

/**
 * @author yaohl
 *
 */
@Data
@EqualsAndHashCode(of={"id"})
@ToString(callSuper = true)
@JsonIgnoreProperties({"fatherReference"})
public class AudienceCategoryVo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6427989362273931154L;
	
	private Integer id;
	private String name;
	private String title;
	private boolean nocheck;
	private boolean checked=false;
	private boolean open=false;
	private Integer parentId;
	
	
	
	private boolean father;
	private boolean choosable = true;
	private BigDecimal ratio = new BigDecimal(1.0);
	private boolean visible;
	
	private Set<AudienceCategoryVo> children;
	private AudienceCategoryVo fatherReference;
	private boolean matched = false;
	
	public AudienceCategoryVo(AudienceCategory ac) {
		this.id = ac.getId();
		this.name = ac.getName();
		this.father = ac.isFather();
		this.choosable = ac.isChoosable();
		this.ratio = ac.getRatio();
		this.visible = ac.isVisible();
		this.parentId = ac.getParentId();
	}
	
	
	public AudienceCategoryVo(Integer id,Integer pId,String rawData,String name,boolean nocheck) {
		this.id = id;
		this.parentId = pId;
		this.name = name;
		this.title=rawData;
		this.nocheck = nocheck;
	}
	
	public AudienceCategoryVo(Short id,Short pId,String rawData,String name,boolean nocheck) {
		this.id = id != null ? new Integer(String.valueOf(id)) : id; 
		this.parentId = pId != null ? new Integer(String.valueOf(pId)) : pId;
		this.name = name;
		this.title=rawData;
		this.nocheck = nocheck;
	}
}
