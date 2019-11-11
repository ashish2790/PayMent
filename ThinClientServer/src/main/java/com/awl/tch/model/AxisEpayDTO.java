package com.awl.tch.model;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Table;

@Table(name = "TCH_AXISEPAY_REPORT" )
public class AxisEpayDTO {

	@Column(name="A_MID")
	private String mid;
	
	@Column(name="A_TID")
	private String tid;
	
	@Column(name="A_RRN")
	private String rrn;
	
	@Column(name="A_URN")
	@Id
	private String urn;
	
	@Column(name="A_CID")
	private String cid;
	
	@Column(name="A_RID")
	private String rid;
	
	@Column(name="A_AMOUNT")
	private String amount;
	
	@Column(name="A_TRASACTIONDATE")
	private String transactionDate;
	
	@Column(name="A_STATUS")
	private String status;
		
	@Column(name="A_ERRORCODE")
	private String errCode;
	
	@Column(name="A_ERRORDESC")
	private String errDesc;
	
	@Column(name="A_CRN")
	private String crn;
	
	@Column(name="A_CUSTNAME")
	private String customerName;

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

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
