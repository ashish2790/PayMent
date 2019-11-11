package com.awl.tch.upi.service;


public class TestQRWSService {/*
	ClassPathXmlApplicationContext ctx; 
	QRRequestParameters input = new QRRequestParameters();
	QRDataElementsObject data = new QRDataElementsObject();
	
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-UPI.xml");
	}
	
	@Test
	@Ignore
	public void testGetQRStringPositive(){
		QRWSService service = (QRWSService) ctx.getBean("qrwsCallService");
		try {
			input = service.getQRString(input.setBank_code("00075"), data.setMid("000022600018181"), data.setTid("22060471"), data.setAmount("10"))
		} catch (UPIException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(input);
	}
	
	@Test
	@Ignore
	public void testGetACKStringPositive(){
		QRWSService service = (QRWSService) ctx.getBean("qrwsCallService");
		try{
			input = service.getACKString(input.setBank_code("00075"), data.getTid("22060471"), data.setMid("000022600018181"), data.getPrgType("3"), data.setTxnId("719519354335285069"));
		} catch(UPIException e){
			e.printStackTrace();
		}
		Assert.assertNotNull(input);
	}
	
	@Test
	@Ignore
	public void testGetCANStringPositive(){
		QRWSService service = (QRWSService) ctx.getBean("qrwsCallService");
		try{
			input = service.getCANString(input.setBank_code("00075"), data.getTid("22060471"), data.setMid("000022600018181"), data.getPrgType("3"), data.setTxnId("719519354335285069"));
		} catch(UPIException e){
			e.printStackTrace();
		}
		Assert.assertNotNull(input);
	}
	
	@Test
	@Ignore
	public void testRefundString{
		QRWSService service = (QRWSService) ctx.getBean("qrwsCallService");
		try{
			input = service.getRefundString(input.setBank_code("00075"), data.setMid("000022600018181"), data.getTid("22060471"), data.getAmount("10"));
		} catch(UPIException e){
			e.printStackTrace();
		}
		Assert.assertNotNull(input);
	}
	
	
	 * Negative test cases : 
	 * null string in request
	 * number instead of String
	 * wrong sequence of fields in request
	 * amount send in wrong format
	 
	
	
*/}
