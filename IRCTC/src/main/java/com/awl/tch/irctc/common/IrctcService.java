package com.awl.tch.irctc.common;

import java.util.Map;

import com.awl.tch.irctc.exception.IRCTCException;

public interface IrctcService<T> {

	public T cosumeWS(Map<String,String> input,T type) throws IRCTCException;
}
