package com.awl.tch.dao;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentAuthDTO;

public interface SaleCompDao extends GenericDao<PaymentAuthDTO>{

	public void validationForPreauthComp(final Payment input) throws TCHServiceException, TCHQueryException;
	
	public PaymentAuthDTO getDtoBasedOnRequestRRN(final Payment input) throws TCHQueryException;
}
