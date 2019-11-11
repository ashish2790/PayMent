package com.awl.tch.brandemi.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class TestMenuObjectdao {

private ApplicationContext context;
	
	@Before
	public void init()
	{
		context = new ClassPathXmlApplicationContext("/spring-brandemi.xml");
	}
	
	@Test
	public void getMidList(){
		MenuDaoImpl menudao = (MenuDaoImpl) context.getBean("menuDao");
		List<String> listMid = menudao.getListOfMid();
		for(String str : listMid)
			System.out.println(str);
		Assert.notEmpty(listMid);
	}
}
