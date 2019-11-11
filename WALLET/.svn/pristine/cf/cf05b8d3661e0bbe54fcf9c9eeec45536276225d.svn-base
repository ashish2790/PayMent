package com.awl.tch.wallet.fc.bean;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;


/**
 * WalletResponseBean is used as a response object(from Fc host to terminal)
 * @author pooja.patil
 *
 */
public class WalletResponseBean {

	@SerializedName("txnId")
    private String txnId;
    
    @SerializedName("responseDescription")
    private String responseDesc;
    
    @SerializedName("responseCode")
    private String responseCode;
    
    @SerializedName("txnAmount")
    private String txnAmount;
    
    @SerializedName("ServerTransactionID")
	private String serverTxnId;
    
    @SerializedName("AdditinalInfo")
    private AdditinalInfo[] additinalInfo;

	public AdditinalInfo[] getAdditinalInfo() {
		return additinalInfo;
	}

	public void setAdditinalInfo(AdditinalInfo[] additinalInfo) {
		this.additinalInfo = additinalInfo;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getServerTxnId() {
		return serverTxnId;
	}

	public void setServerTxnId(String serverTxnId) {
		this.serverTxnId = serverTxnId;
	}

	@Override
	public String toString() {
		return "WalletResponseBean [txnId=" + txnId + ", responseDesc=" + responseDesc + ", responseCode="
				+ responseCode + ", txnAmount=" + txnAmount + ", additinalInfo=" + Arrays.toString(additinalInfo) + "]";
	}
	
}
