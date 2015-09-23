package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.ToString;

/**
 * 页面流失，不去除参数
 * @author yousheng.zhao
 */
@Entity
@Table(name = "rpt_page_loss_full")
@ToString(callSuper = true)
public class PageLossFull extends BasePageLoss{
	
	private static final long serialVersionUID = 266074133191532757L;

}




