package com.awl.tch.server;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RejectedExecutionHandlerImpl.class);

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// TODO Auto-generated method stub
		//System.out.println(r.toString() + " is rejected");
		logger.info(r.toString() + " is rejected");
	}

}
