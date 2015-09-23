/**
 * 
 */
package com.ipinyou.base.install;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author lijt
 * 
 */
public abstract class AbsDbInstaller implements Installer {

	@Value("${auto.ddl}")
	private String autoDdl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.install.Installer#getOrder()
	 */
	@Override
	public int getOrder() {
		return 0;
	}

	protected boolean updateDb() {
		return "update".equalsIgnoreCase(autoDdl)
				|| "create".equalsIgnoreCase(autoDdl)
				|| "create-drop".equalsIgnoreCase(autoDdl);
	}

	@Override
	public void run() {
		if(updateDb()){
			runInternal();
		}
	}

	protected abstract void runInternal();
	
}
