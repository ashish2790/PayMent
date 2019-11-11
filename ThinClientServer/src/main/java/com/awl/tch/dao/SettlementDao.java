package com.awl.tch.dao;

import com.awl.tch.bean.Settlement;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.PaymentSettleDTO;

public interface SettlementDao extends GenericDao<PaymentSettleDTO>{
	
	public void getSettlementDetails(final Settlement input) throws TCHQueryException;
	public void doAutoSettlement() throws TCHQueryException;
	public void getSettlementDetailsNew(final Settlement input) throws TCHQueryException;
	
}
