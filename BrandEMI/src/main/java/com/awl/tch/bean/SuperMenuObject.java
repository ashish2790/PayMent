package com.awl.tch.bean;

import java.util.Map;

import com.awl.tch.brandemi.model.ValidationParameter;
import com.google.gson.annotations.SerializedName;

public class SuperMenuObject {

	@SerializedName("tSerNum")
	private String terminalSerialNumber;
	
	@SerializedName("menuObj")
	private MenuObject[] menuObject;
	
	@SerializedName("trk2")
	private String track2;

	
	@SerializedName("fld55")
	
	private String field55;
	
	@SerializedName("cltId")
	
	private String clientId;
	
	@SerializedName("invNum")
	
	private String invoiceNumber;
	
	@SerializedName("binNum")
	
	private String binNumber;

	@SerializedName("refVl")
	private String referenceValue;
	
	@SerializedName("rrn")
	
	private String retrivalRefNumber;
	
	@SerializedName("bthNum")
	
	private String batchNumber;
	
	@SerializedName("tnxCnt")
	private String noOfCount;
	
	@SerializedName("tnxSchId")
		
	private String schemaTransactionId;
	
	@SerializedName("panNum")
	private String panNumber;
	
	@SerializedName("expDt")
	private String expiryDate;
	
	@SerializedName("lst4DgtVl")
	private String lastFourDigitValue;
	
	@SerializedName("isFld55")
	private String issuerField55;
	
	@SerializedName("pnBlk")
	private String pinBlock;
	
	@SerializedName("authCd")
	private String authorizationId;
	
	@SerializedName("stNum")
	private String stanNumber;
	/*
	 * AAB related json value
	 */
	@SerializedName("unqId")
	private String branchCode;
	
	@SerializedName("totDcc")
	private String totalDcc;
	
	
	@SerializedName("totInr")			
	private String totalInr;
	
	@SerializedName("pinBypas")
	private String pinBypass;
	
	@SerializedName("orgAmt")
	
	private String originalAmount;

	@SerializedName("exgRt")
	
	private String exchangeRate;
	
	@SerializedName("crdEntMd")
	private String cardEntryMode;

	@SerializedName("curCd")
	
	private String currencyCode;
	
	@SerializedName("curNm")
	
	private String currencyName;
	
	@SerializedName("mrkup")
	
	private String markup;
	
	@SerializedName("mid")
	
	private String merchantId;
	
	@SerializedName("tid")
	
	private String terminalId;
	
	@SerializedName("pckNum")
	private String packectNumber;
	
	@SerializedName("desflg")
	private String decisionFlag;
	
	@SerializedName("dt")
	private String date;
	
	@SerializedName("tm")
	private String time;
	
	@SerializedName("emiObj")
	private EMIObject emiObject;
	
	@SerializedName(value="imeiNum")
	private String IMIENumber;
	
	@SerializedName(value="mrMbNum")
	private String mobileNumber;
	
	@SerializedName("prtObj")
	DataPrintObject[] dataPrintObject;
	
	@SerializedName("billObj")
	private BillingObj[] billingObject;
	
	@SerializedName("custNm")
	private String customerName;
	
	@SerializedName("crdLbl")
	private String cardLabel;
	
	@SerializedName("skuObj")
	private SkuObject[] skuCodes;


	
	/**
	 * @return the cardLabel
	 */
	public String getCardLabel() {
		return cardLabel;
	}

	/**
	 * @param cardLabel the cardLabel to set
	 */
	public void setCardLabel(String cardLabel) {
		this.cardLabel = cardLabel;
	}

	/**
	 * @return the skuCodes
	 */
	public SkuObject[] getSkuCodes() {
		return skuCodes;
	}

	/**
	 * @param skuCodes the skuCodes to set
	 */
	public void setSkuCodes(SkuObject[] skuCodes) {
		this.skuCodes = skuCodes;
	}

	private transient String username;
	private transient String password;
	private transient String serialNumber;
	private transient String dateValue;
	private transient String skuCode;
	private transient String requestType;
	private transient String responseValue;
	private transient String brandName;
	
	/*
	 * Brand emi changes
	 */
	private transient String manuFactureName;
	private transient String manufatureDisclaimer;
	private transient String manifactureCode;
	private transient String productDesc;
	private transient String productValue;
	private transient String issuerBankName;
	private transient String acqDisclaimer;
	private transient String issuerDisclaimer;
	private transient String tenure;
	
	private transient ValidationParameter validationParameter;
	private transient String[] productDetails;
	private transient String[] manufactureDetails;
	
	private transient Map<String, TenureObject> tenureMap;
	private transient String emiValidateSerial;
	private transient String issuerName;
	
	/**
	 * @return the issuerName
	 */
	public String getIssuerName() {
		return issuerName;
	}

	/**
	 * @param issuerName the issuerName to set
	 */
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	/**
	 * @return the emiValidateSerial
	 */
	public String getEmiValidateSerial() {
		return emiValidateSerial;
	}

	/**
	 * @param emiValidateSerial the emiValidateSerial to set
	 */
	public void setEmiValidateSerial(String emiValidateSerial) {
		this.emiValidateSerial = emiValidateSerial;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the tenure
	 */
	public String getTenure() {
		return tenure;
	}

	/**
	 * @param tenure the tenure to set
	 */
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	/**
	 * @return the tenureMap
	 */
	public Map<String, TenureObject> getTenureMap() {
		return tenureMap;
	}

	/**
	 * @param tenureMap the tenureMap to set
	 */
	public void setTenureMap(Map<String, TenureObject> tenureMap) {
		this.tenureMap = tenureMap;
	}

	/**
	 * @param discountFlag the discountFlag to set
	 */
	public void setDiscountFlag(String discountFlag) {
		this.discountFlag = discountFlag;
	}

	private transient String discountFlag;
	
	/*
	 * end of Brand emi changes
	 */
	
	
	/**
	 * @return the discountFlag
	 */
	public String getDiscountFlag() {
		return discountFlag;
	}

	/**
	 * @return the issuerBankName
	 */
	public String getIssuerBankName() {
		return issuerBankName;
	}

	/**
	 * @param issuerBankName the issuerBankName to set
	 */
	public void setIssuerBankName(String issuerBankName) {
		this.issuerBankName = issuerBankName;
	}

	/**
	 * @return the acqDisclaimer
	 */
	public String getAcqDisclaimer() {
		return acqDisclaimer;
	}

	/**
	 * @param acqDisclaimer the acqDisclaimer to set
	 */
	public void setAcqDisclaimer(String acqDisclaimer) {
		this.acqDisclaimer = acqDisclaimer;
	}

	/**
	 * @return the issuerDisclaimer
	 */
	public String getIssuerDisclaimer() {
		return issuerDisclaimer;
	}

	/**
	 * @param issuerDisclaimer the issuerDisclaimer to set
	 */
	public void setIssuerDisclaimer(String issuerDisclaimer) {
		this.issuerDisclaimer = issuerDisclaimer;
	}

	/**
	 * @return the productValue
	 */
	public String getProductValue() {
		return productValue;
	}

	/**
	 * @param productValue the productValue to set
	 */
	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @return the productDetails
	 */
	public String[] getProductDetails() {
		return productDetails;
	}

	/**
	 * @param productDetails the productDetails to set
	 */
	public void setProductDetails(String[] productDetails) {
		this.productDetails = productDetails;
	}

	/**
	 * @return the manufactureDetails
	 */
	public String[] getManufactureDetails() {
		return manufactureDetails;
	}

	/**
	 * @param manufactureDetails the manufactureDetails to set
	 */
	public void setManufactureDetails(String[] manufactureDetails) {
		this.manufactureDetails = manufactureDetails;
	}

	/**
	 * @return the validationParameter
	 */
	public ValidationParameter getValidationParameter() {
		return validationParameter;
	}

	/**
	 * @param validationParameter the validationParameter to set
	 */
	public void setValidationParameter(ValidationParameter validationParameter) {
		this.validationParameter = validationParameter;
	}

	/**
	 * @return the manufatureDisclaimer
	 */
	public String getManufatureDisclaimer() {
		return manufatureDisclaimer;
	}

	/**
	 * @param manufatureDisclaimer the manufatureDisclaimer to set
	 */
	public void setManufatureDisclaimer(String manufatureDisclaimer) {
		this.manufatureDisclaimer = manufatureDisclaimer;
	}

	/**
	 * @return the billingObject
	 */
	public BillingObj[] getBillingObject() {
		return billingObject;
	}

	/**
	 * @param billingObject the billingObject to set
	 */
	public void setBillingObject(BillingObj[] billingObject) {
		this.billingObject = billingObject;
	}

	/**
	 * @return the dataPrintObject
	 */
	public DataPrintObject[] getDataPrintObject() {
		return dataPrintObject;
	}

	/**
	 * @param dataPrintObject the dataPrintObject to set
	 */
	public void setDataPrintObject(DataPrintObject[] dataPrintObject) {
		this.dataPrintObject = dataPrintObject;
	}

	/**
	 * @return the manuFactureName
	 */
	public String getManuFactureName() {
		return manuFactureName;
	}

	/**
	 * @param manuFactureName the manuFactureName to set
	 */
	public void setManuFactureName(String manuFactureName) {
		this.manuFactureName = manuFactureName;
	}

	
	/**
	 * @return the manifactureCode
	 */
	public String getManifactureCode() {
		return manifactureCode;
	}

	/**
	 * @param manifactureCode the manifactureCode to set
	 */
	public void setManifactureCode(String manifactureCode) {
		this.manifactureCode = manifactureCode;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the iMIENumber
	 */
	public String getIMIENumber() {
		return IMIENumber;
	}

	/**
	 * @param iMIENumber the iMIENumber to set
	 */
	public void setIMIENumber(String iMIENumber) {
		IMIENumber = iMIENumber;
	}

	/**
	 * @return the referenceValue
	 */
	public String getReferenceValue() {
		return referenceValue;
	}

	/**
	 * @param referenceValue the referenceValue to set
	 */
	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

	/**
	 * @return the responseValue
	 */
	public String getResponseValue() {
		return responseValue;
	}

	/**
	 * @param responseValue the responseValue to set
	 */
	public void setResponseValue(String responseValue) {
		this.responseValue = responseValue;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the dateValue
	 */
	public String getDateValue() {
		return dateValue;
	}

	/**
	 * @param dateValue the dateValue to set
	 */
	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}

	/**
	 * @return the skuCode
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
	 * @param skuCode the skuCode to set
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	/**
	 * @return the emiObject
	 */
	public EMIObject getEmiObject() {
		return emiObject;
	}

	/**
	 * @param emiObject the emiObject to set
	 */
	public void setEmiObject(EMIObject emiObject) {
		this.emiObject = emiObject;
	}

	
	/**
	 * @return the track2
	 */
	public String getTrack2() {
		return track2;
	}

	/**
	 * @param track2 the track2 to set
	 */
	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	/**
	 * @return the field55
	 */
	public String getField55() {
		return field55;
	}

	/**
	 * @param field55 the field55 to set
	 */
	public void setField55(String field55) {
		this.field55 = field55;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the binNumber
	 */
	public String getBinNumber() {
		return binNumber;
	}

	/**
	 * @param binNumber the binNumber to set
	 */
	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}

	/**
	 * @return the retrivalRefNumber
	 */
	public String getRetrivalRefNumber() {
		return retrivalRefNumber;
	}

	/**
	 * @param retrivalRefNumber the retrivalRefNumber to set
	 */
	public void setRetrivalRefNumber(String retrivalRefNumber) {
		this.retrivalRefNumber = retrivalRefNumber;
	}

	/**
	 * @return the batchNumber
	 */
	public String getBatchNumber() {
		return batchNumber;
	}

	/**
	 * @param batchNumber the batchNumber to set
	 */
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	/**
	 * @return the noOfCount
	 */
	public String getNoOfCount() {
		return noOfCount;
	}

	/**
	 * @param noOfCount the noOfCount to set
	 */
	public void setNoOfCount(String noOfCount) {
		this.noOfCount = noOfCount;
	}

	/**
	 * @return the schemaTransactionId
	 */
	public String getSchemaTransactionId() {
		return schemaTransactionId;
	}

	/**
	 * @param schemaTransactionId the schemaTransactionId to set
	 */
	public void setSchemaTransactionId(String schemaTransactionId) {
		this.schemaTransactionId = schemaTransactionId;
	}

	/**
	 * @return the panNumber
	 */
	public String getPanNumber() {
		return panNumber;
	}

	/**
	 * @param panNumber the panNumber to set
	 */
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the lastFourDigitValue
	 */
	public String getLastFourDigitValue() {
		return lastFourDigitValue;
	}

	/**
	 * @param lastFourDigitValue the lastFourDigitValue to set
	 */
	public void setLastFourDigitValue(String lastFourDigitValue) {
		this.lastFourDigitValue = lastFourDigitValue;
	}

	/**
	 * @return the issuerField55
	 */
	public String getIssuerField55() {
		return issuerField55;
	}

	/**
	 * @param issuerField55 the issuerField55 to set
	 */
	public void setIssuerField55(String issuerField55) {
		this.issuerField55 = issuerField55;
	}

	/**
	 * @return the pinBlock
	 */
	public String getPinBlock() {
		return pinBlock;
	}

	/**
	 * @param pinBlock the pinBlock to set
	 */
	public void setPinBlock(String pinBlock) {
		this.pinBlock = pinBlock;
	}

	/**
	 * @return the authorizationId
	 */
	public String getAuthorizationId() {
		return authorizationId;
	}

	/**
	 * @param authorizationId the authorizationId to set
	 */
	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

	/**
	 * @return the stanNumber
	 */
	public String getStanNumber() {
		return stanNumber;
	}

	/**
	 * @param stanNumber the stanNumber to set
	 */
	public void setStanNumber(String stanNumber) {
		this.stanNumber = stanNumber;
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * @return the totalDcc
	 */
	public String getTotalDcc() {
		return totalDcc;
	}

	/**
	 * @param totalDcc the totalDcc to set
	 */
	public void setTotalDcc(String totalDcc) {
		this.totalDcc = totalDcc;
	}

	/**
	 * @return the totalInr
	 */
	public String getTotalInr() {
		return totalInr;
	}

	/**
	 * @param totalInr the totalInr to set
	 */
	public void setTotalInr(String totalInr) {
		this.totalInr = totalInr;
	}

	/**
	 * @return the pinBypass
	 */
	public String getPinBypass() {
		return pinBypass;
	}

	/**
	 * @param pinBypass the pinBypass to set
	 */
	public void setPinBypass(String pinBypass) {
		this.pinBypass = pinBypass;
	}

	/**
	 * @return the originalAmount
	 */
	public String getOriginalAmount() {
		return originalAmount;
	}

	/**
	 * @param originalAmount the originalAmount to set
	 */
	public void setOriginalAmount(String originalAmount) {
		this.originalAmount = originalAmount;
	}

	/**
	 * @return the exchangeRate
	 */
	public String getExchangeRate() {
		return exchangeRate;
	}

	/**
	 * @param exchangeRate the exchangeRate to set
	 */
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	/**
	 * @return the cardEntryMode
	 */
	public String getCardEntryMode() {
		return cardEntryMode;
	}

	/**
	 * @param cardEntryMode the cardEntryMode to set
	 */
	public void setCardEntryMode(String cardEntryMode) {
		this.cardEntryMode = cardEntryMode;
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the currencyName
	 */
	public String getCurrencyName() {
		return currencyName;
	}

	/**
	 * @param currencyName the currencyName to set
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	/**
	 * @return the markup
	 */
	public String getMarkup() {
		return markup;
	}

	/**
	 * @param markup the markup to set
	 */
	public void setMarkup(String markup) {
		this.markup = markup;
	}

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the terminalId
	 */
	public String getTerminalId() {
		return terminalId;
	}

	/**
	 * @param terminalId the terminalId to set
	 */
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the decisionFlag
	 */
	public String getDecisionFlag() {
		return decisionFlag;
	}

	/**
	 * @param decisionFlag the decisionFlag to set
	 */
	public void setDecisionFlag(String decisionFlag) {
		this.decisionFlag = decisionFlag;
	}

	/**
	 * @return the packectNumber
	 */
	public String getPackectNumber() {
		return packectNumber;
	}

	/**
	 * @param packectNumber the packectNumber to set
	 */
	public void setPackectNumber(String packectNumber) {
		this.packectNumber = packectNumber;
	}

	/**
	 * @return the menuObject
	 */
	public MenuObject[] getMenuObject() {
		return menuObject;
	}

	/**
	 * @param menuObject the menuObject to set
	 */
	public void setMenuObject(MenuObject[] menuObject) {
		this.menuObject = menuObject;
	}

	/**
	 * @return the terminalSerialNumber
	 */
	public String getTerminalSerialNumber() {
		return terminalSerialNumber;
	}

	/**
	 * @param terminalSerialNumber the terminalSerialNumber to set
	 */
	public void setTerminalSerialNumber(String terminalSerialNumber) {
		this.terminalSerialNumber = terminalSerialNumber;
	}
	
	/**
	 * @return the brandName
	 */
	
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	
	
}
