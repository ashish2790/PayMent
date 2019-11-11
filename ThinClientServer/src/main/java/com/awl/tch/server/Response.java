package com.awl.tch.server;

import com.awl.tch.bean.HealthObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

public class Response {

		@SerializedName(value = "sErrPrs")
		@Expose
	private boolean errorPresent;

	@SerializedName(value = "errCd")
	@Expose
	private String errorCode;

	@SerializedName(value = "errMsg")
	@Expose
	private String errorMessage;

	@SerializedName("rspCd")
	private String responseCode;
	
	@SerializedName("isFld55")
	private String issuerField55;
	
	@SerializedName(value="hltObj")
	private HealthObject healthObject;
	
	@SerializedName(value = "resObj")
	private Object responseObject;
	
	private transient String responseJSON;

	public boolean isErrorPresent() {
		return errorPresent;
	}

	
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}



	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	/**
	 * @return the issuerField55
	 */
	public String getIssuerField55() {
		return issuerField55;
	}


	/**
	 * @param issuerField55 the issuerField55 to set
	 */
	public void setIssuerField55(String issuerField55) {
		this.issuerField55 = issuerField55;
	}


	/**
	 * @return the healthObject
	 */
	public HealthObject getHealthObject() {
		return healthObject;
	}


	/**
	 * @param healthObject the healthObject to set
	 */
	public void setHealthObject(HealthObject healthObject) {
		this.healthObject = healthObject;
	}


	public void setErrorPresent(boolean errorPresent) {
		this.errorPresent = errorPresent;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	
	public String getResponseJSON() {
		return responseJSON;
	}

	public void setResponseJSON(String responseJSON) {
		this.responseJSON = responseJSON;
	}

	public static void setErrorResponseObject(Response res, String errorCode,String errorMessage) {
		setErrorResponseObject(res, errorCode, errorMessage, null);
	}
	public static void setErrorResponseObject(Response res, String errorCode,String errorMessage, String responseCode) {
		setErrorResponseObject(res,errorCode,errorMessage,responseCode,null);
	}
	public static void setErrorResponseObject(Response res, String errorCode,String errorMessage,String responseCode, String issuerField55)	{
		setErrorResponseObject(res,errorCode,errorMessage,responseCode,issuerField55,null);
	}
	public static void setErrorResponseObject(Response res, String errorCode,String errorMessage,String responseCode, String issuerField55, HealthObject hlthObj) {
		res.setIssuerField55(issuerField55);
		res.setResponseCode(responseCode);
		res.setErrorCode(errorCode);
		res.setErrorMessage(errorMessage);
		res.setHealthObject(hlthObj);
		res.setErrorPresent(true);
	}
	
	
	public static String getErrorResponse(Response res,String errorCode,String errorMessage)
	{
		res.setErrorCode(errorCode);
		res.setErrorMessage(errorMessage);
		res.setErrorPresent(true);
		Gson g= new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String resStr = g.toJson(res);
		return resStr;
	}
	
	public static void setResponseObject(Response res,Object resObj) throws Exception
	{
		res.setErrorPresent(false);
		//res.setResponseObject(UtilityHelper.setDefaultMandatoryValue(resObj));
		res.setResponseObject(resObj);
	}

}
