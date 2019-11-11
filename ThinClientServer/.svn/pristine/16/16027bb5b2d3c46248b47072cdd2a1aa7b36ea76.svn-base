package com.awl.job.tch.schedular.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.awl.tch.dao.SettlementDaoImpl;
import com.awl.tch.exceptions.TCHQueryException;

public class AutoSettlementJob extends QuartzJobBean{

	private static Logger logger = LoggerFactory.getLogger(AutoSettlementJob.class);
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		SettlementDaoImpl s = (SettlementDaoImpl) context.getJobDetail().getJobDataMap().get("settlementDao");
		logger.debug("Executing auto settlement");
		try {
			//System.out.println("ithe");
			s.doAutoSettlement();
			//System.out.println("mag ithe");
		} catch (TCHQueryException e) {
			e.printStackTrace();
			logger.debug("Exception while executing");
		}
		logger.debug("Done Executing auto settlement");
	   }

}
