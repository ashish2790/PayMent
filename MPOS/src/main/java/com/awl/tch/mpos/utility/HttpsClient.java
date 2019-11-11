package com.awl.tch.mpos.utility;

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

import javax.management.RuntimeErrorException;
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
			url = new URL(uri);
			HttpURLConnection con=null;
			// Install the all-trusting host verifier
			if(isHttps)
			{
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
				SSLContext sc = SSLContext.getInstance("TLSv1.2");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				con = (HttpsURLConnection)url.openConnection();
			}
			else
			{
				con = (HttpURLConnection) url.openConnection();
			}
			
			if(headers!=null)
			{
				for(Entry<String, String> ent : headers.entrySet())
				{
					con.setRequestProperty(ent.getKey(), ent.getValue());
				}
			}
			
			if(queryString!=null)
			{
				con.setRequestMethod("POST");
				con.setDoOutput(true);
				con.setConnectTimeout(25000);
				con.setRequestProperty("Content-Type", "application/json");
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(queryString);
				wr.flush();
				wr.close();
			}
			
			//print_https_cert(con);
			
			int responseCode = con.getResponseCode();
			logger.debug("Http status for request :"+responseCode);
			response = getResponse(con);

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
		logger.debug("Inside getResponse()..");
		logger.debug("con : "+con);
		if(con!=null){
			logger.debug("If con not null..");
			InputStream inputStream=con.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
				logger.debug("Inside while()..");
				baos.write(buffer, 0, length);
			}
			resp=baos.toString("UTF-8");
		}
		return resp;
	}
}
