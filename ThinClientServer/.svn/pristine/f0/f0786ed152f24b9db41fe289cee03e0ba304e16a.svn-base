package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class WalletClient extends Thread {
	@Override
	public void run() {
		System.out.println("Here");
		long startTime = System.currentTimeMillis();
		int i = 0;
		String hostName = "127.0.0.1";
		int portNumber = 9001;
		while (i < 1) {
			System.out.println("here2" + Thread.currentThread().getName());
			try (Socket kkSocket = new Socket(hostName, portNumber);
					PrintWriter out = new PrintWriter(
							kkSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(kkSocket.getInputStream()));) {
				String wallet2 = "{  \"reqTyp\":       \"SALE\", \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"cltId\":        \"TC0000000000557\",              \"crdEntMd\":     \"WLT\",          \"mrMbNum\":      \"8860532795\",           \"menuObj\":      [{                              \"tbN\":  \"0\",                            \"cTCd\": \"0\",                            \"nTCd\": \"1\",                            \"dspNm\":        \"SALE\",                         \"hdNm\": \"SELECT PAYMENT MODE\"                   }, {                            \"tbN\":  \"1\",                            \"cTCd\": \"1\",                            \"nTCd\": \"11\",                           \"dspNm\":        \"WALLET\",                               \"hdNm\": \"SELECT PAYMENT MODE\"                   }, {                            \"tbN\":  \"2\",                            \"cTCd\": \"11\",                           \"nTCd\": \"12\",                           \"dspNm\":        \"FREECHARGE\",                           \"hdNm\": \"SELECT WALLET\"                 }],             \"refVl\":        \"1234\",         \"rrn\":  \"803400001001\",         \"orgAmt\":       \"100\",          \"stNum\":        \"000118\",               \"invNum\":       \"000085\",               \"dt\":   \"180206\",               \"tm\":   \"105010\"        }}\n";
				String wallet1 = "{ \"reqTyp\":       \"SALE\", \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"cltId\":        \"TC0000000000557\",              \"crdEntMd\":     \"WLT\",          \"mrMbNum\":      \"8860532795\",           \"menuObj\":      [{                              \"tbN\":  \"0\",                            \"cTCd\": \"0\",                            \"nTCd\": \"1\",                            \"dspNm\":        \"SALE\",                         \"hdNm\": \"SELECT PAYMENT MODE\"                   }, {                            \"tbN\":  \"1\",                            \"cTCd\": \"1\",                            \"nTCd\": \"11\",                           \"dspNm\":        \"WALLET\",                               \"hdNm\": \"SELECT PAYMENT MODE\"                   }, {                            \"tbN\":  \"2\",                            \"cTCd\": \"11\",                           \"nTCd\": \"12\",                           \"dspNm\":        \"FREECHARGE\",                           \"hdNm\": \"SELECT WALLET\"                 }],             \"orgAmt\":       \"100\",          \"dt\":   \"180206\",               \"tm\":   \"105003\"        }}\n";
				//String wallet1 = "{ \"reqTyp\":     \"SALE\", \"reqObj\":     { \"tSerNum\":    \"17057WL25828534\", \"cltId\":      \"TC0000000000557\", \"crdEntMd\":   \"WLT\", \"mrMbNum\":    \"8860532795\", \"menuObj\":    [{  \"tbN\": \"0\",  \"cTCd\":       \"0\", \"nTCd\":       \"1\",  \"dspNm\":      \"SALE\",  \"hdNm\":       \"SELECT PAYMENT MODE\"  }, {  \"tbN\": \"1\",  \"cTCd\":       \"1\",  \"nTCd\":       \"11\",  \"dspNm\":      \"WALLET\",  \"hdNm\":       \"SELECT PAYMENT MODE\" }, {  \"tbN\": \"2\",  \"cTCd\":       \"11\",  \"nTCd\":       \"12\",  \"dspNm\":      \"FREECHARGE\",  \"hdNm\":       \"SELECT WALLET\" }], \"orgAmt\":     \"10000\", \"stNum\":      \"000002\", \"invNum\":     \"000001\", \"dt\":  \"171221\", \"tm\":  \"111925\" }}\n";
				//String wallet2 = "{  \"reqTyp\":       \"SALE\", \"reqObj\":       {  \"tSerNum\":      \"17057WL25828534\",  \"cltId\":        \"TC0000000000557\", \"crdEntMd\":     \"WLT\",  \"mrMbNum\":      \"8860532795\",  \"menuObj\":      [{  \"tbN\":  \"0\",  \"cTCd\": \"0\", \"nTCd\": \"1\", \"dspNm\":        \"SALE\",  \"hdNm\": \"SELECT PAYMENT MODE\"  }, {  \"tbN\":  \"1\",  \"cTCd\": \"1\", \"nTCd\": \"11\", \"dspNm\":        \"WALLET\",  \"hdNm\": \"SELECT PAYMENT MODE\"  }, { \"tbN\":  \"2\",  \"cTCd\": \"11\", \"nTCd\": \"12\",  \"dspNm\":        \"FREECHARGE\", \"hdNm\": \"SELECT WALLET\"  }], \"refVl\":        \"1234\",  \"rrn\":  \"802900000015\", \"orgAmt\":       \"10000\",\"stNum\":        \"000006\",\"invNum\":       \"000001\",  \"dt\":   \"171221\",  \"tm\":   \"111930\"}}\n";
				out.print(wallet2);
				out.flush();
				String incommingMessage = "";
				char cbuf[] = new char[4096];
				int charRead = in.read(cbuf);
				incommingMessage = new String(cbuf);
				System.out.println("Server :" + charRead + ":"
						+ incommingMessage);
				Thread.sleep(1L);
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + hostName);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to "
						+ hostName);
				System.exit(1);
			} catch (Exception e) {
				 System.exit(1);
			}
			i++;
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

	}

	public static void main(String[] args) {

		WalletClient s[]  = new WalletClient[1];

        for(WalletClient saleclient : s)
        {
                saleclient = new WalletClient();
                saleclient.start();

        }
	}

}
