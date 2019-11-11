package com.awl.tch.dao;

import org.springframework.stereotype.Repository;

import com.awl.tch.model.LoggingDTO;

@Repository("loggingDao")
public class LoggingDaoImpl extends GenericDaoImpl<LoggingDTO> implements LoggingDao {
	
}
