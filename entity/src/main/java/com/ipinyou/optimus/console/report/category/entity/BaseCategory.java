package com.ipinyou.optimus.console.report.category.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.Entity;

/**
 * 人群分类报表基础实体
 * @author zhyhang
 *
 */
@Data
@MappedSuperclass
public abstract class BaseCategory implements Entity {

	private static final long serialVersionUID = 7757188307834481199L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	/**
	 * 创建时间
	 */
	@Column(nullable=false)
	protected Timestamp creation;
		
	/**
	 * 广告主公司id
	 */
	@Index(name="advertiserCompanyId")
	protected long advertiserCompanyId;


	/**
	 * 广告主id
	 */
	@Index(name="advertiserId")
	protected long advertiserId;

	/**
	 * 人群属性id
	 */
	@Index(name="categoryId")
	protected short categoryId ;
	
	/**
	 * 日期，年-月-日
	 */
	@Index(name="day")
	@Column(nullable=false)
	protected Date day;
	
	/**
	 * 曝光pv
	 */
	protected long impPv ;

	/**
	 * 曝光uv
	 */
	protected long impUv ;
	
	/**
	 * 点击pv
	 */
	protected long clickPv ;

	/**
	 * 点击uv
	 */
	protected long clickUv ;
	
	/**
	 * 到达pv
	 */
	protected long reachPv ;

	/**
	 * 到达uv
	 */
	protected long reachUv ;
	
	/**
	 * 曝光转化pv<p>
	 * 注：包含点击转化，实际曝光转化是指：看了广告但没有点击广告产生的转化
	 */
	protected long impConvPv ;

	/**
	 * 曝光转化uv
	 */
	protected long impConvUv ;
	
	/**
	 * 点击转化pv<p>
	 * 点击品友创意带来的转化
	 */
	protected long clickConvPv ;

	/**
	 * 点击转化uv
	 */
	protected long clickConvUv ;

}
