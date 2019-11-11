package com.awl.tch.brandemi.service;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.MenuObject;
import com.awl.tch.bean.SuperMenuObject;
import com.awl.tch.brandemi.dao.MenuDaoImpl;
import com.awl.tch.brandemi.dao.MerchantDaoImpl;
import com.awl.tch.brandemi.dao.ProductDaoImpl;
import com.awl.tch.brandemi.model.ValidationParameter;
import com.awl.tch.brandemi.util.Constants;
import com.awl.tch.brandemi.util.HelperUtil;
import com.awl.tch.brandemi.validation.ManufacturerService;
import com.awl.tch.brandemi.validation.ManufacturerServiceFactory;

@Service("brandEMIService")
public class BrandEMIService {
	
	private static final Logger logger = LoggerFactory.getLogger(BrandEMIService.class);
	@Autowired
	@Qualifier("menuService")
	private MenuServiceImpl menuService;
	
	@Autowired
	@Qualifier("menuDao")
	private MenuDaoImpl menuDao;
	
	@Autowired
	@Qualifier("productDao")
	private ProductDaoImpl productDao;
	
	@Autowired
	@Qualifier("merchantDao")
	private MerchantDaoImpl merchantDao;
	
	static{
		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        /*System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");*/
	}
	
	
	public  LinkedList<MenuObject> reloadMenuObject()
	{
		LinkedList<MenuObject> menuList = null;
		List<String> midList = menuDao.getListOfMid();
		/*while(mids.hasNext())
		{
			String mid = mids.next();
			menuList = menuService.getMenuObject(mid);
			CacheService.add(mid, menuList);
			logger.info("Cache reloaded for mid:"+mid);
		}*/
		
		for(String mid : midList){
			try{
				menuList = menuService.getMenuObject(mid);
				CacheService.add(mid, menuList);
				logger.info("Cache reloaded for mid:"+mid);
			}catch(Exception e){
				logger.debug("Exception for mid "+ mid,e);
			}
		}
		return menuList;
	}
	
	public  LinkedList<MenuObject> reloadMenuObject(String mid)
	{
		LinkedList<MenuObject> menuList = null;
		menuList = menuService.getMenuObject(mid);
		CacheService.add(mid, menuList);
		logger.info("Cache reloaded for mid:"+mid);
		return menuList;
	}
	//@Cacheable(value = "menuObject", key = "#mid")
	public  LinkedList<MenuObject> getMenuObject(String mid)
	{
		logger.info("MID  from thinclient :"+mid);
		LinkedList<MenuObject> menuList = CacheService.get(mid);
		if(menuList!=null)
			return menuList;
		else
		{
			menuList = menuService.getMenuObject(mid);
			CacheService.add(mid, menuList);
			logger.info("Cache loaded for mid:"+mid);
			return menuList;
		}
	}
	
	@SuppressWarnings("unchecked")
	public  LinkedList<MenuObject> getSKUCodeMenu(String mid,SuperMenuObject superMenuObj )
	{
		MenuObject[] selectedMenu = superMenuObj.getMenuObject();  
		logger.info("Menu Object from thinclient :"+selectedMenu);
		Integer ids[] = null;
		try{
			ids = CacheService.getActualIds(mid, selectedMenu);
		}catch(Exception e){
			logger.debug("Exception occur .. again reloading cache");
			reloadMenuObject(mid);
			ids = CacheService.getActualIds(mid, selectedMenu);
		}
		if(ids!=null)
		{
			logger.debug("Manufaturer name : " +selectedMenu[1].getDisplayName());
			logger.info("Bin Number from SuperMenuObject : "+superMenuObj.getBinNumber());
			String[] skuCodes = productDao.getSKUDetails(mid,superMenuObj.getBinNumber(),selectedMenu[1].getDisplayName(), ids);
			LinkedList<MenuObject> skuMenuList = new LinkedList<>();
			for(String skuCode : skuCodes)
			{
				MenuObject skuMenu =  new MenuObject(0,1,0,skuCode,"Select SKU Code");
				skuMenuList.add(skuMenu);
			}
			return skuMenuList;
		}
		else{
			logger.debug("No mapping for given mid " + mid);
			return (LinkedList<MenuObject>) Collections.EMPTY_LIST;
		}
	}
	
	@SuppressWarnings("unchecked")
	public  boolean getSKUCodeMenu_WithoutBin(String mid,SuperMenuObject superMenuObj )
	{
		MenuObject[] selectedMenu = superMenuObj.getMenuObject();  
		logger.info("Menu Object from thinclient :"+selectedMenu);
		Integer ids[]  = CacheService.getActualIds(mid, selectedMenu);
		if(ids!=null)
		{
			logger.debug("Manufaturer name : " +selectedMenu[1].getDisplayName());
			logger.info("Bin Number from SuperMenuObject : "+superMenuObj.getBinNumber());
			String[] skuCodes = productDao.getSKUDetails(mid,selectedMenu[1].getDisplayName(), ids);
			if(skuCodes != null && skuCodes.length > 0){
				logger.debug("SKU CODE present but not mapped to present bin");
				logger.debug("SKU CODEs" + skuCodes);
				return true;
			}else{
				logger.debug("NO SKU CODE MAPPED");
			}
		}
		return false;
	}
	
	public  String[] getProductDetails(String mid,SuperMenuObject superMenuObject) throws Exception
	{
		MenuObject[] selectedMenu =  superMenuObject.getMenuObject();
		logger.info("Menu Object from thinclient :"+selectedMenu);
		Integer ids[] = CacheService.getActualIds(mid, selectedMenu);
		logger.debug("Bin value: "+ superMenuObject.getBinNumber());
		String binValue = superMenuObject.getBinNumber() !=null ? superMenuObject.getBinNumber().substring(0,6) : "";
		if(ids!=null)
		{
			logger.debug("Manufaturer name : " +selectedMenu[1].getDisplayName());
			String[] productDetails = productDao.getProductDetails(mid, selectedMenu[1].getDisplayName(),superMenuObject.getSkuCode(),binValue,ids);
			return productDetails;
		}
		else
			throw new Exception("Index mismatched with Cache"); 
	}
	
	public  String[] getNoEmiCashbackDetails(String mid,MenuObject[] selectedMenu) throws Exception
	{
		logger.info("Menu Object from thinclient :"+selectedMenu);
		Integer ids[] = CacheService.getActualIds(mid, selectedMenu);
		if(ids!=null)
		{
			String[] productDetails = productDao.getNoEmiCashbackDetails(mid, ids);
			return productDetails;
		}
		else
			throw new Exception("Index mismatched with Cache"); 
	}
	
	public  ValidationParameter getValidationParameter(String mid,MenuObject[] selectedMenu)
	{
		logger.info("Menu Object from thinclient :"+selectedMenu);
		Integer ids[] = CacheService.getActualIds(mid, selectedMenu);
		return merchantDao.getValidationParameter(mid, ids);
	}
	
	public  String blockSerialNumber(String manufacturerName, HashMap<String,String> config, String... args) throws MalformedURLException
	{
		ManufacturerService  service =  ManufacturerServiceFactory.getManufacturerService(manufacturerName,args);
		if(service==null)
			return HelperUtil.generateResponse(Constants.FALSE, "BLOCK FAILED");
		return service.blockSerialNumber(config, args);
	}
	
	public  String unblockSerialNumber(String manufacturerName, HashMap<String,String> config, String... args) throws MalformedURLException
	{
		ManufacturerService  service =  ManufacturerServiceFactory.getManufacturerService(manufacturerName,args);
		if(service==null)
			return HelperUtil.generateResponse(Constants.FALSE, "UNBLOCK FAILED");
		return service.unblockSerialNumber(config, args);
	}
	
	public  String getDealerCode(String mid, MenuObject[] selectedMenu)
	{
		logger.info("Menu Object from thinclient :"+selectedMenu);
		Integer ids[] = CacheService.getActualIds(mid, selectedMenu);
		return merchantDao.getDealerCode(mid,ids[1]);
	}
	
	public  String[] getManufacturerDetails(String mid,MenuObject[] selectedMenu)
	{
		logger.info("MID from thinclient :"+mid);
		Integer ids[] = CacheService.getActualIds(mid, selectedMenu);
		return merchantDao.getManufacturerDetails(mid,ids[1]);
	}
	
	
	public boolean isBinSupported(String mid,String binValue){
		logger.debug("Checking bin supported or not");
		binValue = binValue.substring(0,6);
		logger.debug("Mid Value : " + mid);
		logger.debug("Bin Value : " + binValue);
		return menuDao.isBinSupport(mid,binValue);
	}
	
	@PostConstruct
	public void initIt() throws Exception {
		//getMenuObject("000112456560060");
		//getMenuObject("000056690630018");
		//getMenuObject("051378205500055");
	}
}
