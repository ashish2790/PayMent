package com.awl.tch.adaptor.wallet;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.wallet.common.WalletException;

public interface WalletAdaptor {

	public Payment getSaleEnquiry(final Payment request) throws  TCHServiceException, TCHQueryException;

	public Payment getReversal(Payment reversalRequest) throws TCHServiceException, WalletException;
	
	public Payment processRefundReq(Payment input) throws TCHServiceException, TCHQueryException;
	
}
