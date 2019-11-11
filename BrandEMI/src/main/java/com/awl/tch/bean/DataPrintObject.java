package com.awl.tch.bean;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((printLabel == null) ? 0 : printLabel.hashCode());
		result = prime * result
				+ ((printVal == null) ? 0 : printVal.hashCode());
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
		DataPrintObject other = (DataPrintObject) obj;
		if (printLabel == null) {
			if (other.printLabel != null)
				return false;
		} else if (!printLabel.equals(other.printLabel))
			return false;
		if (printVal == null) {
			if (other.printVal != null)
				return false;
		} else if (!printVal.equals(other.printVal))
			return false;
		return true;
	}

}
