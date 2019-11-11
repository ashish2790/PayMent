package com.awl.job.tch.schedular.job;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.awl.job.tch.schedular.MyJobOne;

@Configuration 
@ComponentScan("com.awl.job.tch.schedular") 
@Component("heartBeat")
public class HeartBeat {
	
	private static Logger logger = LoggerFactory.getLogger(HeartBeat.class);
	/*@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("jobone");
		obj.setTargetMethod("myTask");
		return obj;
		
	}
	//Job  is scheduled for 3+1 times with the interval of 30 seconds
		@Bean
		public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
			SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
			stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
			stFactory.setStartDelay(3000);
			stFactory.setRepeatInterval(30000);
			stFactory.setRepeatCount(3);
			return stFactory;
		}*/
		@Bean
		public JobDetailFactoryBean jobDetailFactoryBean(){
			JobDetailFactoryBean factory = new JobDetailFactoryBean();
			factory.setJobClass(MyJobOne.class);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("logger", logger);
			//map.put(AutoSettlementJob.COUNT, 1);
			factory.setJobDataAsMap(map);
			factory.setGroup("mygroup");
			factory.setName("myjob");
			return factory;
		}
		//Job is scheduled after every 1 minute 
		@Bean
		public CronTriggerFactoryBean cronTriggerFactoryBean(){
			CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
			stFactory.setJobDetail(jobDetailFactoryBean().getObject());
			stFactory.setName("mytrigger");
			stFactory.setGroup("mygroup");
			
			stFactory.setCronExpression("0/15 * * * * ?");
			return stFactory;
		}
		
		@Bean
		public JobDetailFactoryBean jobDetailFactoryBean1(){
			JobDetailFactoryBean factory = new JobDetailFactoryBean();
			factory.setJobClass(MyJobOne.class);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("logger", logger);
			//map.put(AutoSettlementJob.COUNT, 1);
			factory.setJobDataAsMap(map);
			factory.setGroup("mygroup");
			factory.setName("myjob");
			return factory;
		}
		//Job is scheduled after every 1 minute 
		@Bean
		public CronTriggerFactoryBean cronTriggerFactoryBean1(){
			CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
			stFactory.setJobDetail(jobDetailFactoryBean1().getObject());
			stFactory.setName("mytrigger");
			stFactory.setGroup("mygroup");
			stFactory.setCronExpression("0/5 * * * * ?");
			return stFactory;
		}
		
		@Bean
		public SchedulerFactoryBean schedulerFactoryBean() {
			SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
			//scheduler.setTriggers(cronTriggerFactoryBean1().getObject(),cronTriggerFactoryBean().getObject());
			return scheduler;
		}
}
