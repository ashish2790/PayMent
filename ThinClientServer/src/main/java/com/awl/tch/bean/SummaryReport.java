package com.awl.tch.bean;

import java.util.Arrays;

import com.awl.tch.annotation.Mandate;
import com.google.gson.annotations.SerializedName;

public class SummaryReport {
	@SerializedName(value="tSerNum")
	private String terminalSerialNumber;
	
	@SerializedName(value="cltId")
	private String clientId;
	
	@SerializedName("mid")
	@Mandate
	private String merchantId;
	
	@SerializedName("tid")
	@Mandate
	private String terminalId;
	
	@SerializedName(value="dt")
	private String date;
	
	@SerializedName("desflg")
	private String decisionFlag;
	
	@SerializedName(value="tm")
	private String time;
	
	@SerializedName(value="bthNum")
	private String batchNumber;
	
	@SerializedName("prtVlObj")
	private PrintObject[] printObject;
	
	@SerializedName("prtPmObj")
	private PromoObject[] promoObject;
	
	@SerializedName("prtObj")
	private DataPrintObject[] dataPrintObj;
	
	@SerializedName("htid")
	private String hostId;
	
	@SerializedName("hndTx")
	private String hundredTxn;
	
	private transient String settlementSummary;
	
	/**
	 * @return the settlementSummary
	 */
	public String getSettlementSummary() {
		return settlementSummary;
	}

	/**
	 * @param settlementSummary the settlementSummary to set
	 */
	public void setSettlementSummary(String settlementSummary) {
		this.settlementSummary = settlementSummary;
	}

	/**
	 * @return the hundredTxn
	 */
	public String getHundredTxn() {
		return hundredTxn;
	}

	/**
	 * @param hundredTxn the hundredTxn to set
	 */
	public void setHundredTxn(String hundredTxn) {
		this.hundredTxn = hundredTxn;
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
	 * @return the dataPrintObj
	 */
	public DataPrintObject[] getDataPrintObj() {
		return dataPrintObj;
	}

	/**
	 * @param dataPrintObj the dataPrintObj to set
	 */
	public void setDataPrintObj(DataPrintObject[] dataPrintObj) {
		this.dataPrintObj = dataPrintObj;
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
	 * @return the printObject
	 */
	public PrintObject[] getPrintObject() {
		return printObject;
	}

	/**
	 * @param printObject the printObject to set
	 */
	public void setPrintObject(PrintObject[] printObject) {
		this.printObject = printObject;
	}

	/**
	 * @return the promoObject
	 */
	public PromoObject[] getPromoObject() {
		return promoObject;
	}

	/**
	 * @param promoObject the promoObject to set
	 */
	public void setPromoObject(PromoObject[] promoObject) {
		this.promoObject = promoObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SummaryReport [terminalSerialNumber=" + terminalSerialNumber
				+ ", clientId=" + clientId + ", merchantId=" + merchantId
				+ ", terminalId=" + terminalId + ", date=" + date
				+ ", decisionFlag=" + decisionFlag + ", time=" + time
				+ ", batchNumber=" + batchNumber + ", printObject="
				+ Arrays.toString(printObject) + ", promoObject="
				+ Arrays.toString(promoObject) + ", dataPrintObj="
				+ Arrays.toString(dataPrintObj) + ", hostId=" + hostId + "]";
	}
	
}
