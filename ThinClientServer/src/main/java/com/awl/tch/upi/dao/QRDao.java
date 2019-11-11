package com.awl.tch.upi.dao;

import com.awl.tch.bean.Payment;


public interface QRDao {
	
	/**
	 * Set the details in TCH_Payment_txn and save information in qr deetails table too.
	 * Get the batch number for terminal
	 * @param input
	 * @return
	 */
	public void qrCodeDetails(final Payment input);
}
