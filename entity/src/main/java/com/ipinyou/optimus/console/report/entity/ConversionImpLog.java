package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.optimus.console.report.entity.BaseLogStatus.ImpActionStatus;

/**
 * 曝光转化详细信息
 * 
 * @author guodong.zhang
 */
@Entity
@Table(name = "conversion_imp_log")
@org.hibernate.annotations.Table( indexes = {@Index(name="orderNoKey", columnNames={"order_no", "advertiser_company_id"} ) }, appliesTo = "conversion_imp_log" )
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ConversionImpLog extends BaseConversionLogDetail implements ImpActionStatus {
	
	private static final long serialVersionUID = -5964196174393871274L;
	
	@Embedded
	private ImpInfo impInfo;


	@Index(name = "clientConfirmPy")
	private boolean clientConfirmPy;
	private boolean matchDsp;
	@Index(name = "matchImp")
	private boolean matchImp;
	
}
