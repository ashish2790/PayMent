/**
 * 
 */
package com.awl.tch.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.bean.MenuObject;

/**
 * @author kunal.surana
 *
 */
public class TestMenuObjectDaoImpl {

	private ApplicationContext context;
	private MenuObjectDao menuObjectDao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("spring.xml");
		menuObjectDao = context.getBean("menuObjectDao",MenuObjectDaoImpl.class);
	}

	/**
	 * Test method for {@link com.awl.tch.dao.MenuObjectDaoImpl#getMenus()}.
	 */
	@Test
	public void testGetMenus() {
		List<MenuObject> menus =  menuObjectDao.getMenus();
		for(MenuObject menu : menus )
		{
			System.out.println(menu);
		}
		Assert.assertTrue(menus.size() > 0);
	}

}
