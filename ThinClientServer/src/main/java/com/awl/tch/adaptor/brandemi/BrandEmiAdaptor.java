package com.awl.tch.adaptor.brandemi;

import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.exceptions.TCHServiceException;

public interface BrandEmiAdaptor {

	/**
	 * Get menu object list from the insight basis on the mid
	 * @param input
	 * @throws TCHServiceException
	 */
	
	public void getMenuDetails(final SuperMenuObject input) throws TCHServiceException;
	
	/**
	 * Get the skucode information from the insight based on the merchant id
	 * and selection of last child node
	 * @param input
	 * @throws TCHServiceException
	 */
	
	public void getSkuCode(final SuperMenuObject input)  throws TCHServiceException;
	
	/**
	 * Get tenure details information from the table based on the 
	 * program code selection
	 * @param input
	 * @throws TCHServiceException
	 */
	
	public void getTenureDetails(final SuperMenuObject input)  throws TCHServiceException;
	
	/**
	 * Send final result to magnus to get apporval for brand emi sale transaction
	 * @param input
	 * @throws TCHServiceException
	 */
	
	public void send(final SuperMenuObject input)  throws TCHServiceException;



	/**
	 * Enquiry about the  offline validation
	 * @param input
	 * @throws TCHServiceException
	 */
	public SuperMenuObject getEnquiry(final SuperMenuObject input) throws TCHServiceException;
	   
}
