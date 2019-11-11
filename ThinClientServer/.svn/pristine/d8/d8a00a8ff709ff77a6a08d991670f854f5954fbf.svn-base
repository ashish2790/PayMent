package com.awl.tch.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSequenceGenerator {

	ApplicationContext ctx;
	
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	@Test
	public void getSequence(){
		CommonDaoImpl cmd = (CommonDaoImpl) ctx.getBean(CommonDaoImpl.class);
		System.out.println(cmd.getSequenceNumber());
	}
}
