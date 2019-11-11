package com.awl.tch.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.awl.tch.model.BrandEMIDTO;

public class TestKalkulasEMiInfo {

	ApplicationContext ctx ;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("/spring.xml");
	}
	
	@Test
	public void savekalkulasData(){
		KalkulaDao k = ctx.getBean(KalkulaDaoImpl.class);
		BrandEMIDTO dto = new BrandEMIDTO();
		dto.setBrandName("yes");
		JdbcTemplate jd = (JdbcTemplate) ctx.getBean("kalkulasJdbcTemplate");
		k.save(dto,jd);
		Assert.notNull(k);
	}
}
