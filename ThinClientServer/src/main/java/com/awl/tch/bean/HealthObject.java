package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;

public class HealthObject{
	
	@SerializedName(value="comNm")
	private String commonName;
	
	@SerializedName(value="simNos")
	private String simNumber;
	
	@SerializedName(value="imeiNum")
	private String IMIENumber;
	
	@SerializedName(value="comIp")
	private String commonIp;
	
	@SerializedName(value="comprt")
	private String commonPort;
	
	@SerializedName(value="comMd")
	private String commMode;	
	
	@SerializedName(value="dispSt")
	private String displayStat;
	
	@SerializedName(value="prtSt")
	private String printStat;
	
	@SerializedName(value="mdmSt")
	private String modemStat;
	
	@SerializedName(value="usrPrTm")
	private String userProcTime;
	
	@SerializedName(value="flTnx")
	private String failTrnxToHost;
	
	@SerializedName(value="ipNum")
	private String ipNum;
	
	@SerializedName(value="prtNum")
	private String portNum;
	
	@SerializedName(value="nacVl")
	private String nacVal;
	
	@SerializedName(value="tmsIp")
	private String tmsIp;
	
	@SerializedName(value="tmsPrt")
	private String tmsPort;
	
	@SerializedName(value="apn")
	private String apn;
	
	@SerializedName(value="rtmFlg")
	private String rtmFlag;
	
	@SerializedName(value="tnxTmOt")
	private String trnxTimeOut;
	
	@SerializedName(value = "dwnLdTm")
	private String downLoadtime;
	
	@SerializedName("qrTmOt")
	private String qrTimeOut;
	
	@SerializedName("qrTmCt")
	private String qrTimeCount;
	
	
	/*@SerializedName("invNum")
	private String invoiceNumber;
	
	@SerializedName("stNum")
	private String stanNumber;*/
	

	/**
	 * @return the invoiceNumber
	 *//*
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	*//**
	 * @param invoiceNumber the invoiceNumber to set
	 *//*
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	*//**
	 * @return the stanNumber
	 *//*
	public String getStanNumber() {
		return stanNumber;
	}

	*//**
	 * @param stanNumber the stanNumber to set
	 *//*
	public void setStanNumber(String stanNumber) {
		this.stanNumber = stanNumber;
	}*/

	/**
	 * @return the qrTimeOut
	 */
	public String getQrTimeOut() {
		return qrTimeOut;
	}

	/**
	 * @param qrTimeOut the qrTimeOut to set
	 */
	public void setQrTimeOut(String qrTimeOut) {
		this.qrTimeOut = qrTimeOut;
	}

	/**
	 * @return the qrTimeCount
	 */
	public String getQrTimeCount() {
		return qrTimeCount;
	}

	/**
	 * @param qrTimeCount the qrTimeCount to set
	 */
	public void setQrTimeCount(String qrTimeCount) {
		this.qrTimeCount = qrTimeCount;
	}

	/**
	 * @return the downLoadtime
	 */
	public String getDownLoadtime() {
		return downLoadtime;
	}

	/**
	 * @param downLoadtime the downLoadtime to set
	 */
	public void setDownLoadtime(String downLoadtime) {
		this.downLoadtime = downLoadtime;
	}

	/**
	 * @return the userProcTime
	 */
	public String getUserProcTime() {
		return userProcTime;
	}

	/**
	 * @param userProcTime the userProcTime to set
	 */
	public void setUserProcTime(String userProcTime) {
		this.userProcTime = userProcTime;
	}

	/**
	 * @return the commMode
	 */
	public String getCommMode() {
		return commMode;
	}

	/**
	 * @param commMode the commMode to set
	 */
	public void setCommMode(String commMode) {
		this.commMode = commMode;
	}

	/**
	 * @return the displayStat
	 */
	public String getDisplayStat() {
		return displayStat;
	}

	/**
	 * @param displayStat the displayStat to set
	 */
	public void setDisplayStat(String displayStat) {
		this.displayStat = displayStat;
	}

	/**
	 * @return the printStat
	 */
	public String getPrintStat() {
		return printStat;
	}

	/**
	 * @param printStat the printStat to set
	 */
	public void setPrintStat(String printStat) {
		this.printStat = printStat;
	}

	/**
	 * @return the modemStat
	 */
	public String getModemStat() {
		return modemStat;
	}

	/**
	 * @param modemStat the modemStat to set
	 */
	public void setModemStat(String modemStat) {
		this.modemStat = modemStat;
	}

	/**
	 * @return the failTrnxToHost
	 */
	public String getFailTrnxToHost() {
		return failTrnxToHost;
	}

	/**
	 * @param failTrnxToHost the failTrnxToHost to set
	 */
	public void setFailTrnxToHost(String failTrnxToHost) {
		this.failTrnxToHost = failTrnxToHost;
	}

	/**
	 * @return the ipNum
	 */
	public String getIpNum() {
		return ipNum;
	}

	/**
	 * @param ipNum the ipNum to set
	 */
	public void setIpNum(String ipNum) {
		this.ipNum = ipNum;
	}

	/**
	 * @return the portNum
	 */
	public String getPortNum() {
		return portNum;
	}

	/**
	 * @param portNum the portNum to set
	 */
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}

	/**
	 * @return the nacVal
	 */
	public String getNacVal() {
		return nacVal;
	}

	/**
	 * @param nacVal the nacVal to set
	 */
	public void setNacVal(String nacVal) {
		this.nacVal = nacVal;
	}

	/**
	 * @return the tmsIp
	 */
	public String getTmsIp() {
		return tmsIp;
	}

	/**
	 * @param tmsIp the tmsIp to set
	 */
	public void setTmsIp(String tmsIp) {
		this.tmsIp = tmsIp;
	}

	/**
	 * @return the tmsPort
	 */
	public String getTmsPort() {
		return tmsPort;
	}

	/**
	 * @param tmsPort the tmsPort to set
	 */
	public void setTmsPort(String tmsPort) {
		this.tmsPort = tmsPort;
	}

	/**
	 * @return the apn
	 */
	public String getApn() {
		return apn;
	}

	/**
	 * @param apn the apn to set
	 */
	public void setApn(String apn) {
		this.apn = apn;
	}

	/**
	 * @return the rtmFlag
	 */
	public String getRtmFlag() {
		return rtmFlag;
	}

	/**
	 * @param rtmFlag the rtmFlag to set
	 */
	public void setRtmFlag(String rtmFlag) {
		this.rtmFlag = rtmFlag;
	}

	/**
	 * @return the trnxTimeOut
	 */
	public String getTrnxTimeOut() {
		return trnxTimeOut;
	}

	/**
	 * @param trnxTimeOut the trnxTimeOut to set
	 */
	public void setTrnxTimeOut(String trnxTimeOut) {
		this.trnxTimeOut = trnxTimeOut;
	}

	/**
	 * @return the commonName
	 */
	public String getCommonName() {
		return commonName;
	}

	/**
	 * @param commonName the commonName to set
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	/**
	 * @return the simNumber
	 */
	public String getSimNumber() {
		return simNumber;
	}

	/**
	 * @param simNumber the simNumber to set
	 */
	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	/**
	 * @return the iMIENumber
	 */
	public String getIMIENumber() {
		return IMIENumber;
	}

	/**
	 * @param iMIENumber the iMIENumber to set
	 */
	public void setIMIENumber(String iMIENumber) {
		IMIENumber = iMIENumber;
	}

	/**
	 * @return the commonIp
	 */
	public String getCommonIp() {
		return commonIp;
	}

	/**
	 * @param commonIp the commonIp to set
	 */
	public void setCommonIp(String commonIp) {
		this.commonIp = commonIp;
	}

	/**
	 * @return the commonPort
	 */
	public String getCommonPort() {
		return commonPort;
	}

	/**
	 * @param commonPort the commonPort to set
	 */
	public void setCommonPort(String commonPort) {
		this.commonPort = commonPort;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HealthObject [commonName=" + commonName + ", simNumber="
				+ simNumber + ", IMIENumber=" + IMIENumber + ", commonIp="
				+ commonIp + ", commonPort=" + commonPort + ", commMode="
				+ commMode + ", displayStat=" + displayStat + ", printStat="
				+ printStat + ", modemStat=" + modemStat + ", userProcTime="
				+ userProcTime + ", failTrnxToHost=" + failTrnxToHost
				+ ", ipNum=" + ipNum + ", portNum=" + portNum + ", nacVal="
				+ nacVal + ", tmsIp=" + tmsIp + ", tmsPort=" + tmsPort
				+ ", apn=" + apn + ", rtmFlag=" + rtmFlag + ", trnxTimeOut="
				+ trnxTimeOut + ", downLoadtime=" + downLoadtime + "]";
	}
}
