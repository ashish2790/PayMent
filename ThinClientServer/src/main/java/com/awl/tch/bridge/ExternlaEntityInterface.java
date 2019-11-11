package com.awl.tch.bridge;

import com.awl.tch.exceptions.TCHServiceException;

public interface ExternlaEntityInterface<T> {
	
	/**
	 * Get amount from web service provided by vendor.
	 * Need to do manipulation in amount based on the response from vendor service
	 * @param input
	 * @return model value based on vendor service response
	 * @throws TCHServiceException
	 */
	public abstract T getAmount(T input) throws TCHServiceException;
	
	
	/**
	 * Update status after successful transaction at WL to vendot web service.
	 * @param input
	 * @return  model value based on vendor service response
	 * @throws TCHServiceException
	 */
	public abstract T updateStatus(T input) throws TCHServiceException;
}
