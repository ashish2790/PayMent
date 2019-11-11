package com.awl.tch.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SaleClient extends Thread {
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

				// String reqEncACK =
				// "{ 	\"reqTyp\":	\"ENQ\", 	\"reqObj\":	{ 		\"tSerNum\":	\"16256WL24997206\", 		\"cltId\":	\"TC0000000000476\", 		\"appNm\":	\"EGRAS\", 		\"unqId\":	\"12345\", 		\"refVl\":	\"08871\", 		\"dt\":	\"170411\", 		\"tm\":	\"111918\" 	} } \n";
				String reqEnc = "{  \"reqTyp\":        \"REF\",  \"reqObj\":       {               \"tSerNum\":      \"17060WL25837288\",              \"cltId\":        \"TC0000000000595\",              \"crdEntMd\":    \"QR\",            \"orgAmt\":       \"1\",            \"stNum\":        \"000630\",               \"refVl\":        \"720112351095\",         \"invNum\":       \"000547\",              \"desflg\":        \"ACKQR\",                \"dt\":   \"170720\",               \"tm\":   \"130449\"        }}\n";
				String brandEmi2nd = "{       \"reqTyp\":       \"BRDEMI\",       \"reqObj\":       {               \"tSerNum\":      \"17049WL25782777\",              \"custNm\":       \"MR S VENKATESH           /\",  \"binNum\":        \"4854980600\",           \"desflg\":       \"EMITEN\",               \"orgAmt\":       \"1233333\",              \"menuObj\":      [{                             \"tbN\":   \"0\",                            \"cTCd\": \"1\",                            \"nTCd\": \"0\",                            \"dspNm\":        \"HW-F551\",                     \"hdNm\":  \"Select SKU Code\",                              \"ctId\": \"0\"                     }],             \"imeiNum\":      \"161\",          \"mrMbNum\":      \"6313131333\",  \"refVl\": \"3131\",         \"dt\":   \"171010\",               \"tm\":   \"155640\"        }}\n";
				String brandEmi3rd = "{         \"reqTyp\":       \"BRDEMI\",         \"reqObj\":       {                 \"tSerNum\":      \"15290WL23648437\",                                 \"binNum\":       \"4375320001\",                 \"crdEntMd\":     \"SWIP\",                 \"panNum\":       \"4375320001378950\",                 \"expDt\":        \"1610\",                 \"orgAmt\":       \"8950\",                 \"stNum\":        \"000384\",                 \"desflg\":       \"BRDSALE\",                 \"imeiNum\":      \"123456789123456\",                 \"mrMbNum\":      \"8655453195\",                 \"refVl\":        \"123456\",                 \"invNum\":       \"000219\",                 \"emiObj\":       {                         \"tenObj\":       [{                                         \"roi\":  \"14.0\",                                         \"chbkPer\":      \"12\",                                         \"chbkAmt\":      \"111\",                                         \"tenr\": \"3\",                                         \"emiAmt\":       \"124.11\",                                         \"prFeePer\":     \"12\"                                 }]                 },                 \"dt\":   \"170614\",                 \"tm\":   \"063150\"         } }\n";
				String brandEmi1 = "{       \"reqTyp\":       \"BRDEMI\",       \"reqObj\":       {               \"tSerNum\":      \"17049WL25782777\",              \"custNm\":       \"MR S VENKATESH           /\",  \"binNum\":        \"4854980600\",           \"desflg\":       \"BRDSKU\",               \"orgAmt\":       \"1233333\",              \"menuObj\":      [{                             \"tbN\":   \"0\",                            \"cTCd\": \"1\",                            \"nTCd\": \"2\",                            \"dspNm\":        \"MOBILE\",                      \"hdNm\":  \"Select Category\",                              \"ctId\": \"1\"                     }, {                            \"tbN\":  \"1\",                            \"cTCd\":\"2\",                             \"nTCd\": \"4\",                            \"dspNm\":        \"SAMSUNG\",                              \"hdNm\": \"Select Brand\",                \"ctId\":  \"0\"                     }, {                            \"tbN\":  \"3\",                            \"cTCd\": \"4\",                            \"nTCd\": \"10\",          \"dspNm\": \"MBO\",                          \"hdNm\": \"Select Scheme\",                                \"ctId\": \"0\"                     }, {                            \"tbN\": \"9\",                             \"cTCd\": \"10\",                           \"nTCd\": \"110\",                          \"dspNm\":        \"SMART PHONE\",                         \"hdNm\":  \"PRODUCT CATEGORY\",                             \"ctId\": \"1\"                     }, {                            \"tbN\":  \"10\",                           \"cTCd\":\"110\",                           \"nTCd\": \"111\",                          \"dspNm\":        \"S14\",                          \"hdNm\": \"MODEL NO\",                            \"ctId\":  \"0\"                     }, {                            \"tbN\":  \"11\",                           \"cTCd\": \"111\",                          \"nTCd\": \"112\",         \"dspNm\": \"5\",                            \"hdNm\": \"SIZE\",                         \"ctId\": \"0\"                     }, {                            \"tbN\":  \"12\",          \"cTCd\":  \"112\",                          \"nTCd\": \"113\",                          \"dspNm\":        \"yellow\",                               \"hdNm\": \"COLOR\",               \"ctId\":  \"0\"                     }],             \"dt\":   \"171010\",               \"tm\":   \"155633\"        }}\n";
				String sale = "{ \"reqTyp\":       \"ENQ\",  \"reqObj\":       {               \"tSerNum\":      \"17049WL25782777\",              \"cltId\":        \"TC0000000000786\",              \"refVl\":        \"13122017000254\",               \"appNm\":        \"MOPS\",         \"dt\":   \"171213\",               \"tm\":   \"155947\"        }}\n";
				String sale1 = "{   \"reqTyp\": \"SALE\",   \"reqObj\": {     \"tSerNum\": \"16366WL25583712\",     \"cltId\": \"TC0000000000485\",     \"binNum\": \"6210948000\",     \"crdEntMd\": \"CHIP\",     \"panNum\": \"71EEB3A21CAF5861F4DBDB88C30269A6\",     \"expDt\": \"3010\",     \"orgAmt\": \"90000\",     \"stNum\": \"000250\",     \"trk2\": \" AASAS\",     \"fld55\": \"82027C00950500000080009A031708179C01005F2A0203565F3401019F02060000000900009F03060000000000009F090200209F100807000103A02002019F1A0203569F1E08534349464D5836359F260813745911C2BCFCE29F2701809F3303E0F0C89F34035E03009F3501229F360200CB9F3704A817A7F39F4104000002499F530152\",     \"invNum\": \"000065\",     \"lst4DgtVl\": \"0011\",     \"dt\": \"170817\",     \"tm\": \"122155\"   } }\n";
				String reqEncACK = "{         \"reqTyp\"       \"ENQ\",         \"reqObj\":       {                 \"tSerNum\":      \"15246WL23468233\",                 \"cltId\":        \"TC0000000000609\",                 \"refVl\":        \"1214\",                 \"appNm\":        \"AAB\",                 \"unqId\":        \"12345\",                 \"dt\":   \"170615\",                 \"tm\":   \"202739\"         } }\n";
				String lastEmi = "{  \"reqTyp\":        \"DETREP\",       \"reqObj\":       {               \"tSerNum\":      \"17057WL25828534\",              \"cltId\":        \"TC0000000000557\",              \"pckNum\":       \"0000\",         \"dt\":   \"171014\",               \"tm\":   \"114746\"        }}\n";
				String handshake = "{  \"reqTyp\":        \"HNDSHK\",       \"reqObj\":       {               \"tSerNum\":      \"15246WL23468233\",              \"eAppVer\":      \"IWL117091017\",         \"pckNum\":      \"0000\",          \"hanVerNum\":    \"0005\",                \"hltObj\":       {                       \"comMd\":        \"GPRS\",                \"simNos\":        \"8991200010305149006\",                  \"imeiNum\":      \"357437066695555\",                      \"ipNum\":        \"192.168.099.004\",                     \"prtNum\":        \"9002\",                 \"apn\":  \"venture.vodafone.in\",                  \"dispSt\":       \"OK\",                   \"prtSt\":        \"OK\",                  \"mdmSt\": \"OK\",                   \"usrPrTm\":      \"10\",                   \"flTnx\":        \"12\"            },              \"dt\":   \"171014\",               \"tm\":   \"174036\"}}\n";
				String saleQR = "{   \"reqTyp\": \"HNDSHK\",   \"reqObj\": {     \"tSerNum\": \"17053WL82819635\",     \"eAppVer\": \"IWL116100817\",     \"pckNum\": \"0000\",     \"hanVerNum\": \"0001\",     \"desflg\": \"S-999\",     \"hltObj\": {       \"comMd\": \"GPRS\",       \"simNos\": \"8991200013304561080\",       \"imeiNum\": \"351828086330361\",       \"ipNum\": \"192.168.099.004\",       \"prtNum\": \"9002\",       \"apn\": \"venture.vodafone.in\",       \"dispSt\": \"OK\",       \"prtSt\": \"OK\",       \"mdmSt\": \"OK\",       \"usrPrTm\": \"10\",       \"flTnx\": \"12\"     },     \"dt\": \"170811\",     \"tm\": \"120721\"   } }\n";
				String saleTest = "{        \"reqTyp\":     \"SALE\",        \"reqObj\":     {               \"tSerNum\":    \"17057WL25828534\",               \"cltId\":      \"TC0000000000557\",               \"binNum\":     \"1502710125\",               \"crdEntMd\":   \"SWIP\",               \"panNum\":     \"282FB82CF0574B8A7A5376DD65314EED\",               \"expDt\":      \"9909\",               \"orgAmt\":     \"1111\",               \"stNum\":      \"000041\",               \"trk2\":       \"0E75FBA8241EC0026562E50B04BF84ABB149B5F4C5EE59D969B7F44DA85A29F79DE185FEF6F9EC74\",               \"invNum\":     \"000027\",               \"dt\":  \"170824\",               \"tm\":  \"114835\"        } }\n";
				// String reqStr =
				// "{ 	\"reqTyp\":	\"SALE\", 	\"reqObj\":	{ 		\"tSerNum\":	\"16057WL24100096\", 		\"cltId\":	\"TC0000000000397\", 		\"binNum\":	\"5420343024\", 		\"crdEntMd\":	\"SWIPE\", 		\"panNum\":	\"0199E1675FC7DA4EDC153D21BF774964\", 		\"expDt\":	\"5011\", 		\"orgAmt\":	\"25500\", 		\"stNum\":	\""+i+"\", 		\"trk2\":	\"BC1A73FB9B31BE7C96F34E92B371C9FBE07E706BAB1A8807B4AED2D9E6882577D2CF6BABC5F14317\", 		\"invNum\":	\"000001\", 		\"lst4DgtVl\":	\"9555\", 		\"appNm\":	\"AAB\", 		\"unqId\":	\"12345\", 		\"refVl\":	\"210\", 		\"dt\":	\"161229\", 		\"tm\":	\"171848\" 	} }\n";
				out.print(sale);
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
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

	}

	public static void main(String[] args) {

		SaleClient s[]  = new SaleClient[1];

        for(SaleClient saleclient : s)
        {
                saleclient = new SaleClient();
                saleclient.start();

        }
	}

}
