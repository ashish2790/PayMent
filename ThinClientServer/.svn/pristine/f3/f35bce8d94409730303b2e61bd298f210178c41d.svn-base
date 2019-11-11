package com.awl.job.tch.schedular.job;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.CredentialException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.awl.tch.constant.Constants;
import com.awl.tch.dao.ReportDaoImpl;
import com.awl.tch.exceptions.TCHServiceException;

public class SBIEPAYReport extends QuartzJobBean{

	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		ReportDaoImpl s = (ReportDaoImpl) context.getJobDetail().getJobDataMap().get(Constants.TCH_REPORT_DAO);
		try {
			List<StringBuilder> data = s.getTransactionDetails("SBIEPAY", "");
			createFile(data);
		} catch (TCHServiceException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private void createFile(List<StringBuilder> data){
		final String FILENAME = "D:\\ashish\\Work\\Thin Client\\SBI epay\\File\\filename.txt";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
			for(StringBuilder str : data){
				bw.write(str.toString());
				bw.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
