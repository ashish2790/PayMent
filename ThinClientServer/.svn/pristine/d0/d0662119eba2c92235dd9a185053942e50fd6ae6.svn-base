--------------------------------------------------------
--  File created - Thursday-February-23-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TCH_UTILITY_INFO
--------------------------------------------------------

  CREATE TABLE "TCH_UTILITY_INFO" 
   (	"U_ID" NUMBER, 
	"U_IP_ADDRESS" VARCHAR2(20 BYTE), 
	"U_PORT_NUMBER" VARCHAR2(4 BYTE), 
	"U_URL" VARCHAR2(50 BYTE), 
	"U_CREATED" DATE, 
	"U_APP_NAME" VARCHAR2(20 BYTE), 
	"U_UPDATED" DATE, 
	"ADDED_BY" VARCHAR2(50 BYTE), 
	"U_PRINT_LABELS" VARCHAR2(600 BYTE)
   ) ;

Insert into TCH_UTILITY_INFO (U_ID,U_IP_ADDRESS,U_PORT_NUMBER,U_URL,U_CREATED,U_APP_NAME,U_UPDATED,U_PRINT_LABELS,ADDED_BY,U_ONE_TIME_LBL,U_LBL_VAL) values (1,null,null,'http://182.75.43.245/APIPay/PlaceMyOrder.asmx',null,'AAB',to_date('25-MAY-17','DD-MON-RR'),'AAB BRANCH CODE:,AAB TRNS ID :','Pooja','ENTER TRSNID :','6');
Insert into TCH_UTILITY_INFO (U_ID,U_IP_ADDRESS,U_PORT_NUMBER,U_URL,U_CREATED,U_APP_NAME,U_UPDATED,U_PRINT_LABELS,ADDED_BY,U_ONE_TIME_LBL,U_LBL_VAL) values (102,null,null,'https://testcpg.irctc.co.in/cpg/service/pos',to_date('06-OCT-17','DD-MON-RR'),'IRCTC',to_date('06-OCT-17','DD-MON-RR'),'TXN ID :, ',null,null,null);
Insert into TCH_UTILITY_INFO (U_ID,U_IP_ADDRESS,U_PORT_NUMBER,U_URL,U_CREATED,U_APP_NAME,U_UPDATED,U_PRINT_LABELS,ADDED_BY,U_ONE_TIME_LBL,U_LBL_VAL) values (2,'0','0','http://164.100.137.245:8080/egras',null,'EGRAS',null,'GRN ID:','AAB BRANCH CODE:,AAB TRNS ID :','ENTER OTP :','5');
Insert into TCH_UTILITY_INFO (U_ID,U_IP_ADDRESS,U_PORT_NUMBER,U_URL,U_CREATED,U_APP_NAME,U_UPDATED,U_PRINT_LABELS,ADDED_BY,U_ONE_TIME_LBL,U_LBL_VAL) values (61,null,null,'https://uat.sbiepay.com/payagg/requestProcessor/challanTransactionStatus',to_date('04-APR-17','DD-MON-RR'),'SBIEPAY',to_date('04-APR-17','DD-MON-RR'),'ATRN ID:','situser','ENTER ATRN :','13');
Insert into TCH_UTILITY_INFO (U_ID,U_IP_ADDRESS,U_PORT_NUMBER,U_URL,U_CREATED,U_APP_NAME,U_UPDATED,U_PRINT_LABELS,ADDED_BY,U_ONE_TIME_LBL,U_LBL_VAL) values (101,null,null,'https://uatmerchant.onlinesbi.com/mservices/mopspos',to_date('06-OCT-17','DD-MON-RR'),'MOPS',to_date('06-OCT-17','DD-MON-RR'),'REF NO :','saloni',null,null);
Insert into TCH_UTILITY_INFO (U_ID,U_IP_ADDRESS,U_PORT_NUMBER,U_URL,U_CREATED,U_APP_NAME,U_UPDATED,U_PRINT_LABELS,ADDED_BY,U_ONE_TIME_LBL,U_LBL_VAL) values (103,null,null,'https://uat-etendering.axisbank.co.in',to_date('06-OCT-17','DD-MON-RR'),'AXISEPAY',to_date('06-OCT-17','DD-MON-RR'),'NAME:,URN:,CRN:',null,null,null);
Insert into TCH_UTILITY_INFO (U_ID,U_IP_ADDRESS,U_PORT_NUMBER,U_URL,U_CREATED,U_APP_NAME,U_UPDATED,U_PRINT_LABELS,ADDED_BY,U_ONE_TIME_LBL,U_LBL_VAL) values (105,null,null,'https://stg-posservice.freecharge.in',to_date('06-OCT-17','DD-MON-RR'),'FREECHARGE',to_date('06-OCT-17','DD-MON-RR'),null,null,null,null);
--------------------------------------------------------
--  DDL for Index TCH_UTILITY_INFO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TCH_UTILITY_INFO_PK" ON "TCH_UTILITY_INFO" ("U_ID") ;
--------------------------------------------------------
--  Constraints for Table TCH_UTILITY_INFO
--------------------------------------------------------

  ALTER TABLE "TCH_UTILITY_INFO" ADD CONSTRAINT "TCH_UTILITY_INFO_PK" PRIMARY KEY ("U_ID");
