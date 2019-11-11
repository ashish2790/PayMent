package com.awl.job.tch.schedular;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.awl.tch.server.TcpServer;

@Configuration 
@ComponentScan("com.awl.job.tch.schedular") 
@Component("jobConfig")
public class JobScheduler{
	
	
	public static void main(String[] args) throws Exception {
		
		
		AssignTask assign =	(AssignTask) TcpServer.getContext().getBean("assignTask");
		new Thread(assign).start();
		
		/*Scheduler scheduler = quartzScheduler.createAndStartScheduler();
		quartzScheduler.fireJob(scheduler, AutoSettlementJob.class);*/
	}

	/*public Scheduler createAndStartScheduler() throws SchedulerException {
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory("quartz.properties");
		Scheduler scheduler = schedFact.getScheduler();
		System.out
				.println("Scheduler name is: " + scheduler.getSchedulerName());
		System.out.println("Scheduler instance ID is: "
				+ scheduler.getSchedulerInstanceId());
		System.out.println("Scheduler context's value for key QuartzTopic is "
				+ scheduler.getContext().getString("QuartzTopic"));
		scheduler.start();
		return scheduler;
	}

	public <T extends Job> void fireJob(Scheduler scheduler, Class<T> jobClass)
			throws SchedulerException, InterruptedException {

		// define the job and tie it to our HelloJob class
		JobBuilder jobBuilder = JobBuilder.newJob(jobClass);
		JobDataMap data = new JobDataMap();
		data.put("latch", this);

		JobDetail jobDetail = jobBuilder
				.usingJobData("example",
						"com.javacodegeeks.quartz.QuartzSchedulerExample")
				.build();

		// Trigger the job to run now, and then every 40 seconds
		Trigger trigger = TriggerBuilder.newTrigger()
				.startNow()
				.withSchedule(
						CronScheduleBuilder.cronSchedule(""))
						.withDescription("MyTrigger").build();

		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(jobDetail, trigger);
	}*/


		/*public static void main(String[] args) {
			ApplicationContext app = new AnnotationConfigApplicationContext(JobSchedulerConfiguration.class);
			JobSchedulerConfiguration j = (JobSchedulerConfiguration) app.getBean("jobConfig");
			new Thread();
		}*/
}
