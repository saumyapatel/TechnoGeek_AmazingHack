package com.amazon.dataObjects;

public class Customer {

	String cust_id="";
	String name = "";
	String notification_req = "";
	int notification_modes = 0;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNotification_req() {
		return notification_req;
	}
	public void setNotification_req(String notification_req) {
		this.notification_req = notification_req;
	}
	public int getNotification_modes() {
		return notification_modes;
	}
	public void setNotification_modes(int notification_modes) {
		this.notification_modes = notification_modes;
	}


}
