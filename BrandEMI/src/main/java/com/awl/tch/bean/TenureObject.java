package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;

public class TenureObject {

	
	@SerializedName("insdesCd")
	private String instdescCode;
	
	@SerializedName("prddesCd")
	private String proddescCode;
	
	@SerializedName("emiAmt")
	private String emiAmount;
	
	@SerializedName("etotAmt")
	private String emiTotalAmount;
	
	@SerializedName("roi")
	private String Roi;
	
	@SerializedName("chbkPer")
	private String cashbackPercent;
	
	@SerializedName("chbkAmt")
	private String cashBackAmount;
	
	@SerializedName("prFee")
	private String perFeeAmount;
	
	@SerializedName("prFeePer")
	private String perFeePercentage;
	
	
	@SerializedName("tenr")
	private String tenure;
	
	@SerializedName("desflg")
	private String descflag;
	
	@SerializedName("pckNum")
	private String packetNum;
	
	private transient String procFeePer;
	private transient String fixedAmount;
	private transient String capAmount;
	private transient String cashBackFlag;
	private transient String discAmount;
	private transient String effectiveCostToCustomer;
	private transient String discountFlag;
	
	
	
	private transient String subventionAmtMfg;
	private transient String subventionAmtIssuer;
	private transient String subventionAmtMID;
	
	private transient String subventionPerMID;
	private transient String subventionPerMfg;
	private transient String subventionPerIssuer;
	
	private transient String cashBackPerMfg;
	private transient String cashBackPerIssuer;
	private transient String cashBackPerMID;
	
	private transient String cashBackAmtMfg;
	private transient String cashBackAmtIssuer;
	private transient String cashBackAmtMID;
	
	private transient String subventionFinalAmt;
	private transient String cashBackFinalAmt;
	
	private transient String subventionFinalPer;
	private transient String cashBackFinalPer;
	
	
	/**
	 * @return the subventionAmtMfg
	 */
	public String getSubventionAmtMfg() {
		return subventionAmtMfg;
	}

	/**
	 * @param subventionAmtMfg the subventionAmtMfg to set
	 */
	public void setSubventionAmtMfg(String subventionAmtMfg) {
		this.subventionAmtMfg = subventionAmtMfg;
	}

	/**
	 * @return the subventionAmtIssuer
	 */
	public String getSubventionAmtIssuer() {
		return subventionAmtIssuer;
	}

	/**
	 * @param subventionAmtIssuer the subventionAmtIssuer to set
	 */
	public void setSubventionAmtIssuer(String subventionAmtIssuer) {
		this.subventionAmtIssuer = subventionAmtIssuer;
	}

	/**
	 * @return the subventionAmtMID
	 */
	public String getSubventionAmtMID() {
		return subventionAmtMID;
	}

	/**
	 * @param subventionAmtMID the subventionAmtMID to set
	 */
	public void setSubventionAmtMID(String subventionAmtMID) {
		this.subventionAmtMID = subventionAmtMID;
	}

	/**
	 * @return the subventionPerMID
	 */
	public String getSubventionPerMID() {
		return subventionPerMID;
	}

	/**
	 * @param subventionPerMID the subventionPerMID to set
	 */
	public void setSubventionPerMID(String subventionPerMID) {
		this.subventionPerMID = subventionPerMID;
	}

	/**
	 * @return the subventionPerMfg
	 */
	public String getSubventionPerMfg() {
		return subventionPerMfg;
	}

	/**
	 * @param subventionPerMfg the subventionPerMfg to set
	 */
	public void setSubventionPerMfg(String subventionPerMfg) {
		this.subventionPerMfg = subventionPerMfg;
	}

	/**
	 * @return the subventionPerIssuer
	 */
	public String getSubventionPerIssuer() {
		return subventionPerIssuer;
	}

	/**
	 * @param subventionPerIssuer the subventionPerIssuer to set
	 */
	public void setSubventionPerIssuer(String subventionPerIssuer) {
		this.subventionPerIssuer = subventionPerIssuer;
	}


	/**
	 * @return the cashBackPerMfg
	 */
	public String getCashBackPerMfg() {
		return cashBackPerMfg;
	}

	/**
	 * @param cashBackPerMfg the cashBackPerMfg to set
	 */
	public void setCashBackPerMfg(String cashBackPerMfg) {
		this.cashBackPerMfg = cashBackPerMfg;
	}

	/**
	 * @return the cashBackPerIssuer
	 */
	public String getCashBackPerIssuer() {
		return cashBackPerIssuer;
	}

	/**
	 * @param cashBackPerIssuer the cashBackPerIssuer to set
	 */
	public void setCashBackPerIssuer(String cashBackPerIssuer) {
		this.cashBackPerIssuer = cashBackPerIssuer;
	}

	/**
	 * @return the cashBackPerMID
	 */
	public String getCashBackPerMID() {
		return cashBackPerMID;
	}

	/**
	 * @param cashBackPerMID the cashBackPerMID to set
	 */
	public void setCashBackPerMID(String cashBackPerMID) {
		this.cashBackPerMID = cashBackPerMID;
	}

	/**
	 * @return the cashBackAmtMfg
	 */
	public String getCashBackAmtMfg() {
		return cashBackAmtMfg;
	}

	/**
	 * @param cashBackAmtMfg the cashBackAmtMfg to set
	 */
	public void setCashBackAmtMfg(String cashBackAmtMfg) {
		this.cashBackAmtMfg = cashBackAmtMfg;
	}

	/**
	 * @return the cashBackAmtIssuer
	 */
	public String getCashBackAmtIssuer() {
		return cashBackAmtIssuer;
	}

	/**
	 * @param cashBackAmtIssuer the cashBackAmtIssuer to set
	 */
	public void setCashBackAmtIssuer(String cashBackAmtIssuer) {
		this.cashBackAmtIssuer = cashBackAmtIssuer;
	}

	/**
	 * @return the cashBackAmtMID
	 */
	public String getCashBackAmtMID() {
		return cashBackAmtMID;
	}

	/**
	 * @param cashBackAmtMID the cashBackAmtMID to set
	 */
	public void setCashBackAmtMID(String cashBackAmtMID) {
		this.cashBackAmtMID = cashBackAmtMID;
	}

	/**
	 * @return the subventionFinalAmt
	 */
	public String getSubventionFinalAmt() {
		return subventionFinalAmt;
	}

	/**
	 * @param subventionFinalAmt the subventionFinalAmt to set
	 */
	public void setSubventionFinalAmt(String subventionFinalAmt) {
		this.subventionFinalAmt = subventionFinalAmt;
	}

	/**
	 * @return the cashBackFinalAmt
	 */
	public String getCashBackFinalAmt() {
		return cashBackFinalAmt;
	}

	/**
	 * @param cashBackFinalAmt the cashBackFinalAmt to set
	 */
	public void setCashBackFinalAmt(String cashBackFinalAmt) {
		this.cashBackFinalAmt = cashBackFinalAmt;
	}

	/**
	 * @return the subventionFinalPer
	 */
	public String getSubventionFinalPer() {
		return subventionFinalPer;
	}

	/**
	 * @param subventionFinalPer the subventionFinalPer to set
	 */
	public void setSubventionFinalPer(String subventionFinalPer) {
		this.subventionFinalPer = subventionFinalPer;
	}

	/**
	 * @return the cashBackFinalPer
	 */
	public String getCashBackFinalPer() {
		return cashBackFinalPer;
	}

	/**
	 * @param cashBackFinalPer the cashBackFinalPer to set
	 */
	public void setCashBackFinalPer(String cashBackFinalPer) {
		this.cashBackFinalPer = cashBackFinalPer;
	}

	/**
	 * @return the discountFlag
	 */
	public String getDiscountFlag() {
		return discountFlag;
	}

	/**
	 * @param discountFlag the discountFlag to set
	 */
	public void setDiscountFlag(String discountFlag) {
		this.discountFlag = discountFlag;
	}

	/**
	 * @return the effectiveCostToCustomer
	 */
	public String getEffectiveCostToCustomer() {
		return effectiveCostToCustomer;
	}

	/**
	 * @param effectiveCostToCustomer the effectiveCostToCustomer to set
	 */
	public void setEffectiveCostToCustomer(String effectiveCostToCustomer) {
		this.effectiveCostToCustomer = effectiveCostToCustomer;
	}

	/**
	 * @return the discAmount
	 */
	public String getDiscAmount() {
		return discAmount;
	}

	/**
	 * @param discAmount the discAmount to set
	 */
	public void setDiscAmount(String discAmount) {
		this.discAmount = discAmount;
	}

	/**
	 * @return the perFeePercentage
	 */
	public String getPerFeePercentage() {
		return perFeePercentage;
	}

	/**
	 * @param perFeePercentage the perFeePercentage to set
	 */
	public void setPerFeePercentage(String perFeePercentage) {
		this.perFeePercentage = perFeePercentage;
	}

	/**
	 * @return the cashBackFlag
	 */
	public String getCashBackFlag() {
		return cashBackFlag;
	}

	/**
	 * @param cashBackFlag the cashBackFlag to set
	 */
	public void setCashBackFlag(String cashBackFlag) {
		this.cashBackFlag = cashBackFlag;
	}

	/**
	 * @return the capAmount
	 */
	public String getCapAmount() {
		return capAmount;
	}

	/**
	 * @param capAmount the capAmount to set
	 */
	public void setCapAmount(String capAmount) {
		this.capAmount = capAmount;
	}

	/**
	 * @return the fixedAmount
	 */
	public String getFixedAmount() {
		return fixedAmount;
	}

	/**
	 * @param fixedAmount the fixedAmount to set
	 */
	public void setFixedAmount(String fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	/**
	 * @return the procFeePer
	 */
	public String getProcFeePer() {
		return procFeePer;
	}

	/**
	 * @param procFeePer the procFeePer to set
	 */
	public void setProcFeePer(String procFeePer) {
		this.procFeePer = procFeePer;
	}

	/**
	 * @return the instdescCode
	 */
	public String getInstdescCode() {
		return instdescCode;
	}

	/**
	 * @param instdescCode the instdescCode to set
	 */
	public void setInstdescCode(String instdescCode) {
		this.instdescCode = instdescCode;
	}

	
	/**
	 * @return the emiTotalAmount
	 */
	public String getEmiTotalAmount() {
		return emiTotalAmount;
	}

	/**
	 * @param emiTotalAmount the emiTotalAmount to set
	 */
	public void setEmiTotalAmount(String emiTotalAmount) {
		this.emiTotalAmount = emiTotalAmount;
	}

	/**
	 * @return the proddescCode
	 */
	public String getProddescCode() {
		return proddescCode;
	}

	/**
	 * @param proddescCode the proddescCode to set
	 */
	public void setProddescCode(String proddescCode) {
		this.proddescCode = proddescCode;
	}

	/**
	 * @return the emiAmount
	 */
	public String getEmiAmount() {
		return emiAmount;
	}

	/**
	 * @param emiAmount the emiAmount to set
	 */
	public void setEmiAmount(String emiAmount) {
		this.emiAmount = emiAmount;
	}

	/**
	 * @return the roi
	 */
	public String getRoi() {
		return Roi;
	}

	/**
	 * @param roi the roi to set
	 */
	public void setRoi(String roi) {
		Roi = roi;
	}

	/**
	 * @return the cashbackPercent
	 */
	public String getCashbackPercent() {
		return cashbackPercent;
	}

	/**
	 * @param cashbackPercent the cashbackPercent to set
	 */
	public void setCashbackPercent(String cashbackPercent) {
		this.cashbackPercent = cashbackPercent;
	}

	/**
	 * @return the cashBackAmount
	 */
	public String getCashBackAmount() {
		return cashBackAmount;
	}

	/**
	 * @param cashBackAmount the cashBackAmount to set
	 */
	public void setCashBackAmount(String cashBackAmount) {
		this.cashBackAmount = cashBackAmount;
	}


	/**
	 * @return the perFeeAmount
	 */
	public String getPerFeeAmount() {
		return perFeeAmount;
	}

	/**
	 * @param perFeeAmount the perFeeAmount to set
	 */
	public void setPerFeeAmount(String perFeeAmount) {
		this.perFeeAmount = perFeeAmount;
	}

	/**
	 * @return the tenure
	 */
	public String getTenure() {
		return tenure;
	}

	/**
	 * @param tenure the tenure to set
	 */
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	/**
	 * @return the descflag
	 */
	public String getDescflag() {
		return descflag;
	}

	/**
	 * @param descflag the descflag to set
	 */
	public void setDescflag(String descflag) {
		this.descflag = descflag;
	}

	/**
	 * @return the packetNum
	 */
	public String getPacketNum() {
		return packetNum;
	}

	/**
	 * @param packetNum the packetNum to set
	 */
	public void setPacketNum(String packetNum) {
		this.packetNum = packetNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TenureObject [emiAmount=" + emiAmount + ", emiTotalAmount="
				+ emiTotalAmount + ", Roi=" + Roi + ", cashbackPercent="
				+ cashbackPercent + ", cashBackAmount=" + cashBackAmount
				+ ", perFeeAmount=" + perFeeAmount + ", perFeePercentage="
				+ perFeePercentage + ", tenure=" + tenure + ", discountFlag="
				+ discountFlag + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Roi == null) ? 0 : Roi.hashCode());
		result = prime * result
				+ ((cashBackAmount == null) ? 0 : cashBackAmount.hashCode());
		result = prime * result
				+ ((cashbackPercent == null) ? 0 : cashbackPercent.hashCode());
		result = prime * result
				+ ((descflag == null) ? 0 : descflag.hashCode());
		result = prime * result
				+ ((emiAmount == null) ? 0 : emiAmount.hashCode());
		result = prime * result
				+ ((emiTotalAmount == null) ? 0 : emiTotalAmount.hashCode());
		result = prime * result
				+ ((instdescCode == null) ? 0 : instdescCode.hashCode());
		result = prime * result
				+ ((packetNum == null) ? 0 : packetNum.hashCode());
		result = prime * result
				+ ((perFeeAmount == null) ? 0 : perFeeAmount.hashCode());
		result = prime
				* result
				+ ((perFeePercentage == null) ? 0 : perFeePercentage.hashCode());
		result = prime * result
				+ ((proddescCode == null) ? 0 : proddescCode.hashCode());
		result = prime * result + ((tenure == null) ? 0 : tenure.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenureObject other = (TenureObject) obj;
		if (Roi == null) {
			if (other.Roi != null)
				return false;
		} else if (!Roi.equals(other.Roi))
			return false;
		if (cashBackAmount == null) {
			if (other.cashBackAmount != null)
				return false;
		} else if (!cashBackAmount.equals(other.cashBackAmount))
			return false;
		if (cashbackPercent == null) {
			if (other.cashbackPercent != null)
				return false;
		} else if (!cashbackPercent.equals(other.cashbackPercent))
			return false;
		if (descflag == null) {
			if (other.descflag != null)
				return false;
		} else if (!descflag.equals(other.descflag))
			return false;
		if (emiAmount == null) {
			if (other.emiAmount != null)
				return false;
		} else if (!emiAmount.equals(other.emiAmount))
			return false;
		if (emiTotalAmount == null) {
			if (other.emiTotalAmount != null)
				return false;
		} else if (!emiTotalAmount.equals(other.emiTotalAmount))
			return false;
		if (instdescCode == null) {
			if (other.instdescCode != null)
				return false;
		} else if (!instdescCode.equals(other.instdescCode))
			return false;
		if (packetNum == null) {
			if (other.packetNum != null)
				return false;
		} else if (!packetNum.equals(other.packetNum))
			return false;
		if (perFeeAmount == null) {
			if (other.perFeeAmount != null)
				return false;
		} else if (!perFeeAmount.equals(other.perFeeAmount))
			return false;
		if (perFeePercentage == null) {
			if (other.perFeePercentage != null)
				return false;
		} else if (!perFeePercentage.equals(other.perFeePercentage))
			return false;
		if (proddescCode == null) {
			if (other.proddescCode != null)
				return false;
		} else if (!proddescCode.equals(other.proddescCode))
			return false;
		if (tenure == null) {
			if (other.tenure != null)
				return false;
		} else if (!tenure.equals(other.tenure))
			return false;
		return true;
	}
	
	
	
	
}
