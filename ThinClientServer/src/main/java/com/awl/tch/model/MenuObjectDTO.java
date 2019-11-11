/**
 * 
 */
package com.awl.tch.model;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Sequence;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.UCurrentTimestamp;

/**
 * @author kunal.surana
 * DTO for table TCH_MENU_OBJECT
 */
@Table(name = "TCH_MENU_OBJECT")
public class MenuObjectDTO {
	
	@Id
	@Column(name = "TMO_ID")
	@Sequence(name = "SEQ_TC_MENUOBJECT_ID")
	private Long id;

	// Following field used in request JSON
	@Column(name = "TMO_MENU_NAME")
	private String menuName;
	
	@Column(name = "TMO_PARENT_KEY")
	private Long parentKey;
	
	@Column(name = "TMO_CREATED")
	@ICurrentTimestamp
	private String created;

	@UCurrentTimestamp
	@Column(name = "TMO_UPDATED")
	private String updated;
	
	@Column(name = "TMO_MENU_FLAG")
	private String menuFlag;
	
	@Column(name = "TMO_HEADER_NAME")
	private String headerFlag;
	
	@Column(name = "TMO_VALUE")
	private String value;

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
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the parentKey
	 */
	public Long getParentKey() {
		return parentKey;
	}

	/**
	 * @param parentKey the parentKey to set
	 */
	public void setParentKey(Long parentKey) {
		this.parentKey = parentKey;
	}

	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}

	/**
	 * @return the menuFlag
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
	 * @return the headerFlag
	 */
	public String getHeaderFlag() {
		return headerFlag;
	}

	/**
	 * @param headerFlag the headerFlag to set
	 */
	public void setHeaderFlag(String headerFlag) {
		this.headerFlag = headerFlag;
	}

	/**
	 * @return the value
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
	
	
}
