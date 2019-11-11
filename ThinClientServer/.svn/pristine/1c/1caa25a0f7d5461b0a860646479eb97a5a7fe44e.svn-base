package com.awl.tch.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.controller.ReloadCacheController;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.google.gson.internal.LinkedTreeMap;

public class TestBrandEmiService {

	BrandEmiService brandEmService = null;
	ReloadCacheController reloadController = null;
	@Before
	public void init()
	{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		brandEmService = ctx.getBean("brandEmiService", BrandEmiService.class);
		reloadController = ctx.getBean("BRDCACHE", ReloadCacheController.class);
		Request requestObj = new Request();
		Response responseObj = new Response();
		requestObj.setRequestType("BRDCACHE");
		LinkedTreeMap<String, Object> obj = new LinkedTreeMap<String, Object>();
		obj.put("prm","000056690630018");
		requestObj.setRequestObject(obj);
		reloadController.process(requestObj, responseObj);
		
	}
	@Test
	@Ignore
	public void getMenuObject(){
		
		SuperMenuObject superobj  = new SuperMenuObject();
		superobj.setTerminalSerialNumber("17060WL25837288");
		
		try {
			superobj = brandEmService.service(superobj);
		} catch (TCHServiceException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(superobj);
	}
}
