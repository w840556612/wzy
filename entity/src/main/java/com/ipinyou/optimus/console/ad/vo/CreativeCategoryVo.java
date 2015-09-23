package com.ipinyou.optimus.console.ad.vo;

import java.sql.Timestamp;
import java.util.Set;

import lombok.Data;

import com.ipinyou.optimus.console.ad.entity.Creative;
import com.ipinyou.optimus.console.ad.entity.Creative.CreativeType;
import com.ipinyou.optimus.console.ad.entity.CreativeCategoryRel;
import com.ipinyou.optimus.console.ad.entity.CreativeKeyword;
import com.ipinyou.optimus.console.ad.entity.CreativeTag;

@Data
public class CreativeCategoryVo {

	public CreativeCategoryVo(Creative creative) {
		this.id = creative.getId();
		this.tags = creative.getTags();
		this.creation = creative.getCreation();
		this.name = creative.getName();
		this.advertiserName=creative.getAdvertiser().getName();
		this.width = creative.getWidth();
		this.height = creative.getHeight();
		this.type = creative.getType();
		this.path = creative.getPath();
		this.thumbnail = creative.getThumbnail();
		this.creativeCategoryRel = creative.getCreativeCategoryRel();
		this.creativeKeyword = creative.getCreativeKeyword();
	}
	
	private Long id;
	
	private Set<CreativeTag> tags;
	
	private String name;
	
	private String advertiserName;

	private int width;

	private int height;

	private CreativeType type;

	private String path;

	private String thumbnail;
	
	private Timestamp creation;

	private String targetUrl;
	
	private CreativeCategoryRel creativeCategoryRel;
	
	private CreativeKeyword creativeKeyword;
}
