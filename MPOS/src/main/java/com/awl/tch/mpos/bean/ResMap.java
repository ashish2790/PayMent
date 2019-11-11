package com.awl.tch.mpos.bean;

import com.google.gson.annotations.SerializedName;

public class ResMap {
	
	
	@SerializedName(value="encdata")
	private String encdata;

	public String getEncdata() {
		return encdata;
	}

	public void setEncdata(String encdata) {
		this.encdata = encdata;
	}

	@Override
	public String toString() {
		return "ResMap [encdata=" + encdata + "]";
	}

}
