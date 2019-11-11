package com.awl.tch.controller;

import org.springframework.stereotype.Controller;

import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.util.ErrorMaster;

@Controller(value="RELOAD_ERR")
public class ErrorReloadController extends AbstractController{

	@Override
	public void process(Request requestObj, Response responseObj) {
		ErrorMaster.load();
	}

}
