package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.awl.tch.adaptor.brandemi.BrandEmiAdaptor;
import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.dao.BrandEmiSerialNumberDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.util.ErrorMaster;

@Component("brandEmiService")
public class BrandEmiService extends AbstractServiceImpl implements TCHService<SuperMenuObject>{

	@Autowired
	@Qualifier(Constants.TCH_BRAND_EMI_ADAPTOR)
	BrandEmiAdaptor brandEmiAdaptor;
	
	@Autowired
	@Qualifier(Constants.TCH_BRAND_EMI_SERIAL_DAO)
	BrandEmiSerialNumberDaoImpl brandEmiSerial;
	
	private static Logger logger = LoggerFactory.getLogger(BrandEmiService.class);
	
	@Override
	public SuperMenuObject service(SuperMenuObject input) throws TCHServiceException {
		
		logger.debug("Entering brand emi service..");
		try {
			logger.debug("Getting mid..");
			input.setMerchantId(brandDao.getMid(input.getTerminalSerialNumber()));
			if(input.getMerchantId() == null || (input.getMerchantId() != null && input.getMerchantId().isEmpty())){
				throw new TCHServiceException(ErrorConstants.TCH_BR02, ErrorMaster.get(ErrorConstants.TCH_BR02));
			}
		} catch (TCHQueryException e1) {
			throw new TCHServiceException(ErrorConstants.TCH_BR02, ErrorMaster.get(ErrorConstants.TCH_BR02));
		}
		
		switch(input.getDecisionFlag() != null ? input.getDecisionFlag() : ""){
		case Constants.TCH_BRDSKU:
			//throw new TCHServiceException("BR-99", "UPDATE AVAILABLE. DOWNLOAD FRAMES?");
			brandEmiAdaptor.getSkuCode(input);
			break;
		case Constants.TCH_BRDTNR:
			brandEmiAdaptor.getTenureDetails(input);
			break;
		case Constants.TCH_BRDSALE:
			brandEmiAdaptor.send(input);
			break;
		case Constants.TCH_BRDENQ:
			input = brandEmiAdaptor.getEnquiry(input);
			break;
		default:
			brandEmiAdaptor.getMenuDetails(input);
		}
		input.setCustomerName(null);
		logger.debug("Exiting brand emi service..");
		return input;
	}
	
}