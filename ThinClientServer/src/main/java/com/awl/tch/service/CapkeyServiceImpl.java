package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.CapKey;
import com.awl.tch.bean.CapKey.CapKeyData;
import com.awl.tch.dao.CapkeyDao;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;

@Service("capkeyService")
public class CapkeyServiceImpl extends AbstractServiceImpl implements CapkeyService {

	private static final Logger logger = LoggerFactory.getLogger(CapkeyServiceImpl.class);
	
	@Autowired
	@Qualifier("capKeyDao")
	CapkeyDao capKeyDao;
	
	@Override
	public CapKey service(CapKey input) throws TCHServiceException {
		
		logger.debug("Entering the service CapKey");
		
		try {
			capKeyDao.getCapKey(input);
		} catch (TCHQueryException e) {
			e.printStackTrace();
			logger.debug("Exception in fetching cap keys :" + e.getMessage());
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		}
		
		/*CapKeyData  capKeyData = input.new CapKeyData();
		capKeyData.setPubIndex("08");
		capKeyData.setPubExponent("03");
		capKeyData.setPubModulos("B74670DAD1DC8983652000E5A7F2F8B35DFD083EE593E5BA895C95729"
				+ "F2BADE9C8ABF3DD9CE240C451C6CEFFC768D83CBAC76ABB8FEA58F013C647007CFF7617BAC"
				+ "2AE3981816F25CC7E5238EF34C4F02D0B01C24F80C2C65E7E7743A4FA8E23206A23ECE290C"
				+ "26EA56DB085C5C5EAE26292451FC8292F9957BE8FF20FAD53E5");
		capKeyData.setPubRid("A000000022");
		capKeyData.setCapkeyVersionNumber("1");
		
		input.setCapKeyData(capKeyData);*/
		logger.debug("Exiting the service CapKey");
		
		return input;
		
	}

}
