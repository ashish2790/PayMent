package com.awl.tch.model;

import java.sql.Date;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Sequence;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.UCurrentTimestamp;
import com.awl.tch.constant.Constants;

@Table(name = "TCH_LOGS")
public class LoggingDTO {
	
	@Id
	@Column(name="ID")
	@Sequence(name = Constants.TCH_LOGS_ID_SEQ)
	private Long id;
	
	@Column(name="REQUEST_TYPE")
	private String requestType;
	
	@Column(name="REQUEST")
	private String request;
	
	@Column(name="RESPONSE")
	private String response;
	
	@Column(name="REQUEST_TIME")
	@ICurrentTimestamp
	private String requestTime;
	
	@Column(name="RESPONSE_TIME")
	@UCurrentTimestamp
	private String responseTime;
	
	@Column(name="PROCESSING_TIME")
	private String processingTime;
	
	@Column(name="STATUS")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(String processingTime) {
		this.processingTime = processingTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	


}
