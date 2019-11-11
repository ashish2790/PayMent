package com.awl.tch.exceptions;
/**
* <h1>Custom exception</h1>
* The class <tt>TCHQueryException</tt> and its subclasses are a form of Throwable 
* that indicates conditions that a reasonable application might want to catch. 
* 
* <p>
* TCHServiceException is mainly used to catch the exception which will be 
* getting generated at dao layer with there 
* respective error code and messages. Based on which error object is
* generated and it is then send to terminal.
*   
* <b>Note:</b> Error messages contains error code and display message in it.
*
* @author  Ashish Bhavsar
* @version 1.0
* @since   2016-09-21
*/
public class TCHQueryException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8987065458623726051L;
	private String msg = "";
	private String errorCode;
	private String errorMessage;
	
	/**
     * Constructs a new TCHQueryException with the specified error code and detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param	errorCode the error code with respective error thrown. 
     * @param   errorMessage   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getErrorMessage()} method.
     */
	public TCHQueryException(String errorCode,String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Get error code.
	 * @return java.lang.String
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Set error code.
	 * @param errorCode it is specific with <tt>type-Code</tt> format.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Get detailed error message.
	 * @return java.lang.String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Set detailed error message.
	 * @param errorMessage detailed error message(based on the error code). 
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public TCHQueryException (String msg){
		super(msg);
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	@Override
	public String toString() {
		return msg;
	}
	 
	
}
