package com.awl.tch.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.awl.tch.bean.MenuObject;
import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHServiceException;
import com.awl.tch.server.Request;
import com.awl.tch.server.Response;
import com.awl.tch.service.BrandEmiService;
import com.awl.tch.util.JsonHelper;

@Controller("BRDEMI")
public class BrandEmiController extends AbstractController{


	private static final Logger logger = LoggerFactory.getLogger(BrandEmiController.class);
	
	@Autowired
	@Qualifier("brandEmiService")
	BrandEmiService brandEmiSerivce;
	
	@Override
	public void process(Request requestObj, Response responseObj) {

		logger.debug("Entering in brand emi controller");
		SuperMenuObject menuRequest = null;
		try{
			try {
				menuRequest =  (SuperMenuObject) JsonHelper.getActualObject(requestObj, SuperMenuObject.class);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| IntrospectionException e) {
				logger.error("Exception from JSON to actual object in brand emi :"+e.getMessage(),e);
				Response.setErrorResponseObject(responseObj,ErrorConstants.TCH_Z002,
						"Invalid Json Format");
				return;
			}
			logger.info("Service method call in brand emi controller");
			try {
				// set menu object details in proper way
				if(menuRequest.getMenuObject() != null){
					getActualMenuObject(menuRequest);
				}
				menuRequest  = brandEmiSerivce.service(menuRequest);
			} catch (TCHServiceException e) {
				logger.debug("Exception occured",e);
				Response.setErrorResponseObject(responseObj, e.getErrorCode(),e.getErrorMessage());
				return;
			}
			Response.setResponseObject(responseObj,menuRequest);	
		}catch(Exception e){
			logger.debug("Exception while setting response"+e,e);
			Response.setErrorResponseObject(responseObj,"BR-99","INVALID JSON D");
		}
	}
	
	private void getActualMenuObject(final SuperMenuObject menuRequest){
		List<MenuObject> menuList = new ArrayList(Arrays.asList(menuRequest.getMenuObject()));
		List<MenuObject> newMenuList = new ArrayList<MenuObject>();
		for(MenuObject menu : menuList){
			menu.setCategoryId(Integer.parseInt(menu.getCategoryIdString()));
			menu.setCurrentTableCode(Integer.parseInt(menu.getCurrentTableCodeString()));
			menu.setNextTableCode(Integer.parseInt(menu.getNextTableCodeString()));
			newMenuList.add(menu);
		}
		menuRequest.setMenuObject(newMenuList.toArray(new MenuObject[newMenuList.size()]));
	}
}
