package com.awl.tch.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.util.Property;

public class RequestHandlerTask implements Runnable {

	protected Socket clientSocket = null;
	
	private static ServerFrontController frontController = ServerFrontController.getInstance();
	
	private static final Logger logger = LoggerFactory.getLogger(RequestHandlerTask.class);

	public RequestHandlerTask(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
			
		BufferedReader reader = null;
		PrintWriter writer = null;
		String outgoingMessage = "";
		try {
			int red = -1;
			byte[] buffer = new byte[Property.getIncommingMsgBuffer()]; // a read buffer of 5KiB
			byte[] redData;
			StringBuilder clientData = new StringBuilder();
			String redDataText;
			while ((red = clientSocket.getInputStream().read(buffer)) > -1) {
				logger.debug("Length received from socket " + red);
			    redData = new byte[red];
			    System.arraycopy(buffer, 0, redData, 0, red);
			    redDataText = new String(redData,"UTF-8"); // assumption that client sends data UTF-8 encoded
			    if(redDataText.charAt(redDataText.length()-1) == '\n'){ //client send \n  at the end of evry request
			    	clientData.append(redDataText);
			    	logger.debug("Length received client data " + clientData.length());
			    		break;
			    }else{
			    	clientData.append(redDataText);
			    	continue;
			    }
			}
			
			
			if(red != -1 || !clientData.toString().trim().isEmpty()){
				logger.debug("Request Server ip :" + clientSocket.getInetAddress());
				if(clientData.toString().contains("trk2")){
					int i = clientData.indexOf("trk2");
					int j = clientData.substring(i).indexOf(",")-1;
					String temp = clientData.substring(i,i+j);
					temp = clientData.toString().replaceAll(temp, "trk2\":\" ").trim();
					logger.info("Client Thread:" + Thread.currentThread().getName()
							+ "|Incoming message Length :" + red
							+ "|Incoming message |" + temp);
				}else{
					logger.info("Client Thread:" + Thread.currentThread().getName()
							+ "|Incoming message Length :" + red
							+ "|Incoming message |" + clientData.toString().trim());
			}
				writer = new PrintWriter(clientSocket.getOutputStream(), true);
				outgoingMessage = frontController.forward(clientData.toString().trim());
			}
			
			try{
				Properties ep = new Properties();
				FileInputStream fis = new FileInputStream(new File("application.properties"));
				ep.load(fis);
				if(ep.getProperty("delay") != null){
					int delay = Integer.parseInt(ep.getProperty("delay"));
					Thread.sleep(delay);
				}
				ep.clear();
				fis.close();
				ep = null;
			}catch(Exception e){
				logger.debug("Exception in fetching information from property file");
			}
			if(outgoingMessage != null){
				//logger.info("Sendig response..");			
				if(outgoingMessage.toCharArray().length != 0){
					writer.println(outgoingMessage);
					logger.info("response send");
					logger.debug("Server outgoing message to Client Thread:" 
						+ Thread.currentThread().getName() +"|length :" + outgoingMessage.toCharArray().length+ "| Outgoing message |"
						+ outgoingMessage);
				}
			}
			
		} catch (IOException e) {
			logger.debug("Error in Client Thread:" + Thread.currentThread().getName(),e);
		} catch (Exception e) {
			logger.debug("Halting response to send to terminal :" 
					+ Thread.currentThread().getName() +"|length :" + outgoingMessage.toCharArray().length+ "| halting Outgoing message |"
					+ outgoingMessage);
			logger.debug("Error in Client Thread:" + Thread.currentThread().getName(),e);
		} 
		finally {
			try {
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
				clientSocket.close();
			} catch (IOException e) {
				logger.debug("Exception occured :" ,e);
				
			}
		}
	}
}
