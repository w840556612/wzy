package com.ipinyou.optimus.console.ad.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;


@Entity
@Data
public class CreativeCategoryRel{
	
	@Id
	private Long creativeId;
	
	
	@Column(length = 500)
	private String categorys;
	

	@Column
	private Long operatorId;
	
	
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(updatable = false, nullable = false)
	private Timestamp creation;

	/**
	 * 最后修改时间
	 */
	@Index(name="lastModified")
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = false)
	private Timestamp lastModified;
	
	@Transient
	private List<AudienceCategory> categoryList;
	
}
