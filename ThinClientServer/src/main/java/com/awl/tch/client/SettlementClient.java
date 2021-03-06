package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SettlementClient {
	public static void main(String[] args) {
		/*if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }*/
		
		long startTime = System.currentTimeMillis();
		
		int i=0;
		while(i<1)			
		{
			
       String hostName = "127.0.0.1";
		//	String hostName = "10.10.11.126";
        int portNumber = 9001;
 
        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            
            String str1 ="{       \"reqTyp\":       \"SSETL\",        \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"cltId\":        \"TC0000000000557\",             \"htid\":  \"WLT\",          \"hltObj\":       {                       \"comMd\":        \"GPRS\",                 \"simNos\":       \"8991200010305439597\",                  \"imeiNum\":      \"351828086817102\",                      \"ipNum\":        \"192.168.099.004\",                      \"prtNum\":       \"9001\",                 \"apn\":  \"venture.vodafone.in\",                  \"dispSt\":       \"OK\",                   \"prtSt\":        \"OK\",                   \"mdmSt\":        \"OK\",                   \"usrPrTm\":      \"10\",  \"flTnx\": \"12\"            },              \"dt\":   \"180209\",               \"tm\":   \"202312\"        }}\n";
            String str = "{ 	\"requestType\":	\"SETTLEMENT\", 	\"requestObject\":	{ 		\"termSerNum\":	\"14298WL22307843\", 		\"cltId\":	\"TC0000000000363\", 		\"healthObj\":	{ 			\"commMode\":	\"GPRS\", 			\"simNos\":	\"8991200015305157453\", 			\"imeiNum\":	\"353992073323249\", 			\"ipNum\":	\"192.168.099.003\", 			\"portNum\":	\"9002\", 			\"apn\":	\"venture.vodafone.in\" 		}, 		\"date\":	\"161019\", 		\"time\":	\"115315\" 	} }";
           //	String reqStr = "{        \"requestType\":       \"SETTELMENT\",        \"requestObject\":     {               \"termSerNum\": \"15290WL23648437\",               \"cltId\":      \"TC0000000000360\",               \"healthObj\":  [{                            \"commName\":   \"GPRS\",                            \"simNo\":      \"8991200015305157453\",                            \"imeiNo\":     \"353992073323249\",                            \"commIp\":     \"192.168.099.003\",                            \"comport\":    \"9001\",                            \"APN\": \"venture.vodafone.in\"                      }],               \"date\":       \"161004\",               \"time\":       \"173235\"        } }";
            out.print(str1);
            out.flush();
            String incommingMessage = "";
            char cbuf[] = new char[4096];
			int charRead =  in.read(cbuf);
			incommingMessage = new String(cbuf);
			System.out.println("response :length :"+charRead +" :"+incommingMessage);
            /*while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer+":"+i);
                    
               
            }*/
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
		System.out.println(endTime - startTime );
		
	}
}
