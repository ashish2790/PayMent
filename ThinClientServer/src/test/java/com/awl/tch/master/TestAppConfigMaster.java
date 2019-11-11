package com.awl.tch.master;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.util.AppConfigMaster;


public class TestAppConfigMaster {

	ApplicationContext context;
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	@Test
	public void loadAppConfig(){
		AppConfigMaster.load();
		Map<String,String> values = AppConfigMaster.getConfigValue("QR");
		Assert.assertNotNull(values);
		
	}
}
