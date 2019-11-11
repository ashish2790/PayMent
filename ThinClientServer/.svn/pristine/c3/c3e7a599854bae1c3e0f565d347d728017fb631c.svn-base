package com.awl.tch.server;

import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Modifier;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.controller.AbstractController;
import com.awl.tch.filter.LoggingFilter;
import com.awl.tch.util.ErrorMaster;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class ServerFrontController implements FrontController {

	private static final ServerFrontController frontCtrller = new ServerFrontController();

	private static ConcurrentHashMap<String, String> controllerMapping = new ConcurrentHashMap<String, String>();
	
	private static final Logger logger = LoggerFactory
			.getLogger(ServerFrontController.class);

	static {
		Config.load();
	}

	private ServerFrontController() {

	}

	public static ServerFrontController getInstance() {
		return frontCtrller;
	}

	@Override
	public String forward(String requestString) throws Exception {
		Gson g = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
		Request requestObject;
		Response response = new Response() ;
		String responseString=null;
		try {
			JsonReader reader = new JsonReader(new StringReader(requestString));
			reader.setLenient(true);
			requestObject = g.fromJson(reader, Request.class);
		} catch (Exception e) {
			logger.error("Invalid Json Format. Exception Message is " + e.getMessage());
			return Response.getErrorResponse(response,ErrorConstants.TCH_Z002,ErrorMaster.get(ErrorConstants.TCH_Z002));
		}
		requestObject.setRequestJSON(requestString);
		String requestType = requestObject.getRequestType().toUpperCase();
		if (requestType != null && !requestType.isEmpty()) {
			String controller = controllerMapping.get(requestType);
			try {
				@SuppressWarnings("unchecked")
				Class<AbstractController> clas = (Class<AbstractController>) Class.forName(controller);
				
				ClassPathXmlApplicationContext ctx = TcpServer.getContext();
				AbstractController controllerInstance   = ctx.getBean(requestType, clas);
				if("RELOAD_ERR".equals(requestType)){
					controllerInstance.process(null, null);
				}else{
					controllerInstance.setApplicationContext(ctx);
					FilterManager filterManager = new FilterManager(controllerInstance);
					LoggingFilter logFilter = ctx.getBean("LoggingFilter", LoggingFilter.class);
					filterManager.setFilter(logFilter);
					filterManager.filterRequest(requestObject, response);
				}
				responseString = response.getResponseJSON();
				
				
			} catch ( ClassNotFoundException e) {
				responseString = Response.getErrorResponse(response,ErrorConstants.TCH_Z500,ErrorMaster.get(ErrorConstants.TCH_Z500));
			
				if (e instanceof ClassNotFoundException) {
					logger.error("Controller [" + controller + "] class not found");
				}
			}catch (Exception e) {
				logger.debug("Exception :" , e);
				responseString = Response.getErrorResponse(response,ErrorConstants.TCH_Z500,ErrorMaster.get(ErrorConstants.TCH_Z500));
			}finally{
				if(responseString != null && responseString.contains(Constants.SACK_REJECT)){
					throw new Exception("In case of sale ack 2nd time");
				}
			}
			
		} else {
			responseString = Response.getErrorResponse(response,ErrorConstants.TCH_Z001,ErrorMaster.get(ErrorConstants.TCH_Z001));
			// throw new IllegalArgumentException("Invalid Request Type");
		}
		return responseString;
	}

	/*
	 * Config loads the request type and controller mapping.
	 */
	private static class Config {
		public static void load() {

			try {
				Properties ep = new Properties();
				InputStream in = ServerFrontController.class
						.getResourceAsStream("/controllerMapping.properties");
				// File f = new File("config.properties");
				// FileInputStream fis = new FileInputStream(f);
				ep.load(in);
				Set<Entry<Object, Object>> configSet = ep.entrySet();
				for (Entry<Object, Object> configEntry : configSet) {
					controllerMapping.put((String) configEntry.getKey(),
							(String) configEntry.getValue());
				}
			} catch (Exception e) {
				logger.debug("Couldn't access properties file:/config.properties from classpath");
			}
		}
	}
}
