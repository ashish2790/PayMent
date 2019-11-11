package com.awl.tch.aop.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.tcp.TcpClient;


public class TestAOP {

	ApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("/spring.xml");
	}
	
	@Test
	public void testAop() throws TCHServiceException{
		TcpClient tcp = ctx.getBean(TcpClient.class);
	}
}
