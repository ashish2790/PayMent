--------------------------------------------------------
--  File created - Monday-December-26-2016   
--------------------------------------------------------
DROP TABLE "TCH_JOB_INFO" cascade constraints;
--------------------------------------------------------
--  DDL for Table TCH_JOB_INFO
--------------------------------------------------------

  CREATE TABLE "TCH_JOB_INFO" 
   (	"J_ID" NUMBER, 
	"J_JOB_NAME" VARCHAR2(90 BYTE), 
	"J_GROUP_NAME" VARCHAR2(50 BYTE), 
	"J_JOB_CLASS_NAME" VARCHAR2(50 BYTE), 
	"J_JOB_CRON_EXPR" VARCHAR2(30 BYTE), 
	"J_JOB_STATUS" VARCHAR2(1 BYTE), 
	"J_IDENTITY_NAME" VARCHAR2(20 BYTE), 
	"J_JOB_DESCRIPTION" VARCHAR2(100 BYTE), 
	"ADDED_BY" VARCHAR2(50 BYTE)
   ) ;
Insert into TCH_JOB_INFO (J_ID,J_JOB_NAME,J_GROUP_NAME,J_JOB_CLASS_NAME,J_JOB_CRON_EXPR,J_JOB_STATUS,J_IDENTITY_NAME,J_JOB_DESCRIPTION,ADDED_BY) values (1,'AutoSettlementTXN','autoSettle','AutoSettlementJob','0 0 0 1/1 * ? *','A','AutoSettlement','Settle transaction auto',null);
--------------------------------------------------------
--  DDL for Index TCH_JOB_INFO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TCH_JOB_INFO_PK" ON "TCH_JOB_INFO" ("J_ID") ;
--------------------------------------------------------
--  Constraints for Table TCH_JOB_INFO
--------------------------------------------------------

  ALTER TABLE "TCH_JOB_INFO" ADD CONSTRAINT "TCH_JOB_INFO_PK" PRIMARY KEY ("J_ID");
  ALTER TABLE "TCH_JOB_INFO" MODIFY ("J_ID" NOT NULL ENABLE);
