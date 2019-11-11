package com.awl.job.tch.schedular;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.awl.job.tch.schedular.dao.JobLogsDaoImpl;
import com.awl.job.tch.schedular.model.JobEntityDTO;
import com.awl.job.tch.schedular.service.JobConfigService;
import com.awl.tch.dao.SettlementDao;

@Component("assignTask")
public class AssignTask implements Runnable{
	
	SchedulerFactory schedFact;
	
	@Autowired
	@Qualifier("jobConfigurationServiceImpl")
	JobConfigService jobConfigService;
	
	@Autowired
	@Qualifier("settlementDao")
	SettlementDao settlementDao;
	
	@Autowired
	@Qualifier("jobLogsImpl")
	JobLogsDaoImpl jobLogsDaoImpl;
	
	public AssignTask() {
		load();
	}
	
	/**
	 * @param schedFact the schedFact to set
	 */
	public void setSchedFact(SchedulerFactory schedFact) {
		this.schedFact = schedFact;
	}
	
	public void startJob(){
		System.out.println("Set job details");
		String jobName = "";
		try{
		
		List<JobEntityDTO> listOfJobs = jobConfigService.getjobDetail();
		Scheduler scheduler = createAndStartScheduler();
		/*JobEntityDTO job1 = new JobEntityDTO();
		job1.setJobClassName("AutoSettlementJob");
		job1.setJobDescription("Settle transaction auto");
		job1.setJobCronExpression("0 0 0 * * ?");
		//job1.setJobCronExpression("0/10 * * * * ?");
		job1.setJobGruopName("autoSettle");
		job1.setJobIdentityName("dfsdf");
		*/
		for(JobEntityDTO job : listOfJobs){
			jobName = job.getJobClassName();
			if(!"AutoSettlementJob".equals(jobName))
				fireJob(scheduler, job);
		}
		}catch (SchedulerException | InterruptedException e){
			System.out.println("Exception while running job" + jobName);
			e.printStackTrace();
		}
		System.out.println("Outside set Job details");
	}
	
	public Scheduler createAndStartScheduler() throws SchedulerException {
		
		Scheduler scheduler = schedFact.getScheduler();
		System.out.println("Scheduler name is: " + scheduler.getSchedulerName());
		System.out.println("Scheduler instance ID is: "+ scheduler.getSchedulerInstanceId());
		System.out.println("Scheduler context's value for key QuartzTopic is "+ scheduler.getContext().getString("QuartzTopic"));
		scheduler.start();
		return scheduler;
	}

	public void fireJob(final Scheduler scheduler, JobEntityDTO jobClass)
			throws SchedulerException, InterruptedException {
		
		try {
			
			@SuppressWarnings("unchecked")
			Class<? extends Job> cls =	(Class<? extends Job>)Class.forName("com.awl.job.tch.schedular.job."+jobClass.getJobClassName()); 
					//(Class<? extends Job>) Class.forName("com.awl.job.tch.schedular.job."+jobClass.getJobClassName());
			System.out.println("jobClass.getJobClassName() :" + jobClass.getJobClassName());
			JobDataMap jobMap = new JobDataMap();
			jobMap.put("jobName", jobClass.getJobClassName());
			jobMap.put("settlementDao",settlementDao);
			jobMap.put("jobDaoImpl",jobLogsDaoImpl);
			
			JobDetail jobDetail = JobBuilder.newJob(cls).setJobData(jobMap)
						.withDescription(jobClass.getJobDescription())
						.withIdentity(jobClass.getJobIdentityName(), "TCH")
						.requestRecovery(true)
						.build();
			System.out.println("Expression : " +jobClass.getJobCronExpression());
			Trigger trigger = TriggerBuilder.newTrigger()
						.startNow()
						.forJob(jobDetail)
						.withSchedule(CronScheduleBuilder.cronSchedule(jobClass.getJobCronExpression()))
						.withDescription(jobClass.getJobClassName())
						.build();

				scheduler.scheduleJob(jobDetail, trigger);
		} catch (SecurityException  | IllegalArgumentException | ClassNotFoundException e) {
		}
	}
	
	private void load(){
		try {
			System.out.println("Assigning quartz file");
			schedFact = new org.quartz.impl.StdSchedulerFactory("quartz.properties");
			//jobConfigService =  new JobConfigurationServiceImpl();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
		}
	}

	@Override
	public void run() {
		startJob();
	}
	
}

