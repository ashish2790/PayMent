package com.awl.tch.dao;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentDTO;

public interface ConToInr {
	public PaymentDTO getOriginalSale(final Payment input) throws TCHQueryException;
}
