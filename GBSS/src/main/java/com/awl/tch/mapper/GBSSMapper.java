package com.awl.tch.mapper;

import com.awl.tch.model.GBSSRequest;
import com.awl.tch.model.GBSSRequestFinal;
import com.awl.tch.model.GBSSResponse;
import com.awl.tch.model.GBSSResponseFinal;
import com.google.gson.Gson;

public class GBSSMapper {

	public static String intoJson(GBSSRequest request){
		Gson gson = new Gson();
		return gson.toJson(request);
	}
	
	public static String intoFinalJaso(GBSSRequestFinal request){
		Gson gson = new Gson();
		return gson.toJson(request);
	}
	
	public static GBSSResponse fromJson(String response){
		Gson gson = new Gson();
		return gson.fromJson(response, GBSSResponse.class);
	}

	public static GBSSResponseFinal fromFinalJson(String response){
		Gson gson = new Gson();
		return gson.fromJson(response, GBSSResponseFinal.class);
	}
	
}
