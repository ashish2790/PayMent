package com.awl.tch.iso8583;

import com.solab.iso8583.CustomField;

public class BCDEncodedField55 implements CustomField<String>{

	@Override
	public String decodeField(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeField(String value) {
		// TODO Auto-generated method stub
		int length = value.length()/2;
		return value.substring(0,length);
	}
	
	public static void main(String[] args) {
		String str = "aaaaa";
		
		System.out.println(str.length()/2);
	}

}
