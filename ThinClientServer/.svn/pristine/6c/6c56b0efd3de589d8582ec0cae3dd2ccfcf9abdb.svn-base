package com.awl.job.tch.schedular.model;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.UCurrentTimestamp;

@Table(name = "TCH_JOB_LOGS")
public class JobLogsEntityDTO {
	
	@Id
	@Column(name = "J_ID")
	private Long id;
	
	@Column(name = "J_JOB_NAME")
	private String jobName;
	
	@Column(name = "J_START_TIME")
	@ICurrentTimestamp
	private String jobStartTime;
	
	@Column(name = "J_END_TIME")
	@ICurrentTimestamp
	@UCurrentTimestamp
	private String jobEndtime;
	
	@Column(name = "J_PROCESSING_TIME")
	private Long processingTime;
	
	@Column(name = "J_STATUS")
	private String status;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobStartTime
	 */
	public String getJobStartTime() {
		return jobStartTime;
	}

	/**
	 * @param jobStartTime the jobStartTime to set
	 */
	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	/**
	 * @return the jobEndtime
	 */
	public String getJobEndtime() {
		return jobEndtime;
	}

	/**
	 * @param jobEndtime the jobEndtime to set
	 */
	public void setJobEndtime(String jobEndtime) {
		this.jobEndtime = jobEndtime;
	}

	/**
	 * @return the processingTime
	 */
	public Long getProcessingTime() {
		return processingTime;
	}

	/**
	 * @param processingTime the processingTime to set
	 */
	public void setProcessingTime(Long processingTime) {
		this.processingTime = processingTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
