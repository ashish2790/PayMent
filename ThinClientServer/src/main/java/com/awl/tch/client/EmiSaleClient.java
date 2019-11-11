package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class EmiSaleClient {
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
        int portNumber = 9002;
 
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
            
            
            String reqStr = "{ 	\"requestType\":	\"SALE\", 	\"requestObject\":	{ 		\"termSerNum\":	\"14298WL22307843\", 		\"binNumber\":	\"5204740000\", 		\"crdEntryMod\":	\"SWIPE\", 		\"panNum\":	\"5204740000001176\", 		\"expDate\":	\"1812\", 		\"orgAmount\":	\"11111\", 		\"track2\":	\"5204740000001176D181299911011111000F\", 		\"cltId\":	\"TC0000000000363\", 		\"invNumber\":	\"000004\", 		\"descflag\":	\"EMI_SALE\", 		\"lstFourDgtVal\":	\"1176\", 		\"emiObj\":	{ 			\"prgObject\":	[{ 					\"progShrtName\":	\"1\", 					\"progShrtName\":	\"1235\", 					\"prodCodeFlag\":	\"AC1\" 				}], 			\"prodCode\":	\"111111\", 			\"imeiNum\":	\"13211321\", 			\"tenureObject\":	[{ 					\"instdescCode\":	\"2222\", 					\"proddescCode\":	\"011\", 					\"roi\":	\"2.5\", 					\"cshbkPer\":	\"4.6\", 					\"cshbkAmt\":	\"122.4\", 					\"perFee\":	\"12.21\", 					\"tenure\":	\"10\" 				}] 		}, 		\"date\":	\"161017\", 		\"time\":	\"162748\" 	} }";
            String req ="{ 	\"requestType\":	\"SALE\", 	\"requestObject\":	{ 		\"termSerNum\":	\"14298WL22307843\", 		\"binNumber\":	\"5300300000\", 		\"crdEntryMod\":	\"SWIPE\", 		\"panNum\":	\"5204740000001176\", 		\"expDate\":	\"1812\", 		\"orgAmount\":	\"11111\", 		\"track2\":	\"5204740000001176D181299911011111000F\", 		\"cltId\":	\"TC0000000000363\", 		\"invNumber\":	\"000001\", 		\"lstFourDgtVal\":	\"1176\", 		\"date\":	\"161017\", 		\"time\":	\"181528\" 	} }";
            String rqStr = "{ 	\"reqTyp\" : \"SALE\", 	\"reqObj\" : { 		\"tSerNum\" : \"17057WL25828534\", 		\"cltId\" : \"TC0000000000557\", 		\"binNum\" : \"4375512427\", 		\"crdEntMd\" : \"CHIP\", 		\"panNum\" : \"B93B486F7A098CAE9A3D2D48B118AC98\", 		\"expDt\" : \"1904\", 		\"pnBlk\" : \"B369283462FCEF67\", 		\"orgAmt\" : \"700000\", 		\"stNum\" : \"000014\", 		\"trk2\" : \"DBB4395A4FC6C1499C2543739BCCE6BA1D07CA25C477B0EF541C0BE26467975C\", 		\"fld55\" : \"82025C00950500000480009A031707249C01005F2A0203565F3401019F02060000007000009F03060000000000009F090200079F100706010A0360AC009F1A0203569F1E08534349464D5836359F260856FAD51E6819FD229F2701409F3303E0F0C89F34034203009F3501229F360205839F370405A206579F4104000000119F530152\", 		\"invNum\" : \"000001\", 		\"desflg\" : \"EMISALE\", 		\"lst4DgtVl\" : \"0005\", 		\"emiObj\" : { 			\"prgObj\" : [{ 					\"prgCd\" : \"NOREMI\", 					\"prgSrtNm\" : \"NORMAL EMI\", 					\"prdCdFlg\" : \"3\" 				} 			], 			\"prdCd\" : \"123456\", 			\"imeiNum\" : \"1234567891\", 			\"tenObj\" : [{ 					\"roi\" : \"6\", 					\"prFee\" : \"0\", 					\"tenr\" : \"6\", 					\"etotAmt\" : \"7123.01\", 					\"emiAmt\" : \"1187.17\", 					\"prFeePer\" : \"2\" 				} 			] 		}, 		\"dt\" : \"170724\", 		\"tm\" : \"123406\" 	} }\n";
            out.print(rqStr);
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
