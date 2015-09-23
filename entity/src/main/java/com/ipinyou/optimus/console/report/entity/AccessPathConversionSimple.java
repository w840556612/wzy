package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.ToString;

/**
 * 广告主网站访客访问轨迹之转化轨迹，去除参数
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_access_path_conversion_simple")
@ToString(callSuper = true)
public class AccessPathConversionSimple extends BaseAccessPath {
	
	private static final long serialVersionUID = -5212987269046960319L;
	
}
