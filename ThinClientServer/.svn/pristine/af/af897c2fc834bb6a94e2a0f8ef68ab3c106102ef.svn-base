package com.awl.tch.service;

import java.util.List;


import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.adaptor.brandemi.BrandEmiAdaptorImpl;
import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.constant.Constants;
import com.awl.tch.dao.BrandEmiDaoImpl;
import com.awl.tch.dao.BrandEmiSerialNumberDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.BrandEMIDTO;


/**
 *  Made by Saloni Jindal on 6-09-2017
 */

public class TestBrandEnquiry {
	ApplicationContext ctx;
	@Before
	public void init()
	{
		 ctx = new ClassPathXmlApplicationContext("spring.xml");
		// brandEmiDao = (BrandEmiDaoImpl) ctx.getBean(Constants.TCH_BRAND_EMI_DAO);
	}
	
	@Test
	public void getEnquiry()
	{
		//BrandEmiAdaptorImpl brandEmiAdapterImpl=new BrandEmiAdaptorImpl();
		BrandEmiAdaptorImpl brandEmiAdapterImpl = (BrandEmiAdaptorImpl) ctx.getBean("brandEmiAdaptor");
		// BrandEmiSerialNumberDaoImpl BrandEmiSerialNumberDaoImpl = (BrandEmiSerialNumberDaoImpl) ctx.getBean("brandEmiSerialDao");
		SuperMenuObject input = new SuperMenuObject();
		input.setTerminalSerialNumber("17060WL25837288");
		input.setSerialNumber("58");
		input.setIMIENumber("58");
		try {
		brandEmiAdapterImpl.getEnquiry(input);
		} catch (TCHServiceException e) {
			e.printStackTrace();
		}
		
		
	}

}
