/**
 * 
 */
package com.egras.exception;

/**
 * @author ashish.bhavsar
 *
 */
public class EGRASServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private String errorCode;
	private String responseCode;
	
	public EGRASServiceException(String code, String message, String resCode){
		super(message);
		this.errorMessage = message;
		this.errorCode = code;
		this.responseCode = resCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
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
}
