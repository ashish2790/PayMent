package com.awl.job.tch.schedular.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.awl.job.tch.schedular.model.JobEntityDTO;
import com.awl.tch.dao.GenericDaoImpl;
import com.awl.tch.server.TcpServer;

@Repository("jobConfigDaoImpl")
public class JobConfigDaoImpl extends GenericDaoImpl<JobEntityDTO>{
	private static final Logger logger =  LoggerFactory.getLogger(JobConfigDaoImpl.class);
	
	public List<JobEntityDTO> getJobDetail(){
		logger.debug("Entering in get job ");
			
		String sql = "SELECT J_JOB_NAME, J_GROUP_NAME, J_JOB_CLASS_NAME, J_JOB_CRON_EXPR, J_JOB_STATUS, J_IDENTITY_NAME FROM TCH_JOB_INFO";
		
		ApplicationContext ctx = TcpServer.getContext();
		DataSource ds = (DataSource) ctx.getBean("dataSource");
		JdbcTemplate jbt = (JdbcTemplate) ctx.getBean("jdbcTemplate",ds);
		
		List<Map<String,Object>> rows = jbt.queryForList(sql);
		List<JobEntityDTO> list = new ArrayList<JobEntityDTO>();
		if(rows != null){
			for(Map<String,Object> row : rows){
				JobEntityDTO j = new JobEntityDTO();
				j.setJobClassName((String) row.get("J_JOB_CLASS_NAME"));
				j.setJobCronExpression((String) row.get("J_JOB_CRON_EXPR"));
				j.setJobGruopName((String) row.get("J_GROUP_NAME"));
				j.setJobIdentityName((String) row.get("J_IDENTITY_NAME"));
				j.setJobName((String) row.get("J_JOB_NAME"));
				j.setJobStatus((String) row.get("J_JOB_STATUS"));
				list.add(j);
			}
		}
		logger.debug("Number of job :" + list.size());
		logger.debug("Exiting in get job ");
		return list;
		
	}
}
