package com.awl.tch.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.SuperMenuObject;

public class TestBrandEmiDao {

	BrandEmiDaoImpl brandEmiDao = null;
	
	@Before
	public void init()
	{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		brandEmiDao = ctx.getBean("brandEmidao", BrandEmiDaoImpl.class);
	}
	
	@Test
	public void getValidationConfirmed(){
		
		SuperMenuObject input  = new SuperMenuObject();
		input.setTerminalSerialNumber("16333WL25422271");
		input.setSerialNumber("1425784513265986532");
		input.setReferenceValue("000114");
		brandEmiDao.validationConfirmMark(input);
		Assert.assertNotNull(input);
	}
	
}
