package com.awl.tch.wallet.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsClient {

	private static  Logger logger = LoggerFactory.getLogger(HttpsClient.class);

	private static HostnameVerifier allHostsValid = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
	
	private static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] certs,
				String authType) {
		}

		public void checkServerTrusted(X509Certificate[] certs,
				String authType) {
		}

	} };

	public static String send(String uri, String queryString,HashMap<String, String> headers)
	{
		String response = "";
		URL url;
		try {
			boolean isHttps = uri.startsWith("https");
			logger.debug("Connecting URL [ "+uri+ " ]");
			url = new URL(uri);
			HttpURLConnection con = null;
			if(isHttps){
				logger.debug("It's https..");
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
				SSLContext sc = SSLContext.getInstance("TLSv1.2");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				con  = (HttpsURLConnection)url.openConnection();
			}
			else{
				logger.debug("It's not https..");
				con = (HttpURLConnection) url.openConnection();
			}
			
			if(headers!=null){
				for(Entry<String, String> ent : headers.entrySet()){				
					con.setRequestProperty(ent.getKey(), ent.getValue());
				}
			}
			
			if(queryString!=null){
				con.setRequestMethod("POST");
				con.setConnectTimeout(700000);
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(queryString);
				wr.flush();
				wr.close();
			}
			
			int responseCode = con.getResponseCode();
			logger.debug("Http status for request :"+responseCode);
			//System.out.println("Http status for request :"+responseCode);
			response  = getResponse(con);
			con.disconnect();
			
		} catch (MalformedURLException e) {
			logger.error("Incorrect URL :"+uri,e);
			throw new RuntimeException(e); 
		} catch (IOException e) {
			logger.error("Exception  while hitting :"+uri,e);
			throw new RuntimeException(e);
		} catch (KeyManagementException e) {
			logger.error("Exception  while hitting :"+uri,e);
			throw new RuntimeException();
		} catch (NoSuchAlgorithmException e) {
			logger.error("Exception  while hitting :"+uri,e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			logger.error("Exception  while hitting :"+uri,e);
			throw new RuntimeException(e);
		}
		
		return response;
	}
	
	
	private static String  getResponse(HttpURLConnection con) throws IOException{
		String resp = null;
		if(con!=null){
			InputStream inputStream=con.getInputStream();
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
			    result.write(buffer, 0, length);
			}
			resp=result.toString("UTF-8");
			logger.info("Response is :"+resp);
			//System.out.println("Response is :"+resp);
		}
		return resp;
	}
}
