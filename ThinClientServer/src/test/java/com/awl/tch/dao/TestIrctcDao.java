package com.awl.tch.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.Payment;

public class TestIrctcDao {

	ApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	@Test
	public void testAlreadyPresent(){
		IrctcDaoImpl dao = ctx.getBean(IrctcDaoImpl.class);
		Payment input = new Payment();
		input.setTerminalSerialNumber("15246WL23468233");
		input.setReferenceValue("100120181204551");
		
		System.out.println(dao.isAreadyUsedTxnId(input));
	}
}
