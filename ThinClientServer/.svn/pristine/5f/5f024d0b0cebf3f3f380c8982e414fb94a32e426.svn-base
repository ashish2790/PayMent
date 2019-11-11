package com.awl.tch.dao;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentDTO;

public interface ReversalDao extends GenericDao<PaymentDTO>{

	public PaymentDTO checkForActualTxn(final Payment input) throws TCHQueryException;
}
