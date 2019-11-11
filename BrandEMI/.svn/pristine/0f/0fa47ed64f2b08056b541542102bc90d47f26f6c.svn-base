package com.awl.tch.brandemi.service;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.MenuObject;

public class TestMenuService {

	private static final Logger logger = LoggerFactory.getLogger(TestMenuService.class);
	
	private ApplicationContext context;
	
	@Before
	public void setUp()
	{
		context = new ClassPathXmlApplicationContext("/spring-brandemi.xml");
	}
	
	@Test
	public void testGetMenuObject() {
		MenuServiceImpl menuService =  (MenuServiceImpl) context.getBean("menuService");
		LinkedList<MenuObject> list = menuService.getMenuObject("075120053903129");
		//LinkedList<MenuObject> list = menuService.getMenuObject("000001005000009");
		for(MenuObject menuObj : list )
		{
			System.out.println(menuObj);
		}
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	
}
