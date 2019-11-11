package com.awl.tch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Property{
	/**
	 * 
	 */
	private static int maxThreadPoolSize;
	private static int serverPort;
	private static boolean isPropertyLoaded;
	private static boolean monitorEnabled;
	private static int threadKeepAliveTime;
	private static int coreThreadPoolSize;
	private static int timeBetweenMonitorThreadRuns;
	private static boolean allowCoreThreadTimeOut;
	private static int incommingMsgBuffer;
	private static String magnusIp;
	private static int magnusPort;
	private static int magnusReadTimeout;
	private static boolean showSql;
	private static String env;
	
	static {
		Property.load();
	}

	public static void load() {	


		try {
			Properties ep = new Properties();
			//InputStream in = Property.class.getResourceAsStream("/application.properties");
			File f= new File("application.properties");
			FileInputStream fis = new FileInputStream(f);
			ep.load(fis);
			
			maxThreadPoolSize = Integer.parseInt(ep.getProperty("maxThreadPoolSize"));
			serverPort = Integer.parseInt(ep.getProperty("serverPort"));
			threadKeepAliveTime = Integer.parseInt(ep.getProperty("threadKeepAliveTime"));
			coreThreadPoolSize = Integer.parseInt(ep.getProperty("coreThreadPoolSize"));
			allowCoreThreadTimeOut = Boolean.parseBoolean(ep.getProperty("allowCoreThreadTimeOut"));
			timeBetweenMonitorThreadRuns = Integer.parseInt(ep.getProperty("timeBetweenMonitorThreadRuns"));
			incommingMsgBuffer = Integer.parseInt(ep.getProperty("incommingMsgBuffer"));
			monitorEnabled = Boolean.parseBoolean(ep.getProperty("enableMonitor"));
			magnusIp =  ep.getProperty("magnusIp");
			magnusPort = Integer.parseInt(ep.getProperty("magnusPort"));
			magnusReadTimeout = Integer.parseInt(ep.getProperty("magnusReadTimeout"));
			showSql = Boolean.parseBoolean(ep.getProperty("showSql"));
			env=ep.getProperty("enviroment");
			isPropertyLoaded = true;
			
		}
		catch (Exception e) {
			isPropertyLoaded = false;
			System.out.println("Couldn't access properties file:application.properties from installation folder" );
			e.printStackTrace();
		}
	}
	
	
	public static boolean allowCoreThreadTimeOut(){
		return allowCoreThreadTimeOut;
	}
	
	public static int getMaxThreadPoolSize() {
		return maxThreadPoolSize;
	}

	public static int getServerPort() {
		return serverPort;
	}

	public static boolean isPropertyLoaded() {
		return isPropertyLoaded;
	}

	public static int getThreadKeepAliveTime() {
		return threadKeepAliveTime;
	}

	public static int getCoreThreadPoolSize() {
		return coreThreadPoolSize;
	}

	public static int getTimeBetweenMonitorThreadRuns() {
		return timeBetweenMonitorThreadRuns;
	}

	public static int getIncommingMsgBuffer() {
		return incommingMsgBuffer;
	}

	public static boolean isMonitorEnabled() {
		return monitorEnabled;
	}

	public static String getMagnusIp() {
		return magnusIp;
	}

	public static int getMagnusPort() {
		return magnusPort;
	}

	public static int getMagnusReadTimeout() {
		return magnusReadTimeout;
	}

	public static boolean isShowSql() {
		return showSql;
	}

	/**
	 * @return the env
	 */
	public static String getEnv() {
		return env;
	}
	
	
	
	
	
	
	
}

