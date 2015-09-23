/**
 * 
 */
package com.ipinyou.base.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.AbstractFlashMapManager;

/**
 * @author lijt
 * 
 */
public class CacheFlashMapManager extends AbstractFlashMapManager {

	private Ehcache flashMapCache;

	public void setFlashMapCache(Ehcache flashMapCache) {
		this.flashMapCache = flashMapCache;
	}

	/**
	 * Retrieve saved FlashMap instances from the cache.
	 */
	@SuppressWarnings("unchecked")
	protected List<FlashMap> retrieveFlashMaps(HttpServletRequest request) {
		String sid = request.getSession().getId();
		Element e = flashMapCache.get(sid);
		if (e == null) {
			return null;
		} else {
			return (List<FlashMap>) e.getObjectValue();
		}
	}

	/**
	 * Save the given FlashMap instance, if not empty, in the cache.
	 */
	protected void updateFlashMaps(List<FlashMap> flashMaps,
			HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getSession().getId();
		flashMapCache.put(new Element(sid, flashMaps));
	}

}
