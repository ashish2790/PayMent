package com.awl.tch.model;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.UCurrentTimestamp;

@Table(name = "TCH_GBSS_REPORT")
public class GBSSDto {

	@Column(name = "G_AMOUNT")
	private String amount;
	
	@Column(name = "G_CHALLEN_NUM")
	@Id
	private String challenNumber;
	
	@Column(name = "G_MID")
	private String mid;
	
	@Column(name = "G_TID")
	private String tid;
	
	@Column(name = "G_TXN_DATE")
	private String transactionDate;
	
	@Column(name = "G_TXN_TIME")
	private String transactionTime;
	
	@Column(name = "G_TXN_ID")
	private String transactionId;
	
	@Column(name = "G_CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "G_PAY_MODE")
	private String paymentMode;
	
	@Column(name = "G_STATUS")
	private String status;
	
	@Column(name = "G_CREATED")
	@ICurrentTimestamp
	private String created;
	
	@Column(name = "G_UPDATED")
	@ICurrentTimestamp
	@UCurrentTimestamp
	private String updated;
	
	@Column(name = "G_RES_STATUS")
	private String responseStatus;
	
	@Column(name = "G_ERR_DESC")
	private String errorDescription;
	
	@Column(name = "G_TAX1")
	private String tax1;
	
	@Column(name = "G_TAX2")
	private String tax2;
	
	@Column(name = "G_FEE")
	private String fee;
	
	@Column(name = "G_TAX1_PER")
	private String tax1Per;
	
	@Column(name = "G_TAX2_PER")
	private String tax2Per;
	
	@Column(name = "G_FEE_PER")
	private String feePer;
	
	/**
	 * @return the tax1Per
	 */
	public String getTax1Per() {
		return tax1Per;
	}

	/**
	 * @param tax1Per the tax1Per to set
	 */
	public void setTax1Per(String tax1Per) {
		this.tax1Per = tax1Per;
	}

	/**
	 * @return the tax2Per
	 */
	public String getTax2Per() {
		return tax2Per;
	}

	/**
	 * @param tax2Per the tax2Per to set
	 */
	public void setTax2Per(String tax2Per) {
		this.tax2Per = tax2Per;
	}

	/**
	 * @return the feePer
	 */
	public String getFeePer() {
		return feePer;
	}

	/**
	 * @param feePer the feePer to set
	 */
	public void setFeePer(String feePer) {
		this.feePer = feePer;
	}

	/**
	 * @return the tax1
	 */
	public String getTax1() {
		return tax1;
	}

	/**
	 * @param tax1 the tax1 to set
	 */
	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}

	/**
	 * @return the tax2
	 */
	public String getTax2() {
		return tax2;
	}

	/**
	 * @param tax2 the tax2 to set
	 */
	public void setTax2(String tax2) {
		this.tax2 = tax2;
	}

	/**
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}

	/**
	 * @return the responseStatus
	 */
	public String getResponseStatus() {
		return responseStatus;
	}

	/**
	 * @param responseStatus the responseStatus to set
	 */
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
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
	 * @return the challenNumber
	 */
	public String getChallenNumber() {
		return challenNumber;
	}

	/**
	 * @param challenNumber the challenNumber to set
	 */
	public void setChallenNumber(String challenNumber) {
		this.challenNumber = challenNumber;
	}

	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param mid the mid to set
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}

	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
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
	 * @return the transactionTime
	 */
	public String getTransactionTime() {
		return transactionTime;
	}

	/**
	 * @param transactionTime the transactionTime to set
	 */
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GBSSDto [amount=" + amount + ", challenNumber="
				+ challenNumber + ", mid=" + mid + ", tid=" + tid
				+ ", transactionDate=" + transactionDate + ", transactionTime="
				+ transactionTime + ", transactionId=" + transactionId
				+ ", customerName=" + customerName + ", paymentMode="
				+ paymentMode + ", status=" + status + ", created=" + created
				+ ", updated=" + updated + ", responseStatus=" + responseStatus
				+ ", errorDescription=" + errorDescription + "]";
	}

}
