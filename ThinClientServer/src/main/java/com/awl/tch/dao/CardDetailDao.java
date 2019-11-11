package com.awl.tch.dao;

import com.awl.tch.bean.Carddetail;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.CardDetailDTO;

public interface CardDetailDao extends GenericDao<CardDetailDTO>{

	public void getCardDetails(final Carddetail cardDetail) throws TCHQueryException;
	
}
