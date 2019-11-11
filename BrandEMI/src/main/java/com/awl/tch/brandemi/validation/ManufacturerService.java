/**
 * 
 */
package com.awl.tch.brandemi.validation;

import java.util.HashMap;

/**
 * @author kunal.surana
 *
 */
public interface ManufacturerService {
	
	
	/**
	 * Blocks product with given serial number 
	 * @param config Confiuration parameters like url, username and password
	 * @param args first element will always be serial number and then optional SKU code
	 * @return String in following format  <TRUE/FALSE>/<MESG>
	 */
	public String blockSerialNumber(HashMap<String,String> config, String... args);
	
	/**
	 * UnBlocks product with given serial number 
	 * @param config Confiuration parameters like url, username and password
	 * @param args first element will always be serial number and then optional SKU code
	 * @return String in following format <TRUE/FALSE>/<MESG> 
	 */
	public String unblockSerialNumber(HashMap<String,String> config, String... args);
}
