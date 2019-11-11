package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class RefundClient {
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
           // String reqStr = "{ 	\"requestType\":	\"REFUND\", 	\"requestObject\":	{ 		\"termSerNum\":	\"15290WL23648437\", 		\"cltId\":	\"TC0000000000396\", 		\"binNumber\":	\"5204740000\", 		\"crdEntryMod\":	\"SWIPE\", 		\"panNum\":	\"5204740000001176\", 		\"expDate\":	\"1812\", 		\"orgAmount\":	\"1222\", 		\"track2\":	\"B5A7F34DBC8B08E74F145A1C883F12817FA1B269AED76F0425F9C69F53F5A9C8E3F4081E360DDD74\", 		\"refVal\":	\"123456789777\", 		\"invNumber\":	\"000005\", 		\"date\":	\"161109\", 		\"time\":	\"165306\" 	} }";
            
            String reqStr = "{\"reqTyp\":        \"REF\",  \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"cltId\":        \"TC0000000000557\",              \"orgAmt\":      \"1000\",          \"refVl\":        \"734506500001\",         \"dt\":   \"171211\",               \"tm\":   \"125508\"        }}\n";
            
            String reqStrLeg2 ="{       \"reqTyp\":       \"REF\",  \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"cltId\":        \"TC0000000000557\",              \"orgAmt\":       \"100000\",               \"refVl\":        \"734506500001\",         \"lst4DgtVl\":    \"1192\",         \"stNum\":        \"000202\",               \"desflg\":       \"ACK_REF\",              \"invNum\":       \"000053\",               \"dt\":   \"171211\",               \"tm\":   \"154030\"        }}\n";
            
            String enq= "{\"reqTyp\":        \"ENQ\",  \"reqObj\":       {               \"tSerNum\":      \"15290WL23648437\",              \"cltId\":        \"TC0000000000360\",              \"refVl\":       \"640330\",                \"appNm\":        \"AXISEPAY\",             \"dt\":   \"171221\",               \"tm\":   \"094044\"        }}\n";

            String enqAck="{       \"reqTyp\":       \"ENQACK\",       \"reqObj\":       {               \"tSerNum\":      \"15290WL23648437\",              \"cltId\":        \"TC0000000000360\",             \"orgAmt\":        \"1\",            \"stNum\":        \"000086\",               \"refVl\":        \"640330\",               \"lst4DgtVl\":    \"4927\",         \"binNum\":       \"4693750558\",           \"appNm\":        \"AXISEPAY\",             \"rrn\":  \"735506230002\",         \"dt\":   \"171221\",               \"tm\":   \"115355\"        }}\n";
            out.print(enqAck);
            out.flush();
            String incommingMessage = "";
            char cbuf[] = new char[1024];
			int charRead =  in.read(cbuf);
			incommingMessage = new String(cbuf);
			System.out.println("Response :" + charRead +":"+ incommingMessage);
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
