package com.awl.tch.upi.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;

import com.awl.tch.upi.bean.QRResponse;
import com.awl.tch.upi.constant.Constants;
import com.awl.tch.upi.utility.HttpsClient;
import com.awl.tch.upi.utility.UPIException;
import com.awl.tch.upi.utility.UpiUtilityHelper;
/*import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;*/
import com.google.gson.Gson;

/**
 * Purpose : call webService by using rest Template and send the received
 * response to EDC
 * <h1>Sending request parameter to Mobile server.</h1>
 * WebServiceCallServiceImpl program is used to set all the business logic
 * related to make a web service call to Mobile server which will send necessary
 * fields in JSON format and on the basis of that information get a generated QR
 * code from Mobile server. all the business logic <tt>satisfy</tt> all the
 * condition for getting response from Mobile team and sends that appropriate
 * response to terminal.
 * <p>
 * In which request parameter must contain mandatory field as <tt>MID</tt>,
 * <tt>TID</tt>, <tt>bank_code</tt>, <tt>qrType</tt> base on which all the
 * related information like <tt>QR code string</tt> <tt>trId</tt> is going to
 * send as response object as <tt>bitmap image</tt>.
 * </p>
 * Finally if all condition are satisfied then based on the <tt>TR ID</tt> an
 * acknowledgement will be sent to Mobile server by a web service call for a
 * successful transaction. <b>Note:</b> Business logic set as per the standard
 * followed by WL.
 *
 * arguments passed are mid, tid, bank_code, qrType, trId as in varargs
 *
 * @author Pooja Patil
 * @version 1.0
 * @since 2017-06-27
 */


@Repository("qrwsCallService")
public class QRWSService implements UPIWebServiceCallService {

	private static Logger logger = LoggerFactory.getLogger(QRWSService.class);
	
	
	Gson gson = new Gson();
	static {
		disableSslVerification();
	}
	
	@Override
	public QRResponse getQRString(String url, String... param) throws JSONException {
		/*System.setProperty("http.proxyHost", "10.10.15.200");
		System.setProperty("http.proxyPort", "8080");*/
		logger.debug("Inside getQRString()..");
		
		String requestString = Constants.UPI_PARM + gson.toJson(UpiUtilityHelper.requestMapper(Constants.UPI_GETQR,param));
		logger.debug("Request to mobile server [ " + requestString  +"]");
		
		String response = sendRequest(requestString, url);
		logger.debug("Response : [" + response + "]");

		if (response != null && !response.isEmpty()) {
			return getResponse(response);
		} 
		logger.debug("Exiting getQRString()..");
		return null;
	}

	@Override
	public QRResponse getACKString(String url, String... param) throws JSONException {
		/*System.setProperty("http.proxyHost", "10.10.15.200");
		System.setProperty("http.proxyPort", "8080");*/
		logger.debug("Inside check status()..");
		
		String requestString = Constants.UPI_PARM + gson.toJson(UpiUtilityHelper.requestMapper(Constants.UPI_CHECKSTATUS,param));
		logger.debug("Request to mobile server [ " + requestString  +"]");
		
		String response = sendRequest(requestString, url);
		logger.debug("Response : [" + response + "]");

		if (response != null && !response.isEmpty()) {
			return getResponse(response);
		} 
		return null;
	}

	@Override
	public QRResponse getCANString(String url, String... param) throws JSONException {
		/*System.setProperty("http.proxyHost", "10.10.15.200");
		System.setProperty("http.proxyPort", "8080");*/
		logger.debug("Inside cancel qr string..");
	
		String requestString = Constants.UPI_PARM + gson.toJson(UpiUtilityHelper.requestMapper(Constants.UPI_CANCELQR,param));
		logger.debug("Request to mobile server [ " + requestString  +"]");
		
		String response = sendRequest(requestString, url);
		logger.debug("Response : [" + response + "]");
	
		if (response != null && !response.isEmpty()) {
			return getResponse(response);
		} 
		return null;
	}
	
	/**
	 * @param param
	 * @return
	 * @throws UPIException
	 * @throws JSONException 
	 */
	public QRResponse getRefundString(String url, String... param) throws JSONException{
		/*System.setProperty("http.proxyHost", "10.10.15.200");
		System.setProperty("http.proxyPort", "8080");*/
		logger.debug("Inside cancel qr string..");
	
		String requestString = Constants.UPI_PARM + gson.toJson(UpiUtilityHelper.requestMapper(Constants.UPI_REFUND,param));
		logger.debug("Request to mobile server [ " + requestString  +"]");
		
		String response = sendRequest(requestString, url);
		logger.debug("Response : [" + response + "]");
	
		if (response != null && !response.isEmpty()) {
			return getResponse(response);
		} 
		return null;
	}
	
	/**
	 * Setting response in QRResponse object
	 * @param response
	 * @return
	 */
	private QRResponse getResponse(String response){
		return gson.fromJson(response, QRResponse.class);
	}
	
	private static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	public String sendRequest(String requestString, String url){
		logger.debug("Sending request..");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestString, httpHeaders);
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/x-www-form-urlencoded");
		return sendRequest(requestString, url,headerMap);
		
		//return new RestTemplate().postForObject(url, httpEntity, String.class);
	}
	
	public String sendRequest(String requestString, String url,HashMap<String, String> map){
		return HttpsClient.send(url, requestString, map);
	}
}
