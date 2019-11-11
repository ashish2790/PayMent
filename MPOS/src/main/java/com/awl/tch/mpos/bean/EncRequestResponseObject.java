package com.awl.tch.mpos.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Bean which defines request and response json parameters 
 * @author Pooja Patil
 * @return
 */
public class EncRequestResponseObject {

	@SerializedName(value="encdata")
	private String encdata;

	public String getEncdata() {
		return encdata;
	}

	public void setEncdata(String encdata) {
		this.encdata = encdata;
	}
	
	@SerializedName(value="encRequestVal")
	private String encRequestVal;

	public String getEncRequestVal() {
		return encRequestVal;
	}

	public void setEncRequestVal(String encRequestVal) {
		this.encRequestVal = encRequestVal;
	}
	
}
