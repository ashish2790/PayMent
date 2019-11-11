package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DetailReportClient {
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
           	String reqStr = "{ 	\"requestType\":	\"REP-DETAIL\", 	\"requestObject\":	{ 		\"termSerNum\":	\"16060WL24120018\", 		\"cltId\":	\"TC0000000000446\", 		\"packetNum\":	\"0000\", 		\"date\":	\"161117\", 		\"time\":	\"123705\" 	} }";
           	
           	String detailReport="{\"reqTyp\":        \"DETREP\",       \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"cltId\":        \"TC0000000000557\",              \"pckNum\":       \"0000\",         \"desflg\":       \"DETREP\",               \"htid\": \"WLT\",          \"dt\":   \"180209\",               \"tm\":   \"121948\"        }}\n";
            out.print(detailReport);
            out.flush();
            String incommingMessage = "";
            char cbuf[] = new char[4096];
			int charRead =  in.read(cbuf);
			incommingMessage = new String(cbuf);
			System.out.println("response :length :"+charRead +" :"+incommingMessage);
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
