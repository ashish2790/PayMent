package com.awl.tch.wallet.fc.model;


import com.awl.tch.wallet.fc.bean.AdditinalInfo;
import com.google.gson.annotations.SerializedName;


/**
 * FcWalletRequest is used as a Request object(from Tch host to Fc host)
 * @author pooja.patil
 *
 */
public class FcWalletRequest {
	
	private transient String url;
	
	@SerializedName("merchantID")
	private String merchantId;
	
	@SerializedName("terminalID")
	private String terminalId;
	
	@SerializedName("platformId")
	private String platformId;
	
	@SerializedName("transactionRefNumber")
	private String transactionRefNumber;
	
	@SerializedName("walletID")
	private String walletId;
	
	@SerializedName("txnAmount")
	private String txnAmount;	
	
	@SerializedName("txnDatenTime")
	private String txnDatenTime;
	
	@SerializedName("ProcCode")
	private String procCode;
	
	@SerializedName("authToken")
	private String authToken;
	
	@SerializedName("OTP")
	private String otp;
	
	@SerializedName("AdditinalInfo")
    private AdditinalInfo[] additinalInfo;
	
	@SerializedName("ServerTransactionID")
	private String serverTxnId;
	
	private transient String requestType;
	
	private transient String termSerNum;
	
	private transient String invNumber;
	
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getTransactionRefNumber() {
		return transactionRefNumber;
	}
	public void setTransactionRefNumber(String TransactionRefNumber) {
		this.transactionRefNumber = TransactionRefNumber;
	}
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public String getProcCode() {
		return procCode;
	}
	/**
	 * @param procCode the procCode to set
	 */
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}
	/**
	 * @return the amount
	 */
	public String getTxnAmount() {
		return txnAmount;
	}
	/**
	 * @param txnAmount the amount to set
	 */
	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}
	
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	public AdditinalInfo[] getAdditinalInfo() {
		return additinalInfo;
	}
	public void setAdditinalInfo(AdditinalInfo[] additinalInfo) {
		this.additinalInfo = additinalInfo;
	}
	
	public String getServerTxnId() {
		return serverTxnId;
	}
	public void setServerTxnId(String serverTxnId) {
		this.serverTxnId = serverTxnId;
	}
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getTermSerNum() {
		return termSerNum;
	}
	public void setTermSerNum(String termSerNum) {
		this.termSerNum = termSerNum;
	}
	public String getInvNumber() {
		return invNumber;
	}
	public void setInvNumber(String invNumber) {
		this.invNumber = invNumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FcWalletRquest [merchantId=" + merchantId + ", terminalId=" + terminalId + ", platformId=" + platformId
				+ ", transactionRefNumber=" + transactionRefNumber + ", walletId=" + walletId + ", txnAmount="
				+ txnAmount + ", txnDatenTime=" + txnDatenTime + ", procCode=" + procCode + ", authToken=" + authToken
				+ ", otp=" + otp +  "]";
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTxnDatenTime() {
		return txnDatenTime;
	}
	public void setTxnDatenTime(String txnDatenTime) {
		this.txnDatenTime = txnDatenTime;
	}
	
}
