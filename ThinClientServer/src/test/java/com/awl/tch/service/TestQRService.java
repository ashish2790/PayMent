package com.awl.tch.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.exceptions.TCHServiceException;

public class TestQRService {

	ClassPathXmlApplicationContext ctx; 
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	@Test
	@Ignore
	public void getQRString(){
		SaleServiceImpl service = (SaleServiceImpl) ctx.getBean(Constants.TCH_SALE_SERVICE);
		Payment input = new Payment();
		input.setTerminalSerialNumber("14063WL21481334");
		
		try {
			input = service.serviceQR(input);
			System.out.println(input.getQrString());
			System.out.println(input.getQrType());
		} catch (TCHServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(input);
	}
	
	@Test
	@Ignore
	public void checkStatus(){
		SaleServiceImpl service = (SaleServiceImpl) ctx.getBean(Constants.TCH_SALE_SERVICE);
		Payment input = new Payment();
		input.setTerminalSerialNumber("16058WL24100942");
		try {
			input.setDecisionFlag(Constants.TCH_QRACK);
			input = service.serviceQR(input);
			System.out.println(input.getQrString());
			System.out.println(input.getQrType());
		} catch (TCHServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(input);
	}
}
