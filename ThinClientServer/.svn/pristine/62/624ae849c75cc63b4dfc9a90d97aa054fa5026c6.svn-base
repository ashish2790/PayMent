package com.awl.tch.model;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Sequence;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.UCurrentTimestamp;

@Table(name = "TCH_HANDSHAKES")
public class HandshakeDTO {

	@Id
	@Column(name = "H_ID")
	@Sequence(name = "SEQ_TC_HANDSHAKES_ID")
	private Long id;

	// Following field used in request JSON
	@Column(name = "H_EDC_APP_VERSION")
	private String edcAppVersion;

	// Following field used in request JSON
	@Column(name = "H_PACKET_NUMBER")
	private Integer packetNumber;

	// Following field used in request JSON
	@Column(name = "H_HANDSHAKE_VERSION")
	private String handshakeVersion;

	@Column(name = "H_BIN_VALUES")
	private String binValues;

	// Following field used in request JSON
	@Column(name = "H_TERMINAL_SERIAL_NUMBER")
	private String terminalSerialNumber;

	@Column(name = "H_CLIENT_ID")
	private String clientId;

	@Column(name = "H_BANK_NAME")
	private String bankName;

	@Column(name = "H_MERCHANT_NAME")
	private String merchantName;

	@Column(name = "H_LEGAL_NAME")
	private String legalName;

	@Column(name = "H_MERCHANT_LOCATION")
	private String merchantLocation;

	@Column(name = "H_LAST_FOUR_DIGIT_PROMPT_FLAG")
	private String lastFourDigitPromptFlag;

	@Column(name = "H_DATE")
	private String date;

	@Column(name = "H_TIME")
	private String time;

	@Column(name = "H_FLAGS")
	private String flags;
	
	@Column(name = "H_HOSTID")
	private String hostIdFlags;

	@Column(name = "H_DESC_FLAG")
	private String descFlag;

	@Column(name = "H_SESSION_KEY")
	private String sessionKey;

	// Following field is used to save response from SWITCH.
	@Column(name = "H_RESPONSE_CODE")
	private String responseCode;

	// Following field is used to send straceNumber in request to SWITCH
	@Column(name = "H_STRACE_NUMBER")
	private String straceNumber;

	// Following field is used to send tid in request to SWITCH
	@Column(name = "H_TID")
	private String tid;

	// Following field is used to send cardAcquiringId in request to SWITCH
	@Column(name = "H_CARD_ACQUIRING_ID")
	private String cardAcquiringId;

	// Following field is used to send NII in request to SWITCH
	@Column(name = "H_NII")
	private String nii;

	// Following field is used for deciding processing Code
	@Column(name = "H_LINE_ENCRYPTION_FLAG")
	private String lineEncryptionFlag;

	// Following field is used for deciding processing Code
	@Column(name = "H_TPDU")
	private String tpdu;

	@Column(name = "H_CREATED")
	@ICurrentTimestamp
	private String created;

	@UCurrentTimestamp
	@Column(name = "H_UPDATED")
	private String updated;

	@Column(name = "H_TERMINAL_MODEL")
	private String terminalModel;
	
	@Column(name = "H_INSTALL_FLAG")
	private String installFlag;

	@Column(name="H_RTM_FLAG")
	private Integer rtmFlag;
	
	@Column(name="H_DWNLD_TM")
	private String dowloadTime;
	
	@Column(name="H_SPECIFIC_TIME")
	private String specificTime;
	
	
	/**
	 * @return the hostIdFlags
	 */
	public String getHostIdFlags() {
		return hostIdFlags;
	}

	/**
	 * @param hostIdFlags the hostIdFlags to set
	 */
	public void setHostIdFlags(String hostIdFlags) {
		this.hostIdFlags = hostIdFlags;
	}

	/**
	 * @return the rtmFlag
	 */
	public Integer getRtmFlag() {
		return rtmFlag;
	}

	/**
	 * @param rtmFlag the rtmFlag to set
	 */
	public void setRtmFlag(Integer rtmFlag) {
		this.rtmFlag = rtmFlag;
	}

	/**
	 * @return the dowloadTime
	 */
	public String getDowloadTime() {
		return dowloadTime;
	}

	/**
	 * @param dowloadTime the dowloadTime to set
	 */
	public void setDowloadTime(String dowloadTime) {
		this.dowloadTime = dowloadTime;
	}

	/**
	 * @return the specificTime
	 */
	public String getSpecificTime() {
		return specificTime;
	}

	/**
	 * @param specificTime the specificTime to set
	 */
	public void setSpecificTime(String specificTime) {
		this.specificTime = specificTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEdcAppVersion() {
		return edcAppVersion;
	}

	public void setEdcAppVersion(String edcAppVersion) {
		this.edcAppVersion = edcAppVersion;
	}

	public Integer getPacketNumber() {
		return packetNumber;
	}

	public void setPacketNumber(Integer packetNumber) {
		this.packetNumber = packetNumber;
	}

	public String getHandshakeVersion() {
		return handshakeVersion;
	}

	public void setHandshakeVersion(String handshakeVersion) {
		this.handshakeVersion = handshakeVersion;
	}

	public String getBinValues() {
		return binValues;
	}

	public void setBinValues(String binValues) {
		this.binValues = binValues;
	}

	public String getTerminalSerialNumber() {
		return terminalSerialNumber;
	}

	public void setTerminalSerialNumber(String terminalSerialNumber) {
		this.terminalSerialNumber = terminalSerialNumber;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getMerchantLocation() {
		return merchantLocation;
	}

	public void setMerchantLocation(String merchantLocation) {
		this.merchantLocation = merchantLocation;
	}

	public String getLastFourDigitPromptFlag() {
		return lastFourDigitPromptFlag;
	}

	public void setLastFourDigitPromptFlag(String lastFourDigitPromptFlag) {
		this.lastFourDigitPromptFlag = lastFourDigitPromptFlag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public String getDescFlag() {
		return descFlag;
	}

	public void setDescFlag(String descFlag) {
		this.descFlag = descFlag;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getStraceNumber() {
		return straceNumber;
	}

	public void setStraceNumber(String straceNumber) {
		this.straceNumber = straceNumber;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCardAcquiringId() {
		return cardAcquiringId;
	}

	public void setCardAcquiringId(String cardAcquiringId) {
		this.cardAcquiringId = cardAcquiringId;
	}

	public String getNii() {
		return nii;
	}

	public void setNii(String nii) {
		this.nii = nii;
	}

	public String getLineEncryptionFlag() {
		return lineEncryptionFlag;
	}

	public void setLineEncryptionFlag(String lineEncryptionFlag) {
		this.lineEncryptionFlag = lineEncryptionFlag;
	}

	public String getTpdu() {
		return tpdu;
	}

	public void setTpdu(String tpdu) {
		this.tpdu = tpdu;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getTerminalModel() {
		return terminalModel;
	}

	public void setTerminalModel(String terminalModel) {
		this.terminalModel = terminalModel;
	}

	public String getInstallFlag() {
		return installFlag;
	}

	public void setInstallFlag(String installFlag) {
		this.installFlag = installFlag;
	}
	
	

}
