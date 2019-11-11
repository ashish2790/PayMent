package com.awl.tch.iso8583;

public final class STANGenerator{
	
	private static int trace;
	private static int lastTrace;
	
	private STANGenerator(){
	}
	
	
	public static  int nextTrace() {
		// TODO Auto-generated method stub
		
		synchronized (STANGenerator.class) {
			lastTrace = trace;
			trace++;			
		}
		if(trace > 999999)
			trace = 1;
		return trace;
	}

	public static int getLastTrace() {
		// TODO Auto-generated method stub
		return lastTrace;
	}
	
	
	
	public static void main(String[] args) {
			System.out.println(""+STANGenerator.nextTrace());
	}

}
