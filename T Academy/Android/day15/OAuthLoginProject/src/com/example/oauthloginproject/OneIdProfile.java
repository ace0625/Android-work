package com.example.oauthloginproject;

import java.io.Serializable;

public class OneIdProfile implements Serializable {

	private static final long serialVersionUID = -299030578909221266L;
	
	private String email;
	private String serviceNo;
	private String userId;
	private String userName;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
