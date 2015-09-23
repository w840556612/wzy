package com.ipinyou.optimus.console.media.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import lombok.Data;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.StrategyAdUnit.AdUnitIdList;
import com.ipinyou.optimus.console.model.Platform;

@Entity
@Data
public class MediaAdunitGroup extends TimedEntity implements Auditable<MediaAdunitGroup>{

	private static final long serialVersionUID = 1L;
	

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public MediaAdunitGroup getOrig() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setOrig(MediaAdunitGroup t) {
		// TODO Auto-generated method stub
	
	}
	
	@NotNull
	@Column(length = 40)
	private String name;
	
	@NotNull
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Platform platform;
	
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="media_adunit_ids", length=65535))
	private AdUnitIdList mediaAdUnitIds;
	
	@NotNull
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private AdUnitDisplayType displayType;
	
	public enum AdUnitDisplayType{
		
		rightPopup("右下弹窗"),preMovieAd("前贴"),pause("暂停"),overlay("overlay");
		
		private String text;
		
		private AdUnitDisplayType(String text){
			this.text = text;
		}
		
		public String getText(){
			return this.text;
		}
	}
	
	private boolean removed=false;
}
