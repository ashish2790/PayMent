package com.awl.tch.adaptor.wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.awl.tch.bean.DataPrintObject;
import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.dao.CommonDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.UtilityDTO;
import com.awl.tch.model.WalletDTO;
import com.awl.tch.server.ApplicationContenxtProvider;
import com.awl.tch.util.AppConfigMaster;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.ISOMessages;
import com.awl.tch.util.ReversalReqCall;
import com.awl.tch.util.UtilityHelper;
import com.awl.tch.wallet.common.WalletException;
import com.awl.tch.dao.WalletDaoImpl;
import com.awl.tch.wallet.fc.bean.WalletResponseBean;
import com.awl.tch.wallet.fc.constant.FcConstant;
import com.awl.tch.wallet.fc.model.FcWalletRequest;
import com.awl.tch.wallet.fc.service.FcService;
import com.awl.tch.wallet.fc.utility.HelperUtil;


/**
 * FcWalletAdaptorImpl maps the terminal request into Tch-host(to Fc-host) request.
 * On the basis of reference value(otp) from terminal request, 2 legs of sale enquiry are differentiated.
 * Request is saved in WalletDTO while response is updated
 * @author pooja.patil
 *
 */
@Component(Constants.TCH_FCWALLET_ADAPTOR)
public class FcWalletAdaptorImpl implements WalletAdaptor{

	private static Logger logger = LoggerFactory.getLogger(FcWalletAdaptorImpl.class);

	@Autowired
	@Qualifier(FcConstant.TCH_FC_SALE_SERVICE_ENQ)
	protected FcService fcSaleService;

	@Autowired
	@Qualifier(FcConstant.TCH_FC_SERVICE_DAO)
	protected WalletDaoImpl fcWalletDaoImpl;

	@Autowired
	@Qualifier(Constants.TCH_REVERSAL_CALL)
	protected ReversalReqCall reversalReqCall ;
	
	@Autowired
	@Qualifier(Constants.TCH_COMMON_DAO)
	protected CommonDaoImpl commonDao;
	
	@Autowired
	@Qualifier(FcConstant.TCH_FC_REFUND_SERVICE)
	protected FcService fcRefundService;
	
	/**
	 * Request from terminal(Payment input) is mapped into FcWalletRequest.
	 * request is identified as 1st or 2nd leg on the basis of ReferenceValue check(as otp)
	 * and sent to Fc host. Request is saved in WalletDTO and response is updated after receiving in WalletDTO.
	 * @param input
	 * @return input
	 * @throws TCHServiceException, TCHQueryException
	 * @author pooja.patil
	 */
	public Payment getSaleEnquiry(Payment input) throws TCHServiceException, TCHQueryException{ 
		// in case of sale ack reject for wallet
		if(Constants.TCH_SALE_ACK.equalsIgnoreCase(input.getDecisionFlag())){
			logger.debug("Inside sale SALE ACK. Throwing exception. Waiting for reversal");
			ackTransaction(input,Constants.TCH_REQUEST_SALE);
		}
		
		FcWalletRequest saleRequest = new FcWalletRequest();
		WalletDTO walletDto =null;
		long startTime = System.currentTimeMillis();
		try {
			// Setting request common to both leg
			saleRequest.setMerchantId(input.getMerchantId()); 
			saleRequest.setTerminalId(input.getTerminalId());
			saleRequest.setUrl(getUtilityInfo(input).get(Constants.TCH_URL));
			saleRequest.setPlatformId(FcConstant.TCH_FC_PLATFORMID);
			saleRequest.setWalletId(input.getMobileNumber()); 
			//String amt = new BigDecimal(input.getOriginalAmount()).movePointLeft(2).toString();
			saleRequest.setTxnAmount(input.getOriginalAmount());
			saleRequest.setTxnDatenTime(UtilityHelper.getFCDateTime());
			saleRequest.setProcCode(FcConstant.TCH_FC_SALE_PROCCODE);
			saleRequest.setInvNumber(input.getInvoiceNumber());
			saleRequest.setRequestType(Constants.TCH_REQUEST_SALE);
			saleRequest.setTermSerNum(input.getTerminalSerialNumber());

			input.setCurrencyCode(Constants.INR_CURR_CODE);
			input.setCurrencyName(Constants.INR_CURR_NAME);
			
			if(isSecondLeg(input.getReferenceValue())){
				logger.info("Executing second leg of Sale enquiry..");
				// Set Otp and get transaction ref number from DB
				saleRequest.setOtp(input.getReferenceValue());
				try {
					//get existing wallet details from DTO on the basis of RRN
					walletDto = fcWalletDaoImpl.getExistingWalletDetails(input.getRetrivalRefNumber());
					logger.info("Auth Token [ "+walletDto.getAuthToken()+" ]");
					logger.info("Transaction Ref number [ "+input.getRetrivalRefNumber()+" ]");
					saleRequest.setTransactionRefNumber(input.getRetrivalRefNumber());
					saleRequest.setAuthToken(walletDto.getAuthToken());
					logger.debug(walletDto.toString());
				} catch (TCHQueryException e) {
					logger.debug("Exception while getting existing Transaction Reference number");
					throw new TCHServiceException(ErrorConstants.TCH_W101, "INVALID REFERENCE NUMBER");
				}
			}else{
				logger.info("Executing first leg of Sale enquiry..");
				// First leg hence generate new transaction ref number
				saleRequest.setTransactionRefNumber(HelperUtil.getReferenceNumber(commonDao.getSequenceNumber()));
				String authToken = HelperUtil.genAuthToken(saleRequest.getMerchantId(), saleRequest.getTerminalId(), saleRequest.getTransactionRefNumber());
				saleRequest.setAuthToken(authToken);
				logger.info("Transaction Ref number [ "+saleRequest.getTransactionRefNumber()+" ]");
				logger.info("Auth Token [ "+saleRequest.getAuthToken()+" ]");
				logger.info("Wallet Id [ "+saleRequest.getWalletId()+" ]");
				walletDto = new WalletDTO().getWalletDTO(saleRequest,Constants.WALLET_TYPE_FC);
				fcWalletDaoImpl.getBatchNumber(input);
				walletDto.setBatchNumber(input.getBatchNumber());
				fcWalletDaoImpl.save(walletDto);
			}
			logger.info("Date and Time [ "+saleRequest.getTxnDatenTime()+" ]");
			
			logger.info("Sale Request [ "+saleRequest.toString()+" ]");
			WalletResponseBean response = fcSaleService.cosumeWS(saleRequest);
			//set response into input
			logger.debug("Setting input from response : [ "+response+" ]");

			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			if(isSecondLeg(input.getReferenceValue()) && totalTime > Constants.TCH_TIMEOUT){
				logger.info("Response receiving time taken [ "+(endTime - startTime)+" ]");
				getReversal(input); 
			}

			List<DataPrintObject> listPrintObj = new ArrayList<DataPrintObject>();
			if(response != null && Constants.TCH_SUCCESS_RESPONSE.equals(response.getResponseCode())){
				//condition to check if its 2nd leg or not
				if(isSecondLeg(input.getReferenceValue())){
					//To print mobile number on charge-slip after 2nd leg
					if(input.getAppName() != null){
						UtilityDTO utilityInfoRow = AppConfigMaster.getUtilInfoValue(input.getAppName());
						if(utilityInfoRow != null){
							if(Constants.WALLET_FREECHARGE.equals(input.getAppName())){
								DataPrintObject d = new DataPrintObject();
								String[] a = utilityInfoRow.getPrintLabels().split(",");
								d.setPrintLabel(a[0]);
								String maskedMobileNumber = UtilityHelper.maskedMobileNumber(input.getMobileNumber());
								d.setPrintVal(maskedMobileNumber);
								listPrintObj.add(d);
							}
							DataPrintObject[] prntObjArray = new DataPrintObject[listPrintObj.size()];
							input.setDataPrintObject(listPrintObj.toArray(prntObjArray));
						}
					}
					walletDto.setIsCompleted(Constants.TCH_Y);
				}
				
				input.setResponseCode(response.getResponseCode());
				input.setRetrivalRefNumber(saleRequest.getTransactionRefNumber());
				input.setBatchNumber(walletDto.getBatchNumber());
				
				walletDto.setInvNumber(input.getInvoiceNumber());
				setResponseNull(input); //set response null as per terminal requirement
				//Get existing DB entry to update response in case of 2nd leg.
				walletDto.setResponseCode(response.getResponseCode());
				walletDto.setResponseDesc(response.getResponseDesc());
				walletDto.setServerTxnId(response.getTxnId());
				fcWalletDaoImpl.update(walletDto); 
			}else{
				walletDto.setResponseCode(response.getResponseCode());
				walletDto.setResponseDesc(response.getResponseDesc());
				walletDto.setServerTxnId(response.getTxnId());
				walletDto.setInvNumber(input.getInvoiceNumber());
				fcWalletDaoImpl.update(walletDto); 
				throw new TCHServiceException(ErrorConstants.TCH_W101, response.getResponseDesc());
			}
			
		}
		catch (TCHQueryException e) {
			throw new TCHServiceException(e.getErrorCode(), e.getMessage());
		}
		catch (Exception e) {
			throw new TCHServiceException(ErrorConstants.TCH_W101, e.getMessage());
		}
		return input;
	}
	
	
	/**
	 * To check whether the incoming request is of 2nd leg or not
	 * @param otp
	 * @author pooja.patil
	 */
	private boolean isSecondLeg(String otp) {
		return (null != otp && !otp.isEmpty());
	}

	/**
	 * Unnecessary fields are set as null before sending response to Terminal.
	 * @param input
	 * @author pooja.patil
	 */
	private void setResponseNull(Payment input) {
		input.setCardEntryMode(null);
		input.setInvoiceNumber(null);
		input.setStanNumber(null);
		input.setMobileNumber(null);
		input.setMenuobj(null);
		input.setReferenceValue(null);
		input.setNoOfCount(null);
	}

	public static Map<String,String> getUtilityInfo(Payment input){
		return AppConfigMaster.getConfigValue(input.getAppName());
	}
	
	@Override
	public Payment processRefundReq(Payment input) throws TCHServiceException, TCHQueryException {
		if(Constants.TCH_REF_ACK.equalsIgnoreCase(input.getDecisionFlag())){
			logger.debug("Inside sale REFUND ACK. Throwing exception. Waiting for reversal");
			ackTransaction(input, Constants.TCH_REQUEST_REFUND);
		}
		
		logger.debug("Inside processVoidReq()");
		FcWalletRequest refundReq=new FcWalletRequest();

		try {
			refundReq.setMerchantId(input.getMerchantId());
			refundReq.setTerminalId(input.getTerminalId());

			refundReq.setPlatformId(FcConstant.TCH_FC_PLATFORMID);
			String txnRefNo = HelperUtil.getReferenceNumber(commonDao.getSequenceNumber());
			refundReq.setTransactionRefNumber(txnRefNo);
			
			logger.debug("TXNREFNO :" +refundReq.getTransactionRefNumber());
			
			refundReq.setTxnDatenTime(UtilityHelper.getFCDateTime());
			refundReq.setUrl(getUtilityInfo(input).get(Constants.TCH_URL));
			refundReq.setProcCode(FcConstant.TCH_FC_REFUND_PROCCODE);

			String authToken = HelperUtil.genAuthToken(refundReq.getMerchantId(), refundReq.getTerminalId(), refundReq.getTransactionRefNumber());
			logger.debug("AUTHTOKEN  :"+authToken);
			refundReq.setServerTxnId(input.getRetrivalRefNumber());
			refundReq.setAuthToken(authToken);
			refundReq.setRequestType(Constants.TCH_REQUEST_REFUND);
			refundReq.setAdditinalInfo(null);
			refundReq.setTermSerNum(input.getTerminalSerialNumber());
			refundReq.setInvNumber(input.getInvoiceNumber());
			WalletDTO walletDto = new WalletDTO().getWalletDTO(refundReq,Constants.WALLET_TYPE_FC);			
			fcWalletDaoImpl.save(walletDto);

			WalletResponseBean response = fcRefundService.cosumeWS(refundReq);
			logger.debug("Response is: ["+response+"]");

			//set response into input
			input.setResponseCode(response.getResponseCode());
			input.setRetrivalRefNumber(refundReq.getTransactionRefNumber());

			walletDto.setResponseDesc(response.getResponseDesc());
			logger.debug("TXN ID :"+response.getTxnId());
			walletDto.setServerTxnId(response.getTxnId());
			walletDto.setResponseCode(response.getResponseCode());
			walletDto.setTxnAmount(input.getOriginalAmount());
			walletDto.setWalletId(input.getMobileNumber());
			fcWalletDaoImpl.getBatchNumber(input);
			walletDto.setBatchNumber(input.getBatchNumber());
			input.setCurrencyCode(Constants.INR_CURR_CODE);
			input.setCurrencyName(Constants.INR_CURR_NAME);
			input.setHealthObject(null);
			/*setRefundApproved as 1 if we get success response*/
			if((Constants.TCH_SUCCESS_RESPONSE).equals(walletDto.getResponseCode())){
				walletDto.setRefundApproved(Constants.TCH_1);
				walletDto.setIsCompleted(Constants.TCH_Y);
				fcWalletDaoImpl.update(walletDto);
			}
			else{
				fcWalletDaoImpl.update(walletDto);
				throw new TCHServiceException(ErrorConstants.TCH_W101, response.getResponseDesc());
			}
		} 
		catch (TCHQueryException e) {
			throw new TCHServiceException(e.getErrorCode(), e.getMessage());
		}
		catch (WalletException e) {
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		} 
		input.setMobileNumber(null);
		input.setMenuobj(null);
		return input;
	}

	/**
	 * To check whether sale or refund is completed. If yes then return true.
	 * @param input, requestType
	 * @author pooja.patil
	 */
	protected boolean ackTransaction(final Payment input, String requestType) throws TCHServiceException{
		try {
			logger.debug("Inside " +requestType+ "_ack condition");
			WalletDTO exstWalletDTO = fcWalletDaoImpl.getDetailsByRequestType(requestType, input.getRetrivalRefNumber()); 
			
			if(exstWalletDTO != null){
				input.setResponseCode(exstWalletDTO.getResponseCode());
				input.setRetrivalRefNumber(exstWalletDTO.getTxnRefId());
				input.setBatchNumber(exstWalletDTO.getBatchNumber());
				if(exstWalletDTO.getResponseCode() != null && Constants.TCH_SUCCESS_RESPONSE.equals(exstWalletDTO.getResponseCode())){
					logger.debug("Getting successful transaction ");
					setResponseNull(input);
					return true;
					} else {
						logger.debug("generate reversal forcefully..");
						throw new TCHServiceException(ErrorConstants.TCH_S014, ISOMessages.getSpecificMessageBasedOnResponseCode(exstWalletDTO.getResponseCode()));
					}
				}else{
					logger.debug("Original transaction not found");
					input.setSaleAckWrong(Constants.TCH_Y);
					throw new TCHServiceException(Constants.SACK_REJECT,"Exception in sale ack while not found orginal transaction and dont give response terminal");
				}
			
		} catch (TCHQueryException e) {
			logger.debug(e.getMessage());
			throw new TCHServiceException(e.getErrorCode(), ErrorMaster.get(e.getErrorCode()));
		}
	}


	/**
	 * sets WalletDTO fields from corresponding FcWalletRequest fields to save in DTO.
	 * @param input
	 * @author pooja.patil
	 */
	public WalletDTO saveDtoSale(FcWalletRequest req) {
		logger.debug("Saving request into DTO");
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setMid(req.getMerchantId());
		walletDTO.setTid(req.getTerminalId());
		walletDTO.setPlatformId(req.getPlatformId());
		walletDTO.setTxnRefId(req.getTransactionRefNumber());
		walletDTO.setWalletId(req.getWalletId());
		walletDTO.setTxnAmount(req.getTxnAmount());
		walletDTO.setTxnDateNTime(UtilityHelper.getWalletDateTime(req.getTxnDatenTime()));
		walletDTO.setProcCode(req.getProcCode());
		walletDTO.setAuthToken(req.getAuthToken());
		walletDTO.setReqType(Constants.TCH_REQUEST_SALE);
		walletDTO.setInvNumber(req.getInvNumber());
		walletDTO.setTermSerNum(req.getTermSerNum());
		walletDTO.setWalletType(Constants.WALLET_TYPE_FC);
		return walletDTO;
	}

	/**
	 * Setting response in FcWalletRequest object for testing purpose
	 * @param response
	 * @throws WalletException 
	 * @throws TCHQueryException
	 */
	public static void main(String[] args) throws TCHQueryException {
		FcWalletAdaptorImpl aa = new FcWalletAdaptorImpl();

		Payment saleEnq = new Payment();
		saleEnq.setMerchantId("037022000042987");
		saleEnq.setTerminalId("21110085");
		saleEnq.setMobileNumber("9999999999");
		saleEnq.setOriginalAmount("1000");
		saleEnq.setInvoiceNumber("000043");
		try {
			aa.getReversal(saleEnq);
		} catch (TCHServiceException e) {
			e.printStackTrace();
		} catch (WalletException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public Payment getReversal(Payment input) throws TCHServiceException, WalletException {
		if(null != input.getInvoiceNumber()){
			try {
				logger.info("Invoice Number [ "+input.getInvoiceNumber()+" ]");
				FcWalletRequest walletRequest = fcWalletDaoImpl.getDetailsByInvoiceNumber(input.getInvoiceNumber());
				if(!Constants.TCH_REQUEST_REVERSAL.equals(walletRequest.getRequestType())){
					if(walletRequest != null){
						walletRequest.setUrl(getUtilityInfo(input).get(Constants.TCH_URL));
						walletRequest.setTxnDatenTime(UtilityHelper.getFCDateTime());
						walletRequest.setProcCode(FcConstant.TCH_FC_REVERSAL_PROCCODE);
					}
					logger.info("Reversal Request [ "+walletRequest.toString()+" ]");
					ClassPathXmlApplicationContext context = ApplicationContenxtProvider.getApplicationContext();
					ReversalReqCall reverseThread = (ReversalReqCall) context.getBean(Constants.TCH_REVERSAL_CALL);
					reverseThread.setWalletRequest(walletRequest);
					reverseThread.start();
				}else{
					logger.debug("Reversal has been already done for this Transaction..");
				}
				//send response to terminal as reversal done
				setResponseNull(input);
				return input;
				
			} catch (TCHQueryException e) {
				logger.error("Exception while fetching details based on Invoice number [ "+input.getInvoiceNumber()+" ]");
			}
		}else {
			logger.error("Invoice number missing in request");
			throw new TCHServiceException(ErrorConstants.TCH_W101,"Invoice number missing.");
		}
		return input;

	}
	
}
