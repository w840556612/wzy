/**
 * 
 */
package com.ipinyou.base.theme;

import static org.junit.Assert.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author lijt
 * 
 */
public class LocaleTest {
	@BeforeClass
	//
	public static void beforeClass() {
		System.setProperty("user.language", "en");
		System.setProperty("user.region", "US");
	}

	@Test
	public void testLocale() {
		ResourceBundle messages = ResourceBundle.getBundle("localetest/test",
				new Locale("zh", "CN"));
		assertEquals("a_zh_CN", messages.getString("a"));
		assertEquals("b_zh", messages.getString("b"));
		assertEquals("c_default", messages.getString("c"));
		messages = ResourceBundle.getBundle("localetest/test", new Locale("en",
				"US"));
		assertEquals("a_en", messages.getString("a"));
		assertEquals("b_default", messages.getString("b"));
		assertEquals("c_default", messages.getString("c"));
	}

	@Test
	public void testLocale2() {
		System.out.println(System.getProperty("user.language"));
		System.out.println(System.getProperty("user.region"));
		ResourceBundle messages = ResourceBundle.getBundle("localetest/test2",
				new Locale("zh", "CN"));
		assertEquals("a_zh", messages.getString("a"));
		messages = ResourceBundle.getBundle("localetest/test2", new Locale(
				"en", "US"));
		assertEquals("a_en", messages.getString("a"));
		messages = ResourceBundle.getBundle("localetest/test2", new Locale(
				"fr", "CA"));
		assertEquals("a_en", messages.getString("a"));
	}
}
