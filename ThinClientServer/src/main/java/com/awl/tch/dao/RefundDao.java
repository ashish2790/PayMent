package com.awl.tch.dao;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentDTO;
import com.awl.tch.model.WalletDTO;

public interface RefundDao extends GenericDao<PaymentDTO>{

	public void getTerminalDetails(final Payment input) throws TCHQueryException;
	public PaymentDTO getExistingDTO(String rrn) throws TCHQueryException;
	public PaymentDTO getPaymentDetails(String rrn) throws TCHQueryException;
	public WalletDTO getExistingDTOFc(String rrn,String termSrNo) throws TCHQueryException;
	public void updateSettledPayment(Payment input,String rrn,String refundAppFlag) throws TCHQueryException;
}
