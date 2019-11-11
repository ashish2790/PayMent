package com.awl.tch.service;

import org.jvnet.hk2.annotations.Service;

import com.awl.tch.util.TerminalParameterCacheMaster;

@Service(name="reloadTerminalParameter")
public class ReloadTerminalParmeterCacheServiceimpl {

	public void reloadTerminalParameter(){
		TerminalParameterCacheMaster.load();
	}
}
