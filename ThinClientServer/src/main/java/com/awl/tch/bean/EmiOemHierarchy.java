package com.awl.tch.bean;


public class EmiOemHierarchy {

	
	private int id;
	private String name;
	private String parentKey;
	private String description;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the parentKey
	 */
	public String getParentKey() {
		return parentKey;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public EmiOemHierarchy(EMIOEMBuilder builder){
		this.name = builder.name;
		this.id = builder.id;
		this.description = builder.description;
		this.parentKey = builder.parentKey;
	}
	

	public static class  EMIOEMBuilder{
		
		private int id;
		private String name;
		private String parentKey;
		private String description;
		
		public EMIOEMBuilder(int id, String name, String parentKey,
				String description) {
			this.id = id;
			this.name = name;
			this.parentKey = parentKey;
			this.description = description;
		}
		
		/**
		 * @param id the id to set
		 */
		public EMIOEMBuilder setId(int id) {
			this.id = id;
			return this;
		}
		/**
		 * @param name the name to set
		 */
		public EMIOEMBuilder setName(String name) {
			this.name = name;
			return this;
		}
		/**
		 * @param parentKey the parentKey to set
		 */
		public EMIOEMBuilder setParentKey(String parentKey) {
			this.parentKey = parentKey;
			return this;
		}
		/**
		 * @param description the description to set
		 */
		public EMIOEMBuilder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public EmiOemHierarchy build(){
			return new EmiOemHierarchy(this);
		}
		
	}
}
