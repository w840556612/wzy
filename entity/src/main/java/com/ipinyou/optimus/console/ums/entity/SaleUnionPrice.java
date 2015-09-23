package com.ipinyou.optimus.console.ums.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

@Entity
@Table(name = "ums_price")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SaleUnionPrice extends TimedEntity implements Auditable<SaleUnionPrice> ,java.io.Serializable{

	private static final long serialVersionUID = -6118264128581669696L;

	@Override
    public String getEntityName() {
	    return "营销联盟套餐价格";
    }
	
	@Transient
	private SaleUnionPrice orig;
	
	/**
	 * 名称
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	private String name;
	
	/**
	 * 价格
	 */
	private BigDecimal price;
	
	/**
	 * 日曝光
	 */
	private long impl;
	 
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

}
