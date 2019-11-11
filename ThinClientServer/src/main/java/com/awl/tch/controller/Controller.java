package com.awl.tch.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public interface Controller<I,O> {
	public void process(I requestObj,O responseObj);
	public ClassPathXmlApplicationContext getApplicationContext();
}
