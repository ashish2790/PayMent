
package com.awl.tch.validator;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.awl.tch.bean.DataPrintObject;
import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.TerminalParameterDTO;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.UtilityHelper;
/*
 * @Purpose: validate ISO packet for payment transaction
 */
public final class ISOPaymentValidator {
	
	public static ConcurrentHashMap<String, String> validateMap =  null;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ISOPaymentValidator.class);
	
	/*
	 * Length validation for each field
	 */
	
	public static <T> void validate(final T obj) throws TCHServiceException{/*
		ISOPaymentValidator iso = new ISOPaymentValidator();
		try{
			iso.load(Constants.TCH_VALIDATOR);
			
			Class<?> type = obj.getClass();
			for(Field field : type.getDeclaredFields()){
				if(field.isAnnotationPresent(SerializedName.class)){
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
					Method getter = pd.getReadMethod();
					if(getter.invoke(obj) != null){
						if(validateMap.get(field.getName()) != null){
							if(!getter.invoke(obj).toString().matches(validateMap.get(field.getName()))){
								throw new TCHServiceException(ErrorConstants.TCH_S100, ErrorMaster.get(ErrorConstants.TCH_S100));
							}
						}
					}
				}
			}
		}catch(IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IntrospectionException
				| ParserConfigurationException | SAXException | IOException e1){
			e1.printStackTrace();
			throw new TCHServiceException(ErrorConstants.TCH_S015, ErrorMaster.get(ErrorConstants.TCH_S015));
			
		}
	*/}
	
	/**
	 * @author ashish.bhavsar
	 * Check for whether provided bin range is valid for terminal or not and set card label
	 */
	public static boolean binSpecificParameterWithValidation(final Payment request, List<TerminalParameterDTO> listTermParamDto) throws TCHServiceException{
		
		logger.debug("Entering in bin range validation");
		
		if(request.getBinNumber() == null){	
			return false;
		}
		int binvalue  = 0;
		if(request.getBinNumber() != null && listTermParamDto != null){
			
			binvalue  = Integer.valueOf(request.getBinNumber().trim().substring(0, request.getBinRangeValue()));
			for(TerminalParameterDTO termParamDTO : listTermParamDto){
				if(request.getTableId() != null){
					if(request.getTableId().contains(Constants.TCH_T1)){
						int binRangeHigh = Integer.valueOf(termParamDTO.getCardRangeHigh()); 
						int binRangeLow = Integer.valueOf(termParamDTO.getCardRangeLow());
						if(binRangeHigh >= binvalue  && binRangeLow <= binvalue){
							logger.debug("bin present in normal bin table");
							return setTransactionParameter(request, termParamDTO);
						}
					}else{
						logger.debug("bin present in jcb upi table");
						//logger.info("Card label :" + request.getCardLabel().substring(2));
						
						return setTransactionParameter(request, termParamDTO);
					}
				}else{
					logger.debug("bin present in normal bin table");
					int binRangeHigh = Integer.valueOf(termParamDTO.getCardRangeHigh()); 
					int binRangeLow = Integer.valueOf(termParamDTO.getCardRangeLow());
					if(binRangeHigh >= binvalue  && binRangeLow <= binvalue){
						logger.debug("bin found :" + binvalue);
						return setTransactionParameter(request, termParamDTO);
					}
				}
			}
		}
		logger.debug("Exiting in bin range validation");
		return false;
	}
	
	private static boolean setTransactionParameter(final Payment request,TerminalParameterDTO termParamDTO) throws TCHServiceException{
		
		logger.debug("in set transaction parameter");
		/*
		 *  check for pan entry mode 
		 *  0 – Manual, 1-Swipe, 2-Both 
		 *  
		 */
		// REFUND column is being used to check key entry mode allowed or not a
		// possible values : Y or N
		if(Constants.TCH_KEYENTER.equalsIgnoreCase(request.getCardEntryMode())){
			if(termParamDTO.getKeyEntryMode() != null && Constants.TCH_N.equals(termParamDTO.getKeyEntryMode())){
				throw new TCHServiceException(ErrorConstants.TCH_S017, ErrorMaster.get(ErrorConstants.TCH_S017));
			}
		}
		request.setCardType(termParamDTO.getCardType());
		// CHANGING LABEL FROM JCB TO RUPAY
		if(Constants.TCH_JCB.equals(request.getCardLabel())){
			request.setCardLabel("RUPAY");
		}
		request.setCardLabel(request.getCardLabel() != null ? request.getCardLabel():termParamDTO.getCardLabel());
		request.setTpdu(termParamDTO.getTpdu().toString());
		if(termParamDTO.getTipPercentage() != null)
			request.setTipPercent(termParamDTO.getTipPercentage().toString());
		request.setNii(termParamDTO.getNii().toString());
		request.setTpdu(termParamDTO.getTpdu().toString());
		request.setCurrencyCode(request.getCurrencyCode() != null ? request.getCurrencyCode() : termParamDTO.getCurrencyCode());
		request.setCurrencyName(request.getCurrencyName() != null? request.getCurrencyName() : termParamDTO.getCurrencyName());
		request.setRefundNewFlag(termParamDTO.getRefundNew());
		request.setPreAuthFlag(termParamDTO.getPreauthFlag());
		request.setAutoSettlement(termParamDTO.getAutoSettlement());
		// already been settled in sale dao 12/04/2017
		/*request.setMerchantId(termParamDTO.getMid());  
		request.setTerminalId(termParamDTO.getTid());*/
		request.setBalEnqAllowed(termParamDTO.getBalanceInquiryAllowed());
		// Se number related changes 20/01/2017
		if(termParamDTO.getSeNumber() != null){
			
			// SET AMEX MID AND TID COMING FROM INSIGHT BY ASHISH ON 13/10/2017
			setAmexMidTid(request,termParamDTO);
			
			/*if(request.getDataPrintObject() != null){
				List<DataPrintObject> dList = new LinkedList<DataPrintObject>(Arrays.asList(request.getDataPrintObject()));
				DataPrintObject d = new DataPrintObject();
				d.setPrintLabel("SE Number : ");
				d.setPrintVal(termParamDTO.getSeNumber());
				dList.add(d);
				DataPrintObject[] dArray = new DataPrintObject[dList.size()];
				request.setDataPrintObject(dList.toArray(dArray));
			}else{
				List<DataPrintObject> dList = new ArrayList<DataPrintObject>();
				DataPrintObject d = new DataPrintObject();
				d.setPrintLabel("SE Number : ");
				d.setPrintVal(termParamDTO.getSeNumber());
				dList.add(d);
				DataPrintObject[] dArray = new DataPrintObject[dList.size()];
				request.setDataPrintObject(dList.toArray(dArray));
			}*/
		}
		// end of Se number related changes
		request.setSeNumber(termParamDTO.getSeNumber());
		if(Constants.TCH_YES.equals(termParamDTO.getDccFlag())){
			request.setMerchantId(termParamDTO.getDccMid());
			request.setTerminalId(termParamDTO.getDccTid());
			request.setDccTransactionFlag(Constants.TCH_YES);
		}
		return true;
	
	}
	
	/**
	 * Check whether card is credit card card or Debit card
	 * As per REV specification
	 * 0 - Credit, 
	 * 1 - Debit, 
	 * 2 – eCash, 
	 * 3 - Loyalty Card,
	 * 4 - UID Card,
	 * 5 - EMI card,
	 * 6 – PIN@POS Credit
	 *  
	 * @param input
	 * @return boolean value <tt>true</tt> if card is not debit card
	 * 						 <tt>false</tt> if card is debit card
	 */						
	public static boolean debitCardCheck(final Payment input){
		
		Integer cardType = input.getCardType();
		if(cardType != null){
			if(cardType == 1){
				return false;
			}
		}
		return true;
	}
	
	
	
	/*
	 * Check for card expire 
	 */
	public static boolean expiryDateValidation(Payment request){
		/*
		 * If card entry mode is swipe, keyenter, emvfallback then only check for expiry validation
		 */
	/*	if(Constants.TCH_SWIPE.equals(request.getCardEntryMode()) ||
				Constants.TCH_KEYENTER.equals(request.getCardEntryMode()) ||
				Constants.TCH_EMVFALLBK.equals(request.getCardEntryMode())){*/
			if(request.getExpiryDate() == null)
				return false;
			if(request.getExpiryDate() != null){
				int monthValue = Integer.valueOf(request.getExpiryDate().substring(2));
				int yearValue = Integer.valueOf(request.getExpiryDate().substring(0,2));
				//if(request.getCardLabel() != null){
					// COMMENTING ALLOWED CHECKED FOR EXPIRY FOR RUPAY AND VISA AS PER UAT TEAM ()
					if(request.getCardLabel().contains("VISA") || request.getCardLabel().contains("RUPAY") || 
							 request.getCardLabel().contains("AMEX")){
						return true;
					}else 
					if(Integer.valueOf(UtilityHelper.MMYY().substring(2)) > yearValue){
						return false;
					}else if(Integer.valueOf(UtilityHelper.MMYY().substring(2)) == yearValue){
						if(Integer.valueOf(UtilityHelper.MMYY().substring(0,2)) > monthValue){
							return false;
						}
					}
					// if month value greater than 12
					if(monthValue > 12){
						return false;
					}
				//}
			}
		//}
		return true;
	}

	
	/**
	 * Check for non zero amount 
	 * @param request payment bean information contain original amount
	 * @return boolean value <tt>true</tt> for valid value
	 * 						 <p><tt>false</tt> for invalid value 
	 */						
	public static boolean nonZeroAmount(Payment request){
		if(request.getOriginalAmount() == null)
			return false;
		
		if(request.getOriginalAmount() != null){
			if(new Double(0.0).compareTo(new Double(request.getOriginalAmount())) == 0 || (new Double(request.getOriginalAmount()) < 0)){
				return false;
			}
		}
		return true;
	}
	
	public static boolean mandateFieldCheck(Payment request){
		
		if(request.getBinNumber() == null || 
				request.getOriginalAmount() == null || 
				request.getCardEntryMode() == null || 
				request.getClientId() == null || 
				request.getInvoiceNumber() == null || 
				request.getTrack2() == null || 
				request.getExpiryDate() == null || 
				request.getTerminalSerialNumber() == null ||
				request.getDate() == null ||
				request.getTime() == null)
			return false;
		if(request.getCardLabel() != null){
			if("Debit".equalsIgnoreCase(request.getCardLabel())){
				if(request.getAccountType() == null){
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void load(String fileName) throws ParserConfigurationException, SAXException, IOException{/*
		//ClassLoader classLoader = this.getClass().getClassLoader();
		if(validateMap == null){
			logger.debug("Loading data in validator map");
			validateMap = new ConcurrentHashMap<String, String>();
			File file = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("bean");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList nListParamters = eElement.getElementsByTagName("parameters");
					for (int temp1 = 0; temp1 < nListParamters.getLength(); temp1++) {
						Element eElementParameters = (Element) nNode;
						NodeList nListParamter = eElementParameters.getElementsByTagName("parameter");
						for (int temp2 = 0; temp2 < nListParamter.getLength(); temp2++) {
							Element eElementParameter = (Element) nNode;
							validateMap.put(eElementParameter.getElementsByTagName("name").item(temp2).getTextContent(),eElementParameter.getElementsByTagName("regExpr").item(temp2).getTextContent());
						}
					}
				}
			}
		}
	*/}
	
	/**
	 * Set amex mid tid in actual mid tid
	 * @param input
	 * @throws TCHServiceException
	 */
	private static void setAmexMidTid(final Payment request,  final TerminalParameterDTO input) throws TCHServiceException{
		logger.debug("Setting AMEX MID and TID");
		if(input.getSeNumber() != null){
			String arr[] = UtilityHelper.getMIDTIDFromSeNumber(input.getSeNumber());
			request.setMerchantId(arr[0]);
			request.setTerminalId(arr[1]);
			logger.debug("AMEX MID [" +request.getMerchantId()+"]");
			logger.debug("AMEX TID [" +request.getTerminalId()+ "]");
		}
	}
}
