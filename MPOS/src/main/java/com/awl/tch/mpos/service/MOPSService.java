package com.awl.tch.mpos.service;

import com.awl.tch.mpos.bean.MOPSBean;
import com.awl.tch.mpos.bean.MOPSResponse;
import com.awl.tch.mpos.exception.SbiMopsException;

public interface MOPSService {

	public MOPSResponse getAmount(MOPSBean sbiReq, String url) throws SbiMopsException;

	public MOPSResponse updateStatus(MOPSBean sbiReq, String url) throws SbiMopsException;

	//public SbiMopsResponse getAmount(SbiMopsResponse sbiRes, String url) throws JSONException;

}
