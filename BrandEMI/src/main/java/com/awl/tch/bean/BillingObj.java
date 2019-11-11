package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;

public class BillingObj {

	@SerializedName("lblNm")
	private String labelName;
	
	@SerializedName("lblVal")
	private String labelValue;
	
	@SerializedName("maxLen")
	private String maxLength;

	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * @param labelName the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return the labelValue
	 */
	public String getLabelValue() {
		return labelValue;
	}

	/**
	 * @param labelValue the labelValue to set
	 */
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}

	/**
	 * @return the maxLength
	 */
	public String getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength the maxLength to set
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	
}
