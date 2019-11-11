package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HandshakeClient {
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
           //	String reqStr = "{         \"reqTyp\":       \"SALE\",         \"reqObj\":       {                 \"tSerNum\":      \"16058WL24100894\",                 \"cltId\":        \"TC0000000000396\",                 \"binNum\":       \"4629864339\",                 \"crdEntMd\":     \"SWIPE\",                 \"panNum\":       \"AE41786EC4544C9414B8D44AB4B77841\",                 \"expDt\":        \"1510\",                 \"orgAmt\":       \"111\",                 \"trk2\": \"DE44BF425A521251FC4275954FA2C2C7AF75E4310C654E411102CCF2662A7EA5\",                 \"invNum\":       \"000007\",                 \"lst4DgtVl\":    \"6007\",                 \"dt\":   \"161215\",                 \"tm\":   \"174212\"         } }\n";
           String reqStr = "{ \"reqTyp\": \"HNDSHK\", \"reqObj\": { \"tSerNum\": \"15246WL23468233\", \"eAppVer\": \"IWL11675311017\", \"pckNum\": \"0000\", \"hanVerNum\": \"0000\", \"hltObj\": { \"comMd\": \"GPRS\", \"simNos\": \"\", \"imeiNum\": \"\", \"ipNum\": \"192.168.099.004\", \"prtNum\": \"9001\", \"apn\": \"venture.vodafone.in\", \"dispSt\": \"OK\", \"prtSt\": \"OK\", \"mdmSt\": \"OK\", \"usrPrTm\": \"10\", \"flTnx\": \"12\" }, \"dt\": \"171101\", \"tm\": \"174301\" }} \n";

           String reqstr1 = "{       \"reqTyp\":       \"HNDSHK\",       \"reqObj\":       {               \"tSerNum\":      \"15290WL23648437\",              \"eAppVer\":      \"IWL11676120118\",              \"pckNum\":        \"0000\",         \"hanVerNum\":    \"0003\",         \"desflg\":       \"S-999\",                \"hltObj\":       {                       \"comMd\":        \"GPRS\",\"simNos\":        \"8991200010305447319\",                  \"imeiNum\":      \"357437068749830\",                      \"ipNum\":        \"192.168.099.004\",                     \"prtNum\":        \"9001\",                 \"apn\":  \"venture.vodafone.in\",                  \"dispSt\":       \"OK\",                   \"prtSt\":        \"OK\",                  \"mdmSt\": \"OK\",                   \"usrPrTm\":      \"10\",                   \"flTnx\":        \"12\"            },              \"dt\":   \"180125\",               \"tm\":   \"133823\"}}\n";

           String fcString ="{       \"reqTyp\":       \"HNDSHK\",       \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"eAppVer\":      \"IWL11676291217\",              \"pckNum\":        \"0000\",         \"hanVerNum\":    \"0000\",         \"hltObj\":       {                       \"comMd\":        \"GPRS\",                 \"simNos\":       \"\",    \"imeiNum\":       \"\",                     \"ipNum\":        \"192.168.099.004\",                      \"prtNum\":       \"9001\",                 \"apn\":  \"venture.vodafone.in\", \"dispSt\":        \"OK\",                   \"prtSt\":        \"OK\",                   \"mdmSt\":        \"OK\",                   \"usrPrTm\":      \"10\",                   \"flTnx\":\"12\"            },              \"dt\":   \"180102\",               \"tm\":   \"174728\"        }}\n";
        	out.print(reqstr1);
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

