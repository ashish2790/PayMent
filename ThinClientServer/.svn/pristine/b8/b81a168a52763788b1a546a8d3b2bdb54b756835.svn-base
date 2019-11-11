package com.awl.tch.tcp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.util.AppConfigMaster;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.Property;

@Component("tcpClient")
public class TcpClient {


	private static final Logger logger = LoggerFactory.getLogger(TcpClient.class);

	private String IP_ADDRESS = Property.getMagnusIp();
	private int PORT =  Property.getMagnusPort();

	private InetSocketAddress hostAddress = new InetSocketAddress(IP_ADDRESS,PORT);
	public String send(String msg) throws TCHServiceException
	{
		SocketChannel clientChannel=null;
		String response="";
		int numRead = -1;
		
		try{
			logger.info("Connecting [ "+ hostAddress +" ]");
			clientChannel = SocketChannel.open(hostAddress);
			clientChannel.socket().setSoTimeout(Property.getMagnusReadTimeout()*1000);
			byte [] message = null;
			ByteBuffer buffer = null;
			message = msg.getBytes("ISO-8859-1");
			
			buffer = ByteBuffer.wrap(message);
			clientChannel.write(buffer);
			buffer.clear();
			buffer = ByteBuffer.allocate(4096);
			
			logger.info("Reading response from magnus");
			numRead = clientChannel.read(buffer);
			logger.debug("length from magnus :" +  numRead);
			if(numRead == -1){
				logger.debug("In witing state, to generate reversal from terminal");
				Thread.sleep(25000);
			}
			byte[] data = new byte[numRead];
			System.arraycopy(buffer.array(), 0, data, 0, numRead);
			response = new String(data,"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception in send of TcpClient :",e.getMessage());
		}catch(SocketTimeoutException ste){
			logger.debug("Socket time out exception");
			throw new TCHServiceException(ErrorConstants.TCH_Z003,ErrorMaster.get(ErrorConstants.TCH_Z003));
		} 
		catch (IOException e) {
			logger.debug("Exception in send of TcpClient :",e.getMessage());
		}
		catch (Exception e) {
			logger.debug("Exception in send of TcpClient :",e.getMessage());
		}
		finally{
			try {
				if(clientChannel == null){
					throw new TCHServiceException(ErrorConstants.TCH_Z003,ErrorMaster.get(ErrorConstants.TCH_Z003));
				}
				clientChannel.close();
			} catch (IOException e) {
				logger.debug("Exception while closing connection :",e.getMessage());
			}
		}
		return response;
	}
	
	
	public String send(String isoMessage, String bankCode) throws TCHServiceException {
		String response = "";
		SocketChannel channel =null;
		
		String bankIds = AppConfigMaster.getConfigValue(Constants.TCH_MAGNUS, Constants.TCH_BANK_1);
		String magnusIp = "";
		Integer magnusPort = 0;
		long startTime = System.currentTimeMillis();
		
		logger.debug("bankcode :" + bankCode);
		
		logger.debug("bankIds :" + bankIds);
		
		if(bankIds != null && bankIds.contains(bankCode)){
			magnusIp = AppConfigMaster.getConfigValue(Constants.TCH_MAGNUS, Constants.TCH_MAGNUS_IP+"_1");
			magnusPort = Integer.parseInt(AppConfigMaster.getConfigValue(Constants.TCH_MAGNUS, Constants.TCH_MAGNUS_PORT+"_1"));
		}else{
			magnusIp = AppConfigMaster.getConfigValue(Constants.TCH_MAGNUS, Constants.TCH_MAGNUS_IP+"_2");
			magnusPort = Integer.parseInt(AppConfigMaster.getConfigValue(Constants.TCH_MAGNUS, Constants.TCH_MAGNUS_PORT+"_2"));
		}
		InetSocketAddress inet = new InetSocketAddress(magnusIp,magnusPort);
		try {
			logger.debug("Connecting [" +inet+"]");
			channel  = SocketChannel.open(inet);
			logger.debug("Connected..");
			channel.socket().setSoTimeout(Property.getMagnusReadTimeout());
			ByteBuffer buffer = ByteBuffer.wrap(isoMessage.getBytes("ISO-8859-1"));
			
			channel.write(buffer);
			
			// getting response from switch
			buffer = ByteBuffer.allocate(4096);
			
			logger.debug("Reading response from switch");
			int readCount = channel.read(buffer);
			long endTime = System.currentTimeMillis();
			long actualResponseTime = endTime - startTime;
			// GET RESPONSE TIME AND TREAT TRANSACTION AS DECLINED AFTER 40 SEC TIMEOUT
			logger.debug("Response received in : " + actualResponseTime + " ms");
			logger.debug("length response from switch :" + readCount);
			if(actualResponseTime > 40000){
				return "";
			}
			
			if(readCount == -1){
				logger.debug("In witing state, to generate reversal from terminal");
				Thread.sleep(25000);
			}
			
			byte[] data = new byte[readCount];
			System.arraycopy(buffer.array(), 0, data, 0, readCount);
			response = new String(data,"ISO-8859-1");
			
		} catch(SocketTimeoutException ste){
			logger.debug("Socket time out exception");
			throw new TCHServiceException(ErrorConstants.TCH_Z003,ErrorMaster.get(ErrorConstants.TCH_Z003),ErrorConstants.TCH_99);//sending response code "99" in switch connection exception because of issue at Terminal end as on 11/22/2017
		}catch (IOException e) {
			logger.debug("Exception in send of TcpClient :",e.getMessage());
			throw new TCHServiceException(ErrorConstants.TCH_Z003,ErrorMaster.get(ErrorConstants.TCH_Z003),ErrorConstants.TCH_99); 
		}catch(Exception  e){
			logger.debug("Exception in send of TcpClient :",e.getMessage());
			throw new TCHServiceException(ErrorConstants.TCH_Z003,ErrorMaster.get(ErrorConstants.TCH_Z003),ErrorConstants.TCH_99);
		}finally{
			try {
				if(channel == null){
					throw new TCHServiceException(ErrorConstants.TCH_Z003,ErrorMaster.get(ErrorConstants.TCH_Z003),ErrorConstants.TCH_99);
				}
				channel.close();
			} catch (IOException e1) {
				logger.debug("Exception while closing connection :",e1.getMessage());
			}
		}
		
		return response;
	}
	
	// duplicate method to point magnus to another port or ip
	
	//public String send(String msg, String type) throws TCHServiceException{
	/*
		logger.debug("Inside method for 152");
		String IP_ADDRESS = "10.10.11.152";
		int PORT = 1947;
		InetSocketAddress hostAddress = new InetSocketAddress(IP_ADDRESS,PORT);
		//hostAddress = new InetSocketAddress(IP_ADDRESS,PORT);
		SocketChannel clientChannel=null;
		String response="";
		try {
			clientChannel = SocketChannel.open(hostAddress);
			clientChannel.socket().setSoTimeout(Property.getMagnusReadTimeout()*1000);
			logger.info("Connecting :"+hostAddress);

			byte [] message = null;

			ByteBuffer buffer = null;
			message = msg.getBytes("ISO-8859-1");
			
			buffer = ByteBuffer.wrap(message);
			clientChannel.write(buffer);
			buffer.clear();

			buffer = ByteBuffer.allocate(4096);
			int numRead = -1;
			numRead = clientChannel.read(buffer);
			
			byte[] data = new byte[numRead];
			System.arraycopy(buffer.array(), 0, data, 0, numRead);


			response = new String(data,"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("Exception in send of TcpClient :",e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Exception in send of TcpClient :",e);
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception in send of TcpClient :",e);
			e.printStackTrace();
		}
		finally{
			try {
				
				if(clientChannel == null){
					throw new TCHServiceException(ErrorConstants.TCH_Z003,ErrorMaster.get(ErrorConstants.TCH_Z003));
				}
				clientChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Exception while closing connection :",e);
			}

		}

		return response;
	*///}
}
