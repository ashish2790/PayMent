package com.awl.tch.httpsclient;

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
			logger.debug("Connecting URL"+uri);
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
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(queryString);
				wr.flush();
				wr.close();
			}
			
			//print_https_cert(con);
			
			int responseCode = con.getResponseCode();
			logger.debug("Http status for request :"+responseCode);
			//System.out.println("Http status for request :"+responseCode);
			response  = getResponse(con);

		} catch (MalformedURLException e) {
			logger.error("Incorrect URL :"+uri,e);
			response = "999";
		} catch (IOException e) {
			logger.error("Exception  while hitting :"+uri,e);
			response = "999";
		} catch (KeyManagementException e) {
			logger.error("Exception  while hitting :"+uri,e);
			response = "999";
		} catch (NoSuchAlgorithmException e) {
			logger.error("Exception  while hitting :"+uri,e);
			response = "999";
		} catch (Exception e) {
			logger.error("Exception  while hitting :"+uri,e);
			response = "999";
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
	
	public static void main(String[] args) {
		System.setProperty("https.proxyHost", "");
		System.setProperty("https.proxyPort", "");
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		String str = send("https://103.209.97.240:8443/POS_Service.svc/Get_POS_CHLN_DETAILS","{ \"Data\":\"fGRF4cEp++OUf4jKNBOWrhlGpsJro6lpiXIqUm/YN7TcO9HcL424v6kPs+tizE3LPH0Z+3smK2crJzQc3orKQtHcRrkYPqL6kUsWmA9P03ZxTXQSB5lInjpuC1/tgPwJYgusRyy9/I3RlyydwwfgvhHiyykCx0rA0m/jIG9T/IOtUyU8mJX8s6OjJjLtkWw5z63kS+s+7B9bvhqKKDuGNNMVfu8dMCQpjP6JuNaDhs/PeCM+OamHwBK72mvhIkEIU5jloIEM/CuXrSOq5QzzsXs1mn/yEIjUpd/yLTILw1JtyOMEJJPb6QSf20mNWhb2OkR1qJb9qU/f5KHbOJKO6rWEW3xSpUIgMmfKJVdCpwMpw43cmelrvrxx18gAFOF286+8YB5jaNH9zbtsnv/KEecOSHw71+SvU00GbKfam2YyCshz2dmTZhWj9jGWxJCbJMamlCAD2VVoOE8qiXSlqInmt2gSmSkCAwyBL+ErtoUtSe2gBqW96GK3vqYnXrs6lzpILFir6oOs7TVO6bmAXJj5TIKt0XZ8wJEqjhh31x43A8fmvsM2Nms/Aytw6nhFvihJOepsrfube06Yu/uucvWw2F/vKheudUKiLu2d9WLC2BrcZPz2/ZxCXfKTzgGf5CafbbTT7a7pFIHOtSPiW77KHOSt5FSaYb/zXlMqs0U=\" }",headerMap);
	}
}
