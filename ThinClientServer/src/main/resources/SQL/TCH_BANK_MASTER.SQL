--------------------------------------------------------
--  File created - Monday-December-26-2016   
--------------------------------------------------------
DROP TABLE "TCH_BANK_MASTER" cascade constraints;
--------------------------------------------------------
--  DDL for Table TCH_BANK_MASTER
--------------------------------------------------------

  CREATE TABLE "TCH_BANK_MASTER" 
   (	"B_BANKID" NUMBER(10,0), 
	"B_BANKCODE" VARCHAR2(20 BYTE), 
	"B_BANKNAME" VARCHAR2(50 BYTE), 
	"B_CREATED" TIMESTAMP (6), 
	"B_UPDATED" TIMESTAMP (6),
	"B_BANK_LOGO" BLOB
   ) ;

SET DEFINE OFF;
Insert into TCH.TCH_BANK_MASTER (B_BANKID,B_BANKCODE,B_BANKNAME,B_CREATED,B_UPDATED) values (3,'0007','BOI',to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into TCH.TCH_BANK_MASTER (B_BANKID,B_BANKCODE,B_BANKNAME,B_CREATED,B_UPDATED) values (4,'0008','SBI',to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into TCH.TCH_BANK_MASTER (B_BANKID,B_BANKCODE,B_BANKNAME,B_CREATED,B_UPDATED) values (5,'0009','Bandhan',to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into TCH.TCH_BANK_MASTER (B_BANKID,B_BANKCODE,B_BANKNAME,B_CREATED,B_UPDATED) values (1,'0006','Axis Bank',to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into TCH.TCH_BANK_MASTER (B_BANKID,B_BANKCODE,B_BANKNAME,B_CREATED,B_UPDATED) values (2,'0005','UBI',to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),to_timestamp('06-SEP-16 12.00.00.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
--------------------------------------------------------
--  DDL for Index TCH_BANK_MASTER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "TCH_BANK_MASTER_PK" ON "TCH_BANK_MASTER" ("B_BANKID");
--------------------------------------------------------
--  Constraints for Table TCH_BANK_MASTER
--------------------------------------------------------

  ALTER TABLE "TCH_BANK_MASTER" ADD CONSTRAINT "TCH_BANK_MASTER_PK" PRIMARY KEY ("B_BANKID");
  ALTER TABLE "TCH_BANK_MASTER" MODIFY ("B_UPDATED" NOT NULL ENABLE);
  ALTER TABLE "TCH_BANK_MASTER" MODIFY ("B_CREATED" NOT NULL ENABLE);
  ALTER TABLE "TCH_BANK_MASTER" MODIFY ("B_BANKNAME" NOT NULL ENABLE);
  ALTER TABLE "TCH_BANK_MASTER" MODIFY ("B_BANKCODE" NOT NULL ENABLE);
  ALTER TABLE "TCH_BANK_MASTER" MODIFY ("B_BANKID" NOT NULL ENABLE);
