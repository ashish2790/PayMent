package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;

public class ProgramObject {

	
	@SerializedName("prgCd")
	private String programCode;
	
	@SerializedName("prgSrtNm")
	private String programShortName;
	
	@SerializedName("prdCdFlg")
	private String productCodeFlag;
	
	private String manufactureName;
	private transient String mfgDisclaimer;
	
	/**
	 * @return the mfgDisclaimer
	 */
	public String getMfgDisclaimer() {
		return mfgDisclaimer;
	}

	/**
	 * @param mfgDisclaimer the mfgDisclaimer to set
	 */
	public void setMfgDisclaimer(String mfgDisclaimer) {
		this.mfgDisclaimer = mfgDisclaimer;
	}

	/**
	 * @return the manufactureName
	 */
	public String getManufactureName() {
		return manufactureName;
	}

	/**
	 * @param manufactureName the manufactureName to set
	 */
	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * @param programCode the programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	/**
	 * @return the programShortName
	 */
	public String getProgramShortName() {
		return programShortName;
	}

	/**
	 * @param programShortName the programShortName to set
	 */
	public void setProgramShortName(String programShortName) {
		this.programShortName = programShortName;
	}

	/**
	 * @return the productCodeFlag
	 */
	public String getProductCodeFlag() {
		return productCodeFlag;
	}

	/**
	 * @param productCodeFlag the productCodeFlag to set
	 */
	public void setProductCodeFlag(String productCodeFlag) {
		this.productCodeFlag = productCodeFlag;
	}

	
}
