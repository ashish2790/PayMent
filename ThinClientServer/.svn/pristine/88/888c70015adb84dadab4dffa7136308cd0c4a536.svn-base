package com.awl.tch.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.DataPrintObject;
import com.awl.tch.bean.Payment;
import com.awl.tch.controller.DccEnquiryController;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.iso8583.ISOdccSale;
import com.awl.tch.service.EnquiryExchangeService;
import com.awl.tch.service.EnquiryExchangeServiceImpl;
import com.google.gson.internal.LinkedTreeMap;
import com.mchange.util.AssertException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;

public class TestEnquiryExchangeDao {

		EnquiryExchangeService enquiryExchangeService = null;
		DccEnquiryController dccEnquiryController = null;
		ClassPathXmlApplicationContext ctx;
		@Before
		public void init()
		{
			ctx = new ClassPathXmlApplicationContext("spring.xml");
			/*enquiryExchangeService = ctx.getBean("dccEnqService", EnquiryExchangeService.class);
			dccEnquiryController = ctx.getBean("ENQEXG", DccEnquiryController.class);
			Request requestObj = new Request();
			Response responseObj = new Response();
			requestObj.setRequestType("ENQEXG");
			LinkedTreeMap<String, Object> obj = new LinkedTreeMap<String, Object>();
			obj.put("prm","000056690630018");
			requestObj.setRequestObject(obj);
			dccEnquiryController.process(requestObj, responseObj);*/
			
		}
		
		@Test
		public void getEnquiryExchange() throws TCHServiceException{
			EnquiryExchangeServiceImpl enqExcg = (EnquiryExchangeServiceImpl) ctx.getBean("dccEnqService");
			Payment input = new Payment();
			input.setTerminalSerialNumber("16058WL24100894");
			input.setClientId("TC0000000000396");
			input.setDate("161206");
			input.setTime("214007");
			
			try {
				input = enqExcg.service(input);
				assertArrayEquals("Response is not correct.", outputObject(input), input.getDataPrintObject());
			} catch (TCHServiceException e) {
				e.printStackTrace();
			}
			Assert.assertNotNull(input);
		}
		
		
		public DataPrintObject[] outputObject( Payment input){
			List<DataPrintObject> listdpo = new ArrayList<DataPrintObject>();
			DataPrintObject d = new DataPrintObject();
			
			//ISOdccSale dccSale = new ISOdccSale();
			d.setPrintLabel("----------------------------------------------");
			listdpo.add(d);
			
			d = new DataPrintObject();
			d.setPrintLabel("CURR Name   Exchg Rate");
			d.setPrintVal(null);
			listdpo.add(d);
			
			d = new DataPrintObject();
			d.setPrintLabel("----------------------------------------------");
			listdpo.add(d);
			
			d = new DataPrintObject();
			StringBuilder str = new StringBuilder();
			for(int j=0;j<5;j++){
				str.append(" ");
			}
			str.append("USD");
			for(int j=0;j<20;j++){
				str.append(" ");
			}
			d.setPrintLabel(str.toString());
			d.setPrintVal("058.2000");
			listdpo.add(d);
			
			d = new DataPrintObject();
			StringBuilder str1 = new StringBuilder();
			for(int j=0;j<5;j++){
				str1.append(" ");
			}
			str.append("EUR");
			for(int j=0;j<20;j++){
				str1.append(" ");
			}
			d.setPrintLabel(str1.toString());
			d.setPrintVal("080.4400");
			listdpo.add(d);
			
			d = new DataPrintObject();
			d.setPrintLabel("----------------------------------------------");	
			listdpo.add(d);
			DataPrintObject[] dArray = new DataPrintObject[listdpo.size()];
			 return listdpo.toArray(dArray);
			
	    }
		
		
	
}
