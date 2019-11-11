package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.constant.Constants;
import com.awl.tch.dao.BrandEmiSerialNumberDaoImpl;
import com.awl.tch.model.BrandEMIDTO;

@Component(Constants.TCH_BRAND_ENQ_SERVICE)
public class BrandEmiEnqServiceImpl implements BrandEmiEnqService{

	private static Logger logger = LoggerFactory.getLogger(BrandEmiEnqServiceImpl.class);
	
	@Autowired
	BrandEmiSerialNumberDaoImpl brandEmiSerialDao;
	
	@Override
	public void getProductDetails(SuperMenuObject input) {
		logger.debug("getting product details");
		BrandEMIDTO emiObject = brandEmiSerialDao.getById("B_REF_VALUE,B_SERIAL_NUMBER",input.getReferenceValue(), input.getIMIENumber());
		
		logger.debug("fetched information :" + emiObject);
		
	}

	
}
