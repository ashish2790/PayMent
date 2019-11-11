package com.awl.tch.bean;

public class EmiOemMaster {

	private Long id;
	private String code;
	private String name;
	private String status;
	private String isParent;
	private String parentKey;
	private String description;
	private String longModelNumber;
	
	public EmiOemMaster(Long id, String code, String name, String status,
			String isParent, String parentKey, String description,
			String longModelNumber) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.status = status;
		this.isParent = isParent;
		this.parentKey = parentKey;
		this.description = description;
		this.longModelNumber = longModelNumber;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the isParent
	 */
	public String getIsParent() {
		return isParent;
	}
	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	/**
	 * @return the parentKey
	 */
	public String getParentKey() {
		return parentKey;
	}
	/**
	 * @param parentKey the parentKey to set
	 */
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the longModelNumber
	 */
	public String getLongModelNumber() {
		return longModelNumber;
	}
	/**
	 * @param longModelNumber the longModelNumber to set
	 */
	public void setLongModelNumber(String longModelNumber) {
		this.longModelNumber = longModelNumber;
	}
	
	
}
