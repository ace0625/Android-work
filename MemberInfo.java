package com.example.assignment2;

import java.io.Serializable;

public class MemberInfo implements Serializable {


	private static final long serialVersionUID = -8701883799659571245L;

	private String name;
	private String id;
	private String pw;
	private String tel;
	private String address;
	private String comment;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "MemberInfo [name=" + name + ", id=" + id + ", pw=" + pw
				+ ", tel=" + tel + ", address=" + address + ", comment="
				+ comment + "]";
	}
	
	
	
}
