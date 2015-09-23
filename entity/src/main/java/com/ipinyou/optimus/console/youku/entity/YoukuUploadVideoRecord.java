package com.ipinyou.optimus.console.youku.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.TimedEntity;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "creative_upload_youku_record")
public class YoukuUploadVideoRecord extends TimedEntity{

    private static final long serialVersionUID = -5057232665023432604L;
    
    @Index(name="creativeId")
    @Column(nullable = false, unique = true)
    private Long creativeId;
    
	@Column(nullable = false, length = 255)
    private String filePath;
    
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = false)
    private Timestamp beginTime;
    
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = true)
    private Timestamp endTime;
	
	@Column(nullable = true, length = 20)
	private String status;
    
	@Column(nullable = true, length = 64)
	private String videoId;
	
}
