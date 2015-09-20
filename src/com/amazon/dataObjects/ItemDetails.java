package com.amazon.dataObjects;

import java.security.Timestamp;

public class ItemDetails
{

	/**
	 * @param args
	 */
	private String itemID=null;
	private String attribute=null;
	private String value=null;
	long date = System.currentTimeMillis();
	private java.sql.Timestamp timeStamp= new java.sql.Timestamp(date);

	public java.sql.Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(java.sql.Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
