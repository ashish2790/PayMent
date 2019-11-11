package com.awl.tch.bean;

import com.awl.tch.annotation.Mandate;
import com.google.gson.annotations.SerializedName;

public class EMVACKBean {
	
	@SerializedName("cltId")
	@Mandate
	private String clientId;
	
	@SerializedName("invNum")
	@Mandate
	private String invoiceNumber;
	
	@SerializedName("tSerNum")
	@Mandate
	private String terminalSerialNumber;
	
	@SerializedName("rrn")
	@Mandate
	private String retrivalRefNumber;

	@SerializedName("fld55")
	private String field55;
	
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EMVACKBean [clientId=" + clientId + ", invoiceNumber="
				+ invoiceNumber + ", terminalSerialNumber="
				+ terminalSerialNumber + ", retrivalRefNumber="
				+ retrivalRefNumber + "]";
	}
	
}
