/**
 * 
 */
package com.ipinyou.base.entity.listener;

import java.sql.Timestamp;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.ipinyou.base.entity.TimedEntity;

/**
 * @author lijt
 * 
 */
public class TimedEntityListener {

	@PrePersist
	public void onPrePersist(TimedEntity e) {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		e.setCreation(t);
		e.setLastModified(t);
	}

	@PreUpdate
	public void onPreUpdate(TimedEntity e) {
		e.setLastModified(new Timestamp(System.currentTimeMillis()));
	}
}
