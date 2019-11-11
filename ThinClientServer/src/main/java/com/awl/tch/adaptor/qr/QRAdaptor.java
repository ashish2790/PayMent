package com.awl.tch.adaptor.qr;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHServiceException;

public interface QRAdaptor {

	
	/**
	 * Get qr string, transaction uniqId and Qr type from the mobile app server
	 * @param request
	 * 
	 * @return
	 * @throws TCHServiceException
	 */
	public Payment getQRCodeString(final Payment request) throws  TCHServiceException;
	
	
	/**
	 * check status of transction whether it is successful or failed.
	 * And send response accordingly to EDC
	 * 
	 * @param request
	 * @return
	 * @throws TCHServiceException
	 */
	public Payment checkStatus(final Payment request) throws  TCHServiceException;
	
	
	/**
	 * if QR cancelle from terminal send the request to mobile app server and
	 * get response as success or failed based on the input.
	 * Send similar response to EDC
	 * 
	 * @param request
	 * @return
	 * @throws TCHServiceException
	 */
	public Payment qrCancel(final Payment request) throws  TCHServiceException;
	
	
	/**
	 * Refund for QR code transaction.
	 * @param request
	 * @return
	 * @throws TCHServiceException
	 */
	public Payment refundQr(final Payment request) throws TCHServiceException;
	
}
