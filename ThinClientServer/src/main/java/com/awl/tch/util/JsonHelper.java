package com.awl.tch.util;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.server.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonHelper {
	private static final Logger logger = LoggerFactory.getLogger(JsonHelper.class);
	
	private JsonHelper(){
		
	}	
	public static Object getActualObject(Request reqObj, Class<?> type) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException, ClassNotFoundException
	{
		Gson gson = new Gson();
		if(reqObj.getRequestObject() == null){
			logger.debug("Checking for null value");
			return null;
		}
		//get the LinkedTrrMap and get JsonObject from it
		JsonObject jsonObject = gson.toJsonTree(reqObj.getRequestObject()).getAsJsonObject();
		//Get the actual object from json object with the  help of Gson
		return gson.fromJson(jsonObject, type);
	}	
}
