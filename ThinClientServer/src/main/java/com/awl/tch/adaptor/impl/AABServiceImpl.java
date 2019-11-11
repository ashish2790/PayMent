/*package com.awl.tch.adaptor.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.awl.tch.aab.model.AABEntity;
import com.awl.tch.adaptor.ExternalEntityService;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.UtilityExternalEntityHelper;

@Service("AABServiceImpl")
public class AABServiceImpl extends ExternalEntityService<AABEntity>{

	private static Logger logger = LoggerFactory.getLogger(AABServiceImpl.class);
	
	@Override
	public void service(Object obj) throws MalformedURLException, TCHServiceException {
		
		AABEntity aab = (AABEntity) obj;
		logger.info("URL : " + aab.getUrl());
		invokeService(aab);
	}

	public void invokeService(AABEntity aab) throws MalformedURLException, TCHServiceException {
		String outputString = "";
		try{
			
			URL url = new URL(aab.getUrl());
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection)connection;
			
			String xmlInput = UtilityExternalEntityHelper.getParameterizedXML(aab);
			logger.info("XML : "+ xmlInput);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			bout.write(buffer);
			byte[] b = bout.toByteArray();
			bout = null;
			// Set the appropriate HTTP parameters.
			httpConn.setRequestProperty("Content-Length",String.valueOf(b.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestProperty("SOAPAction", aab.getSoapAction());
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			
			OutputStream out = httpConn.getOutputStream();
			out.write(b);
			out.close();
			 
			//Read the response.
			 
			//Write the SOAP message response to a String.
			int red = -1;
			buffer  = new byte[4096];
			byte[] redData; 
			while ((red = httpConn.getInputStream().read(buffer)) > -1) {
				redData = new byte[red];
			    System.arraycopy(buffer, 0, redData, 0, red);
			    outputString = new String(redData,"UTF-8"); // assumption that client sends data UTF-8 encoded
			}
			while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
			}
			logger.debug("Response String ->" + outputString);
			Document document = parseXmlFile(outputString);
			NodeList nodeLst = document.getElementsByTagName("CardPaymentRequestResult");
			outputString = nodeLst.item(0).getTextContent();
			logger.info("response result -> " + outputString);
			//Write the SOAP message formatted to the console.
			String formattedSOAPResponse = formatXML(outputString);
			System.out.println(formattedSOAPResponse);
			aab.setResult(outputString);
		}catch(IOException e){
			e.printStackTrace();
			throw new TCHServiceException("ErrorConstants.TCH_A001, ErrorMaster.get(ErrorConstants.TCH_A001),"01"); // set response code as '01' to convert TCE into stand alone mode
		}
	}
	//format the XML in your String
	public String formatXML(String unformattedXml) {
		try {
		Document document = parseXmlFile(unformattedXml);
		OutputFormat format = new OutputFormat(document);
		format.setIndenting(true);
		format.setIndent(3);
		format.setOmitXMLDeclaration(true);
		Writer out = new StringWriter();
		XMLSerializer serializer = new XMLSerializer(out, format);
		serializer.serialize(document);
		return out.toString();
		} catch (IOException e) {
		throw new RuntimeException(e);
		}
	}
	 
	private Document parseXmlFile(String in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse(new InputSource(new StringReader(in)));
			
			} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
			} catch (SAXException e) {
			throw new RuntimeException(e);
			} catch (IOException e) {
			throw new RuntimeException(e);
			}
	}
	
	
}
*/