package com.awl.tch.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestImageLogo {
	ApplicationContext ctx ;
	
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	@Test
	@Ignore
	public void bankLogo(){
		
	}
}
