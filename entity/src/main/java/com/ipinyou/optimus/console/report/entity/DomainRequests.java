package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author ganwenlong
 * 
 */
@Entity
@Data
public class DomainRequests implements com.ipinyou.base.entity.Entity {

	private static final long serialVersionUID = 6948855407377520865L;
	
	@Id
	@Column(length = 300, nullable = false)
	private String domain;
	@Id
	private Date day;
	private long requests;
	
	
}
