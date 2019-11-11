package com.awl.tch.mpos.exception;

public class SbiMopsException extends Exception{

			private static final long serialVersionUID = 1L;

			private String errorMessage;
			private String errorCode;
			private String responseCode;
			
			public SbiMopsException(String code, String message, String resCode){
				super(message);
				this.errorMessage = message;
				this.errorCode = code;
				this.responseCode = resCode;
			}
			
			public SbiMopsException(String code, String message){
				this(code,message,null);
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

}
