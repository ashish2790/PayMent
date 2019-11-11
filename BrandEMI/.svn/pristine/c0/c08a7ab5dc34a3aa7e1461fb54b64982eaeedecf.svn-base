package com.awl.tch.brandemi.model;

public class ChildProduct {
	private Integer categoryId;
	private Integer childId;
	private Integer schemeId;
	private Integer manufacturerId;
	
	
	public ChildProduct(Integer categoryId, Integer childId, Integer schemeId, Integer manufacturerId) {
		super();
		this.categoryId = categoryId;
		this.childId = childId;
		this.schemeId = schemeId;
		this.manufacturerId = manufacturerId;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getChildId() {
		return childId;
	}


	public void setChildId(Integer childId) {
		this.childId = childId;
	}


	public Integer getSchemeId() {
		return schemeId;
	}


	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}


	public Integer getManufacturerId() {
		return manufacturerId;
	}


	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childId == null) ? 0 : childId.hashCode());
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
		ChildProduct other = (ChildProduct) obj;
		if (childId == null) {
			if (other.childId != null)
				return false;
		} else if (!childId.equals(other.childId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChildProduct [categoryId=" + categoryId + ", childId=" + childId + ", schemeId=" + schemeId
				+ ", manufacturerId=" + manufacturerId + "]";
	}

	
}
