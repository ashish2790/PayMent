package com.awl.job.tch.schedular.dao;

import org.springframework.stereotype.Repository;

import com.awl.job.tch.schedular.model.JobLogsEntityDTO;
import com.awl.tch.dao.GenericDaoImpl;

@Repository("jobLogsImpl")
public class JobLogsDaoImpl extends GenericDaoImpl<JobLogsEntityDTO>{

	
	public int save(JobLogsEntityDTO j){
		return super.save(j);
	}
	
}
