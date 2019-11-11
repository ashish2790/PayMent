package com.awl.tch.model;

import java.util.Date;

import org.springframework.context.annotation.Conditional;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Table;

@Table(name="TC_TERM_PARAMETER")
public class TerminalParameterDTO {

	@Id
	@Column(name = "PERMPARAMID")
	private Integer id;

	@Column(name = "MEPARAMID")
	private Integer merchantParameterId;

	@Column(name = "MID")
	private String mid;

	@Column(name = "TID")
	private String tid;

	@Column(name = "CARD_TYPE")
	private Integer cardType;

	@Column(name = "CARD_RANGE_LOW")
	private String cardRangeLow;

	@Column(name = "CARD_RANGE_HIGH")
	private String cardRangeHigh;

	@Column(name = "CARD_LENGTH")
	private String cardLength;

	@Column(name = "FLOOR_LIMIT")
	private String floorLimit;

	@Column(name = "CARD_LABEL")
	private String cardLabel;

	@Column(name = "LUHN_CHECK_TYPE")
	private Integer luhnCheckType;

	@Column(name = "RAS_CON_TIME_OUT")
	private Integer rasConnectionTimeout;

	@Column(name = "RAS_RES_TIME_OUT")
	private Integer rasResetTimeout;

	@Column(name = "MODEM_MODE")
	private Integer modemMode;

	@Column(name = "MODEM_PARAM")
	private String modemParam;

	@Column(name = "CONNECT_TIME_OUT")
	private Integer connectTimeout;

	@Column(name = "RESP_TIME_OUT")
	private Integer	responseTimeout;

	@Column(name = "AUTH_BAUD_RATE")
	private Integer	authBaudRate;

	@Column(name = "SETTL_BAUD_RATE")
	private Integer	settlementBaudRate;

	@Column(name = "BATCH_NUM")
	private Integer	batchNum;

	@Column(name = "INVOICE_NUM")
	private Integer	invoiceNum;

	@Column(name = "STAN")
	private Integer	stan;

	@Column(name = "TPDU")
	private String	tpdu;

	@Column(name = "HOST_TYPE")
	private Integer	hostType;

	@Column(name = "HOST_SUB_TYPE")
	private Integer	hostSubType;

	@Column(name = "TRICKLE_FEED_CNT")
	private Integer	trickleFeedCount;

	@Column(name = "NII")
	private Integer nii;

	@Column(name = "COMM_LABEL")
	private String commLabel;

	@Column(name = "TIPPERCENT")
	private Integer	tipPercentage;

	@Column(name = "CASHBACK")
	private String	cashback;

	@Column(name = "CASHBACKPUR")
	private String	cashbackPur;

	@Column(name = "AUTHORISATION")
	private String	authorisation;

	@Column(name = "ADJUSTMENT")
	private String	adjustment;

	@Column(name = "REFUNDNEW")
	private String	refundNew;

	@Column(name = "OFFLINECHK")
	private String	offlineCheck;

	@Column(name = "AUTOSETTLEMENT")
	private String	autoSettlement;

	@Column(name = "LASTFOURDIGIT")
	private String	lastFourDigit;

	@Column(name = "UNIQUE_KEY_REQ")
	private String	uniqueKeyRequest;

	@Column(name = "LINEENC")
	private String	lineEncryption;

	@Column(name = "DAILY_TRAN_LIMIT")
	private Integer	dailyTransactionLimit;

	@Column(name = "TXNPERBATCH")
	private Integer	transactionPerBatch;

	@Column(name = "FOOTER_MESSAGE")
	private String	footerMessage;

	@Column(name = "TRANS_PWD")
	private String	transactionPassword;

	@Column(name = "ECASH_PWD")
	private String	ecashPassword;

	@Column(name = "SUPERVISOR_PWD")
	private String	supervisorPassword;

	@Column(name = "CLOSE_SETTL_PWD")
	private String	closeSettlementPassword;

	@Column(name = "CLR_BATCH_PWD")
	private String	clrBatchPassword;

	@Column(name = "DIAL_TYPE")
	private String	dialType;

	@Column(name = "BLIND_DIAL")	
	private String blindDial;

	@Column(name = "TRAINING_MODE")
	private String	trainingMode;

	@Column(name = "CLERK_LOGIN")
	private String	clerkLogin;

	@Column(name = "PAN_ENTRY_MODE")
	private Integer panEntryMode;

	@Column(name = "BAL_INQ_ALWD")
	private String	balanceInquiryAllowed;

	@Column(name = "FOURDBC_FLAG")
	private String	fourDbcFlag;

	@Column(name = "APPLICATIONMODE")
	private Integer	applicationMode;

	@Column(name = "ADDEDON")
	private Date	addedOn;

	@Column(name = "ADDEDBY")
	private String	addedBy;

	@Column(name = "ADDIPADDRESS")
	private String	addIpAddred;

	@Column(name = "MODIFIEDON")
	private Date	modifiedOn;

	@Column(name = "MODIFIEDBY")
	private String	modifiedBy;

	@Column(name = "MODIFIEDIPADDRESS")
	private String	modifiedIpAddress;

	@Column(name = "RELOAD")
	private String	reload;

	@Column(name = "PINPROMPT")
	private String	pinPrompt;
	
	@Column(name="TIPFLAG")
	private String tipFlag;
	
	@Column(name="CURRENCYNAME")
	private String currencyName;
	
	@Column(name="CURRENCYCODE")
	private String currencyCode;
	
	@Column(name="PREAUTH")
	private String preauthFlag;
	
	@Column(name="DCCFLAG")
	private String dccFlag;
	
	
	@Column(name="DCCMID")
	private String dccMid;
	
	@Column(name="DCCTID")
	private String dccTid;
	
	@Column(name="SE_NUMBER")
	private String seNumber;
	
	@Column(name="REFUND")
	private String keyEntryMode;
	
	/**
	 * @return the keyEntryMode
	 */
	public String getKeyEntryMode() {
		return keyEntryMode;
	}

	/**
	 * @param keyEntryMode the keyEntryMode to set
	 */
	public void setKeyEntryMode(String keyEntryMode) {
		this.keyEntryMode = keyEntryMode;
	}

	/**
	 * @return the seNumber
	 */
	public String getSeNumber() {
		return seNumber;
	}

	/**
	 * @param seNumber the seNumber to set
	 */
	public void setSeNumber(String seNumber) {
		this.seNumber = seNumber;
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the dccTid
	 */
	public String getDccTid() {
		return dccTid;
	}

	/**
	 * @param dccTid the dccTid to set
	 */
	public void setDccTid(String dccTid) {
		this.dccTid = dccTid;
	}

	/**
	 * @return the dccFlag
	 */
	public String getDccFlag() {
		return dccFlag;
	}

	/**
	 * @param dccFlag the dccFlag to set
	 */
	public void setDccFlag(String dccFlag) {
		this.dccFlag = dccFlag;
	}

	/**
	 * @return the dccMid
	 */
	public String getDccMid() {
		return dccMid;
	}

	/**
	 * @param dccMid the dccMid to set
	 */
	public void setDccMid(String dccMid) {
		this.dccMid = dccMid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantParameterId() {
		return merchantParameterId;
	}

	public void setMerchantParameterId(Integer merchantParameterId) {
		this.merchantParameterId = merchantParameterId;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardRangeLow() {
		return cardRangeLow;
	}

	public void setCardRangeLow(String cardRangeLow) {
		this.cardRangeLow = cardRangeLow;
	}

	public String getCardRangeHigh() {
		return cardRangeHigh;
	}

	public void setCardRangeHigh(String cardRangeHigh) {
		this.cardRangeHigh = cardRangeHigh;
	}

	public String getCardLength() {
		return cardLength;
	}

	public void setCardLength(String cardLength) {
		this.cardLength = cardLength;
	}

	public String getFloorLimit() {
		return floorLimit;
	}

	public void setFloorLimit(String floorLimit) {
		this.floorLimit = floorLimit;
	}

	public String getCardLabel() {
		return cardLabel;
	}

	public void setCardLabel(String cardLabel) {
		this.cardLabel = cardLabel;
	}

	public Integer getLuhnCheckType() {
		return luhnCheckType;
	}

	public void setLuhnCheckType(Integer luhnCheckType) {
		this.luhnCheckType = luhnCheckType;
	}

	public Integer getRasConnectionTimeout() {
		return rasConnectionTimeout;
	}

	public void setRasConnectionTimeout(Integer rasConnectionTimeout) {
		this.rasConnectionTimeout = rasConnectionTimeout;
	}

	public Integer getRasResetTimeout() {
		return rasResetTimeout;
	}

	public void setRasResetTimeout(Integer rasResetTimeout) {
		this.rasResetTimeout = rasResetTimeout;
	}

	public Integer getModemMode() {
		return modemMode;
	}

	public void setModemMode(Integer modemMode) {
		this.modemMode = modemMode;
	}

	public String getModemParam() {
		return modemParam;
	}

	public void setModemParam(String modemParam) {
		this.modemParam = modemParam;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getResponseTimeout() {
		return responseTimeout;
	}

	public void setResponseTimeout(Integer responseTimeout) {
		this.responseTimeout = responseTimeout;
	}

	public Integer getAuthBaudRate() {
		return authBaudRate;
	}

	public void setAuthBaudRate(Integer authBaudRate) {
		this.authBaudRate = authBaudRate;
	}

	public Integer getSettlementBaudRate() {
		return settlementBaudRate;
	}

	public void setSettlementBaudRate(Integer settlementBaudRate) {
		this.settlementBaudRate = settlementBaudRate;
	}

	public Integer getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
	}

	public Integer getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(Integer invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public Integer getStan() {
		return stan;
	}

	public void setStan(Integer stan) {
		this.stan = stan;
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

	public Integer getHostType() {
		return hostType;
	}

	public void setHostType(Integer hostType) {
		this.hostType = hostType;
	}

	public Integer getHostSubType() {
		return hostSubType;
	}

	public void setHostSubType(Integer hostSubType) {
		this.hostSubType = hostSubType;
	}

	public Integer getTrickleFeedCount() {
		return trickleFeedCount;
	}

	public void setTrickleFeedCount(Integer trickleFeedCount) {
		this.trickleFeedCount = trickleFeedCount;
	}

	public Integer getNii() {
		return nii;
	}

	public void setNii(Integer nii) {
		this.nii = nii;
	}

	public String getCommLabel() {
		return commLabel;
	}

	public void setCommLabel(String commLabel) {
		this.commLabel = commLabel;
	}

	public Integer getTipPercentage() {
		return tipPercentage;
	}

	public void setTipPercentage(Integer tipPercentage) {
		this.tipPercentage = tipPercentage;
	}

	public String getCashback() {
		return cashback;
	}

	public void setCashback(String cashback) {
		this.cashback = cashback;
	}

	public String getCashbackPur() {
		return cashbackPur;
	}

	public void setCashbackPur(String cashbackPur) {
		this.cashbackPur = cashbackPur;
	}

	public String getAuthorisation() {
		return authorisation;
	}

	public void setAuthorisation(String authorisation) {
		this.authorisation = authorisation;
	}

	public String getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}

	public String getRefundNew() {
		return refundNew;
	}

	public void setRefundNew(String refundNew) {
		this.refundNew = refundNew;
	}

	public String getOfflineCheck() {
		return offlineCheck;
	}

	public void setOfflineCheck(String offlineCheck) {
		this.offlineCheck = offlineCheck;
	}

	public String getAutoSettlement() {
		return autoSettlement;
	}

	public void setAutoSettlement(String autoSettlement) {
		this.autoSettlement = autoSettlement;
	}

	public String getLastFourDigit() {
		return lastFourDigit;
	}

	public void setLastFourDigit(String lastFourDigit) {
		this.lastFourDigit = lastFourDigit;
	}

	public String getUniqueKeyRequest() {
		return uniqueKeyRequest;
	}

	public void setUniqueKeyRequest(String uniqueKeyRequest) {
		this.uniqueKeyRequest = uniqueKeyRequest;
	}

	public String getLineEncryption() {
		return lineEncryption;
	}

	public void setLineEncryption(String lineEncryption) {
		this.lineEncryption = lineEncryption;
	}

	public Integer getDailyTransactionLimit() {
		return dailyTransactionLimit;
	}

	public void setDailyTransactionLimit(Integer dailyTransactionLimit) {
		this.dailyTransactionLimit = dailyTransactionLimit;
	}

	public Integer getTransactionPerBatch() {
		return transactionPerBatch;
	}

	public void setTransactionPerBatch(Integer transactionPerBatch) {
		this.transactionPerBatch = transactionPerBatch;
	}

	public String getFooterMessage() {
		return footerMessage;
	}

	public void setFooterMessage(String footerMessage) {
		this.footerMessage = footerMessage;
	}

	public String getTransactionPassword() {
		return transactionPassword;
	}

	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}

	public String getEcashPassword() {
		return ecashPassword;
	}

	public void setEcashPassword(String ecashPassword) {
		this.ecashPassword = ecashPassword;
	}

	public String getSupervisorPassword() {
		return supervisorPassword;
	}

	public void setSupervisorPassword(String supervisorPassword) {
		this.supervisorPassword = supervisorPassword;
	}

	public String getCloseSettlementPassword() {
		return closeSettlementPassword;
	}

	public void setCloseSettlementPassword(String closeSettlementPassword) {
		this.closeSettlementPassword = closeSettlementPassword;
	}

	public String getClrBatchPassword() {
		return clrBatchPassword;
	}

	public void setClrBatchPassword(String clrBatchPassword) {
		this.clrBatchPassword = clrBatchPassword;
	}

	public String getDialType() {
		return dialType;
	}

	public void setDialType(String dialType) {
		this.dialType = dialType;
	}

	public String getBlindDial() {
		return blindDial;
	}

	public void setBlindDial(String blindDial) {
		this.blindDial = blindDial;
	}

	public String getTrainingMode() {
		return trainingMode;
	}

	public void setTrainingMode(String trainingMode) {
		this.trainingMode = trainingMode;
	}

	public String getClerkLogin() {
		return clerkLogin;
	}

	public void setClerkLogin(String clerkLogin) {
		this.clerkLogin = clerkLogin;
	}

	public Integer getPanEntryMode() {
		return panEntryMode;
	}

	public void setPanEntryMode(Integer panEntryMode) {
		this.panEntryMode = panEntryMode;
	}

	public String getBalanceInquiryAllowed() {
		return balanceInquiryAllowed;
	}

	public void setBalanceInquiryAllowed(String balanceInquiryAllowed) {
		this.balanceInquiryAllowed = balanceInquiryAllowed;
	}

	public String getFourDbcFlag() {
		return fourDbcFlag;
	}

	public void setFourDbcFlag(String fourDbcFlag) {
		this.fourDbcFlag = fourDbcFlag;
	}

	public Integer getApplicationMode() {
		return applicationMode;
	}

	public void setApplicationMode(Integer applicationMode) {
		this.applicationMode = applicationMode;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getAddIpAddred() {
		return addIpAddred;
	}

	public void setAddIpAddred(String addIpAddred) {
		this.addIpAddred = addIpAddred;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIpAddress() {
		return modifiedIpAddress;
	}

	public void setModifiedIpAddress(String modifiedIpAddress) {
		this.modifiedIpAddress = modifiedIpAddress;
	}

	public String getReload() {
		return reload;
	}

	public void setReload(String reload) {
		this.reload = reload;
	}

	public String getPinPrompt() {
		return pinPrompt;
	}

	public void setPinPrompt(String pinPrompt) {
		this.pinPrompt = pinPrompt;
	}
	
	
	public String getTipFlag() {
		return tipFlag;
	}

	public void setTipFlag(String tipFlag) {
		this.tipFlag = tipFlag;
	}

	
	/**
	 * @return the preauthFlag
	 */
	public String getPreauthFlag() {
		return preauthFlag;
	}

	/**
	 * @param preauthFlag the preauthFlag to set
	 */
	public void setPreauthFlag(String preauthFlag) {
		this.preauthFlag = preauthFlag;
	}

	@Override
	public String toString() {
		return "TerminalParameterDTO [id=" + id + ", merchantParameterId="
				+ merchantParameterId + ", mid=" + mid + ", tid=" + tid
				+ ", cardType=" + cardType + ", cardRangeLow=" + cardRangeLow
				+ ", cardRangeHigh=" + cardRangeHigh + ", cardLength="
				+ cardLength + ", floorLimit=" + floorLimit + ", cardLabel="
				+ cardLabel + ", luhnCheckType=" + luhnCheckType
				+ ", rasConnectionTimeout=" + rasConnectionTimeout
				+ ", rasResetTimeout=" + rasResetTimeout + ", modemMode="
				+ modemMode + ", modemParam=" + modemParam
				+ ", connectTimeout=" + connectTimeout + ", responseTimeout="
				+ responseTimeout + ", authBaudRate=" + authBaudRate
				+ ", settlementBaudRate=" + settlementBaudRate + ", batchNum="
				+ batchNum + ", invoiceNum=" + invoiceNum + ", stan=" + stan
				+ ", tpdu=" + tpdu + ", hostType=" + hostType
				+ ", hostSubType=" + hostSubType + ", trickleFeedCount="
				+ trickleFeedCount + ", nii=" + nii + ", commLabel="
				+ commLabel + ", tipPercentage=" + tipPercentage
				+ ", cashback=" + cashback + ", cashbackPur=" + cashbackPur
				+ ", authorisation=" + authorisation + ", adjustment="
				+ adjustment + ", refundNew=" + refundNew + ", offlineCheck="
				+ offlineCheck + ", autoSettlement=" + autoSettlement
				+ ", lastFourDigit=" + lastFourDigit + ", uniqueKeyRequest="
				+ uniqueKeyRequest + ", lineEncryption=" + lineEncryption
				+ ", dailyTransactionLimit=" + dailyTransactionLimit
				+ ", transactionPerBatch=" + transactionPerBatch
				+ ", footerMessage=" + footerMessage + ", transactionPassword="
				+ transactionPassword + ", ecashPassword=" + ecashPassword
				+ ", supervisorPassword=" + supervisorPassword
				+ ", closeSettlementPassword=" + closeSettlementPassword
				+ ", clrBatchPassword=" + clrBatchPassword + ", dialType="
				+ dialType + ", blindDial=" + blindDial + ", trainingMode="
				+ trainingMode + ", clerkLogin=" + clerkLogin
				+ ", panEntryMode=" + panEntryMode + ", balanceInquiryAllowed="
				+ balanceInquiryAllowed + ", fourDbcFlag=" + fourDbcFlag
				+ ", applicationMode=" + applicationMode + ", addedOn="
				+ addedOn + ", addedBy=" + addedBy + ", addIpAddred="
				+ addIpAddred + ", modifiedOn=" + modifiedOn + ", modifiedBy="
				+ modifiedBy + ", modifiedIpAddress=" + modifiedIpAddress
				+ ", reload=" + reload + ", pinPrompt=" + pinPrompt + "]";
	}
	
	

}
