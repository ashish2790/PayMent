package com.awl.tch.brandemi.validation;

import com.awl.tch.brandemi.util.Constants;

public class LGServiceFactory {

	public static ManufacturerService getLGService(String category)
	{
		switch (category) {
		case Constants.GSM:
			return GsmLGService.getInstance();
		case Constants.NON_GSM:
			return NonGsmLGService.getInstance();
		default:
			break;
		}
		return null;
	}
}
