package com.awl.tch.main;

import com.awl.tch.server.TcpServer;

public class StartServer {
	
	public static void main(String[] args) {
		TcpServer server = new TcpServer();
		new Thread(server).start();
		
		/*try {
		    Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Stopping Server");
		server.stop();*/
	}

}
