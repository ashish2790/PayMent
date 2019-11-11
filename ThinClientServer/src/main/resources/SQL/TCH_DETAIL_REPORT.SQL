--------------------------------------------------------
--  File created - Monday-December-26-2016   
--------------------------------------------------------
DROP TABLE "TCH_DETAIL_REPORT" cascade constraints;
--------------------------------------------------------
--  DDL for Table TCH_DETAIL_REPORT
--------------------------------------------------------

  CREATE TABLE "TCH_DETAIL_REPORT" 
   (	"D_ID" NUMBER, 
	"D_DISPLAY_MSG_PARAM" VARCHAR2(30 BYTE), 
	"D_COLUMN_NAME" VARCHAR2(30 BYTE), 
	"D_APP_NAME" VARCHAR2(10 BYTE), 
	"D_DISPLAY_SEQ_NUMBER" NUMBER(*,0), 
	"D_CREATED" DATE, 
	"D_UPDATED" DATE, 
	"ADDED_BY" VARCHAR2(50 BYTE)
   ) ;

Insert into TCH_DETAIL_REPORT (D_ID,D_DISPLAY_MSG_PARAM,D_COLUMN_NAME,D_APP_NAME,D_DISPLAY_SEQ_NUMBER,D_CREATED,D_UPDATED,ADDED_BY) values (1,'Tran Type','P_REQUEST_TYPE','TCH',1,null,null,null);
Insert into TCH_DETAIL_REPORT (D_ID,D_DISPLAY_MSG_PARAM,D_COLUMN_NAME,D_APP_NAME,D_DISPLAY_SEQ_NUMBER,D_CREATED,D_UPDATED,ADDED_BY) values (2,'Invoice Number','P_INVOICENUMBER','TCH',2,null,null,null);
Insert into TCH_DETAIL_REPORT (D_ID,D_DISPLAY_MSG_PARAM,D_COLUMN_NAME,D_APP_NAME,D_DISPLAY_SEQ_NUMBER,D_CREATED,D_UPDATED,ADDED_BY) values (3,'Auth code','P_AUTH_ID','TCH',3,null,null,null);
Insert into TCH_DETAIL_REPORT (D_ID,D_DISPLAY_MSG_PARAM,D_COLUMN_NAME,D_APP_NAME,D_DISPLAY_SEQ_NUMBER,D_CREATED,D_UPDATED,ADDED_BY) values (4,'Primary A/C No','P_LAST_FOUR_DIGIT','TCH',4,null,null,null);
Insert into TCH_DETAIL_REPORT (D_ID,D_DISPLAY_MSG_PARAM,D_COLUMN_NAME,D_APP_NAME,D_DISPLAY_SEQ_NUMBER,D_CREATED,D_UPDATED,ADDED_BY) values (5,'Exp Date','P_EXPIRYDATE','TCH',5,null,null,null);
Insert into TCH_DETAIL_REPORT (D_ID,D_DISPLAY_MSG_PARAM,D_COLUMN_NAME,D_APP_NAME,D_DISPLAY_SEQ_NUMBER,D_CREATED,D_UPDATED,ADDED_BY) values (6,'Amount in Rs','P_ORIGINAL_AMOUNT','TCH',6,null,null,null);
Insert into TCH_DETAIL_REPORT (D_ID,D_DISPLAY_MSG_PARAM,D_COLUMN_NAME,D_APP_NAME,D_DISPLAY_SEQ_NUMBER,D_CREATED,D_UPDATED,ADDED_BY) values (7,'Card Type','P_CARD_LABEL','TCH',7,null,null,null);
--------------------------------------------------------
--  DDL for Index TCH_DETAIL_REPORT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TCH_DETAIL_REPORT_PK" ON "TCH_DETAIL_REPORT" ("D_ID");
--------------------------------------------------------
--  Constraints for Table TCH_DETAIL_REPORT
--------------------------------------------------------

  ALTER TABLE "TCH_DETAIL_REPORT" ADD CONSTRAINT "TCH_DETAIL_REPORT_PK" PRIMARY KEY ("D_ID");
  ALTER TABLE "TCH_DETAIL_REPORT" MODIFY ("D_ID" NOT NULL ENABLE);
