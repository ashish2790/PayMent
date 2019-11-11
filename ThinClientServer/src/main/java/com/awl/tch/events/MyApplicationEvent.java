package com.awl.tch.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import com.awl.tch.util.AppConfigMaster;
import com.awl.tch.util.EMIMaster;
import com.awl.tch.util.ErrorMaster;
import com.awl.tch.util.ISOMessages;
import com.awl.tch.util.TerminalParameterCacheMaster;

public class MyApplicationEvent implements ApplicationListener<MyEvent>{
private static Logger logger  = LoggerFactory.getLogger(MyApplicationEvent.class);

	@Override
	public void onApplicationEvent(MyEvent event) {
		logger.debug("Event occured...");
		ISOMessages.load();
		ErrorMaster.load();
		AppConfigMaster.load();
		EMIMaster.load();
		TerminalParameterCacheMaster.load();
		logger.debug("Event closed...");
	}
}
