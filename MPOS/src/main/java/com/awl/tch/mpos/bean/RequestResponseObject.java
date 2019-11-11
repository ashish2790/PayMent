package com.awl.tch.mpos.bean;

import com.google.gson.annotations.SerializedName;

public class RequestResponseObject {
	
	@SerializedName(value="resMap")
	private ResMap resMap;

	public ResMap getResMap() {
		return resMap;
	}

	

	public void setResMap(ResMap resMap) {
		this.resMap = resMap;
	}

	@Override
	public String toString() {
		return "RequestResponseObject [resMap=" + resMap + "]";
	}
	
	

}
