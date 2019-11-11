package com.awl.tch.externalentityImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.BillingObj;
import com.awl.tch.bean.Payment;
import com.awl.tch.bridge.ExternalEntityBridge;
import com.awl.tch.bridge.ExternlaEntityInterface;
import com.awl.tch.constant.Constants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.SbiepayDTO;
import com.tch.sbiepay.exception.TCHSbiepayException;
import com.tch.sbiepay.model.SBIepaEntity;
import com.tch.sbiepay.service.SBIepayService;

@Service(Constants.TCH_EPAY_BRIDGE)
public class EpayBridgeServiceImpl extends ExternalEntityBridge implements ExternlaEntityInterface<Payment>{

	private static Logger logger = LoggerFactory.getLogger(EpayBridgeServiceImpl.class);
	@Override
	public Payment updateStatus(Payment input) throws TCHServiceException {
		logger.debug("ACK to sbi epay");
		logger.debug("Fetched url from DB ->" +getUtilityInfo(input).get(Constants.TCH_URL));
		SBIepaEntity sbiepay = new SBIepaEntity();
		sbiepay.setUrl(getUtilityInfo(input).get(Constants.TCH_URL));
		sbiepay.setAggregatorId("SBIEPAY");
		sbiepay.setATRN(input.getReferenceValue());
		sbiepay.setUniqueRefNumber(input.getRetrivalRefNumber());
		sbiepay.setAmount(new BigDecimal(input.getOriginalAmount()).movePointLeft(2).toPlainString());
		sbiepay.setStatus(Constants.TCH_Y);
		sbiepay.setStatusDescription("Payment transferred.");
		try{
			SBIepayService.setStatus(sbiepay);
		}catch(TCHSbiepayException e){
			throw new TCHServiceException(e.getErrorCode(), e.getErroMessage());
		}
		return input;
	}

	@Override
	public Payment getAmount(Payment input) throws TCHServiceException {
		logger.debug("inside codition for SBIEPAY");
		SBIepaEntity sbiepay = new SBIepaEntity();
		//sbiepay.setUrl(uDto.getUrl());
		sbiepay.setUrl(getUtilityInfo(input).get(Constants.TCH_URL));
		sbiepay.setATRN(input.getReferenceValue());
		//input.setOriginalAmount("10000");
		SbiepayDTO sbiDto = new SbiepayDTO();
		sbiDto.setAtrn(input.getReferenceValue());
		try {
			input.setOriginalAmount(SBIepayService.getAmount(sbiepay));
			sbiDto.setAmount(input.getOriginalAmount());
			logger.debug("Merchant name [" + sbiepay.getMerchantName() +"] Amount [" + sbiepay.getAmount() +"]");
			List<BillingObj> lstBillingSBIEapy = new ArrayList<BillingObj>();
			BillingObj sbiEpayBlg = new BillingObj();
			sbiEpayBlg.setLabelName(getUtilityInfo(input).get(Constants.TCH_SBIEPAY_LABEL1));
			sbiEpayBlg.setLabelValue(input.getReferenceValue());
			lstBillingSBIEapy.add(sbiEpayBlg);
			sbiEpayBlg = new BillingObj();
			
			sbiEpayBlg.setLabelName(getUtilityInfo(input).get(Constants.TCH_SBIEPAY_LABEL2));
			sbiEpayBlg.setLabelValue(sbiepay.getMerchantName());
			lstBillingSBIEapy.add(sbiEpayBlg);
			BillingObj[] blgObjArr1 = new BillingObj[lstBillingSBIEapy.size()];
			input.setBillingObject(lstBillingSBIEapy.toArray(blgObjArr1)); // set billing object
			logger.debug("payment processor" + sbiepay.getPaymentProcessor());
			sbiDto.setPaymentProcessor(sbiepay.getPaymentProcessor());
			epayDao.save(sbiDto);
			
		} catch (TCHSbiepayException e1) {
			throw new TCHServiceException(e1.getErrorCode(),e1.getErroMessage());
		}
		return input;
	}

}
