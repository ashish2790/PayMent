package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;

public class ExtraObject {

	@SerializedName("xtrDtNm")
	private String extraDataName;
	
	@SerializedName("xtrDtVl")
	private String extraDataValue;

	/**
	 * @return the extraDataName
	 */
	public String getExtraDataName() {
		return extraDataName;
	}

	/**
	 * @param extraDataName the extraDataName to set
	 */
	public void setExtraDataName(String extraDataName) {
		this.extraDataName = extraDataName;
	}

	/**
	 * @return the extraDataValue
	 */
	public String getExtraDataValue() {
		return extraDataValue;
	}

	/**
	 * @param extraDataValue the extraDataValue to set
	 */
	public void setExtraDataValue(String extraDataValue) {
		this.extraDataValue = extraDataValue;
	}
	
	
}
