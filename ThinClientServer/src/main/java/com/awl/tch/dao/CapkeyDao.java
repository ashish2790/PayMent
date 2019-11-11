package com.awl.tch.dao;

import com.awl.tch.bean.CapKey;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.CapKeyDTO;

public interface CapkeyDao extends GenericDao<CapKeyDTO>{

	public int getCapKey(final CapKey capKey) throws TCHQueryException;
}
