/**
 * 
 */
package com.ipinyou.base.entity.listener;

import java.sql.Timestamp;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.ipinyou.base.entity.NoIdTimedEntity;

/**
 * @author lijt
 * 
 */
public class NoIdTimedEntityListener {

	@PrePersist
	public void onPrePersist(NoIdTimedEntity e) {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		e.setCreation(t);
		e.setLastModified(t);
	}

	@PreUpdate
	public void onPreUpdate(NoIdTimedEntity e) {
		e.setLastModified(new Timestamp(System.currentTimeMillis()));
	}
}
