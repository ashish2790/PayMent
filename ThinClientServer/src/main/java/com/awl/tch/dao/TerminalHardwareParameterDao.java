package com.awl.tch.dao;

import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.TerminalHardwareParameterDTO;

public interface TerminalHardwareParameterDao extends GenericDao<TerminalHardwareParameterDTO>{

	public TerminalHardwareParameterDTO getMidTid(String terminalSerialNumber) throws TCHQueryException;
}
