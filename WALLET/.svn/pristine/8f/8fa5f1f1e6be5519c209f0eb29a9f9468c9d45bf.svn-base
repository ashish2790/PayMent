package com.awl.tch.wallet.common;

/**
* <h1>Custom exception</h1>
* The class <tt>WalletException</tt> and its subclasses are a form of Throwable 
* that indicates conditions that a reasonable application might want to catch. 
* 
* <p>
* WalletException is mainly used to catch the exception which will be 
* getting generated at service layer with there 
* respective error code and messages. Based on which error object is
* generated and it is then send to terminal(Error msgs will be decided by FC Host).
*   
* <b>Note:</b> Error messages contains error code and display message in it.
*
* @author  pooja.patil
*/
public class WalletException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	private String errorCode;
	private String responseCode;
	
	public WalletException(String message){
		super(message);
	}
	public WalletException(String errorMessage, String errorCode) {
		this(errorCode, errorMessage, null);
	}
	public WalletException(String errorCode,String errorMessage, String responseCode){
		super(errorMessage);
		this.setResponseCode(responseCode);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
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
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
}
