package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class VoidClient2 {
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		
		int i=0;
		while(i<1)			
		{
			
       String hostName = "127.0.0.1";
        int portNumber = 9001;
 
        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            
            String reqVoid = "{ 	\"requestType\":	\"VOID\", 	\"requestObject\":	{ 		\"termSerNum\":	\"15290WL23648437\", 		\"cltId\":	\"TC0000000000396\", 		\"orgAmount\":	\"1.22\", 		\"refVal\":	\"000005\", 		\"descflag\":	\"VOID_ACK\", 		\"lstFourDgtVal\":	\"1176\", 		\"date\":	\"161109\", 		\"time\":	\"165540\" 	} }";
            out.print(reqVoid);
            out.flush();
            String incommingMessage = "";
            char cbuf[] = new char[1024];
			int charRead =  in.read(cbuf);
			incommingMessage = new String(cbuf);
			System.out.println("Server :"+incommingMessage);
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

