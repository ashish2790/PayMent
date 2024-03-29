package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class RefundClientRRN {
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
            
          /*  fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
            }*/
          //  String reqKeyenter = "{ 	\"requestType\":	\"SALE\", 	\"requestObject\":	{ 		\"binNumber\":	\"607169\", 		\"orgAmount\":	\"111\", 		\"crdEntryMod\":	\"KEYENTER\", 		\"panNum\":	\"6071690000000314\", 		\"expDate\":	\"0221\", 		\"termSerNum\":	\"IWL22307843 \", 		\"accTypeName\":	\"NULL\", 		\"pinPrptFlag\":	\"NULL\", 		\"field55\":	\"NULL\", 		\"addAmount\":	\"NULL\", 		\"cltId\":	\"TC00000311\", 		\"descflag\":	\"NULL\", 		\"refVal\":	\"NULL\", 		\"invNumber\":	\"000004\", 		\"track2\":	\"\", 		\"date\":	\"160826\", 		\"time\":	\"183015\" 	} }";
            String reqStr = "{ 	\"requestType\":	\"REFUND\", 	\"requestObject\":	{ 		\"termSerNum\":	\"15290WL23648437\", 		\"orgAmount\":	\"25\", 		\"cltId\":	\"\", 		\"refVal\":	\"629009480001\", 		\"invNumber\":	\"000001\", 		\"date\":	\"160929\", 		\"time\":	\"154154\" 	} }";
           // String reqVoid = "{ 	\"requestType\":	\"VOID\", 	\"requestObject\":	{ 		\"orgAmount\":	\"NULL\", 		\"refVal\":	\"624508330001\", 		\"descflag\":	\"NULL\", 		\"termSerNum\":	\"IWL22307843\", 		\"date\":	\"140214\", 		\"time\":	\"160118\" 	} }";
           // String ReqVoid2  = "{\"requestType\":  \"VOID\",         \"requestObject\":        {                 \"orgAmount\":    \"111\",                 \"rrn\":  \"624508330001\",                 \"descflag\":     \"ACK\",                 \"termSerNum\":   \"IWL22307843\",                 \"date\": \"140214\",                 \"time\": \"174407\"         } }";
           //String reqStr = "{\"requestType\":\"HANDSHAKE\",\"requestObject\":{\"termSerNum\":\"IWL23393518\",\"date\":\"16052016\",\"time\":\"164230\"}}";
            //reqStr = ISO8583.keyExchange();
            out.print(reqStr);
            out.flush();
            String incommingMessage = "";
            char cbuf[] = new char[1024];
			int charRead =  in.read(cbuf);
			incommingMessage = new String(cbuf);
			System.out.println("Server :"+incommingMessage);
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
