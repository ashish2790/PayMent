package com.awl.tch.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHServiceException;

public class TestSaleCompService {

	ApplicationContext ctx;
	
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	@Test
	@Ignore
	public void service(){
		SaleCompServiceImpl saleService =(SaleCompServiceImpl)ctx.getBean("saleCompServiceImpl");
		Payment input = new Payment();
		input.setTerminalSerialNumber("17005CT26194274");
		input.setClientId("TC0000000000754");
		input.setBinNumber("4854980600");
		input.setCardEntryMode("CHIP");
		input.setPanNumber("631736915E1CDA2B2DD0CB243DD29617");
		input.setExpiryDate("1712");
		input.setPinBlock("E99BC7CD283C4D3C");
		input.setOriginalAmount("777777");
		input.setStanNumber("000304");
		input.setField55("82025C00950502000480009A031708169C01005F2A0203565F3401019F02060000007777779F03060000000000009F090200079F100706010A038020009F1A0203569F1E08534349464D5836359F2608A0B67F7FFA832DFF9F2701009F3303E0F0C89F34030203009F3501229F3602021E9F370434EFFFB69F4104000003039F530152");
		input.setReferenceValue("722811190002");
		input.setInvoiceNumber("000148");
		input.setDecisionFlag("Z1");
		input.setLastFourDigitValue("7495");
		input.setDate("170816");
		input.setTime("165040");
		try {
			saleService.service(input);
		} catch (TCHServiceException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(input);
	}
}
