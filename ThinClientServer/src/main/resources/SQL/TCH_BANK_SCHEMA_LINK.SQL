--------------------------------------------------------
--  DDL for Table TCH_BANK_SCHEMA_LINK
--------------------------------------------------------

  CREATE TABLE "TCH_BANK_SCHEMA_LINK" 
   (	"BS_BANKID" NUMBER(10,0), 
	"BS_SCHEMAID" NUMBER(10,0)
   );
--------------------------------------------------------
--  Constraints for Table TCH_BANK_SCHEMA_LINK
--------------------------------------------------------

  ALTER TABLE "TCH_BANK_SCHEMA_LINK" MODIFY ("BS_SCHEMAID" NOT NULL ENABLE);
  ALTER TABLE "TCH_BANK_SCHEMA_LINK" MODIFY ("BS_BANKID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table TCH_BANK_SCHEMA_LINK
--------------------------------------------------------

  ALTER TABLE "TCH_BANK_SCHEMA_LINK" ADD CONSTRAINT "FK_BANKID" FOREIGN KEY ("BS_BANKID")
	  REFERENCES "TCH_BANK_MASTER" ("B_BANKID") ENABLE;
  ALTER TABLE "TCH_BANK_SCHEMA_LINK" ADD CONSTRAINT "FK_SCHEMAID" FOREIGN KEY ("BS_SCHEMAID")
	  REFERENCES "TCH_SCHEMA_MASTER" ("S_SCHEMAID") ENABLE;
