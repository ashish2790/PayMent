package com.egras.entity;

import org.bouncycastle.util.encoders.Base64;

import com.egras.encryption.EgrasEncyption;
import com.egras.exception.EGRASServiceException;

public class SaveStatus {

	private String userId;
	private String bankRefNumber;
	private String CIN;
	private String password;
	private String bankCode;
	private String encData;
	private String error;
	private String success;
	private String paid_Amount;
	private String tran_Status;
	private String paidDate;
	private String grn;
	
	/**
	 * @return the grn
	 */
	public String getGrn() {
		return grn;
	}
	/**
	 * @param grn the grn to set
	 */
	public void setGrn(String grn) {
		this.grn = grn;
	}
	/**
	 * @return the paidDate
	 */
	public String getPaidDate() {
		return paidDate;
	}
	/**
	 * @param paidDate the paidDate to set
	 */
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	/**
	 * @return the tran_Status
	 */
	public String getTran_Status() {
		return tran_Status;
	}
	/**
	 * @param tran_Status the tran_Status to set
	 */
	public void setTran_Status(String tran_Status) {
		this.tran_Status = tran_Status;
	}
	/**
	 * @return the paid_Amount
	 */
	public String getPaid_Amount() {
		return paid_Amount;
	}
	/**
	 * @param paid_Amount the paid_Amount to set
	 */
	public void setPaid_Amount(String paid_Amount) {
		this.paid_Amount = paid_Amount;
	}
	/**
	 * @return the cIN
	 */
	public String getCIN() {
		return CIN;
	}
	/**
	 * @param cIN the cIN to set
	 */
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	/**
	 * @return the bankRefNumber
	 */
	public String getBankRefNumber() {
		return bankRefNumber;
	}
	/**
	 * @param bankRefNumber the bankRefNumber to set
	 */
	public void setBankRefNumber(String bankRefNumber) {
		this.bankRefNumber = bankRefNumber;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the encData
	 * @throws Exception 
	 */
	public String getEncData() throws Exception {
		encData = this.toString();
		encData = encData + "|checkSum="+EgrasEncyption.GetMD5Hash(encData);
		String base64Encoded = new String(Base64.encode(EgrasEncyption.encryptText(encData, EgrasEncyption.getSecretEncryptionKey())));
		encData = base64Encoded;
		return encData;
	}
	/**
	 * @param encData the encData to set
	 * @throws EGRASServiceException 
	 */
	public void setEncData(String encData) throws EGRASServiceException {
		this.encData = encData;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GRN=" + grn + "|BANK_CODE="+bankCode + "|BankReferenceNo="+bankRefNumber+ "|CIN=" + CIN 
				+ "|PAID_DATE=" + paidDate +"|PAID_AMOUNT=" + paid_Amount + "|TRANS_STATUS="
				+ tran_Status;
	}
	
	
}	
