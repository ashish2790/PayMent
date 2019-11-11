package com.awl.tch.wallet.fc.bean;

import com.google.gson.annotations.SerializedName;


/**
 * Array object AdditinalInfo is used as a field in json request/response
 * to store additional info sent to/from FC host 
 * @author pooja.patil
 *
 */
public class AdditinalInfo {

	 @SerializedName("Tag")
	 private String tag;
	
	 @SerializedName("Value")
	 private String value;

	 public String getTag() {
		return tag;
	 }

	 public void setTag(String tag) {
		this.tag = tag;
	 }

	 public String getValue() {
		return value;
	 }

	 public void setValue(String value) {
		this.value = value;
	 }
	 @Override
		public String toString() {
			return "tag=" + tag + ", value=" + value ;
		}



	
	    
}
