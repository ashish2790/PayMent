--------------------------------------------------------
--  File created - Thursday-February-23-2017   
--------------------------------------------------------
DROP TABLE "TCH_BATCH_SUMMARY";
--------------------------------------------------------
--  DDL for Table TCH_BATCH_SUMMARY
--------------------------------------------------------

  CREATE TABLE "TCH_BATCH_SUMMARY" 
   (	"ID" NUMBER, 
	"B_MID" VARCHAR2(25 BYTE), 
	"B_TID" VARCHAR2(20 BYTE), 
	"B_TERMINAL_SERIAL_NUMBER" VARCHAR2(20 BYTE), 
	"B_OPEN_DATE" TIMESTAMP (6), 
	"B_CLOSE_DATE" TIMESTAMP (6), 
	"B_BATCH_NUMBER" VARCHAR2(10 BYTE)
   ) ;