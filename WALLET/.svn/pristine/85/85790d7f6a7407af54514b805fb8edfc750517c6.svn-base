package com.awl.tch.wallet.common;

import java.util.HashMap;

import com.awl.tch.wallet.fc.constant.FcConstant;
import com.google.gson.Gson;

public abstract class AbstractWalletService {

	/**
	 * Parse json string and convert the response in respective object 
	 * @param reqType 
	 * @param jsonString
	 * @return Object with  
	 */
	public Object parseGson(String reqType, String jsonString){
		Gson g =  new Gson();
		switch(reqType){
		case FcConstant.TCH_FC_SALE_SERVICE_ENQ:
			return g.fromJson(jsonString, com.awl.tch.wallet.fc.bean.WalletResponseBean.class);
		case FcConstant.TCH_FC_REFUND_SERVICE:
			return g.fromJson(jsonString, com.awl.tch.wallet.fc.bean.WalletResponseBean.class);
		case FcConstant.TCH_FC_REVERSE_SERVICE:
			return g.fromJson(jsonString, com.awl.tch.wallet.fc.bean.WalletResponseBean.class);
		default :
			return null;
		}
	}
	
	/**
	 * Parse json string and convert the response in respective object 
	 * @param reqType 
	 * @param jsonString
	 * @return Object with  
	 */
	public HashMap<String,String>  setHeaderParams(int length) {
		HashMap<String,String> headerMap = new HashMap<String,String>(4);
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");
        headerMap.put("Content-Length", String.valueOf(length));
        return headerMap;
	}
}
