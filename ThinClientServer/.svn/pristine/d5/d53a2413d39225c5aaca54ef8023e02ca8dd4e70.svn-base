package com.awl.tch.server;

public class Request {

	private String reqTyp;
	private Object reqObj;
	private transient String requestJSON;

	public String getRequestType() {
		return reqTyp;
	}

	public void setRequestType(String requestType) {
		this.reqTyp = requestType;
	}

	public Object getRequestObject() {
		return reqObj;
	}

	public void setRequestObject(Object requestObject) {
		this.reqObj = requestObject;
	}
	
	public String getRequestJSON() {
		return requestJSON;
	}

	public void setRequestJSON(String requestJSON) {
		this.requestJSON = requestJSON;
	}

	@Override
	public String toString() {
		return "{ requestType=" + reqTyp + ", requestObject="
				+ reqObj + " }";
	}

}
