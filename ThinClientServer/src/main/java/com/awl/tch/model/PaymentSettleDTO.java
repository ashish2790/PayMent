package com.awl.tch.model;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.Transient;

/**
 * @author ashish.bhavsar
 * 
 */
@Table(name="TCH_SETTLED_PAYMENT_TXN")
public class PaymentSettleDTO {

	@Id
	@Column(name="P_ID")
	private Long paymentId;
	
	@Column(name="P_PANNUMBER")
	private String panNumber;

	@Column(name="P_ORIGINAL_AMOUNT")
	private Double originalAmount;

	@Column(name="P_CARD_ENTRYMODE")
	private String cardEntryMode;

	@Column(name="P_EXCHANGE_RATE")
	private Double exchangeRate;

	@Column(name="P_CURRENCY_CODE")
	private Integer currencyCode;
	
	@Column(name="P_CURRENCY_NAME")
	private String currencyName;
	
	@Column(name="P_MARKUP")
	private String markup;
	
	@Column(name="P_MERCHANTID")
	private String merchantId;
	
	@Column(name="P_TERMINALID")
	private String terminalId;
	
	@Column(name="P_CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="P_CLIENTID")
	private String clientId;
	
	@Column(name="P_INVOICENUMBER")
	private String invoiceNumber;
	
	@Column(name="P_TRACK2")
	private String track2;
	
	@Column(name="P_STAN_NUMBER")
	private String stanNumber;
	
	@Column(name="P_BINNUMBER")
	private String binNumber;
	
	@Column(name="P_EXPIRYDATE")
	private String expiryDate;
	
	@Column(name="P_FIELD55")
	private String field55;
	
	@Column(name="P_ADDAMOUNT")
	private Double additionalAmount;
	
	@Column(name="P_ACCOUNT_TYPE")
	private String accountType;
	
	@Column(name="P_TERMINAL_SERIAL_NUMBER")
	private String terminalSerialNumber;
	
	@Column(name="P_CARD_LABEL")
	private String cardLabel;
	
	@Column(name="P_DATE")
	private String date;

	@Column(name="P_TIME")
	private String time;
	
	@Column(name="P_SESSION_KEY")
	private String sessionKey;
	
	@Column(name="P_DECISION_FLAG")
	private String decisionFlag;
	
	@Column(name="P_REFERENCE_VALUE")
	private String referenceValue;
	
	@Column(name="P_BATCH_NUMBER")
	private Integer batchNumber;
	
	@Column(name="P_PIN_BLOCK")
	private String pinBlock;
	
	@Column(name="P_DCC_AMOUNT")
	private Double dccAmount;
	
	@Column(name="P_REQUEST_TYPE")
	private String requestType;
	
	@Column(name="P_RESPONSE_CODE")
	private String responseCode;
	
	@Column(name="P_STATUS")
	private String status;
	
	@Column(name="P_RETRIVAL_REF_NUMBER")
	private String retrivalRefNumber;
	
	@Column(name="P_AUTH_ID")
	private String authorizationId;
	
	@Column(name="P_TRANSACTION_ID")
	private String schemaTransactionId;
	
	@Column(name="P_VOID_APPROVED")
	private Integer voidApprovedFlag; 
	
	@Column(name="P_CREATED")
	@ICurrentTimestamp
	private String created;
	
	@Column(name="P_UPDATED")
	@ICurrentTimestamp
	private String updated;
	
	@Column(name="P_SETTLEMENT_FLAG")
	private String settlementFlag;
	
	@Column(name="P_REFUND_FLAG")
	private Integer refundFlag;
	
	@Column(name="P_LAST_FOUR_DIGIT")
	private String lastFourDigitValue;
	
	@Column(name="P_ISSUE_FIELD55")
	private String issuerField55;
	
	@Column(name="P_VOID_STATUS")
	private String voidStatus;
	
	@Column(name="P_REFUND_STATUS")
	private String refundStatus;
	
	@Column(name="P_EXTRA_OBJ")
	private String extraValue;
	
	@Column(name="P_TIP_PERCENT")
	private String tipPercent;
	
	@Column(name = "P_CASHBACK")
	private String	cashback;

	@Column(name = "P_CASHBACKPUR")
	private String	cashbackPur;
	
	@Column(name = "P_TIP_APPROVED")
	private String tipApproved;
	
	@Column(name = "P_REFUND_APPROVED")
	private String refundApproved;
	
	@Column(name = "P_TPDU")
	private String tpdu;
	
	@Column(name = "P_NII")
	private String nii;

	@Column(name="P_SALE_COMP_FLAG")
	private String saleCompletionFlag;
	
	@Column(name="P_PROCESSING_CODE")
	private String processingCode;
	
	@Column(name="P_MTI")
	private String MTI;
	
	@Column(name="P_FIELD63")
	private String field63;
	
	@Column(name="P_BRANCH_CODE")
	private String branchCode;
	
	@Column(name="P_APP_NAME")
	private String appName;
	
	@Column(name="P_TXN_CHANNEL")
	private String transactionChannel;
	
	@Column(name="P_EXTRA_INFO")
	private String extraInfo;

	@Column(name="P_BANK_CODE")
	private String bankCode;
	
	@Column(name="P_EMI_INDICATOR")
	private String emiIndicator;     //Added by Saloni Jindal on 7-09-2017
	/*
	 * Change in code by Saloni Jindal on 7-09-2017
	 */
	@Column(name="P_CARD_TYPE")
	private String cardTypeInd;     //Added by ashish for irctc
	
	
	@Column(name="P_INTNT_FLAG")
	private String internationalFlag;
	
	
	@Column(name = "P_CONVENIENCE_FLG")
	private String feeFlag;
	
	@Column(name = "P_FEE1")
	private String feeValue;
	
	@Column(name = "P_TAX1")
	private transient String cgsttax;
	
	@Column(name = "P_TAX2")
	private transient String igsttax;
	
	/**
	 * @return the feeFlag
	 */
	public String getFeeFlag() {
		return feeFlag;
	}

	/**
	 * @param feeFlag the feeFlag to set
	 */
	public void setFeeFlag(String feeFlag) {
		this.feeFlag = feeFlag;
	}

	/**
	 * @return the feeValue
	 */
	public String getFeeValue() {
		return feeValue;
	}

	/**
	 * @param feeValue the feeValue to set
	 */
	public void setFeeValue(String feeValue) {
		this.feeValue = feeValue;
	}

	/**
	 * @return the cgsttax
	 */
	public String getCgsttax() {
		return cgsttax;
	}

	/**
	 * @param cgsttax the cgsttax to set
	 */
	public void setCgsttax(String cgsttax) {
		this.cgsttax = cgsttax;
	}

	/**
	 * @return the igsttax
	 */
	public String getIgsttax() {
		return igsttax;
	}

	/**
	 * @param igsttax the igsttax to set
	 */
	public void setIgsttax(String igsttax) {
		this.igsttax = igsttax;
	}



	private transient Integer tempBatchNumber;
	

	public String getCardTypeInd() {
		return cardTypeInd;
	}

	public void setCardTypeInd(String cardTypeInd) {
		this.cardTypeInd = cardTypeInd;
	}

	/**
	 * @return the tempBatchNumber
	 */
	public Integer getTempBatchNumber() {
		return tempBatchNumber;
	}

	/**
	 * @param tempBatchNumber the tempBatchNumber to set
	 */
	public void setTempBatchNumber(Integer tempBatchNumber) {
		this.tempBatchNumber = tempBatchNumber;
	}

	/**
	 * @return the internationalFlag
	 */
	public String getInternationalFlag() {
		return internationalFlag;
	}

	/**
	 * @param internationalFlag the internationalFlag to set
	 */
	public void setInternationalFlag(String internationalFlag) {
		this.internationalFlag = internationalFlag;
	}

	/**
	 * @return the emiIndicator
	 */
	public String getEmiIndicator() {
		return emiIndicator;
	}
	
	/**
	 * @param emiIndicator the emiIndicator to set
	 */
	public void setEmiIndicator(String emiIndicator) {
		this.emiIndicator = emiIndicator;
	}

	// End of changes
	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * @return the extraInfo
	 */
	public String getExtraInfo() {
		return extraInfo;
	}

	/**
	 * @param extraInfo the extraInfo to set
	 */
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
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
	 * @return the transactionChannel
	 */
	public String getTransactionChannel() {
		return transactionChannel;
	}

	/**
	 * @param transactionChannel the transactionChannel to set
	 */
	public void setTransactionChannel(String transactionChannel) {
		this.transactionChannel = transactionChannel;
	}



	@Transient
	private String tableId;
	
	/**
	 * @return the tableId
	 */
	public String getTableId() {
		return tableId;
	}


	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}



	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}



	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
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
	 * @return the field63
	 */
	public String getField63() {
		return field63;
	}



	/**
	 * @param field63 the field63 to set
	 */
	public void setField63(String field63) {
		this.field63 = field63;
	}



	/**
	 * @return the mTI
	 */
	public String getMTI() {
		return MTI;
	}



	/**
	 * @param mTI the mTI to set
	 */
	public void setMTI(String mTI) {
		MTI = mTI;
	}



	/**
	 * @return the processingCode
	 */
	public String getProcessingCode() {
		return processingCode;
	}



	/**
	 * @param processingCode the processingCode to set
	 */
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}



	/**
	 * @return the saleCompletionFlag
	 */
	public String getSaleCompletionFlag() {
		return saleCompletionFlag;
	}



	/**
	 * @param saleCompletionFlag the saleCompletionFlag to set
	 */
	public void setSaleCompletionFlag(String saleCompletionFlag) {
		this.saleCompletionFlag = saleCompletionFlag;
	}



	/**
	 * @return the tpdu
	 */
	public String getTpdu() {
		return tpdu;
	}



	/**
	 * @param tpdu the tpdu to set
	 */
	public void setTpdu(String tpdu) {
		this.tpdu = tpdu;
	}



	/**
	 * @return the nii
	 */
	public String getNii() {
		return nii;
	}



	/**
	 * @param nii the nii to set
	 */
	public void setNii(String nii) {
		this.nii = nii;
	}



	/**
	 * @return the refundApproved
	 */
	public String getRefundApproved() {
		return refundApproved;
	}



	/**
	 * @param refundApproved the refundApproved to set
	 */
	public void setRefundApproved(String refundApproved) {
		this.refundApproved = refundApproved;
	}



	/**
	 * @return the tipApproved
	 */
	public String getTipApproved() {
		return tipApproved;
	}



	/**
	 * @param tipApproved the tipApproved to set
	 */
	public void setTipApproved(String tipApproved) {
		this.tipApproved = tipApproved;
	}



	/**
	 * @return the cashback
	 */
	public String getCashback() {
		return cashback;
	}



	/**
	 * @param cashback the cashback to set
	 */
	public void setCashback(String cashback) {
		this.cashback = cashback;
	}



	/**
	 * @return the cashbackPur
	 */
	public String getCashbackPur() {
		return cashbackPur;
	}



	/**
	 * @param cashbackPur the cashbackPur to set
	 */
	public void setCashbackPur(String cashbackPur) {
		this.cashbackPur = cashbackPur;
	}



	/**
	 * @return the tipPercent
	 */
	public String getTipPercent() {
		return tipPercent;
	}



	/**
	 * @param tipPercent the tipPercent to set
	 */
	public void setTipPercent(String tipPercent) {
		this.tipPercent = tipPercent;
	}



	/**
	 * @return the extraValue
	 */
	public String getExtraValue() {
		return extraValue;
	}



	/**
	 * @param extraValue the extraValue to set
	 */
	public void setExtraValue(String extraValue) {
		this.extraValue = extraValue;
	}



	public Long getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the voidStatus
	 */
	public String getVoidStatus() {
		return voidStatus;
	}



	/**
	 * @param voidStatus the voidStatus to set
	 */
	public void setVoidStatus(String voidStatus) {
		this.voidStatus = voidStatus;
	}



	/**
	 * @return the refundStatus
	 */
	public String getRefundStatus() {
		return refundStatus;
	}



	/**
	 * @param refundStatus the refundStatus to set
	 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
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
	 * @return the cardEntryMode
	 */
	public String getCardEntryMode() {
		return cardEntryMode;
	}


	/**
	 * @return the refundFlag
	 */
	public Integer getRefundFlag() {
		return refundFlag;
	}



	/**
	 * @param refundFlag the refundFlag to set
	 */
	public void setRefundFlag(Integer refundFlag) {
		this.refundFlag = refundFlag;
	}



	/**
	 * @param cardEntryMode the cardEntryMode to set
	 */
	public void setCardEntryMode(String cardEntryMode) {
		this.cardEntryMode = cardEntryMode;
	}



	/**
	 * @return the exchangeRate
	 */
	public Double getExchangeRate() {
		return exchangeRate;
	}



	/**
	 * @param exchangeRate the exchangeRate to set
	 */
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}



	/**
	 * @return the currencyCode
	 */
	public Integer getCurrencyCode() {
		return currencyCode;
	}



	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(Integer currencyCode) {
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
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}



	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
	 * @return the batchNumber
	 */
	public Integer getBatchNumber() {
		return batchNumber;
	}



	/**
	 * @param batchNumber the batchNumber to set
	 */
	public void setBatchNumber(Integer batchNumber) {
		this.batchNumber = batchNumber;
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
	 * @return the dccAmount
	 */
	public Double getDccAmount() {
		return dccAmount;
	}



	/**
	 * @param dccAmount the dccAmount to set
	 */
	public void setDccAmount(Double dccAmount) {
		this.dccAmount = dccAmount;
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
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}



	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the voidApprovedFlag
	 */
	public Integer getVoidApprovedFlag() {
		return voidApprovedFlag;
	}



	/**
	 * @param voidApprovedFlag the voidApprovedFlag to set
	 */
	public void setVoidApprovedFlag(Integer voidApprovedFlag) {
		this.voidApprovedFlag = voidApprovedFlag;
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
	 * @return the originalAmount
	 */
	public Double getOriginalAmount() {
		return originalAmount;
	}



	/**
	 * @param originalAmount the originalAmount to set
	 */
	public void setOriginalAmount(Double originalAmount) {
		this.originalAmount = originalAmount;
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
	 * @return the additionalAmount
	 */
	public Double getAdditionalAmount() {
		return additionalAmount;
	}



	/**
	 * @param additionalAmount the additionalAmount to set
	 */
	public void setAdditionalAmount(Double additionalAmount) {
		this.additionalAmount = additionalAmount;
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
	 * @return the sessionKey
	 */
	public String getSessionKey() {
		return sessionKey;
	}



	/**
	 * @param sessionKey the sessionKey to set
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
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
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}



	/**
	 * @return the settlementFlag
	 */
	public String getSettlementFlag() {
		return settlementFlag;
	}



	/**
	 * @param settlementFlag the settlementFlag to set
	 */
	public void setSettlementFlag(String settlementFlag) {
		this.settlementFlag = settlementFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentDTO [paymentId=" + paymentId + ", panNumber="
				+ panNumber + ", originalAmount=" + originalAmount
				+ ", cardEntryMode=" + cardEntryMode + ", exchangeRate="
				+ exchangeRate + ", currencyCode=" + currencyCode
				+ ", currencyName=" + currencyName + ", markup=" + markup
				+ ", merchantId=" + merchantId + ", terminalId=" + terminalId
				+ ", customerName=" + customerName + ", clientId=" + clientId
				+ ", invoiceNumber=" + invoiceNumber + ", track2=" + track2
				+ ", stanNumber=" + stanNumber + ", binNumber=" + binNumber
				+ ", expiryDate=" + expiryDate + ", field55=" + field55
				+ ", additionalAmount=" + additionalAmount + ", accountType="
				+ accountType + ", terminalSerialNumber="
				+ terminalSerialNumber + ", cardLabel=" + cardLabel + ", date="
				+ date + ", time=" + time + ", sessionKey=" + sessionKey
				+ ", decisionFlag=" + decisionFlag + ", referenceValue="
				+ referenceValue + ", batchNumber=" + batchNumber
				+ ", pinBlock=" + pinBlock + ", dccAmount=" + dccAmount
				+ ", requestType=" + requestType + ", responseCode="
				+ responseCode + ", status=" + status + ", retrivalRefNumber="
				+ retrivalRefNumber + ", authorizationId=" + authorizationId
				+ ", schemaTransactionId=" + schemaTransactionId
				+ ", voidApprovedFlag=" + voidApprovedFlag + ", created="
				+ created + ", updated=" + updated + ", settlementFlag="
				+ settlementFlag + ", refundFlag=" + refundFlag
				+ ", lastFourDigitValue=" + lastFourDigitValue
				+ ", issuerField55=" + issuerField55 + ", voidStatus="
				+ voidStatus + ", refundStatus=" + refundStatus
				+ ", extraValue=" + extraValue + ", tipPercent=" + tipPercent
				+ ", cashback=" + cashback + ", cashbackPur=" + cashbackPur
				+ ", tipApproved=" + tipApproved + ", refundApproved="
				+ refundApproved + ", tpdu=" + tpdu + ", nii=" + nii
				+ ", saleCompletionFlag=" + saleCompletionFlag
				+ ", processingCode=" + processingCode + ", MTI=" + MTI
				+ ", field63=" + field63 + ", branchCode=" + branchCode
				+ ", appName=" + appName + ", transactionChannel="
				+ transactionChannel + ", extraInfo=" + extraInfo
				+ ", bankCode=" + bankCode + ", emiIndicator=" + emiIndicator
				+ ", cardTypeInd=" + cardTypeInd + ", internationalFlag="
				+ internationalFlag + ", feeFlag=" + feeFlag + ", feeValue="
				+ feeValue + ", tableId=" + tableId + "]";
	}
}
