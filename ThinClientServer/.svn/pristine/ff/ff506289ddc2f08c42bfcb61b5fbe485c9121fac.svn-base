package com.awl.tch.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.model.HandshakeDTO;

public class TestHandshakeDaoImpl {
	
	HandshakeDaoImpl handshakeDAO = null;
	
	
	@Before
	public void init()
	{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		handshakeDAO = ctx.getBean("handshakeDAO", HandshakeDaoImpl.class);
	}
	
	@Test
	@Ignore
	public void testSave()
	{
			HandshakeDTO h = new HandshakeDTO();
		
			int returnValue = handshakeDAO.save(h);
			Assert.assertEquals(1, returnValue);
	}
	
	@Test
	@Ignore
	public void testUpdate()
	{
			HandshakeDTO h = new HandshakeDTO();
			int returnValue = handshakeDAO.update(h);
			Assert.assertEquals(1, returnValue);
	}
	
	@Test	
	@Ignore
	public void testGet()
	{
			int  id = 2 ;
			HandshakeDTO h = handshakeDAO.getById(id);
			System.out.println(h);
			Assert.assertNotEquals(null, h);
	}
	
	@Test
	@Ignore
	public void testGetAll()
	{
			List<HandshakeDTO>  list = handshakeDAO.getAll();
			Assert.assertNotEquals(null, list);
			Assert.assertEquals(2, list.size());
			for(HandshakeDTO hs : list)
			{
				System.out.println(hs);
				
			}
	}
	
	@Test
	@Ignore
	public void testDelete()
	{
		int  id = 2 ;
		int returnValue = handshakeDAO.deleteById(id);
		Assert.assertEquals(1, returnValue);

	}

}
