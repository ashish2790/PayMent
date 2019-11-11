package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.Settlement;
import com.awl.tch.constant.Constants;
import com.awl.tch.dao.PreAuthDaoImpl;
import com.awl.tch.dao.SettlementDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;

@Service("settlementServiceImpl")
public class SettelmentSeviceImpl extends AbstractServiceImpl implements SettelementService{

	private static final Logger logger  = LoggerFactory.getLogger(SettelmentSeviceImpl.class);
	
	@Autowired
	@Qualifier("settlementDao")
	SettlementDaoImpl settlemetDaoImpl;
	
	@Autowired
	@Qualifier("preAuthDaoImpl")
	PreAuthDaoImpl preauthDaoimpl;
	
	@Override
	public Settlement service(Settlement input) throws TCHServiceException {
		
		try {
			if(input.getHostId() == null){
				settlemetDaoImpl.getSettlementDetails(input);
			}else if(Constants.TCH_AMEX.equals(input.getHostId())){
				logger.debug("host id:" + input.getHostId());
				settlemetDaoImpl.getSettlementDetailsNew(input);
			}
		} catch (TCHQueryException e) {
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		}
		
		return input;
	}
}
