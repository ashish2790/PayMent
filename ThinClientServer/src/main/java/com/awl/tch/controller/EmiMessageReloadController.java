package com.awl.tch.controller;

import org.springframework.stereotype.Controller;

import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.util.EMIMaster;

@Controller(value="RELOAD_EMI")
public class EmiMessageReloadController extends AbstractController {

	@Override
	public void process(Request requestObj, Response responseObj) {
		EMIMaster.load();
		responseObj.setErrorPresent(false);
	}
}
