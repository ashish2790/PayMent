package com.awl.tch.upi.bean;

import com.google.gson.annotations.SerializedName;

public class DataPrintObject {
	
	@SerializedName("prtLbl")
	private String printLabel;
	
	@SerializedName("prtVl")
	private String printVal;
	
	@SerializedName("prtFrm")
	private String printForm;

	/**
	 * @return the printLabel
	 */
	public String getPrintLabel() {
		return printLabel;
	}

	/**
	 * @param printLabel the printLabel to set
	 */
	public void setPrintLabel(String printLabel) {
		this.printLabel = printLabel;
	}

	/**
	 * @return the printVal
	 */
	public String getPrintVal() {
		return printVal;
	}

	/**
	 * @param printVal the printVal to set
	 */
	public void setPrintVal(String printVal) {
		this.printVal = printVal;
	}

	/**
	 * @return the printForm
	 */
	public String getPrintForm() {
		return printForm;
	}

	/**
	 * @param printForm the printForm to set
	 */
	public void setPrintForm(String printForm) {
		this.printForm = printForm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataPrintObject [printLabel=" + printLabel + ", printVal="
				+ printVal + ", printForm=" + printForm + "]";
	}
	
	
}
