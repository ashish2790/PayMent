package com.awl.tch.brandemi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.tch.bean.MenuObject;
import com.awl.tch.brandemi.dao.MenuDaoImpl;
import com.awl.tch.brandemi.model.ChildProduct;
import com.awl.tch.brandemi.model.Menu;
import com.awl.tch.brandemi.model.MenuNode;
import com.awl.tch.brandemi.util.MenuList;

@Service("menuService")
public class MenuServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	@Qualifier("menuDao")
	private MenuDaoImpl menuDao;
	
	public LinkedList<MenuObject> getMenuObject(String mid)
	{
		logger.debug("inside service");
		LinkedList<MenuObject>  menuList = new LinkedList<MenuObject>();
		
		LinkedList<Integer>  tempQueue = new LinkedList<Integer>();
		
		Menu menOutput =  menuDao.getMenuDetails(mid);
		List<Map<String, Object>> menuCategoryList = menuDao.getCategoryManId(mid);
		List<HashMap<String, Object>>  screen1menuList = menOutput.getScreen1();
		List<HashMap<String, Object>>  screen2menuList = menOutput.getScreen2();
		List<HashMap<String, Object>>  screen3menuList = menOutput.getScreen3();
		List<HashMap<String, Object>>  programList = menOutput.getPrograms();
		
		
		HashSet<Integer> schemeSet = new HashSet<Integer>(); 
		ArrayList<ChildProduct> midProducts = new ArrayList<ChildProduct>(); 
		
		for(HashMap<String, Object> program : programList)
		{
			ChildProduct cp = new ChildProduct(((BigDecimal) program.get("CATEGORYID")).intValue(), ((BigDecimal) program.get("CHILDID")).intValue(),
					((BigDecimal) program.get("SCHEMEID")).intValue(), ((BigDecimal) program.get("PRODUCTMANUFACTURERID")).intValue());
			midProducts.add(cp);
			schemeSet.add(((BigDecimal) program.get("SCHEMEID")).intValue());
		}
		int i = 1;
		int screenId = 0;
		
		for(HashMap<String, Object> screen1menu : screen1menuList)
		{
			String catName = (String) screen1menu.get("CATEGORYNAME");
			int id = ((BigDecimal) screen1menu.get("CATEGORYID")).intValue();
			MenuObject menuObj = new MenuObject();
			menuObj.setTableNumber(screenId);
			menuObj.setCurrentTableCode(1);
			if(i <= screen1menuList.size())
			{	
				i++;
				tempQueue.add(i);
				menuObj.setNextTableCode(i);
			}
			menuObj.setDisplayName(catName);
			menuObj.setHeaderName("Select Category");
			menuObj.setId(id);
			menuObj.setCategoryId(id);
			menuList.add(menuObj);
		}
		int temp = 0;
		
		while(temp < screen1menuList.size())
		{
			temp++;
			int currentId = tempQueue.removeFirst();
			screenId++;
			for(HashMap<String, Object> screen2menu : screen2menuList)
			{
				
				String brandName = (String) screen2menu.get("MANUFACTURERNAME");
				int id = ((BigDecimal) screen2menu.get("MANUFACTURERID")).intValue();
				for(Map<String, Object> map : menuCategoryList){
					String manId = ((BigDecimal) map.get("MANUFACTURERID")).toPlainString();
					String catId = ((BigDecimal) map.get("CATEGORYID")).toPlainString();
					if(manId.equals(String.valueOf(id)) && catId.equals(String.valueOf(currentId-1))){
						MenuObject menuObj = new MenuObject();
						menuObj.setTableNumber(screenId);
						menuObj.setCurrentTableCode(currentId);
						if(i <= (screen2menuList.size() + i))
						{	
							i++;
							tempQueue.add(i);
							menuObj.setNextTableCode(i);
						}
						menuObj.setDisplayName(brandName);
						menuObj.setHeaderName("Select Brand");
						menuObj.setId(id); 
						menuList.add(menuObj);
					}
				}
			}
		}
		temp = 0;
		int previousSize = tempQueue.size();
		int x = 0;
		while(temp < previousSize)
		{
			temp++;
			int currentId = tempQueue.removeFirst();
			//i++;
			screenId++;
			//tempQueue.add(i);
			for(HashMap<String, Object> screen3menu : screen3menuList)
			{
				String scheme = (String) screen3menu.get("SCHEMECODE");
				int id = ((BigDecimal) screen3menu.get("SCHEMEID")).intValue();
				if(schemeSet!=null && schemeSet.contains(id))
				{
					MenuObject menuObj = new MenuObject();
					menuObj.setTableNumber(screenId);
					menuObj.setCurrentTableCode(currentId);
					if(i <= (screen3menuList.size() + i))
					{	
						//if(x == 0)
						//{
							x = ++i;
							tempQueue.add(x);
						//}
						
						//logger.debug("added element :"+i);
						menuObj.setNextTableCode(x);
					}
					menuObj.setDisplayName(scheme);
					menuObj.setHeaderName("Select Scheme");
					menuObj.setId(id);
					menuList.add(menuObj);
				}
				MenuNode mn = new MenuNode(-1, id, scheme, "Select Scheme");
			}
		}
		
		if(midProducts != null && midProducts.size() != 0 ){
			Map<Integer,List<MenuNode>> productHeirarchy = menuDao.getMenuHierarchy(getChildIds(midProducts));
			MenuList.getMenu(midProducts, menuList,i,tempQueue, ++screenId , productHeirarchy);
			logger.debug("Menu array size :" + menuList.size());
			/*for(MenuObject menu1 : menuList){
				logger.debug(mid +" "+menu1);
			}*/
		}else{
			logger.debug("No mapping for mid :" + mid);
		}
		return menuList;
	}
	
	private Set<Integer> getChildIds(List<ChildProduct> products)
	{
		Set<Integer> childs = new HashSet<>();
		for(ChildProduct product : products)
		{
			childs.add(product.getChildId());
		}
		return childs;
		
	}

}

