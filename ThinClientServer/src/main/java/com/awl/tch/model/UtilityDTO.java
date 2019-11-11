package com.awl.tch.model;

import java.util.Date;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.UCurrentTimestamp;

@Table(name = "TCH_UTILITY_INFO")
public class UtilityDTO {

	@Column(name="U_ID")
	private long id;
	
	@Column(name="U_IP_ADDRESS")
	private String ipAddress;
		
	@Column(name="U_PORT_NUMBER")
	private String portNumber;
	
	@Column(name="U_URL")
	private String url;
	
	@Column(name="U_CREATED")
	@ICurrentTimestamp
	private Date created;
	
	@Column(name="U_UPDATED")
	@ICurrentTimestamp
	@UCurrentTimestamp
	private Date updated;
	
	@Column(name="U_APP_NAME")
	private String appName;
	
	@Column(name="U_PRINT_LABELS")
	private String printLabels;
	
	

	
	/**
	 * @return the printLabels
	 */
	public String getPrintLabels() {
		return printLabels;
	}

	/**
	 * @param printLabels the printLabels to set
	 */
	public void setPrintLabels(String printLabels) {
		this.printLabels = printLabels;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the portNumber
	 */
	public String getPortNumber() {
		return portNumber;
	}

	/**
	 * @param portNumber the portNumber to set
	 */
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
