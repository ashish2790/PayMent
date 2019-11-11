package com.awl.tch.server;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.controller.AbstractController;
import com.awl.tch.filter.Filter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FilterChainImpl implements FilterChain {
	
	private List<Filter> filters = new LinkedList<Filter>();
	private AbstractController target;
	private int pos=0;
	
	private static final Logger logger = LoggerFactory
			.getLogger(FilterChainImpl.class);
	
	public void addFilter(Filter filter){
	      filters.add(filter);
	}

	@Override
	public void doFilter(Request request, Response response) {
		
		if(pos < filters.size())
		{
			Filter filter = filters.get(pos);	
			pos++;
			filter.doFilter(request, response, this);
		}
		else
			target.process(request,response);
		
		String responseString = null;
		try {
			Gson g = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
			responseString = g.toJson(response);
		} catch (Exception e) {
			responseString = Response.getErrorResponse(response,"ErrorConstants.TCH_R003","Unable to parse Response Object");
			logger.error("Unable to parse Response Object. Exception Message is " + e.getMessage(),e);
		}catch(Throwable t){
			logger.debug("Catch in throwable error :" + t.getStackTrace()) ;
		} 
		response.setResponseJSON(responseString);
	}
	
	public void setTarget(AbstractController target){
	      this.target = target;
	}
	

}
