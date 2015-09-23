/**
 * 
 */
package com.ipinyou.base.autoreloadconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import lombok.Data;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author lijt
 * @author ganwenlong
 */
public class ConfigHolderTest {
	@BeforeClass
	public static void beforeClass(){
		System.setProperty("ipinyou.home","/tmp");
	}

	@Test
	public void testConstructor() { 
		try { 
			new ConfigHolder<Long>(null, null, null); 
			assertTrue(false); 
		} catch(IllegalArgumentException e) { 
			assertEquals("confGenerator is null.", e.getMessage()); 
		} 
		try { 
			new ConfigHolder<Long>(new ConfigHolder.ReloadableConfigGenerator<Long>() {
				@Override
				public Long generate(PropertiesConfiguration conf) { return null; } 
			}, null, null); 
			assertTrue(false); 
		} catch(IllegalArgumentException e) { 
			assertEquals("confDirectoryName is null.", e.getMessage()); 
		} 
		try { 
			new ConfigHolder<Long>(new ConfigHolder.ReloadableConfigGenerator<Long>() {
				@Override
				public Long generate(PropertiesConfiguration conf) { return null; } 
			}, "", null); 
			assertTrue(false); 
		} catch(IllegalArgumentException e) { 
			assertEquals("confFileName is null.", e.getMessage()); 
		} 
		try { 
			new ConfigHolder<Long>(new ConfigHolder.ReloadableConfigGenerator<Long>() {
				@Override
				public Long generate(PropertiesConfiguration conf) { return null; } 
			}, "ss  ssd", "x  f"); 
			assertTrue(false); 
		} catch(RuntimeException e) { 
			assertTrue(e.getCause() instanceof ConfigurationException); 
		} 
		new ConfigHolder<Long>(new ConfigHolder.ReloadableConfigGenerator<Long>() {
			@Override
			public Long generate(PropertiesConfiguration conf) { return null; } 
		}, System.getProperty("ipinyou.home") + File.separator + "config", "autoreloadconfig.properties"); 
	} 
	
	@Data
	private static class ReloadableTest { 
		private int a; 
		private int b; 
	} 
	private ReloadableTest buffConfig; 
	private ConfigHolder.ReloadableConfigGenerator<ReloadableTest> generator = 
			new ConfigHolder.ReloadableConfigGenerator<ReloadableTest>() {
				@Override
				public ReloadableTest generate(PropertiesConfiguration conf) { 
					ReloadableTest test = new ReloadableTest(); 
					buffConfig = test; 
					return test;
				} 
	}; 
	@Test
	public void testReload() throws IOException, InterruptedException { 
		URL url = ClassLoader.getSystemResource(""); 
		String dir = url.getFile();  
		File file = new File(dir, "test-autoreloadconfig.properties"); 
		
		FileUtils.writeStringToFile(file, "a=1\nb=2", false); 
		ConfigHolder<ReloadableTest> holder = new ConfigHolder<ReloadableTest>(
				generator, dir, file.getName(), 10000); 
		ReloadableTest config1 = holder.getConfig(); 
		assertEquals(buffConfig, config1); 
		
		FileUtils.writeStringToFile(file, "a=4\nb=5", false); 
		ReloadableTest config2 = holder.getConfig(); 
		assertEquals(config1, config2); 
		assertEquals(buffConfig, config2); 
		
		Thread.sleep(9000); 
		
		FileUtils.writeStringToFile(file, "a=4\nb=5", false); 
		ReloadableTest config3 = holder.getConfig(); 
		assertEquals(config1, config3); 
		assertEquals(buffConfig, config3); 
		
		Thread.sleep(1010); 
		
		FileUtils.writeStringToFile(file, "a=4\nb=5", false); 
		ReloadableTest config4 = holder.getConfig(); 
		assertTrue(config1 != config4); 
		assertEquals(buffConfig, config4); 
		
		holder.stopReload(); 
		
		Thread.sleep(11000); 
		ReloadableTest config5 = holder.getConfig(); 
		assertEquals(config4, config5); 
		assertEquals(buffConfig, config4); 
		
		file.delete(); 
	} 
	
}
