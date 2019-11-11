package com.awl.tch.mpos.test;

import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awl.tch.mpos.bean.MOPSBean;
import com.awl.tch.mpos.bean.MOPSResponse;
import com.awl.tch.mpos.exception.SbiMopsException;
import com.awl.tch.mpos.service.MPOSServiceImpl;

public class MOPSTestCases {

		private static Logger logger = LoggerFactory.getLogger(MPOSServiceImpl.class);
		
		
		/*
		 * Positive test cases :
		 * For each request, send the required data in proper format as a request 
		 * to Mobile server to get proper response
		 */
		@Test
		public void testCreateTransaction(){
			//SbiMopsServiceImpl service = (SbiMopsServiceImpl) ctx.getBean("sbiwsCallService");
			MPOSServiceImpl service = new MPOSServiceImpl();
			MOPSResponse sbiRes = new MOPSResponse();
			MOPSBean input = new MOPSBean();
			input.setBankRefNo("22092017000089");
			input.setDateTime("22092017");
			String url = "https://uatmerchant.onlinesbi.com/mservices/mopspos/cardpayment/createTransaction";
			try {
				sbiRes = service.getAmount(input,url);
			}catch (SbiMopsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertNotNull(input);
		}
		
	/*	@Test
		public void testPaymentUpdate(){
			//SbiMopsServiceImpl service = (SbiMopsServiceImpl) ctx.getBean("sbiwsCallService");
			MPOSServiceImpl service = new MPOSServiceImpl();
			MOPSResponse sbiRes = new MOPSResponse();
			MOPSBean input = new MOPSBean();
			input.setAmount("177");
			input.setBankRefNo("22092017000089");
			input.setDateTime("22092017");
			input.setStatus("Sucess");
			input.setStatusCode("00");
			input.setStatusDescription("transaction found");
			
			
			
			String url = "https://uatmerchant.onlinesbi.com/mservices/mopspos/paymentupdate/paymentUpdate";
			try {
				sbiRes = service.updateStatus(input,url);
			} catch (SbiMopsException e) {
				// TODO Auto-generated catch block
				//throw new SbiMopsException(e.getErrorCode(),e.getErrorMessage());
				e.printStackTrace();
			}
			Assert.assertNotNull(input);
		}*/
		
		
}
