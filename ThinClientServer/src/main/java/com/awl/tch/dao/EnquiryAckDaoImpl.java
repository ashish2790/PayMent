package com.awl.tch.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.awl.tch.model.PaymentDTO;

@Repository("enquiryAckDaoImpl")
public class EnquiryAckDaoImpl extends GenericDaoImpl<PaymentDTO>{

	private static Logger logger = LoggerFactory.getLogger(EnquiryAckDaoImpl.class);
	
	
	
}
