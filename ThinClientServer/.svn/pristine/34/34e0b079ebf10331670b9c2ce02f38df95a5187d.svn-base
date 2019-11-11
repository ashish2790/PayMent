package com.awl.tch.externalEntity;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import org.tempuri.Pos_data;

import com.awl.tch.constant.Constants;
import com.awl.tch.egras.EgrasAdaptorImpl;
import com.awl.tch.egras.EgrasAdptor;
import com.awl.tch.util.AppConfigMaster;
import com.egras.constant.EgrasEnumConstant;


public class TestEgrasWebService {

	ApplicationContext context= null;
	EgrasAdptor egrasAdaptor = null;
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("spring.xml");
		AppConfigMaster.load();
		egrasAdaptor = (EgrasAdaptorImpl) context.getBean(EgrasEnumConstant.EGRAS_ADPTOR); 
	}
	
	@Test
	@Ignore
	public void getGRNWS() throws RemoteException{
		Pos_data pos = null;
		pos = egrasAdaptor.getGRN("", AppConfigMaster.getConfigValue(Constants.TCH_EGRAS, Constants.TCH_URL));
		Assert.notNull(pos);
	}
}
