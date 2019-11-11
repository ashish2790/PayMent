package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;

public class CapKey {
	
	@SerializedName(value="pckNum")
	private String packetNumber;
	
	@SerializedName(value="cpkVerNo")
	private String capKeyVersion;
	
	@SerializedName(value="capObj")
	private CapKeyData capKeyData[];
	
	public String getPacketNumber() {
		return packetNumber;
	}



	public void setPacketNumber(String packetNumber) {
		this.packetNumber = packetNumber;
	}



	public String getCapKeyVersion() {
		return capKeyVersion;
	}



	public void setCapKeyVersion(String capKeyVersion) {
		this.capKeyVersion = capKeyVersion;
	}



	public CapKeyData[] getCapKeyData() {
		return capKeyData;
	}



	public void setCapKeyData(CapKeyData[] capKeyData) {
		this.capKeyData = capKeyData;
	}



	public class CapKeyData{
		@SerializedName(value="pubRid")
		private String pubRid;
		
		@SerializedName(value="pubIdx")
		private String pubIndex;
		
		@SerializedName(value="pubExp")
		private String pubExponent;
		
		@SerializedName(value="pubMod")
		private String pubModulos;
		
		@SerializedName(value="cpkVerNo")
		private String  capkeyVersionNumber;
		

		public String getPubRid() {
			return pubRid;
		}

		public void setPubRid(String pubRid) {
			this.pubRid = pubRid;
		}

		public String getPubIndex() {
			return pubIndex;
		}

		public void setPubIndex(String pubIndex) {
			this.pubIndex = pubIndex;
		}

		public String getPubExponent() {
			return pubExponent;
		}

		public void setPubExponent(String pubExponent) {
			this.pubExponent = pubExponent;
		}

		public String getPubModulos() {
			return pubModulos;
		}

		public void setPubModulos(String pubModulos) {
			this.pubModulos = pubModulos;
		}

		/**
		 * @return the capkeyVersionNumber
		 */
		public String getCapkeyVersionNumber() {
			return capkeyVersionNumber;
		}

		/**
		 * @param capkeyVersionNumber the capkeyVersionNumber to set
		 */
		public void setCapkeyVersionNumber(String capkeyVersionNumber) {
			this.capkeyVersionNumber = capkeyVersionNumber;
		}
		
		
		
		
		
	}
}
