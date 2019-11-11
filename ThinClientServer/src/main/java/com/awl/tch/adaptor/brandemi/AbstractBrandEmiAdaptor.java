/**
 * 
 */
package com.awl.tch.adaptor.brandemi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.awl.tch.bean.MenuObject;
import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.brandemi.model.ValidationParameter;
import com.awl.tch.brandemi.service.BrandEMIService;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.dao.BrandEmiDaoImpl;
import com.awl.tch.dao.BrandEmiSerialNumberDaoImpl;
import com.awl.tch.dao.EnquiryDaoImpl;
import com.awl.tch.dao.KalkulaDao;
import com.awl.tch.dao.SaleDaoImpl;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.BrandEMIDTO;
import com.awl.tch.service.SaleServiceImpl;
import com.awl.tch.util.ErrorMaster;

/**
 * @author ashish.bhavsar
 *
 */
public abstract class AbstractBrandEmiAdaptor{
	
	@Autowired
	JdbcTemplate kalkulasJdbcTemplate;
	
	@Autowired
	@Qualifier(Constants.TCH_BRAND_EMI_SERVICE)
	BrandEMIService brandEmiservice;
	
	@Autowired
	@Qualifier(Constants.TCH_BRAND_EMI_DAO)
	BrandEmiDaoImpl brandDao;
	
	@Autowired
	@Qualifier(Constants.TCH_ENQUIRY_DAO)
	EnquiryDaoImpl enquiryDaoImpl;

	@Autowired
	@Qualifier(Constants.TCH_SALE_SERVICE)
	SaleServiceImpl saleService;
	
	@Autowired
	@Qualifier(Constants.TCH_BRAND_EMI_SERIAL_DAO)
	BrandEmiSerialNumberDaoImpl brandEmiSerial;
	
	@Autowired
	@Qualifier("paymentSaleDao")
	SaleDaoImpl paymentSaleDao;
	
	@Autowired
	@Qualifier("kalkulasDao")
	KalkulaDao kalkulaDao;
	
	public static ConcurrentHashMap<String, MenuObject[]> brdEmiStepWiseCaching = new ConcurrentHashMap<String, MenuObject[]>();
	public static ConcurrentHashMap<String, Object> generalCacheForBRDEMI = new ConcurrentHashMap<String,Object>();
	public static HashMap<String , Integer> brdEmiMapping = new HashMap<String, Integer>();
	public static ConcurrentHashMap<String, SuperMenuObject> brandEmiFinalCache = new ConcurrentHashMap<String, SuperMenuObject>();
	
	static{
		/*System.setProperty("https.proxyHost", "10.10.15.200");
        System.setProperty("https.proxyPort", "8080");*/
        init();
	}
	
	private static void init() {
		brdEmiMapping.put("MBO", 1);
		brdEmiMapping.put("CIB", 2);
		brdEmiMapping.put("MOBILE", 1);
		brdEmiMapping.put("CONSUMER DURABLES", 2);
		brdEmiMapping.put("1", 1);
		brdEmiMapping.put("2", 2);
		brdEmiMapping.put("3", 3);
		brdEmiMapping.put("4", 4);
		brdEmiMapping.put("5", 5);
		brdEmiMapping.put("6", 6);
		brdEmiMapping.put("7", 7);
		brdEmiMapping.put("8", 8);
		brdEmiMapping.put("9", 9);
		brdEmiMapping.put("10", 10);
		brdEmiMapping.put("11", 11);
		brdEmiMapping.put("12", 12);
		brdEmiMapping.put("13", 13);
		brdEmiMapping.put("14", 14);
		brdEmiMapping.put("15", 15);
		brdEmiMapping.put("16", 16);
		brdEmiMapping.put("17", 17);
		brdEmiMapping.put("18", 18);
		brdEmiMapping.put("19", 19);
		brdEmiMapping.put("20", 20);
		brdEmiMapping.put("21", 21);
		brdEmiMapping.put("22", 22);
		brdEmiMapping.put("23", 23);
		brdEmiMapping.put("24", 24);
	}
	/**
	 * Put information in cache for future reference
	 * @param input
	 * @throws TCHServiceException
	 */
	protected void putInGenericCache(final SuperMenuObject input) throws TCHServiceException{
		try{
			//generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_ISSUER_PRODUCT, brandEmiservice.getProductDetails(input.getMerchantId(), brdEmiStepWiseCaching.get(input.getTerminalSerialNumber())));
			generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_ISSUER_MFG,brandEmiservice.getManufacturerDetails(input.getMerchantId(), brdEmiStepWiseCaching.get(input.getTerminalSerialNumber())));
			generalCacheForBRDEMI.put(input.getTerminalSerialNumber()+Constants.TCH_BRD_ISSUER_VALID,brandEmiservice.getValidationParameter(input.getMerchantId(), brdEmiStepWiseCaching.get(input.getTerminalSerialNumber())));
		}catch (Exception e){
			throw new TCHServiceException(ErrorConstants.TCH_BR08, ErrorMaster.get(ErrorConstants.TCH_BR08));
		}
	}
	
	/**
	 * get brand emi scheme in Integer format
	 * @param termSerNum
	 * @return
	 */
	public static Integer getBrandEmiSchemeInInt(String termSerNum){
		Integer schemint = 0;
		if(brdEmiStepWiseCaching.get(termSerNum) != null && brdEmiStepWiseCaching.get(termSerNum).length > 2){
			schemint = getSchemeMappingInInt(brdEmiStepWiseCaching.get(termSerNum)[2].getDisplayName());
		}
		return schemint;
	}
	/**
	 * Get product category in Integer format
	 * @param termSerNum
	 * @return
	 */
	public static Integer getBrandEmiProductCategoryInInt(String termSerNum){
		Integer prdCateogryint = 0;
		if(brdEmiStepWiseCaching.get(termSerNum) != null && brdEmiStepWiseCaching.get(termSerNum).length > 1){
			prdCateogryint = getProducCategoryMappingInInt(brdEmiStepWiseCaching.get(termSerNum)[0].getDisplayName());
		}
		return prdCateogryint;
	}
	/**
	 * get scheme value in Integer format
	 * @param scheme
	 * @return
	 */
	public static Integer getSchemeMappingInInt(String scheme){
		return brdEmiMapping.get(scheme);
	}
	/**
	 * get product category value in Integer format
	 * @param prdCategory
	 * @return
	 */
	public static Integer getProducCategoryMappingInInt(String prdCategory){
		return brdEmiMapping.get(prdCategory);
	}
	
	protected static List<String> getManufactureDetails(final SuperMenuObject input){
		return Arrays.asList((String[]) generalCacheForBRDEMI.get(input.getTerminalSerialNumber()+Constants.TCH_BRD_ISSUER_MFG));
	}
	
	protected static List<String> getProductDetails(final SuperMenuObject input){
		return Arrays.asList((String[]) generalCacheForBRDEMI.get(input.getTerminalSerialNumber()+Constants.TCH_BRD_ISSUER_PRODUCT));
	}
	
	public static ValidationParameter getValidationDetails(final SuperMenuObject input){
		return (ValidationParameter) generalCacheForBRDEMI.get(input.getTerminalSerialNumber()+Constants.TCH_BRD_ISSUER_VALID);
	}
	
	protected BrandEMIDTO setValues(final SuperMenuObject input, ValidationParameter param){
		BrandEMIDTO validationValue = new BrandEMIDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		String saleDate = "";
		if(input.getMenuObject() != null){
			validationValue.setSkuCode(input.getMenuObject()[0].getDisplayName());
		}
		try {
			saleDate = sdf1.format(sdf.parse(input.getDate()));
		} catch (ParseException e1) {
		}
		validationValue.setInvoiceNumber(input.getReferenceValue());
		String days = "0";
		if(param.getNumberOfDays()!=null)
		 days = param.getNumberOfDays();
		validationValue.setBrandName(brdEmiStepWiseCaching.get(input.getTerminalSerialNumber())[1].getDisplayName().toUpperCase());
		//validationValue.setExtraInfo(brdEmiMapping.get(brdEmiStepWiseCaching.get(input.getTerminalSerialNumber())[0].getDisplayName().toUpperCase()).toString());//product category  :brdEmiMapping.put("MOBILE", 1) 	brdEmiMapping.put("CONSUMER DURABLES", 2);
		validationValue.setNumberOfDays(Integer.parseInt(days));
		validationValue.setReferenceValue(input.getRetrivalRefNumber());
		validationValue.setSerialNumber(input.getIMIENumber());
		validationValue.setSaleDate(Integer.parseInt(saleDate));
		validationValue.setTerminalSerialNumber(input.getTerminalSerialNumber());
		return validationValue;
	}
	
	public static String getDiscountFlag(String terminalSerialNumber,int tenure){
		return (String) generalCacheForBRDEMI.get(terminalSerialNumber+tenure); 
	}
	
	public static String getIssuerDisc(String terminalSerialNumber){
		return (String) generalCacheForBRDEMI.get(terminalSerialNumber+Constants.TCH_BRD_ISSUER_DISCLAIMER); 
	}
	
	/**
	 * Vaidate input parameter basis on rule set in insight
	 * Input parameter like mobile number. Invoice number, Imie number
	 * 
	 * @param input
	 * @throws TCHServiceException
	 */
	public void validationForInputParameters(final SuperMenuObject input, final ValidationParameter param) throws TCHServiceException{
		
		//validation for imie number
		
		if(input.getIMIENumber() == null && Constants.TCH_BRAND_EMI_REALTIME.equals(param.getValidationType())){
			throw new TCHServiceException(ErrorConstants.TCH_BR21,"IMIE NUMBER NOT VALID");
		}else if(input.getIMIENumber() != null && Constants.TCH_BRAND_EMI_REALTIME.equals(param.getValidationType()) && input.getIMIENumber().isEmpty())
			throw new TCHServiceException(ErrorConstants.TCH_BR21,"IMIE NUMBER NOT VALID");
		
		//validation for mobile number
		if(input.getMobileNumber() == null && Constants.TCH_BRAND_EMI_REALTIME.equals(param.getValidationType())){
			throw new TCHServiceException(ErrorConstants.TCH_BR21,"MOBILE NUMBER NOT VALID");
		}else{
			if(input.getMobileNumber() != null && Constants.TCH_BRAND_EMI_REALTIME.equals(param.getValidationType()) && input.getMobileNumber().length() < 10){
				throw new TCHServiceException(ErrorConstants.TCH_BR21,"MOBILE NUMBER NOT VALID");}
		}
		
		//validation for invoice number
		if(input.getReferenceValue() == null){
			throw new TCHServiceException(ErrorConstants.TCH_BR22,"INVOICE NUMBER NOT VALID");
		}else if(input.getReferenceValue() != null && input.getReferenceValue().isEmpty()){
			throw new TCHServiceException(ErrorConstants.TCH_BR22,"INVOICE NUMBER NOT VALID");
		}
	}
}
