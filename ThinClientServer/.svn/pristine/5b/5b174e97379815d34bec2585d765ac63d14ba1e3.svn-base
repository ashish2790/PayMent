package com.awl.tch.externalentityImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.BillingObj;
import com.awl.tch.bean.Payment;
import com.awl.tch.bridge.ExternalEntityBridge;
import com.awl.tch.bridge.ExternlaEntityInterface;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ExternalEntityConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.irctc.exception.IRCTCException;
import com.awl.tch.irctc.service.IrctcSaleConfirmSerivceImpl;
import com.awl.tch.irctc.service.IrctcSaleEnqServiceImpl;
import com.awl.tch.irctc.service.IrctcVoidServiceImpl;
import com.awl.tch.model.IrctcDTO;
import com.tch.irctc.model.IrctcCacheObject;
import com.tch.irctc.model.SaleConfirm;
import com.tch.irctc.model.SaleEnquiry;
import com.tch.irctc.model.VoidTxn;

@Service(Constants.TCH_IRCTC_BRIDGE)
public class IrctcBridgeServiceImpl extends ExternalEntityBridge implements ExternlaEntityInterface<Payment>{

	private static Logger logger = LoggerFactory.getLogger(IrctcBridgeServiceImpl.class);
	@Override
	public Payment updateStatus(Payment input) throws TCHServiceException {
		
		logger.debug("inside codition for IRCTC");
		try{
			 String[] arr = getMidTids(input.getTerminalSerialNumber(), null);
			 input.setMerchantId(arr[0]);
			 input.setTerminalId(arr[1]);
			}catch(TCHQueryException e){
				throw new TCHServiceException(e.getErrorCode(),e.getErrorMessage());
			}
		
		IrctcCacheObject cacheObject = ExternalEntityBridge.irctcCache.get(input.getTerminalSerialNumber());
		SaleConfirm saleConfirm=new SaleConfirm();
		if(cacheObject != null){
			saleConfirm.setCardProvider(cacheObject.getCardLabel());
			saleConfirm.setCardType(cacheObject.getCardType());
			saleConfirm.setInvoiceNumber(cacheObject.getInvoiceNumber());
			saleConfirm.setCardDigit(cacheObject.getLast4Digit());
			saleConfirm.setBankCode(ExternalEntityConstants.TCH_IRCTC_BANKCODE);
			saleConfirm.setCpgTxnId(cacheObject.getCpgTxnId());
			saleConfirm.setAppTxnId(cacheObject.getTxnId());
	        saleConfirm.setAuthCode(cacheObject.getAuthCode());
	        saleConfirm.setBatchNo(String.format("%06d", Integer.valueOf(cacheObject.getBatchNumber())));
			logger.debug("cacheObject.getCardType()  :" +cacheObject.getCardType());
			saleConfirm.setCardType(cacheObject.getCardType() != null ? cacheObject.getCardType() : "D");
	        saleConfirm.setPinStatus(cacheObject.getPinStatus());

		}
		
		//changes as per docx start
        saleConfirm.setPosMID(input.getMerchantId()); 
       // saleConfirm.setAuthCode(input.getAuthorizationId());
        cacheObject.setPosMID(saleConfirm.getPosMID());
        //String paddedBatchNo = String.format("%06d", input.getBatchNumber());
        //saleConfirm.setBatchNo(paddedBatchNo);
        //changes as per docx end

		saleConfirm.setTid(input.getTerminalId());
		saleConfirm.setAssetSrNo(input.getTerminalSerialNumber());
		saleConfirm.setServiceProvider(ExternalEntityConstants.TCH_IRCTC_SERVICE_PROVIDER);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String datevalue = sdf.format(new Date());
		saleConfirm.setTimeStamp(datevalue);
		String amount = new BigDecimal(input.getOriginalAmount()).movePointLeft(2).toPlainString();
		logger.debug("amount passed :" + amount);
		saleConfirm.setAmount(amount);
		
		//Check for txn id id more than 20 amount is appeneded from terminal end.
		// Removing the same from tch
		logger.debug("Original ref value");
		if(input.getReferenceValue().length() > 21){
			input.setReferenceValue(input.getReferenceValue().substring(0,21));
		}
		//end of changes
		
		//saleConfirm.setAppTxnId(input.getReferenceValue());   
		saleConfirm.setCpgTxnId(input.getReferenceValue());   //change to cpg instead of app
		saleConfirm.setRrn(input.getRetrivalRefNumber());
		IrctcSaleConfirmSerivceImpl saleCon =new IrctcSaleConfirmSerivceImpl();
		IrctcDTO irctcDto =new IrctcDTO();
		try {
			saleConfirm = saleCon.cosumeWS(getUtilityInfo(input), saleConfirm);
		irctcDto.setTid(input.getTerminalId());
		irctcDto.setAsstSrNo(input.getTerminalSerialNumber());
		irctcDto.setServiceProvider(ExternalEntityConstants.TCH_IRCTC_SERVICE_PROVIDER);
		/*irctcDto.setCpgTxnId(input.getReferenceValue());   //change to cpg instead of app */
		irctcDto.setAmount(input.getOriginalAmount());
		if(cacheObject != null){
			irctcDto.setInvoiceNo(cacheObject.getInvoiceNumber());
			irctcDto.setCardigit(cacheObject.getLast4Digit());
			irctcDto.setCardProvider(cacheObject.getCardLabel());
			irctcDto.setCardType(cacheObject.getCardType() != null ? cacheObject.getCardType() : "D");
			irctcDto.setPosMID(cacheObject.getPosMID());
			irctcDto.setAuthCode(cacheObject.getAuthCode());
			irctcDto.setPinStatus(cacheObject.getPinStatus());
			irctcDto.setBatchNo(String.format("%06d", Integer.valueOf(cacheObject.getBatchNumber())));
		}
		irctcDto.setRrn(input.getRetrivalRefNumber());
		irctcDto.setTimeStamp(datevalue);
		irctcDto.setReqType(ExternalEntityConstants.TCH_IRCTC_SALE_CONFIRM);
		irctcDto.setStatus(saleConfirm.getStatus());
		irctcDto.setAppTxnId(saleConfirm.getAppTxnId());
		irctcDto.setCpgTxnId(saleConfirm.getCpgTxnId());
		//changes as per docx start
		
		irctcDto.setStnCode(saleConfirm.getStnCode());
		irctcDto.setDivCode(saleConfirm.getDivCode());
		irctcDto.setZoneCode(saleConfirm.getZoneCode());
		irctcDto.setRlyTid(saleConfirm.getRlyTID());
		irctcDto.setClientAppCode(saleConfirm.getClientAppCode());
		irctcDto.setMessage(saleConfirm.getMessage());
		//changes as per docx end
		input.setStatus(saleConfirm.getStatus());
		irctcDao.updateInfo(irctcDto);
		irctcDao.updatepaymentDto(irctcDto);
		} catch (IRCTCException e) {
			throw new TCHServiceException(e.getErrorCode(),e.getErrorMessage());
		}finally{
			logger.debug("Clearing cache...");
			ExternalEntityBridge.irctcCache.remove(input.getTerminalSerialNumber());
		}
		return input;		
	}

	@Override
	public Payment getAmount(Payment input) throws TCHServiceException {
		logger.debug("inside codition for IRCTC");
		
		try{
			 String[] arr = getMidTids(input.getTerminalSerialNumber(), null);
			 input.setMerchantId(arr[0]);
			 input.setTerminalId(arr[1]);
			}catch(TCHQueryException e){
				throw new TCHServiceException(e.getErrorCode(),e.getErrorMessage());
			}
		logger.debug("Merchant id :["+ input.getMerchantId()+"]");
		logger.debug("Terminal id :["+ input.getTerminalId()+"]");
		
		SaleEnquiry saleEnquiry=new SaleEnquiry();
		saleEnquiry.setTid(input.getTerminalId());
		saleEnquiry.setAssetSrNo(input.getTerminalSerialNumber());
		saleEnquiry.setServiceProvider(ExternalEntityConstants.TCH_IRCTC_SERVICE_PROVIDER);
		saleEnquiry.setBankCode(ExternalEntityConstants.TCH_IRCTC_BANKCODE);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String date = sdf.format(new Date());
		saleEnquiry.setTimeStamp(date);
		IrctcSaleEnqServiceImpl saleEnq =new IrctcSaleEnqServiceImpl();
		IrctcDTO irctcDto =new IrctcDTO();
		try {
			logger.debug("Sending request to service");
			saleEnquiry = saleEnq.cosumeWS(getUtilityInfo(input), saleEnquiry);
			input.setReferenceValue(saleEnquiry.getCpgTxnId()); // send reference value to terminal // change to cpg
			//extra check to avoid repeat use of transction id
			if(!irctcDao.isAreadyUsedTxnId(input)){
				throw new IRCTCException("IR-04", "TXN ID ALREADY USED");
			}
			IrctcCacheObject cacheObject = new IrctcCacheObject();
			irctcDto.setTid(saleEnquiry.getTid());
			irctcDto.setMid(input.getMerchantId());
			irctcDto.setAsstSrNo(input.getTerminalSerialNumber());
			irctcDto.setStatus(saleEnquiry.getStatus());
			irctcDto.setServiceProvider(ExternalEntityConstants.TCH_IRCTC_SERVICE_PROVIDER);
			irctcDto.setTimeStamp(date);
			irctcDto.setAppTxnId(saleEnquiry.getAppTxnId());
			irctcDto.setCpgTxnId(saleEnquiry.getCpgTxnId());
			
			irctcDto.setReqType(ExternalEntityConstants.TCH_IRCTC_ENQUIRY);
			String amount = new BigDecimal(saleEnquiry.getAmount()).multiply(HUNDRED_VAL).stripTrailingZeros().toPlainString();
			logger.debug("amount passed :" + amount);
			irctcDto.setAmount(amount);
			
			input.setOriginalAmount(amount);
			List<BillingObj> lstBillingSBIEapy = new ArrayList<BillingObj>();
			
			BillingObj irctcBlg = new BillingObj();
			irctcBlg.setLabelName(getUtilityInfo(input).get(Constants.TCH_IRCTC_LABEL1));
			irctcBlg.setLabelValue(saleEnquiry.getAppTxnId());
			lstBillingSBIEapy.add(irctcBlg);
			
			irctcBlg = new BillingObj();
			irctcBlg.setLabelName(getUtilityInfo(input).get(Constants.TCH_IRCTC_LABEL2));
			irctcBlg.setLabelValue(saleEnquiry.getAmount());
			lstBillingSBIEapy.add(irctcBlg);
			
			
			BillingObj[] blgObjArr1 = new BillingObj[lstBillingSBIEapy.size()];
			input.setBillingObject(lstBillingSBIEapy.toArray(blgObjArr1)); // set billing object
			
			cacheObject.setAmount(amount);
			cacheObject.setTerminalSerialNumber(input.getTerminalSerialNumber());
			cacheObject.setTxnId(saleEnquiry.getAppTxnId());
			cacheObject.setCpgTxnId(saleEnquiry.getCpgTxnId());
			
			
			ExternalEntityBridge.irctcCache.put(input.getTerminalSerialNumber(), cacheObject); // store information in cache
			
			irctcDao.save(irctcDto);
		} catch (IRCTCException e) {
			ExternalEntityBridge.irctcCache.remove(input.getTerminalSerialNumber());
			throw new TCHServiceException(e.getErrorCode(),e.getErrorMessage());
		} 
		return input;
	}
	
	public static void main(String[] args) throws TCHServiceException {
		/*Payment input = new Payment();
		if(args != null && args.length > 0){
		input.setTerminalSerialNumber(args[0]);
		input.setTerminalId(args[1]);}
		IrctcBridgeServiceImpl n = new IrctcBridgeServiceImpl();
		n.getAmount(input);*/
		String str = "0601201814493401234561231";
		System.out.println(str.substring(0,21));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		System.out.println(date);
	}
	
	public void voidTransaction(Payment input) throws TCHServiceException{
		try{
			 String[] arr = getMidTids(input.getTerminalSerialNumber(), null);
			 input.setMerchantId(arr[0]);
			 input.setTerminalId(arr[1]);
			}catch(TCHQueryException e){
				throw new TCHServiceException(e.getErrorCode(),e.getErrorMessage());
			}
		logger.debug("Merchant id :["+ input.getMerchantId()+"]");
		logger.debug("Terminal id :["+ input.getTerminalId()+"]");

		IrctcVoidServiceImpl voidService = new IrctcVoidServiceImpl();
		try{
			VoidTxn txnType = new VoidTxn();
			txnType.setAmount(new BigDecimal(input.getOriginalAmount()).movePointLeft(2).toPlainString());
			txnType.setAssetSrNo(input.getTerminalSerialNumber());
			txnType.setTid(input.getTerminalId());
			txnType.setAppTxnId(irctcDao.getApptxnId(input.getIrctcRefValue()));
			txnType.setCpgTxnId(input.getIrctcRefValue());
			voidService.cosumeWS(getUtilityInfo(input), txnType);
			
			IrctcDTO dto = new IrctcDTO();
			dto.setAppTxnId(txnType.getAppTxnId());
			dto.setCpgTxnId(txnType.getCpgTxnId());
			dto.setReqType(ExternalEntityConstants.TCH_IRCTC_VOID);
			dto.setMessage(txnType.getMessage());
			irctcDao.updateInfo(dto);
			
		}catch(IRCTCException e){
			throw new TCHServiceException(e.getErrorCode(),e.getErrorMessage());
		}catch (Exception e) {
			logger.debug("Exception occured...",e);
		}
	}
	
		
}
