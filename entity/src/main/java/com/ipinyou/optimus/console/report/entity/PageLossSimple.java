package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.ToString;

/**
 * 页面流失，去除参数
 * @author yousheng.zhao
 */
@Entity
@Table(name = "rpt_page_loss_simple")
@ToString(callSuper = true)
public class PageLossSimple extends BasePageLoss{
	
	private static final long serialVersionUID = 7948603593734967542L;

}




