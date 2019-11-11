package com.awl.tch.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.constant.Constants;
import com.awl.tch.events.MyEventPublisher;
import com.awl.tch.util.Property;

public class TcpServer implements Runnable {

	private ServerSocket serverSocket = null;
	private int serverPort;
	private boolean isStopped = false;
	//private RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
	
	//Extra change to increase the thread count 
	BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>() {
	    private static final long serialVersionUID = -6903933921423432194L;
	    @Override
	    public boolean offer(Runnable e) {
	        /*
	         * Offer it to the queue if there is 0 items already queued, else
	         * return false so the TPE will add another thread. If we return false
	         * and max threads have been reached then the RejectedExecutionHandler
	         * will be called which will do the put into the queue.
	         */
	        if (size() == 0) {
	            return super.offer(e);
	        } else {
	            return false;
	        }
	    }
	};
	
	private ExecutorService threadPool = new ThreadPoolExecutor(
			Property.getCoreThreadPoolSize(), Property.getMaxThreadPoolSize(),
			Property.getThreadKeepAliveTime(), TimeUnit.MICROSECONDS,
			queue, new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					try {
			            /*
			             * This does the actual put into the queue. Once the max threads
			             * have been reached, the tasks will then queue up.
			             */
			            executor.getQueue().put(r);
			        } catch (InterruptedException e) {
			            Thread.currentThread().interrupt();
			            return;
			        }
				}
			});
	
	protected Thread runningThread = null;
	private static final Logger logger = LoggerFactory
			.getLogger(TcpServer.class);
	private static ClassPathXmlApplicationContext context =ApplicationContenxtProvider.getApplicationContext(); 
	

	public TcpServer() {
		this(Property.getServerPort());
	}
	
	

	public static ClassPathXmlApplicationContext getContext() {
		return context;
	}



	private TcpServer(int serverPort) {
		super();
		this.serverPort = serverPort;

		if (Property.isMonitorEnabled()) {
			MonitorThread monitor = new MonitorThread(
					(ThreadPoolExecutor) threadPool,
					Property.getTimeBetweenMonitorThreadRuns());
			Thread monitorThread = new Thread(monitor);
			monitorThread.start();
		}

	}

	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e) {
			//e.printStackTrace();
			throw new RuntimeException("Cannot open port "+serverPort, e);
		}
	}

	public synchronized boolean isStopped() {
		return isStopped;
	}

	public synchronized void stop() {
		try {
			this.isStopped = true;
			this.serverSocket.close();
		} catch (IOException e) {
			//e.printStackTrace();
			throw new RuntimeException("Error closing server", e);
		}
	}

	@Override
	public void run() {
		
		/*synchronized (this) {
			this.runningThread = Thread.currentThread();
		}*/
		openServerSocket();
		load();
		Socket clientSocket = null;
		while (!isStopped()) {
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if (isStopped()) {
					logger.info("Server Stopped.");
					// System.out.println("Server Stopped.") ;
					break;
				}
				throw new RuntimeException("Error accepting client connection",e);
			}
			this.threadPool.execute(new RequestHandlerTask(clientSocket));
			clientSocket = null;
		}
		this.threadPool.shutdown();
		logger.info("Server Stopped.");
		// System.out.println("Server Stopped.") ;
	}

	private void load(){
		MyEventPublisher publisher = (MyEventPublisher) context.getBean(Constants.TCH_EVENT); 
		publisher.publish();
	}
}
