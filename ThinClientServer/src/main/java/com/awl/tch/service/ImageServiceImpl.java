package com.awl.tch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.ImagePrint;
import com.awl.tch.dao.ImageDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.exceptions.TCHServiceException;

@Service("imageService")
public class ImageServiceImpl implements ImageService{

	
	private static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
	
	@Autowired
	@Qualifier("imageDaoImpl")
	ImageDaoImpl imageDaoImpl;
	
	@Override
	public ImagePrint service(ImagePrint input)
			throws TCHServiceException {
		logger.info("Inside sevice impl of image");
		try {
			imageDaoImpl.getBankLogo(input);
		} catch (TCHQueryException e) {
			logger.debug("Exception while fetching image data");
			throw new TCHServiceException(e.getErrorCode(), e.getErrorMessage());
		}
		return input;
	}
}
