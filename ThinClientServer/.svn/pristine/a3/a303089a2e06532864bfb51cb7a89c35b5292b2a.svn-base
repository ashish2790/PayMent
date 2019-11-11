package com.awl.tch.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.Carddetail;
import com.awl.tch.bean.Carddetail.CardDetailData;
import com.awl.tch.dao.CardDetailDao;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.model.CardDetailDTO;

@Service("cardDetailService")
public class CardDetailServiceImpl implements CardDetailService{

	private static final Logger logger  = LoggerFactory.getLogger(CardDetailServiceImpl.class);
	
	@Autowired
	@Qualifier("cardDetailDao")
	CardDetailDao cardDetailDao;
	
	@Override
	public Carddetail service(Carddetail input) throws TCHServiceException {
		
		logger.debug("Entering in service");
		
		//List<CardDetailDTO> listCardDetail =  cardDetailDao.getAll();
		try {
			cardDetailDao.getCardDetails(input);
		} catch (TCHQueryException e) {
			logger.debug("Exception fetching data for card details");
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		}
		
		
		/*input.setCardDetailVersion("0100");
		Carddetail.CardDetailData cardData = input.new CardDetailData();
		cardData.setApplicationId("A0000000031010");
		cardData.setCtlsFloorLimit("0");
		cardData.setCvmLimits("01000");
		cardData.setTargetPercent("0");	
		cardData.setThresholdVal("0");
		cardData.setMaxTargetPercent("0");
		cardData.setNonOdvcTransactionLimit("01000");
		cardData.setOdvcTransactionLimit("01000");
		cardData.setTacDefault("DC4004F800");
		cardData.setTacOffline("DC4000A800");
		cardData.setTacOnline("0010000000");
		
		Carddetail.CardDetailData[] cardDataArray  = new CardDetailData[1];
		cardDataArray[0] = cardData;
		
		input.setCardDetailObject(cardDataArray);*/
		logger.debug("Exiting in service");
		return input;
	}
}
