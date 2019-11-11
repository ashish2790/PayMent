package com.awl.tch.service;

import java.rmi.RemoteException;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.awl.tch.egras.EgrasAdaptorImpl;

public class TestEgrasServiceImpl {

	ApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("/spring.xml");
	}
	
	@Test
	@Ignore
	public void nonTreasuryService(){
		EgrasAdaptorImpl e = ctx.getBean(EgrasAdaptorImpl.class);
		try {
			Map<String,String> map = e.getNontrasoryInfo("30928214", "728307250209","200", "121017", 'S', "https://egrashry.nic.in");
			for(String str : map.keySet()){
				System.out.print(str +" |");
				System.out.println(map.get(str));
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
