package com.awl.tch.model;

import java.io.Serializable;

import org.springframework.context.annotation.Conditional;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Table;

@Table(name="TC_TERM_NFCEMV")
public class CardDetailDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TERMNFCEMVID")
	private Integer id;
	
	@Column(name = "BANK_CODE")
	private String bankCode;
	
	@Column(name = "INSTITUTIONID")
	private Integer institutionId;
	
	@Column(name = "MEPARAMID")
	private Integer meParamId;
	
	@Column(name = "MID")
	private String mid;
	
	@Column(name = "TID")
	private String tid;
	
	@Column(name = "APPLICATIONID")
	private String applicationId;
	
	@Column(name = "CARD_LABEL")
	private String cardLabel;
	
	@Column(name = "APP_ACTIVATION_FLAG")
	private String appActivationFlag;
	
	@Column(name = "EMV_TAC_DECLINE")
	private String emvTagDecline;
	
	@Column(name = "EMV_TAC_ONLINE")
	private String emvTagOnline;
	
	@Column(name = "EMV_TAC_DEFAULT")
	private String emvTacDefault;
	
	@Column(name = "EMV_FLOORLIMIT")
	private String emvFloorLimit;
	
	@Column(name = "TARGET")
	private String target;

	@Column(name = "THRESHOLDVALUE")
	private String thresholdValue;
	
	@Column(name = "MAXTARGET")
	private String maxTarget;
	
	@Column(name = "NFC_AID")
	private String nfcAid;
	
	@Column(name = "NFC_TAC_DECLINE")
	private String nfcTacDecline;
	
	@Column(name = "NFC_TAC_ONLINE")
	private String nfcTacOnline;
	
	@Column(name = "NFC_TAC_DEFAULT")
	private String nfcTacDefault;
	
	@Column(name = "NFC_CTLS_FLOOR_LIMIT")
	private String nfcCtlsFloorLimit;
	
	@Column(name = "NFC_CTLS_CVM_REQ_LIMIT")
	private String nfcCtlsCvmrequiredLimit;
	
	@Column(name = "NFC_ODVC_TRNS_LIMIT")
	private String nfcOdvcTransactionLimit;
	
	@Column(name = "NFC_NON_ODVC_TRNS_LIMIT")
	private String nfcNonOdvcTransactionLimit;
	
	@Column(name = "NFC_AIDACTIVATION_FLAG")
	private String nfcAidActivationFlag;
	
	@Column(name = "ADDEDBY")
	private String addedBy;
	
	@Column(name = "ADDEDON")
	private String addedOn;
	
	@Column(name = "MODIFIEDBY")
	private String modifyBy;
	
	@Column(name = "MODIFIEDON")
	private String modifyOn;
	
	@Column(name = "IPADDRESS")
	private String ipAddress;
	
	@Column(name ="ISNFC")
	private String isNFC;
	
	@Column(name="ISEMVACK")
	private String emvACK;
	
	
	
	
	/**
	 * @return the emvACK
	 */
	public String getEmvACK() {
		return emvACK;
	}

	/**
	 * @param emvACK the emvACK to set
	 */
	public void setEmvACK(String emvACK) {
		this.emvACK = emvACK;
	}

	/**
	 * @return the isNFC
	 */
	public String getIsNFC() {
		return isNFC;
	}

	/**
	 * @param isNFC the isNFC to set
	 */
	public void setIsNFC(String isNFC) {
		this.isNFC = isNFC;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the institutionId
	 */
	public Integer getInstitutionId() {
		return institutionId;
	}

	/**
	 * @param institutionId the institutionId to set
	 */
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	/**
	 * @return the meParamId
	 */
	public Integer getMeParamId() {
		return meParamId;
	}

	/**
	 * @param meParamId the meParamId to set
	 */
	public void setMeParamId(Integer meParamId) {
		this.meParamId = meParamId;
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
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the cardLabel
	 */
	public String getCardLabel() {
		return cardLabel;
	}

	/**
	 * @param cardLabel the cardLabel to set
	 */
	public void setCardLabel(String cardLabel) {
		this.cardLabel = cardLabel;
	}

	/**
	 * @return the appActivationFlag
	 */
	public String getAppActivationFlag() {
		return appActivationFlag;
	}

	/**
	 * @param appActivationFlag the appActivationFlag to set
	 */
	public void setAppActivationFlag(String appActivationFlag) {
		this.appActivationFlag = appActivationFlag;
	}

	/**
	 * @return the emvTagDecline
	 */
	public String getEmvTagDecline() {
		return emvTagDecline;
	}

	/**
	 * @param emvTagDecline the emvTagDecline to set
	 */
	public void setEmvTagDecline(String emvTagDecline) {
		this.emvTagDecline = emvTagDecline;
	}

	/**
	 * @return the emvTagOnline
	 */
	public String getEmvTagOnline() {
		return emvTagOnline;
	}

	/**
	 * @param emvTagOnline the emvTagOnline to set
	 */
	public void setEmvTagOnline(String emvTagOnline) {
		this.emvTagOnline = emvTagOnline;
	}

	/**
	 * @return the emvTacDefault
	 */
	public String getEmvTacDefault() {
		return emvTacDefault;
	}

	/**
	 * @param emvTacDefault the emvTacDefault to set
	 */
	public void setEmvTacDefault(String emvTacDefault) {
		this.emvTacDefault = emvTacDefault;
	}

	/**
	 * @return the emvFloorLimit
	 */
	public String getEmvFloorLimit() {
		return emvFloorLimit;
	}

	/**
	 * @param emvFloorLimit the emvFloorLimit to set
	 */
	public void setEmvFloorLimit(String emvFloorLimit) {
		this.emvFloorLimit = emvFloorLimit;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the thresholdValue
	 */
	public String getThresholdValue() {
		return thresholdValue;
	}

	/**
	 * @param thresholdValue the thresholdValue to set
	 */
	public void setThresholdValue(String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	/**
	 * @return the maxTarget
	 */
	public String getMaxTarget() {
		return maxTarget;
	}

	/**
	 * @param maxTarget the maxTarget to set
	 */
	public void setMaxTarget(String maxTarget) {
		this.maxTarget = maxTarget;
	}

	/**
	 * @return the nfcAid
	 */
	public String getNfcAid() {
		return nfcAid;
	}

	/**
	 * @param nfcAid the nfcAid to set
	 */
	public void setNfcAid(String nfcAid) {
		this.nfcAid = nfcAid;
	}

	/**
	 * @return the nfcTacDecline
	 */
	public String getNfcTacDecline() {
		return nfcTacDecline;
	}

	/**
	 * @param nfcTacDecline the nfcTacDecline to set
	 */
	public void setNfcTacDecline(String nfcTacDecline) {
		this.nfcTacDecline = nfcTacDecline;
	}

	/**
	 * @return the nfcTacOnline
	 */
	public String getNfcTacOnline() {
		return nfcTacOnline;
	}

	/**
	 * @param nfcTacOnline the nfcTacOnline to set
	 */
	public void setNfcTacOnline(String nfcTacOnline) {
		this.nfcTacOnline = nfcTacOnline;
	}

	/**
	 * @return the nfcTacDefault
	 */
	public String getNfcTacDefault() {
		return nfcTacDefault;
	}

	/**
	 * @param nfcTacDefault the nfcTacDefault to set
	 */
	public void setNfcTacDefault(String nfcTacDefault) {
		this.nfcTacDefault = nfcTacDefault;
	}

	/**
	 * @return the nfcCtlsFloorLimit
	 */
	public String getNfcCtlsFloorLimit() {
		return nfcCtlsFloorLimit;
	}

	/**
	 * @param nfcCtlsFloorLimit the nfcCtlsFloorLimit to set
	 */
	public void setNfcCtlsFloorLimit(String nfcCtlsFloorLimit) {
		this.nfcCtlsFloorLimit = nfcCtlsFloorLimit;
	}

	/**
	 * @return the nfcCtlsCvmrequiredLimit
	 */
	public String getNfcCtlsCvmrequiredLimit() {
		return nfcCtlsCvmrequiredLimit;
	}

	/**
	 * @param nfcCtlsCvmrequiredLimit the nfcCtlsCvmrequiredLimit to set
	 */
	public void setNfcCtlsCvmrequiredLimit(String nfcCtlsCvmrequiredLimit) {
		this.nfcCtlsCvmrequiredLimit = nfcCtlsCvmrequiredLimit;
	}

	/**
	 * @return the nfcOdvcTransactionLimit
	 */
	public String getNfcOdvcTransactionLimit() {
		return nfcOdvcTransactionLimit;
	}

	/**
	 * @param nfcOdvcTransactionLimit the nfcOdvcTransactionLimit to set
	 */
	public void setNfcOdvcTransactionLimit(String nfcOdvcTransactionLimit) {
		this.nfcOdvcTransactionLimit = nfcOdvcTransactionLimit;
	}

	/**
	 * @return the nfcNonOdvcTransactionLimit
	 */
	public String getNfcNonOdvcTransactionLimit() {
		return nfcNonOdvcTransactionLimit;
	}

	/**
	 * @param nfcNonOdvcTransactionLimit the nfcNonOdvcTransactionLimit to set
	 */
	public void setNfcNonOdvcTransactionLimit(String nfcNonOdvcTransactionLimit) {
		this.nfcNonOdvcTransactionLimit = nfcNonOdvcTransactionLimit;
	}

	/**
	 * @return the nfcAidActivationFlag
	 */
	public String getNfcAidActivationFlag() {
		return nfcAidActivationFlag;
	}

	/**
	 * @param nfcAidActivationFlag the nfcAidActivationFlag to set
	 */
	public void setNfcAidActivationFlag(String nfcAidActivationFlag) {
		this.nfcAidActivationFlag = nfcAidActivationFlag;
	}

	/**
	 * @return the addedBy
	 */
	public String getAddedBy() {
		return addedBy;
	}

	/**
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	/**
	 * @return the addedOn
	 */
	public String getAddedOn() {
		return addedOn;
	}

	/**
	 * @param addedOn the addedOn to set
	 */
	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

	/**
	 * @return the modifyBy
	 */
	public String getModifyBy() {
		return modifyBy;
	}

	/**
	 * @param modifyBy the modifyBy to set
	 */
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	/**
	 * @return the modifyOn
	 */
	public String getModifyOn() {
		return modifyOn;
	}

	/**
	 * @param modifyOn the modifyOn to set
	 */
	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
}
