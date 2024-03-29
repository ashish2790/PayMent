create or replace 
PROCEDURE TCH_CARD_DETAILS_PROC 
(
  I_TERMINAL_SERIAL_NUMBER IN VARCHAR2
, O_SQL_ERROR_CODE OUT VARCHAR2
, O_APP_ERROR_CODE OUT VARCHAR2
, O_DEBUG_POINT OUT VARCHAR2
, C_CARD_DETAILS OUT SYS_REFCURSOR  
) AS

V_COUNT NUMBER(4):=NULL;  

BEGIN
  
  O_DEBUG_POINT:='1';
  SELECT COUNT(1) INTO V_COUNT FROM TC_TERM_NFCEMV TTN JOIN TC_TID_HWSR_MAPPING THM ON TTN.MID = THM.MID AND TTN.TID = THM.TID 
          JOIN TC_ME_PARAMETER TMP ON  TMP.MEPARAMID = TTN.MEPARAMID 
          WHERE THM.HWSRNO = I_TERMINAL_SERIAL_NUMBER ;
  
    IF V_COUNT = 0 THEN
      BEGIN
        O_DEBUG_POINT:='1A';
        RAISE_APPLICATION_ERROR(-20021,'NO MAPPING FOUND IN INSIGHT');
      END;
    ELSE
      BEGIN
        OPEN C_CARD_DETAILS FOR
           
          SELECT TTN.NFC_AID, TTN.EMV_TAC_DECLINE, TTN.EMV_TAC_ONLINE, TTN.EMV_TAC_DEFAULT, TTN.APPLICATIONID, TTN.TARGET,
          TTN.THRESHOLDVALUE, TTN.MAXTARGET,TTN.NFC_CTLS_FLOOR_LIMIT, TTN.EMV_FLOORLIMIT, TTN.NFC_ODVC_TRNS_LIMIT, 
          TTN.NFC_NON_ODVC_TRNS_LIMIT, TTN.NFC_CTLS_CVM_REQ_LIMIT,TTN.ISNFC,TTN.NFC_TAC_DECLINE,TTN.NFC_TAC_ONLINE,TTN.NFC_TAC_DEFAULT,TTN.ISEMVACK
          FROM TC_TERM_NFCEMV TTN JOIN TC_TID_HWSR_MAPPING THM ON TTN.MID = THM.MID AND TTN.TID = THM.TID 
          JOIN TC_ME_PARAMETER TMP ON  TMP.MEPARAMID = TTN.MEPARAMID 
          WHERE THM.HWSRNO = I_TERMINAL_SERIAL_NUMBER 
          ORDER BY TTN.TERMNFCEMVID DESC ;
          
      END;
    END IF;
    O_DEBUG_POINT :='2';
     EXCEPTION
      WHEN NO_DATA_FOUND THEN 
        IF O_DEBUG_POINT = '1A' THEN 
            O_APP_ERROR_CODE := 'C-101';
        END IF;
      WHEN OTHERS THEN
        IF O_DEBUG_POINT = '1A' THEN 
            O_APP_ERROR_CODE := 'C-101'; 
        END IF ;      
        O_SQL_ERROR_CODE := SQLCODE; 
        
END TCH_CARD_DETAILS_PROC;