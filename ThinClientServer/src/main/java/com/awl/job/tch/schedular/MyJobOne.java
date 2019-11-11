package com.awl.job.tch.schedular;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.awl.tch.util.EMIMaster;
import com.awl.tch.util.ErrorMaster;

@Service("jobone")
public class MyJobOne implements Job{
	
	private static Logger logger = null;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		logger = (Logger) context.getJobDetail().getJobDataMap().get("logger");
		try{
			/*DataSource ds =  (DataSource) TcpServer.getContext().getBean("dataSource");
			JdbcTemplate jdbcTemplate = (JdbcTemplate) TcpServer.getContext().getBean("jdbcTemplate",ds);*/
			if(EMIMaster.getMap() != null && EMIMaster.getMap().size() == 0)
				EMIMaster.load();
			if(ErrorMaster.getMap() !=null && ErrorMaster.getMap().size() == 0)
				ErrorMaster.load();
			
			/*String query = "SELECT EM_ERROR_CODE FROM TCH_ERROR_MASTER";
			logger.debug("Heart Beat start executing...");
			jdbcTemplate.queryForList(query);*/
		}catch (Exception e){
			logger.debug("Exception while making connection :" + e.getMessage());
		}
		
	}
}
