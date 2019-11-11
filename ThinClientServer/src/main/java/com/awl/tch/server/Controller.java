package com.awl.tch.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public interface Controller<I,O> {
	public O process(I input);
	public ClassPathXmlApplicationContext getApplicationContext();
}
