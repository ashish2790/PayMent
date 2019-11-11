package com.awl.job.tch.schedular;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import com.awl.job.tch.schedular.dao.JobLogsDaoImpl;
import com.awl.job.tch.schedular.model.JobLogsEntityDTO;

public class MyJobListener implements JobListener {

	
	JobLogsDaoImpl jobLogsDaoImpl;
	
	public void jobToBeExecuted(JobExecutionContext context) {
		System.out.println("Job to be exected: " + context.getJobDetail().getJobDataMap().getString("jobName") + ", job listener: " + getName());
		
		JobLogsEntityDTO j = new JobLogsEntityDTO();
		j.setJobName(context.getJobDetail().getJobDataMap().getString("jobName"));
		jobLogsDaoImpl = (JobLogsDaoImpl) context.getJobDetail().getJobDataMap().get("jobDaoImpl");
		Long sequenceNumber = jobLogsDaoImpl.getSequence("SEQ_TC_JOB_ID"); 
		j.setId(sequenceNumber);
		j.setStatus("FAIL");
		jobLogsDaoImpl.save(j);
		context.put("logsDetails", j);
	}

	public void jobExecutionVetoed(JobExecutionContext context) {
	}

	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		System.out.println("Job was executed: " + context.getFireInstanceId() + ", job listener: " + getName());
		JobLogsEntityDTO j = (JobLogsEntityDTO) context.get("logsDetails");
		j.setProcessingTime(context.getJobRunTime());
		jobLogsDaoImpl =(JobLogsDaoImpl) context.getJobDetail().getJobDataMap().get("jobDaoImpl");
		j.setStatus("SUCCESS");
		jobLogsDaoImpl.update(j);
	}

	public String getName() {
		return "MyJobListener";
	}

}
