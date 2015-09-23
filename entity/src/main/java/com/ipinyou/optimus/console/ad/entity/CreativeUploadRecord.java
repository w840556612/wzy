package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.TimedEntity;


@Entity
@Data
@Table(appliesTo = "creative_upload_record", indexes={
		@Index(name="userid_day_hour_idx", columnNames = {"user_id", "day", "hour"})
})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"creation", "lastModified"})
public class CreativeUploadRecord extends TimedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4116605175612956296L;
	
	
	public CreativeUploadRecord(){
		
	}
	
	public CreativeUploadRecord(Long userId, java.sql.Date day, int hour, int amount){
		this.userId = userId;
		this.day = day;
		this.hour = hour;
		this.amount = amount;
	}
	
	@NotNull
	@Column(nullable = false)
	private Long userId;
	
	
	@Column(nullable = false)
	@DateTimeFormat(iso=ISO.DATE)
	private java.sql.Date day;
	
	
	@Column(nullable = false)
	private int hour;
	
	
	@Column(nullable = false)
	private int amount;

}
