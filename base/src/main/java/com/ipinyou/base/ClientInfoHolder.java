package com.ipinyou.base;

import com.ipinyou.base.model.ClientInfo;

/**
 * 使用TheadLocal方式保存客户端信息(ClientInfo)，主要提供给com.ipinyou.base.filter.
 * SetClientInfoFilter调用，业务层在记录审计日志时会通从ThreadLocal中获取相关数据
 * 
 * @author lijt
 * 
 */
public class ClientInfoHolder {

	/**
	 * 客户信息
	 */
	private static ThreadLocal<ClientInfo> client_info = new ThreadLocal<ClientInfo>();

	/**
	 * 是否通过通用框架记录日志
	 */
	private static ThreadLocal<Boolean> operation_log = new ThreadLocal<Boolean>();

	private ClientInfoHolder() {
		super();
	}

	/**
	 * @return 获取当前线程中的ClientInfo
	 */
	public static ClientInfo getClientInfo() {
		return client_info.get();
	}

	/**
	 * @param client
	 *            存储ClientInfo到当前线程
	 */
	public static void setClientInfo(ClientInfo client) {
		client_info.set(client);
	}

	public static boolean getOperationLog() {
		Boolean b = operation_log.get();
		return b != null ? b : false;
	}

	public static void setOperationLog(boolean log) {
		operation_log.set(log);
	}
}
