package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReloadCache implements Runnable{
	
		private String mid;
		ReloadCache(String param){
			this.mid = param;
		}
		public void run() {
			long startTime = System.currentTimeMillis();
			int i=0;
		       String hostName = "127.0.0.1";
		        int portNumber = 9002;
		        while(i < 1){
		        try (
		            Socket kkSocket = new Socket(hostName, portNumber);
		            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
		            BufferedReader in = new BufferedReader(
		                new InputStreamReader(kkSocket.getInputStream()));
		        ) {
		        	String reloadCache = "";
		        	if(mid != null)
		        	 reloadCache = "{ \"reqTyp\" : \"BRDCACHE\"	,\"reqObj\": {\"prm\":\""+mid+"\"} }\n";
		        	else{
		        		reloadCache = "{ \"reqTyp\" : \"BRDCACHE\"}\n";
		        	}
		        	System.out.println(reloadCache);
		            out.print(reloadCache);
		            out.flush();
		            String incommingMessage = "";
		            char cbuf[] = new char[4096];
					int charRead =  in.read(cbuf);
					incommingMessage = new String(cbuf);
					System.out.println("Server :"+charRead + ":"+incommingMessage);
					 Thread.sleep(1L);
		        } catch (UnknownHostException e) {
		            System.err.println("Don't know about host " + hostName);
		            System.exit(1);
		        } catch (IOException e) {
		            System.err.println("Couldn't get I/O for the connection to " +
		                hostName);
		            System.exit(1);
		        } catch (Exception e) {
		        	 System.exit(1);
				}
		        i++;
				}
		        long endTime = System.currentTimeMillis();
				System.out.println("done...." );
				
		}
		/**
		 * @return the mid
		 */
		public String getMid() {
			return mid;
		}
		/**
		 * @param mid the mid to set
		 */
		public void setMid(String mid) {
			this.mid = mid;
		}
		public static void main(String[] args) {
			String mid = null;
			
			if(args.length > 0)
				if(args[0] != null){
					mid = args[0];  
				}
			ReloadCache s  = new ReloadCache(mid);
			new Thread(s).start();
		}
}
