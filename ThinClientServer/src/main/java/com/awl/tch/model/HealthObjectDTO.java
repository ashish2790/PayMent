package com.awl.tch.model;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Sequence;
import com.awl.tch.annotation.Table;
import com.awl.tch.constant.Constants;



@Table(name = "TCH_HEALTH_OBJECT" )
public class HealthObjectDTO{

	@Id
	@Column(name = "HO_ID")
	@Sequence(name=Constants.SEQ_HEALTH_OBJECT)
	private int id;
	
	@Column(name = "IMEI_NUMBER")
	private String imeiNumber;
	
	@Column(name = "SIM_NUMBER")
	private String simNumber;
	
	@Column(name = "COMMON_MODE")
	private String commMode;
		
	@Column(name = "DISPLAY_STAT")
	private String displayStat; 
	
	@Column(name = "PRINT_STAT")
	private String printStat;
	
	@Column(name = "MODEM_STAT")
	private String modemStat;
		
	@Column(name = "USER_PROC_TIME")
	private Integer userProcTime; 
	
	@Column(name = "FAIL_TXN_TO_HOST")
	private Integer failTrnxToHost;
	
	@Column(name = "IP_NUMBER")
	private String ipNum;
		
	@Column(name = "PORT_NUMBER")
	private String portNum; 
	
	@Column(name = "NAC_VAL")
	private Integer nacVal;
	
	@Column(name = "TMS_IP")
	private String tmsIp;
	
	@Column(name = "TMS_PORT")
	private String tmsPort;
		
	@Column(name = "APN")
	private String apn; 
	
	@Column(name = "RTM_FLAG")
	private Integer rtmFlag;
	
	@Column(name = "TXN_TIMEOUT")
	private Integer trnxTimeOut;
	
	@Column(name = "MID")
	private String mid;
		
	@Column(name = "TID")
	private String tid; 
	
	@Column(name = "CLIENT_ID")
	private String clientId;
	
	@Column(name = "TERMINAL_SERIAL_NUMBER")
	private String terminalSerialNumber;

	@Column(name = "ADDED_BY")
	private String addedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImeiNumber() {
		return imeiNumber;
	}

	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public String getCommMode() {
		return commMode;
	}

	public void setCommMode(String commMode) {
		this.commMode = commMode;
	}

	public String getDisplayStat() {
		return displayStat;
	}

	public void setDisplayStat(String displayStat) {
		this.displayStat = displayStat;
	}

	public String getPrintStat() {
		return printStat;
	}

	public void setPrintStat(String printStat) {
		this.printStat = printStat;
	}

	public String getModemStat() {
		return modemStat;
	}

	public void setModemStat(String modemStat) {
		this.modemStat = modemStat;
	}

	public Integer getUserProcTime() {
		return userProcTime;
	}

	public void setUserProcTime(Integer userProcTime) {
		this.userProcTime = userProcTime;
	}

	public Integer getFailTrnxToHost() {
		return failTrnxToHost;
	}

	public void setFailTrnxToHost(Integer failTrnxToHost) {
		this.failTrnxToHost = failTrnxToHost;
	}

	public String getIpNum() {
		return ipNum;
	}

	public void setIpNum(String ipNum) {
		this.ipNum = ipNum;
	}

	public String getPortNum() {
		return portNum;
	}

	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}

	public Integer getNacVal() {
		return nacVal;
	}

	public void setNacVal(Integer nacVal) {
		this.nacVal = nacVal;
	}

	public String getTmsIp() {
		return tmsIp;
	}

	public void setTmsIp(String tmsIp) {
		this.tmsIp = tmsIp;
	}

	public String getTmsPort() {
		return tmsPort;
	}

	public void setTmsPort(String tmsPort) {
		this.tmsPort = tmsPort;
	}

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public Integer getRtmFlag() {
		return rtmFlag;
	}

	public void setRtmFlag(Integer rtmFlag) {
		this.rtmFlag = rtmFlag;
	}

	public Integer getTrnxTimeOut() {
		return trnxTimeOut;
	}

	public void setTrnxTimeOut(Integer trnxTimeOut) {
		this.trnxTimeOut = trnxTimeOut;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTerminalSerialNumber() {
		return terminalSerialNumber;
	}

	public void setTerminalSerialNumber(String terminalSerialNumber) {
		this.terminalSerialNumber = terminalSerialNumber;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

}
