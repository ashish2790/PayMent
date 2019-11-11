package com.awl.tch.brandemi.model;

public class MenuNode {
	private int previousId=-1;
	private int currentId;
	private String displayName;
	private String headerName;
	
	
	public MenuNode(int previousId, int currentId, String displayName, String headerName) {
		super();
		this.previousId = previousId;
		this.currentId = currentId;
		this.displayName = displayName;
		this.headerName = headerName;
	}
	public int getPreviousId() {
		return previousId;
	}
	public void setPreviousId(int previousId) {
		this.previousId = previousId;
	}
	public int getCurrentId() {
		return currentId;
	}
	public void setCurrentId(int currentId) {
		this.currentId = currentId;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentId;
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
		MenuNode other = (MenuNode) obj;
		if (currentId != other.currentId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MenuNode [previousId=" + previousId + ", currentId=" + currentId + ", displayName=" + displayName
				+ ", headerName=" + headerName + "]";
	}
	
	
}
