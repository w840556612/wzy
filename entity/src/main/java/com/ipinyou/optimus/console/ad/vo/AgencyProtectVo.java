package com.ipinyou.optimus.console.ad.vo;

import java.sql.Timestamp;

import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.ad.entity.Advertiser.ProtectStatus;
import com.ipinyou.optimus.console.ad.entity.AgencyProtectHistory;
import com.ipinyou.optimus.console.user.entity.Pool;

import lombok.Data;



@Data
public class AgencyProtectVo {
	
	private Long advertiserId;
	
	private String registerName;
	
	private String protectContactName;
	private String protectCellphone;
	private String province;
	private String city;
	private String protectWebsite;
	private ProtectStatus protectStatus = ProtectStatus.NotProtect;
	private Pool pool;
	private Pool protectPool;
	private Timestamp protectStart;
	private Timestamp protectEnd;
	private String address;
	
	
	
	
	public AgencyProtectVo(Advertiser advertiser,AgencyProtectHistory history){
		this.advertiserId = advertiser.getId();
		this.registerName = advertiser.getRegisterName();
		this.pool         = advertiser.getPool();
		this.address      = advertiser.getAddress();
		if(history!=null){
			this.protectContactName = history.getProtectContactName();
			this.protectCellphone   = history.getProtectCellphone();
			this.province           = history.getProvince();
			this.city               = history.getCity();
			this.protectWebsite     = history.getProtectUrl();
			this.protectPool        = history.getProtectPool();
			this.protectStart       = history.getProtectStart();
			this.protectEnd         = history.getProtectEnd();
		}else{
			this.protectWebsite     = advertiser.getWebsite();
			this.protectContactName = "["+advertiser.getContactName()+"]";
			this.protectCellphone   = "["+advertiser.getProtectCellphone()+"]";
		}
	}
	
	
	private static final long MILLI_SECOND_PER_DAY = 1000 * 60 * 60 * 24;
	private static final long MILLI_SECOND_PER_HOUR = 60 * 60 * 1000;
	private static final long HOUR_PER_DAY = 24;

	public long getProtectedDays() {
		if (protectStart == null) {
			return 0;
		}
		long l = System.currentTimeMillis() - protectStart.getTime();
		long day = l / MILLI_SECOND_PER_DAY;
		return day;
	}

	public long getProtectedDayHours() {
		if (protectStart == null) {
			return 0;
		}
		long l = System.currentTimeMillis() - protectStart.getTime();
		long day = l / MILLI_SECOND_PER_DAY;
		long hour = (l / MILLI_SECOND_PER_HOUR - day * HOUR_PER_DAY);
		return hour;
	}

	public long getProtectRemainDays() {
		if (protectEnd == null) {
			return 0;
		}
		long l = protectEnd.getTime() - System.currentTimeMillis();
		long day = l / MILLI_SECOND_PER_DAY;
		return day;
	}

	public long getProtectRemainDayHours() {
		if (protectEnd == null) {
			return 0;
		}
		long l = protectEnd.getTime() - System.currentTimeMillis();
		long day = l / MILLI_SECOND_PER_DAY;
		long hour = (l / MILLI_SECOND_PER_HOUR - day * HOUR_PER_DAY);
		return hour;
	}
}
