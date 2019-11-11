package com.awl.tch.service;

import com.awl.tch.exceptions.TCHServiceException;

public interface TCHService<E>{
	public E service(E input) throws TCHServiceException;
}
