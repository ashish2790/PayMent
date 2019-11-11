package com.awl.tch.exceptions;
/**
* <h1>Custom exception</h1>
* The class <tt>TCHServiceException</tt> and its subclasses are a form of Throwable 
* that indicates conditions that a reasonable application might want to catch. 
* 
* <p>
* TCHServiceException is mainly used to catch the exception which will be 
* getting generated at service layer with there 
* respective error code and messages. Based on which error object is
* generated and it is then send to terminal.
*   
* <b>Note:</b> Error messages contains error code and display message in it.
*
* @author  Kunal Surana
* @version 1.0
* @since   2016-09-21
*/
public class TCHServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4675704666743503433L;
	private String errorCode;
	private String errorMessage;
	private String responseCode;
	private String issuerField55;
	
	/**
     * Constructs a new TCHServiceException with the specified error code and detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param	errorCode the error code with respective error thrown. 
     * @param   errorMessage   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getErrorMessage()} method.
     */
	public TCHServiceException(String errorCode,String errorMessage) {
		this(errorCode,errorMessage,null);
	}
	public TCHServiceException(String errorCode,String errorMessage, String responseCode){
		this(errorCode, errorMessage, responseCode, null);
	}
	public TCHServiceException(String errorCode,String errorMessage, String responseCode, String issuerField55)
	{
		super(errorMessage);
		this.issuerField55 = issuerField55;
		this.responseCode = responseCode;
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
	 * Get detailed error message.
	 * @return java.lang.String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}
	/**
	 * @return the issuerField55
	 */
	public String getIssuerField55() {
		return issuerField55;
	}

	
}
