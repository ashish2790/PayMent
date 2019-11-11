package com.awl.tch.irctc.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.awl.tch.irctc.constant.IrctcConstant;
import com.google.gson.Gson;
import com.tch.irctc.model.SaleConfirm;
import com.tch.irctc.model.SaleEnquiry;
import com.tch.irctc.model.TransactionEnquiry;
import com.tch.irctc.model.VoidTxn;

public abstract class AbstractIrctcService {

	static{
		disableSslVerification();
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
	/**
	 * Get the http entity with basic authorization in it 
	 * @param username  
	 * @param password 
	 * @return http entity string response 
	 */
	public HttpEntity<String> setHttpEntity(String username,String password){
		byte[] encoding = Base64.encodeBase64((username+":"+password).getBytes());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		httpHeaders.set("Authorization", "Basic "+ new String(encoding));
		//httpHeaders.set("Authorization","Basic YWRtaW46YWRtaW4=");
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
		httpHeaders = null;
		return httpEntity;
	}
	
	
	/**
	 * Parse json string and convert the response in respective object 
	 * @param reqType 
	 * @param jsonString
	 * @return Object with  
	 */
	public Object parseGson(String reqType, String jsonString){
		Gson g =  new Gson();
		switch(reqType){
		case IrctcConstant.TCH_IRCTC_SALE_SERVICE:
			return g.fromJson(jsonString, SaleEnquiry.class);
		case IrctcConstant.TCH_IRCTC_SALE_CONF_SERVICE:
			return g.fromJson(jsonString, SaleConfirm.class);
		case IrctcConstant.TCH_IRCTC_SALE_VOID_SERVICE:
			return g.fromJson(jsonString, VoidTxn.class);
		case IrctcConstant.TCH_IRCTC_TRANSACTION_ENQUIRY_SERVICE:
			return g.fromJson(jsonString, TransactionEnquiry.class);
		default :
			return null;
		}
	}
}
