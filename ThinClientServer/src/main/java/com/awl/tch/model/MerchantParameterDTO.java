package com.awl.tch.model;

import java.util.Date;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.Table;

@Table(name="TC_ME_PARAMETER")
public class MerchantParameterDTO {

	@Column(name="MEPARAMID")
	private Integer merchantParameterId;
	
	@Column(name="BANKCODE")
	private String bankCode;
	
	@Column(name="INSTITUTIONID")
	private Integer institutionId;
	
	@Column(name="MEPARAMID")
	private String merchantTYpe;
	
	@Column(name="BANKNAME")
	private String bankName;
	
	@Column(name="MID")
	private String mid;
	
	@Column(name="DBANAME")
	private String dbName;
	
	@Column(name="STATENAME")
	private String stateName;
	
	@Column(name="CITYNAME")
	private String cityName;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="PRIMARYPHONE")
	private String primaryPhone;
	
	@Column(name="SECONDARYPHONE")
	private String secondaryPhone;
	
	@Column(name="HEARTBEATINTERVAL")
	private String heartBeatInterval;
	
	@Column(name="ADDEDON")
	private String addedOn;
	
	@Column(name="ADDEDBY")
	private String addedBy;
	
	@Column(name="ADDIPADDRESS")
	private String AddIPAddress;
	
	@Column(name="MODIFIEDON")
	private String modifiedOn;
	
	@Column(name="MODIFIEDBY")
	private String modifiedBy;
	
	@Column(name="MODIFIEDIPADDRESS")
	private String modifiedIPAddress;
	
	@Column(name="LEGALNAME")
	private String legalName;
	
	@Column(name="INTTRALLOWED")
	private String intraAllowed;

	@Column(name="OPENPCRFLAG")
	private String openPcrFlag;
	
	public Integer getMerchantParameterId() {
		return merchantParameterId;
	}

	public void setMerchantParameterId(Integer merchantParameterId) {
		this.merchantParameterId = merchantParameterId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getMerchantTYpe() {
		return merchantTYpe;
	}

	public void setMerchantTYpe(String merchantTYpe) {
		this.merchantTYpe = merchantTYpe;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getHeartBeatInterval() {
		return heartBeatInterval;
	}

	public void setHeartBeatInterval(String heartBeatInterval) {
		this.heartBeatInterval = heartBeatInterval;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}


	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getAddIPAddress() {
		return AddIPAddress;
	}

	public void setAddIPAddress(String addIPAddress) {
		AddIPAddress = addIPAddress;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIPAddress() {
		return modifiedIPAddress;
	}

	public void setModifiedIPAddress(String modifiedIPAddress) {
		this.modifiedIPAddress = modifiedIPAddress;
	}

	/**
	 * @return the legalName
	 */
	public String getLegalName() {
		return legalName;
	}

	/**
	 * @param legalName the legalName to set
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	/**
	 * @return the intraAllowed
	 */
	public String getIntraAllowed() {
		return intraAllowed;
	}

	/**
	 * @param intraAllowed the intraAllowed to set
	 */
	public void setIntraAllowed(String intraAllowed) {
		this.intraAllowed = intraAllowed;
	}

	/**
	 * @return the openPcrFlag
	 */
	public String getOpenPcrFlag() {
		return openPcrFlag;
	}

	/**
	 * @param openPcrFlag the openPcrFlag to set
	 */
	public void setOpenPcrFlag(String openPcrFlag) {
		this.openPcrFlag = openPcrFlag;
	}
	
	
	
	
}
