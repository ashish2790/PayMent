package com.awl.tch.model;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.UCurrentTimestamp;

@Table(name = "TCH_EGRASS_REPORT" )
public class EgrassReportDTO{

	@Column(name = "E_ID")
	private int id;
	
	@Column(name = "E_REMITTER_NAME")
	private String remitterName;
	
	@Column(name = "E_AMOUNT")
	private String amount;
	
	@Id
	@Column(name = "E_GRN")
	private String grn;
	
	@Column(name = "E_REMITTER_FATHERNAME")
	private String remitterFatherName;
	
	@Column(name = "E_FUND_RECEIVED_DATE")
	private String fundReceivedDate;
	
	@Column(name = "E_DATE_OF_PROCESSING")
	private String dateOfProcessing;
	
	@Column(name = "E_POS_REFERENCE")
	private String posReference;
	
	@Column(name = "E_OTHER_SERVICE_CHARGES")
	private String otherServiceCharges;
	
	@Column(name = "E_ACCOUNT_6")
	private String account_6;
	
	@Column(name = "E_AMOUNT_6")
	private String amount_6;
	
	@Column(name = "E_ACCOUNT_7")
	private String account_7;
	
	@Column(name = "E_AMOUNT_7")
	private String amount_7;
	
	@Column(name = "E_ACCOUNT_8")
	private String account_8;
	
	@Column(name = "E_AMOUNT_8")
	private String amount_8;
	
	@Column(name = "E_ACCOUNT_9")
	private String account_9;
	
	@Column(name = "E_AMOUNT_9")
	private String amount_9;
	
	@Column(name = "E_ACCOUNT_10")
	private String account_10;
	
	@Column(name = "E_AMOUNT_10")
	private String amount_10;

	@Column(name = "E_STATUS")
	private String status;
	
	@Column(name = "E_CREATED")
	@ICurrentTimestamp
	private String created;
	
	@Column(name = "E_UPDATED")
	@ICurrentTimestamp
	@UCurrentTimestamp
	private String updated;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemitterName() {
		return remitterName;
	}

	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getGrn() {
		return grn;
	}

	public void setGrn(String grn) {
		this.grn = grn;
	}

	public String getRemitterFatherName() {
		return remitterFatherName;
	}

	public void setRemitterFatherName(String remitterFatherName) {
		this.remitterFatherName = remitterFatherName;
	}

	public String getFundReceivedDate() {
		return fundReceivedDate;
	}

	public void setFundReceivedDate(String fundReceivedDate) {
		this.fundReceivedDate = fundReceivedDate;
	}

	public String getDateOfProcessing() {
		return dateOfProcessing;
	}

	public void setDateOfProcessing(String dateOfProcessing) {
		this.dateOfProcessing = dateOfProcessing;
	}

	public String getPosReference() {
		return posReference;
	}

	public void setPosReference(String posReference) {
		this.posReference = posReference;
	}

	public String getOtherServiceCharges() {
		return otherServiceCharges;
	}

	public void setOtherServiceCharges(String otherServiceCharges) {
		this.otherServiceCharges = otherServiceCharges;
	}

	public String getAccount_6() {
		return account_6;
	}

	public void setAccount_6(String account_6) {
		this.account_6 = account_6;
	}

	public String getAmount_6() {
		return amount_6;
	}

	public void setAmount_6(String amount_6) {
		this.amount_6 = amount_6;
	}

	public String getAccount_7() {
		return account_7;
	}

	public void setAccount_7(String account_7) {
		this.account_7 = account_7;
	}

	public String getAmount_7() {
		return amount_7;
	}

	public void setAmount_7(String amount_7) {
		this.amount_7 = amount_7;
	}

	public String getAccount_8() {
		return account_8;
	}

	public void setAccount_8(String account_8) {
		this.account_8 = account_8;
	}

	public String getAmount_8() {
		return amount_8;
	}

	public void setAmount_8(String amount_8) {
		this.amount_8 = amount_8;
	}

	public String getAccount_9() {
		return account_9;
	}

	public void setAccount_9(String account_9) {
		this.account_9 = account_9;
	}

	public String getAmount_9() {
		return amount_9;
	}

	public void setAmount_9(String amount_9) {
		this.amount_9 = amount_9;
	}

	public String getAccount_10() {
		return account_10;
	}

	public void setAccount_10(String account_10) {
		this.account_10 = account_10;
	}

	public String getAmount_10() {
		return amount_10;
	}

	public void setAmount_10(String amount_10) {
		this.amount_10 = amount_10;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EgrassReportDTO [id=" + id + ", remitterName=" + remitterName
				+ ", amount=" + amount + ", grn=" + grn
				+ ", remitterFatherName=" + remitterFatherName
				+ ", fundReceivedDate=" + fundReceivedDate
				+ ", dateOfProcessing=" + dateOfProcessing + ", posReference="
				+ posReference + ", otherServiceCharges=" + otherServiceCharges
				+ ", status=" + status + ", created=" + created + ", updated="
				+ updated + "]";
	}
	
}