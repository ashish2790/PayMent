package com.awl.tch.dao;

import java.util.List;

import com.awl.tch.bean.Payment;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.PaymentDTO;

public interface SaleDao extends GenericDao<PaymentDTO> {

	public PaymentDTO getExistSaleParamter(final Payment input) throws TCHServiceException, TCHQueryException;
	public List<String> getEmiBin() throws TCHQueryException;
	public Payment getEMIEnquiry(final Payment input) throws TCHQueryException;
}
