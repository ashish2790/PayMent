package com.awl.tch.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.MenuObject;
import com.awl.tch.constant.Constants;
import com.awl.tch.model.MposDTO;

public class TestMposDao {
	
	ApplicationContext ctx;
	@Before
	public void init()
	{
		 ctx = new ClassPathXmlApplicationContext("spring.xml");
		// brandEmiDao = (BrandEmiDaoImpl) ctx.getBean(Constants.TCH_BRAND_EMI_DAO);
	}
	
	@Test
	public void getEnquiry()
	{
		//BrandEmiAdaptorImpl brandEmiAdapterImpl = (BrandEmiAdaptorImpl) ctx.getBean("brandEmiAdaptor");
		
		MposDaoImpl mposDao=(MposDaoImpl)ctx.getBean(Constants.TCH_MPOS_DAO);
		MposDTO mpos=new MposDTO();
		mpos.setReqType("ENQUIRY");
		mpos.setAmount("");
		mpos.setBankRefNo("YYYYMMDD00036699");
		mpos.setDateTime("02082017");
		//mpos.setEncData(encData);
		//mpos.setEncRequestVal(encRequestVal);
		//mpos.setResMap(resMap);
		mpos.setStatus("Failure");
		mpos.setStatusCode("POS003");
		mpos.setStatusDescription("No transaction found.");
		mpos.setTransactionStatus("");
		mposDao.save(mpos);
		


		}
}
