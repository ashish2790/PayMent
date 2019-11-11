package com.awl.tch.dao;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.WalletDTO;
import com.awl.tch.wallet.fc.model.FcWalletRequest;

public interface WalletDao {

	public WalletDTO getExistingWalletDetails(String txnRefNumber) throws TCHQueryException;

	public FcWalletRequest getDetailsByInvoiceNumber(String rrn) throws TCHQueryException;
	
	public WalletDTO getDetailsByRequestType(String reqType, String txnRefNumber) throws TCHQueryException;
	
	public void getBatchNumber(Payment input) throws TCHQueryException;

}
