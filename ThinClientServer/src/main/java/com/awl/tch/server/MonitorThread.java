package com.awl.tch.server;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorThread implements Runnable {
	private ThreadPoolExecutor executor;

	private int seconds;

	private boolean run = true;

	private static final Logger logger = LoggerFactory
			.getLogger(MonitorThread.class);

	public MonitorThread(ThreadPoolExecutor executor, int delay) {
		this.executor = executor;
		this.seconds = delay;
	}

	public void shutdown() {
		this.run = false;
	}

	@Override
	public void run() {
		while (run) {
			/*
			 * System.out.println( String.format(
			 * "[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s"
			 * , this.executor.getPoolSize(), this.executor.getCorePoolSize(),
			 * this.executor.getActiveCount(),
			 * this.executor.getCompletedTaskCount(),
			 * this.executor.getTaskCount(), this.executor.isShutdown(),
			 * this.executor.isTerminated()));
			 */
			logger.info(String
					.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
							this.executor.getPoolSize(),
							this.executor.getCorePoolSize(),
							this.executor.getActiveCount(),
							this.executor.getCompletedTaskCount(),
							this.executor.getTaskCount(),
							this.executor.isShutdown(),
							this.executor.isTerminated()));
			try {
				Thread.sleep(seconds * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}