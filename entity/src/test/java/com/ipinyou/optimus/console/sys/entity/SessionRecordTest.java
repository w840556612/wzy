/**
 * 
 */
package com.ipinyou.optimus.console.sys.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author lijt
 * 
 */
public class SessionRecordTest {

	@Test
	public void testSerialize() {
		SessionRecord sr = new SessionRecord();
		sr.setAttribute("key", "test");
		sr.serialize();
		sr.getAttributes().clear();
		sr.deserialize();
		assertEquals("test", sr.getAttribute("key"));
	}

}
