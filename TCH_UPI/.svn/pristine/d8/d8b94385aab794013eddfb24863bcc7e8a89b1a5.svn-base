package com.awl.tch.upi.utility;

import com.awl.tch.upi.bean.QRRequestParameters;

public class UPIException extends Exception{
	
	/**
	 * 
	 */
	private String errorCode;
	private String errorMessage;
	
	private static final long serialVersionUID = 6770438599527725430L;

	/**
	 * generates response String if exception is thrown
	 * @param status 
	 * @param message
	 * @return the formatted String
	 */
	public UPIException(String message) {
        super(message);
    }
	
	public QRRequestParameters generateResponse(String status,String message){
		StringBuilder sb = new StringBuilder();
		sb.append(status);
		sb.append("/");
		sb.append(message);
		QRRequestParameters qrStr = new QRRequestParameters();
		qrStr.setStr(sb.toString());
		System.err.println("UPIException : "+qrStr);
		return qrStr;
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
	
}
