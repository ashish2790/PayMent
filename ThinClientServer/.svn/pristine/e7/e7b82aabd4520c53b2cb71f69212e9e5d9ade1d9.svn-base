package com.awl.tch.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHQueryException;


public class TestAbstractGenericDao {
	ApplicationContext context;
	
	@Before
	public void init(){
		 context = new ClassPathXmlApplicationContext("/spring.xml");
	}
	
	@Test
	@Ignore
	public void getTerminalInfo(){
		SaleDaoImpl hnd = (SaleDaoImpl) context.getBean("paymentSaleDao");
		Payment p = new Payment();
		p.setTerminalSerialNumber("17060WL25837288");
		try {
			hnd.getTerminalDetails(p);
		} catch (TCHQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(p.getTerminalId());
		Assert.assertNotNull(p.getBankCode());
		Assert.assertNotNull(p.getMerchantId());
	}
	
}
