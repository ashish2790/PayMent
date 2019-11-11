package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author ashish.bhavsar
 *
 */

public class Carddetail {

	// Following field used in request JSON
	@SerializedName(value="tSerNum")
	private String terminalSerialNumber;
	
	//Following field used in request JSON
	@SerializedName("reqTyp")
	private String requestType;
	
	@SerializedName("crdDetObj")
	private CardDetailData[] cardDetailObject;
	
	//Following field used in request JSON
	@SerializedName("pckNum")
	private String packetNumber;
	
	//Following field used in request JSON
	@SerializedName("crdDtVer")
	private String cardDetailVersion;
	
	//Following field used in response only JSON
	@SerializedName("desflg")
	private String desFlag;
	
	public class CardDetailData{
			
		@SerializedName("isNfc")
		private String isNFC;
		
		@SerializedName("aid")
		private String applicationId;
		
		@SerializedName("tacOnl")
		private String tacOnline;
		
		@SerializedName("tacDef")
		private String tacDefault;
		
		@SerializedName("tacOff")
		private String tacOffline;
		
		@SerializedName("trgPer")
		private String targetPercent;
		
		@SerializedName("thrVal")
		private String thresholdVal;
		
		@SerializedName("mxTrgPer")
		private String maxTargetPercent;
		
		@SerializedName("ctlFlrLt")
		private String ctlsFloorLimit;
		
		@SerializedName("odvTnxLt")
		private String odvcTransactionLimit;
		
		@SerializedName("nOdTnxLt")
		private String nonOdvcTransactionLimit;
		
		@SerializedName("cvmLmt")
		private String cvmLimits;
		
		@SerializedName("crdLbl")
		private String cardLabel;
		
		@SerializedName("emvAck")
		private String emvACK;
		
		private transient String activationFlag;
		
		
		/**
		 * @return the emvACK
		 */
		public String getEmvACK() {
			return emvACK;
		}

		/**
		 * @param emvACK the emvACK to set
		 */
		public void setEmvACK(String emvACK) {
			this.emvACK = emvACK;
		}

		/**
		 * @return the cardLabel
		 */
		public String getCardLabel() {
			return cardLabel;
		}

		/**
		 * @param cardLabel the cardLabel to set
		 */
		public void setCardLabel(String cardLabel) {
			this.cardLabel = cardLabel;
		}

		/**
		 * @return the activationFlag
		 */
		public String getActivationFlag() {
			return activationFlag;
		}

		/**
		 * @param activationFlag the activationFlag to set
		 */
		public void setActivationFlag(String activationFlag) {
			this.activationFlag = activationFlag;
		}

		/**
		 * @return the isNFC
		 */
		public String getIsNFC() {
			return isNFC;
		}

		/**
		 * @param isNFC the isNFC to set
		 */
		public void setIsNFC(String isNFC) {
			this.isNFC = isNFC;
		}

		public String getApplicationId() {
			return applicationId;
		}

		public void setApplicationId(String applicationId) {
			this.applicationId = applicationId;
		}

		public String getTacOnline() {
			return tacOnline;
		}

		public void setTacOnline(String tacOnline) {
			this.tacOnline = tacOnline;
		}

		public String getTacDefault() {
			return tacDefault;
		}

		public void setTacDefault(String tacDefault) {
			this.tacDefault = tacDefault;
		}

		public String getTacOffline() {
			return tacOffline;
		}

		public void setTacOffline(String tacOffline) {
			this.tacOffline = tacOffline;
		}

		public String getTargetPercent() {
			return targetPercent;
		}

		public void setTargetPercent(String targetPercent) {
			this.targetPercent = targetPercent;
		}

		public String getThresholdVal() {
			return thresholdVal;
		}

		public void setThresholdVal(String thresholdVal) {
			this.thresholdVal = thresholdVal;
		}

		public String getMaxTargetPercent() {
			return maxTargetPercent;
		}

		public void setMaxTargetPercent(String maxTargetPercent) {
			this.maxTargetPercent = maxTargetPercent;
		}

		public String getCtlsFloorLimit() {
			return ctlsFloorLimit;
		}

		public void setCtlsFloorLimit(String ctlsFloorLimit) {
			this.ctlsFloorLimit = ctlsFloorLimit;
		}

		public String getOdvcTransactionLimit() {
			return odvcTransactionLimit;
		}

		public void setOdvcTransactionLimit(String odvcTransactionLimit) {
			this.odvcTransactionLimit = odvcTransactionLimit;
		}

		public String getNonOdvcTransactionLimit() {
			return nonOdvcTransactionLimit;
		}

		public void setNonOdvcTransactionLimit(String nonOdvcTransactionLimit) {
			this.nonOdvcTransactionLimit = nonOdvcTransactionLimit;
		}

		public String getCvmLimits() {
			return cvmLimits;
		}

		public void setCvmLimits(String cvmLimits) {
			this.cvmLimits = cvmLimits;
		}
		
	}

	public String getRequestType() {
		return requestType;
	}


	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


	public CardDetailData[] getCardDetailObject() {
		return cardDetailObject;
	}


	public void setCardDetailObject(CardDetailData[] cardDetailObject) {
		this.cardDetailObject = cardDetailObject;
	}


	public String getPacketNumber() {
		return packetNumber;
	}


	public void setPacketNumber(String packetNumber) {
		this.packetNumber = packetNumber;
	}


	public String getCardDetailVersion() {
		return cardDetailVersion;
	}


	public void setCardDetailVersion(String cardDetailVersion) {
		this.cardDetailVersion = cardDetailVersion;
	}

	public String getTerminalSerialNumber() {
		return terminalSerialNumber;
	}

	public void setTerminalSerialNumber(String terminalSerialNumber) {
		this.terminalSerialNumber = terminalSerialNumber;
	}


	public String getDesFlag() {
		return desFlag;
	}


	public void setDesFlag(String desFlag) {
		this.desFlag = desFlag;
	}


	@Override
	public String toString() {
		return "Carddetail [terminalSerialNumber=" + terminalSerialNumber
				+ ", requestType=" + requestType + ", cardDetailObject="
				+ cardDetailObject + ", packetNumber=" + packetNumber
				+ ", cardDetailVersion=" + cardDetailVersion + ", desFlag="
				+ desFlag + "]";
	}
}
	