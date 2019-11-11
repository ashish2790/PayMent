package com.awl.job.tch.schedular.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.awl.job.tch.schedular.dao.JobConfigDaoImpl;
import com.awl.job.tch.schedular.model.JobEntityDTO;

@Service("jobConfigurationServiceImpl")
public class JobConfigurationServiceImpl implements JobConfigService{
	
	private static final Logger logger = LoggerFactory.getLogger(JobConfigurationServiceImpl.class);
	
	@Autowired
	@Qualifier("jobConfigDaoImpl")
	JobConfigDaoImpl jobDaoImpl;

	@Override
	public List<JobEntityDTO> getjobDetail() {
		logger.debug("Entering in sevice getjobDetail");
		List<JobEntityDTO> listJobEntityDTOs =  jobDaoImpl.getJobDetail();
		logger.debug("Exiting in sevice getjobDetail");
		return listJobEntityDTOs;
	}
	
	
	
}
