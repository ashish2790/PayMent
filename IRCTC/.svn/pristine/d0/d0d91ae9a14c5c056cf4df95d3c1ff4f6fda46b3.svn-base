package com.awl.tch.irctc.adaptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.awl.tch.irctc.constant.IrctcConstant;
import com.awl.tch.irctc.exception.IRCTCException;
import com.awl.tch.irctc.service.IrctcSaleConfService;
import com.awl.tch.irctc.service.IrctcSaleConfirmSerivceImpl;
import com.awl.tch.irctc.service.IrctcSaleEnqServiceImpl;
import com.awl.tch.irctc.service.IrctcSaleService;
import com.awl.tch.irctc.service.IrctcVoidService;
import com.awl.tch.irctc.service.IrctcVoidServiceImpl;
import com.tch.irctc.model.SaleConfirm;
import com.tch.irctc.model.SaleEnquiry;
import com.tch.irctc.model.VoidTxn;

public class IrctcAdaptorImpl implements IrctcAdaptor{

	@Autowired
	IrctcSaleService irctcSaleService;
	
	@Autowired
	IrctcSaleConfService irctcSaleConfService;
	
	@Autowired
	IrctcVoidService irctcVoidService;

	
	@Override
	public void invoke(String reqtype,Map<String, String> map,Object type) throws IRCTCException {
		// TODO Auto-generated method stub
		switch (reqtype) {
		case IrctcConstant.TCH_IRCTC_SALE_SERVICE:
			irctcSaleService.cosumeWS(map, (SaleEnquiry)type);
			break;
		case IrctcConstant.TCH_IRCTC_SALE_CONF_SERVICE:
			irctcSaleConfService.cosumeWS(map, (SaleConfirm)type);
			break;
		case IrctcConstant.TCH_IRCTC_SALE_VOID_SERVICE:
			irctcVoidService.cosumeWS(map, (VoidTxn)type);
			break;
		default:
			break;
		}
	}
}
