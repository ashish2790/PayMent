package com.awl.tch.mpos.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Bean which defines entities for SbiMops
 * @author Pooja Patil
 * @return
 */
public class MOPSBean {

	

	@SerializedName("bankRefNo")
	private String bankRefNo;

	@SerializedName("dateTime")
	private String dateTime;

	@SerializedName("amount")
	private String amount;

	@SerializedName("status")
	private String status;

	@SerializedName("statusCode")
	private String statusCode;

	@SerializedName("transactionStatus")
	private String transactionStatus;
	
	@SerializedName("statusDescription")
	private String statusDescription;

	@SerializedName("encData")
	private EncRequestResponseObject encData;
	
	@SerializedName("encRequestVal")
	private String encRequestVal;
    
    @SerializedName("resMap")
	private EncRequestResponseObject resMap;
    
   
    
	public EncRequestResponseObject getEncData() {
		return encData;
	}

	public void setEncData(EncRequestResponseObject encData) {
		this.encData = encData;
	}

	public String getEncRequestVal() {
		return encRequestVal;
	}

	public void setEncRequestVal(String encRequestVal) {
		this.encRequestVal = encRequestVal;
	}

	public EncRequestResponseObject getResMap() {
		return resMap;
	}

	public void setResMap(EncRequestResponseObject resMap) {
		this.resMap = resMap;
	}
	
	public String getBankRefNo() {
		return bankRefNo;
	}

	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

}
