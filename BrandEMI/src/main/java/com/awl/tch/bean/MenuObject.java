package com.awl.tch.bean;

import com.google.gson.annotations.SerializedName;
	
/**
 * @author kunal.surana
 *
 */
public class MenuObject {
	
	@SerializedName("tbN")
	private String tableNumberString;
	private transient Integer tableNumber;
	
	@SerializedName("cTCd")
	private String currentTableCodeString;
	private transient Integer currentTableCode;
	
	@SerializedName("nTCd")
	private String nextTableCodeString;
	private transient Integer nextTableCode;
	
	@SerializedName("dspNm")
	private String displayName;
	
	@SerializedName("hdNm")
	private String headerName;
	
	@SerializedName("ctId")
	private String categoryIdString="0";
	private transient Integer categoryId=0;
	
	private transient String value;
	private transient String menuFlag;
	private transient boolean leaf;
	private transient Integer rootId;
	

	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public MenuObject()
	{
		
	}
	
	public MenuObject(Integer id) {
		super();
		this.id = id;
	}

	public MenuObject(Integer tableNumber, Integer currentTableCode, Integer nextTableCode, String displayName,
			String headerName, Integer id) {
		super();
		this.tableNumber = tableNumber;
		this.currentTableCode = currentTableCode;
		this.nextTableCode = nextTableCode;
		this.displayName = displayName;
		this.headerName = headerName;
		this.id = id;
		this.tableNumberString = ""+ tableNumber;
		this.currentTableCodeString = ""+ currentTableCode;
		this.nextTableCodeString = ""+ nextTableCode;
	}
	
	public MenuObject(Integer tableNumber, Integer currentTableCode, Integer nextTableCode, String displayName,
			String headerName) {
		super();
		this.tableNumber = tableNumber; 
		this.currentTableCode = currentTableCode;
		this.nextTableCode = nextTableCode;
		this.displayName = displayName;
		this.headerName = headerName;
		this.tableNumberString = ""+ tableNumber;
		this.currentTableCodeString = ""+ currentTableCode;
		this.nextTableCodeString = ""+ nextTableCode;
	}

	private transient Integer id;

	/**
	 * @return the tableNumberString
	 */
	public String getTableNumberString() {
		return tableNumberString;
	}

	/**
	 * @param tableNumberString the tableNumberString to set
	 */
	public void setTableNumberString(String tableNumberString) {
		if(tableNumberString != null){
			this.tableNumber = Integer.parseInt(tableNumberString);
		}
		this.tableNumberString = tableNumberString;
	}

	/**
	 * @return the currentTableCodeString
	 */
	public String getCurrentTableCodeString() {
		return currentTableCodeString;
	}

	/**
	 * @param currentTableCodeString the currentTableCodeString to set
	 */
	public void setCurrentTableCodeString(String currentTableCodeString) {
		if(currentTableCodeString != null){
			this.currentTableCode = Integer.parseInt(currentTableCodeString);
		}
		this.currentTableCodeString = currentTableCodeString;
	}

	/**
	 * @return the nextTableCodeString
	 */
	public String getNextTableCodeString() {
		return nextTableCodeString;
	}

	/**
	 * @param nextTableCodeString the nextTableCodeString to set
	 */
	public void setNextTableCodeString(String nextTableCodeString) {
		if(nextTableCodeString != null){
			this.nextTableCode = Integer.parseInt(nextTableCodeString);
		}
		this.nextTableCodeString = nextTableCodeString;
	}

	public Integer getTableNumber() {
		if(this.tableNumber != null){
			this.tableNumberString = tableNumber.toString();
		}
		return tableNumber;
	}

	public void setTableNumber(Integer tableNumber) {
		if(tableNumber != null){
			this.tableNumberString = tableNumber.toString();
		}
		this.tableNumber = tableNumber;
	}

	public Integer getCurrentTableCode() {
		if(this.currentTableCode != null){
			this.currentTableCodeString = currentTableCodeString.toString();
		}
		return currentTableCode;
	}

	public void setCurrentTableCode(Integer currentTableCode) {
		if(currentTableCode != null){
			this.currentTableCodeString = currentTableCode.toString();
		}
		this.currentTableCode = currentTableCode;
	}

	public Integer getNextTableCode() {
		if(this.nextTableCode != null){
			this.nextTableCodeString = nextTableCode.toString();
		}
		return nextTableCode;
	}

	public void setNextTableCode(Integer nextTableCode) {
		if(nextTableCode != null){
			this.nextTableCodeString = nextTableCode.toString();
		}
		this.nextTableCode = nextTableCode;
	}

	public String getCategoryIdString() {
		return categoryIdString;
	}
	public void setCategoryIdString(String categoryIdString) {
		if(categoryIdString != null){
			this.categoryId = Integer.parseInt(categoryIdString);
		}
		this.categoryIdString = categoryIdString;
	}
	public Integer getCategoryId() {
		if(this.categoryId != null){
			this.categoryIdString = this.categoryId.toString();
		}
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		if(categoryId != null){
			this.categoryIdString = categoryId.toString();
		}
		this.categoryId = categoryId;
	}
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	/**
	 * @return the value which is mapped with TCH_VALUE column of TCH_MENU_OBJECT table
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	

	/**
	 * @return the menuFlag which is mapped with TCH_MENU_FLAG column of TCH_MENU_OBJECT table
	 */
	public String getMenuFlag() {
		return menuFlag;
	}

	/**
	 * @param menuFlag the menuFlag to set
	 */
	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}
	

	/**
	 * @return the rootId
	 */
	public Integer getRootId() {
		return rootId;
	}

	/**
	 * @param rootId the rootId to set
	 */
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentTableCode == null) ? 0 : currentTableCode.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((nextTableCode == null) ? 0 : nextTableCode.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuObject other = (MenuObject) obj;
		if (currentTableCode == null) {
			if (other.currentTableCode != null)
				return false;
		} else if (!currentTableCode.equals(other.currentTableCode))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (nextTableCode == null) {
			if (other.nextTableCode != null)
				return false;
		} else if (!nextTableCode.equals(other.nextTableCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MenuObject [" + tableNumber + "|" + currentTableCode + "|"
				+ nextTableCode + "|" + displayName + "|" + headerName +  "|" + categoryId +  "|" + id +"]";
		
	}
	
	
}
