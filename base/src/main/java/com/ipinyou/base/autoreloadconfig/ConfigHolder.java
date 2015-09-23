/**
 * 
 */
package com.ipinyou.base.autoreloadconfig;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 持有配置文件的引用对象
 * 
 * @author lijt
 * @author ganwenlong  ---2013-2-19 进行了部分修改，以实现应公用。
 */
public class ConfigHolder<T> { 

	private static final Logger logger = LoggerFactory
			.getLogger(ConfigHolder.class);

	/** ReloadableConfig(T)对象的生成器 */
	private final ReloadableConfigGenerator<T> ConfGenerator; 
	/** 配置文件所在的目录 */
	private final String ConfDirectoryName; 
	/** 配置文件的文件名 */
	private final String ConfFileName; 
	/** 配置文件的监控器，监控文件是否发生改变 */
	private final FileAlterationMonitor ConfMonitor; 
	/** 当前持有的配置对象 */
	private volatile T conf; 
	
	/**
	 * 
	 * @author ganwenlong
	 * 2013-2-19 下午2:13:45
	 * @param confGenerator
	 * @param confDirectoryName
	 * @param confFileName
	 */
	public ConfigHolder(ReloadableConfigGenerator<T> confGenerator, 
			String confDirectoryName, String confFileName) { 
		this(confGenerator, confDirectoryName, confFileName, 15000); 
		
	}
	public ConfigHolder(ReloadableConfigGenerator<T> confGenerator, 
			String confDirectoryName, String confFileName, long interval) { 
		
		Assert.notNull(confGenerator, "confGenerator is null."); 
		Assert.notNull(confDirectoryName, "confDirectoryName is null."); 
		Assert.notNull(confFileName, "confFileName is null."); 
		ConfGenerator = confGenerator; 
		ConfDirectoryName = confDirectoryName; 
		ConfFileName = confFileName; 

		/* 初始化时进行第一次读取，需要保证配置文件读取正确。 */
		PropertiesConfiguration pconf = null;
		try {
			pconf = new PropertiesConfiguration(ConfDirectoryName + File.separator + ConfFileName);
		} catch (ConfigurationException e) { 
			logger.error("启动ConfigHolder时加载" + ConfDirectoryName + File.separator + ConfFileName + 
					"文件出错，将抛出异常。错误信息：{} \t详细信息: {}",
					e.getLocalizedMessage(), ExceptionUtils.getStackTrace(e)); 
			throw new RuntimeException(e);  //如果不正确则报错。
		} 
		/* 初始化配置对象 */
		reload(pconf); 

		/* 初始化完成后，开启配置文件监控器 */
		ConfMonitor = new FileAlterationMonitor(interval); 
		initFileMonitor(); 
	} 
	private void initFileMonitor() { 
		FileAlterationObserver observer = 
				new FileAlterationObserver(ConfDirectoryName, new NameFileFilter(ConfFileName));
		observer.addListener(new FileAlterationListenerAdaptor() {
			@Override
			public void onFileChange(File file) {
				logger.info("文件变更了: {}, 开始更新配置文件。", file.getAbsolutePath());
				/** 当文件变化后执行刷新代码。 */
				reload();  
				logger.info("更新配置文件: {} 完成",file.getAbsolutePath());
			}
			@Override
			public void onStart(FileAlterationObserver observer) {
				logger.debug("开始监听:" + observer.getDirectory());
			}
			@Override
			public void onStop(FileAlterationObserver observer) {
				logger.debug("停止监听:" + observer.getDirectory());
			}
		}); 
		ConfMonitor.addObserver(observer);
		try {
			ConfMonitor.start(); 
		} catch (Exception e) {
			logger.error("启动配置文件监听器失败，错误信息：{} \t详细信息: {}" , 
					e.getLocalizedMessage(), ExceptionUtils.getStackTrace(e)); 
			throw new RuntimeException(e);  //运行期间必须要有配置文件监控器。
		} 
	} 
	
	/**
	 * 停止热加载---
	 * 关闭配置文件监控器
	 * @author ganwenlong
	 * 2013-2-19 下午2:40:26
	 */
	public void stopReload(){
		try {
			ConfMonitor.stop();
		} catch (Exception e) {
			logger.warn("停止配置文件监听器失败，错误信息：{} \t详细信息: {}", 
					e.getLocalizedMessage(), ExceptionUtils.getStackTrace(e)); 
		}
	}
	
	/**
	 * 重新加载配置文件
	 * @author ganwenlong
	 * 2013-2-19 下午2:42:05
	 */
	private void reload() { 
		/* 在运行期间如果读取配置文件出错，则不更新配置文件对象，但要报警。 */
		PropertiesConfiguration pconf = null; 
		try {
			pconf = new PropertiesConfiguration(ConfDirectoryName + File.separator + ConfFileName);
		} catch (ConfigurationException e) { 
			logger.warn("加载" + ConfDirectoryName + File.separator + ConfFileName + 
					"文件出错，暂不执行热加载，错误信息：{} \t详细信息: {}",
					e.getLocalizedMessage(), ExceptionUtils.getStackTrace(e)); 
			return; 
		} 
		reload(pconf); 
	}
	private void reload(PropertiesConfiguration pconf) { 
		conf = ConfGenerator.generate(pconf); 
	}
	
	/**
	 * 获取当先的配置对象
	 * @author ganwenlong
	 * 2013-2-19 下午2:42:37
	 * @return
	 */
	public T getConfig() {
		return conf;
	}
	
	
	/**
	 * 配置文件生成器，
	 * 子实现需要考虑，generate不一定会串行调用的。
	 * @author ganwenlong
	 * 2013-2-19 下午2:43:01
	 * @param <T>
	 */
	public static interface ReloadableConfigGenerator<T> {
		T generate(PropertiesConfiguration conf); 
	}
	
}
