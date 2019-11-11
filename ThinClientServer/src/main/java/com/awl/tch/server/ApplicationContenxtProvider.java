package com.awl.tch.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContenxtProvider {

	  private static ClassPathXmlApplicationContext context;
	 
	    public static ClassPathXmlApplicationContext getApplicationContext() {
	    	if (null==context){
	    		 context =new ClassPathXmlApplicationContext("spring.xml"); 
	    	}
	        return context;
	    }

	   
	

}
