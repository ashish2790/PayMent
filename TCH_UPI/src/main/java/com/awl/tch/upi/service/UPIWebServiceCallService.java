package com.awl.tch.upi.service;

//import org.springframework.stereotype.Component;

import org.codehaus.jettison.json.JSONException;

import com.awl.tch.upi.bean.QRRequestParameters;
import com.awl.tch.upi.bean.QRResponse;
import com.awl.tch.upi.utility.UPIException;

//@Component("UPI")
/**
 * abstract methods to handle webservice calls
 * @author pooja.patil
 *
 */
public interface UPIWebServiceCallService{
	
	/**
	 * For each request from EDC to TCH for QR generation, 
	 * TCH sends request to Mobile server to get qr string in response 
	 * to send back to EDC.
	 * @param values
	 * @return
	 * @throws JSONException 
	 * @throws UPIException
	 */
	public QRResponse getQRString(String url, String... values) throws JSONException ;

	/**
	 * For each request from EDC to TCH to get the Acknowledgement 
	 * from Mobile Server whether the transaction was successful or failed 
	 * and send that response back to EDC.
	 * @param values
	 * @return
	 * @throws UPIException
	 */
	public QRResponse getACKString(String url, String...values) throws JSONException;

	/**
	 * For each request from EDC to TCH about canceling the QR transaction
	 * TCH sends request to Mobile server to cancel that corresponding transaction
	 * and sends response back to EDC.
	 * @param values
	 * @return
	 * @throws UPIException
	 */
	public QRResponse getCANString(String url, String...values) throws JSONException;
	
	
	

}
