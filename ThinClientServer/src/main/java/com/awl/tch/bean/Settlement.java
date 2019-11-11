package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;

public class Settlement {
	
	@SerializedName(value="tSerNum")
	private String terminalSerialNumber;
	
	@SerializedName(value="cltId")
	private String clientId;
	
	@SerializedName(value="hltObj")
	private HealthObject healthObject;
	
	@SerializedName(value="dt")
	private String date;
	
	@SerializedName(value="tm")
	private String time;	
	
	@SerializedName("htid")
	private String hostId;
	
	@SerializedName("desflg")
	private String decisionFlag;
	
	private transient String stanNumber;
	private transient String merchantId;
	private transient String terminalId;
	private transient String batchNumber;
	private transient String debitCount;
	private transient String creditCount;
	private transient String creditAmount;
	private transient String debitAmount;
	private transient String responseCode; 
	private transient String retrivalRefNumber;
	
	private transient String settlementNew;
	
	
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
	 * @return the hostId
	 */
	public String getHostId() {
		return hostId;
	}




	/**
	 * @param hostId the hostId to set
	 */
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}




	/**
	 * @return the settlementNew
	 */
	public String getSettlementNew() {
		return settlementNew;
	}




	/**
	 * @param settlementNew the settlementNew to set
	 */
	public void setSettlementNew(String settlementNew) {
		this.settlementNew = settlementNew;
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
	 * @return the debitCount
	 */
	public String getDebitCount() {
		return debitCount;
	}




	/**
	 * @param debitCount the debitCount to set
	 */
	public void setDebitCount(String debitCount) {
		this.debitCount = debitCount;
	}




	/**
	 * @return the creditCount
	 */
	public String getCreditCount() {
		return creditCount;
	}




	/**
	 * @param creditCount the creditCount to set
	 */
	public void setCreditCount(String creditCount) {
		this.creditCount = creditCount;
	}




	/**
	 * @return the creditAmount
	 */
	public String getCreditAmount() {
		return creditAmount;
	}




	/**
	 * @param creditAmount the creditAmount to set
	 */
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}




	/**
	 * @return the debitAmount
	 */
	public String getDebitAmount() {
		return debitAmount;
	}




	/**
	 * @param debitAmount the debitAmount to set
	 */
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
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
	 * @return the healthObject
	 */
	public HealthObject getHealthObject() {
		return healthObject;
	}




	/**
	 * @param healthObject the healthObject to set
	 */
	public void setHealthObject(HealthObject healthObject) {
		this.healthObject = healthObject;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Settlement [terminalSerialNumber=" + terminalSerialNumber
				+ ", clientId=" + clientId + ", healthObject=" + healthObject
				+ ", date=" + date + ", time=" + time + "]";
	}
	
	
	
}
