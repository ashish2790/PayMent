
package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.dao.EnquiryDaoImpl;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.mpos.service.MPOSServiceImpl;
/*import com.awl.tch.irctc.common.IrctcService;
import com.awl.tch.irctc.exception.IRCTCException;
import com.awl.tch.irctc.service.IrctcSaleEnqServiceImpl;
import com.awl.tch.irctc.service.IrctcSaleService;*/
//import com.tch.irctc.model.SaleEnquiry;
//import com.awl.tch.dao.IrctcDaoImpl;
//import com.awl.tch.model.IrctcDTO;

@Service("enquiryServiceImpl")
public class EnquiryServiceImpl extends AbstractServiceImpl implements TCHService<Payment>{

	@Autowired
	@Qualifier(Constants.TCH_SBI_SERVICEIMPL)
	MPOSServiceImpl sbiMposService;
	
	@Autowired
	@Qualifier(Constants.TCH_ENQUIRY_DAO)
	EnquiryDaoImpl enquiryDaoImpl;
	
	private static Logger logger = LoggerFactory.getLogger(EnquiryServiceImpl.class);

	@Override
	public Payment service(Payment input) throws TCHServiceException {
		
		logger.debug("Inside the service of enq");
		String appName = input.getAppName().trim();
		logger.debug("App name :[" + appName +"]");
		
		logger.debug("Reference value :["+input.getReferenceValue()+"]");
		
		setProxy(appName);
		
		switch(appName){
		case Constants.TCH_AAB:
			input = aabService.getAmount(input);
			break;
			
		case Constants.TCH_EGRAS:
			input = egrasService.getAmount(input);
			break;
			
		case Constants.TCH_SBIEPAY:
			input = epayService.getAmount(input);
			break;
			
		case Constants.TCH_IRCTC :
			input = irctcService.getAmount(input);
			break;
			
		case Constants.TCH_MPOS:
			input = mopsService.getAmount(input);
			break;
		case Constants.TCH_GBSS:
			 input = gbssService.getAmount(input);
			
			break;
		case Constants.TCH_AXIS_EPAY:
			input = axisEpayService.getAmount(input);
			break;
			
		default :logger.info("inside switch condition of default" );
			break;
		}
		//input.setOriginalAmount("1234");
		input.setCustomerName(null);
		logger.debug("Exiting the service of enq");
		return input;
	}

	
}
