package com.tch.sbiepay.model;

import com.tch.sbiepay.constant.SBIepayConstant;

public class SBIepaEntity {

	private String aggregatorId;
	private String ATRN;
	private String merchantName;
	private String merchantOrderNo;
	private String PaymentProcessor;
	private String amount;
	private String status;
	private String statusDescription;
	private String transactionDate;
	private String transactionExpiryDate;
	private String mobileNo;
	private String emailID;
	private String uniqueRefNumber;
	private String ackStatus;
	private String url;
	/**
	 * @return the url
	 */	
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the aggregatorId
	 */
	public String getAggregatorId() {
		return aggregatorId;
	}
	/**
	 * @param aggregatorId the aggregatorId to set
	 */
	public void setAggregatorId(String aggregatorId) {
		this.aggregatorId = aggregatorId;
	}
	
	/**
	 * @return the paymentProcessor
	 */
	public String getPaymentProcessor() {
		return PaymentProcessor;
	}
	/**
	 * @param paymentProcessor the paymentProcessor to set
	 */
	public void setPaymentProcessor(String paymentProcessor) {
		PaymentProcessor = paymentProcessor;
	}
	/**
	 * @return the aTRN
	 */
	public String getATRN() {
		return ATRN;
	}
	/**
	 * @param aTRN the aTRN to set
	 */
	public void setATRN(String aTRN) {
		ATRN = aTRN;
	}
	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}
	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	/**
	 * @return the merchantOrderNo
	 */
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}
	/**
	 * @param merchantOrderNo the merchantOrderNo to set
	 */
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
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
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}
	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return transactionDate;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	/**
	 * @return the transactionExpiryDate
	 */
	public String getTransactionExpiryDate() {
		return transactionExpiryDate;
	}
	/**
	 * @param transactionExpiryDate the transactionExpiryDate to set
	 */
	public void setTransactionExpiryDate(String transactionExpiryDate) {
		this.transactionExpiryDate = transactionExpiryDate;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}
	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	/**
	 * @return the uniqueRefNumber
	 */
	public String getUniqueRefNumber() {
		return uniqueRefNumber;
	}
	/**
	 * @param uniqueRefNumber the uniqueRefNumber to set
	 */
	public void setUniqueRefNumber(String uniqueRefNumber) {
		this.uniqueRefNumber = uniqueRefNumber;
	}
	/**
	 * @return the ackStatus
	 */
	public String getAckStatus() {
		return ackStatus;
	}
	/**
	 * @param ackStatus the ackStatus to set
	 */
	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}
	
	/*
	 * Transaction Details Fetching Rest API
	 */
	
	public String getTransactionDetailsRequest(){
		return new StringBuilder(SBIepayConstant.TCH_SBIEPAY_AGGREGATORID).append(this.ATRN).toString();
	}
	
	/*
	 * Payment Confirmation Status REST API :
	 */
	public String getPyamentConfirmationRequest(){
		return new StringBuilder(SBIepayConstant.TCH_SBIEPAY_AGGREGATORID)
				.append(this.ATRN).append("|")
				.append(this.uniqueRefNumber).append("|")
				.append(this.amount).append("|")
				.append(this.status).append("|")
				.append(this.statusDescription).toString();
	}
}
