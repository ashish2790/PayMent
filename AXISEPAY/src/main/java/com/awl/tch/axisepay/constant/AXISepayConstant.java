package com.awl.tch.axisepay.constant;


public interface AXISepayConstant {

	
	//final String TCH_AXIS_CHECKSUM = AXISepayHelper.Property.getChecksumKey();
	
	final String TCH_AXIS_CHECKSUM = "aX!5";
	
	final String TCH_AXIS_SUCCESS_CODE_000 = "000";
	
	final String TCH_AXIS_SUCCESS_CODE_101 = "101";
	
	final String TCH_AXISEPAY_PARAMETER_VALUE ="i=";
	
	//final String TCH_AXIS_TRANSACTION_DETAILS ="/easypay2.0/frontend/pos/enquiry";
	//Production URL
	final String TCH_AXIS_TRANSACTION_DETAILS ="/index.php/pos/enquiry";
	
	//final String TCH_AXIS_SALE_COMPELETION ="/easypay2.0/frontend/pos/confirmation";
	//Production URL
	final String TCH_AXIS_SALE_COMPELETION ="/index.php/pos/confirmation";
	//Production URL
	//final String TCH_AXIS_SALE_DECLINE ="/easypay2.0/frontend/pos/fail";
	final String TCH_AXIS_SALE_DECLINE ="index.php/pos/fail";
	
	//Production URL
	//final String TCH_AXIS_TRANSACTION_ENQ ="/easypay2.0/frontend/pos/tranenq";
	final String TCH_AXIS_TRANSACTION_ENQ ="/index.php/pos/tranenq";
	
	final String TCH_AXISEPAY_BOOKED = "BOOKED";
	
	final String TCH_AXISEPAY_EXPIRED = "EXPIRED";
	
	final String TCH_AXISEPAY_PROCESSED = "PROCESSED";
	
}