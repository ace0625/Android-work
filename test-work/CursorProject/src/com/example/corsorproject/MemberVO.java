package com.example.corsorproject;

import java.io.Serializable;

public class MemberVO implements Serializable {

	private static final long serialVersionUID = -2852091449520516741L;
	
	private int _id;
	private String fName;
	private String lName;
	private int age;
	private String phoneNumber;
	private int bigo;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getBigo() {
		return bigo;
	}
	public void setBigo(int bigo) {
		this.bigo = bigo;
	}
	@Override
	public String toString() {
		return "MemberVO [_id=" + _id + ", fName=" + fName + ", lName=" + lName
				+ ", age=" + age + ", phoneNumber=" + phoneNumber + ", bigo="
				+ bigo + "]";
	}
	
	
}
