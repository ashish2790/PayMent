package com.awl.tch.egras;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tempuri.BankStatus_info;
import org.tempuri.Pos_data;

import com.egras.entity.SaveStatus;
import com.egras.exception.EGRASServiceException;

public interface EgrasAdptor {
	public static List<String> listEgras = new ArrayList<String>();
	/**
	 * Get the GRN , amount , Name and status based on otp
	 * @param otp 
	 * @author ashish.bhavsar
	 * @return 
	 * @throws RemoteException 
	 */
	public Pos_data getGRN(String otp, String endPoint) throws RemoteException;
	
	
	/**
	 * Give the information to update status in encrypted format and get the appropriate response for the same
	 * @author ashish.bhavsar
	 * @return 
	 * @throws RemoteException 
	 * @throws EGRASServiceException 
	 */
	public BankStatus_info updateStatus(SaveStatus saveStatusObj,String endpoint) throws RemoteException, EGRASServiceException;
	
	/**
	 * Get the amount related information from the service 
	 * @author ashish.bhavsar
	 * @throws RemoteException 
	 * @throws NumberFormatException 
	 */
	public Map<String,String> getNontrasoryInfo(String grn,String pos_txtID,String grn_Amt, String txt_datetime, char status,String endpoint) throws NumberFormatException, RemoteException;
	
	/**
	 * Get the list of String for non treasury information
	 * @return
	 */
	public List<String> getEgrasList();
}
