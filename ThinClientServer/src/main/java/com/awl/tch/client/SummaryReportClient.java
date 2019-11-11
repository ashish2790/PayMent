package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SummaryReportClient {
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
            
          
           String reqStr ="{       \"reqTyp\":       \"SDTL\", \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"cltId\":        \"TC0000000000557\",              \"pckNum\":       \"0000\",         \"dt\":   \"180206\",               \"tm\":   \"144212\"        }} \n";
			out.print(reqStr);
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
