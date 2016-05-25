package com.example.httpproject;

import java.io.Serializable;

public class InfoVO implements Serializable {

	private static final long serialVersionUID = -3645855794136686606L;
	
	private String id;
	private String name;
	private String email;
	private String pw;
	
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "InfoVO [id=" + id + ", name=" + name + ", email=" + email
				+ ", pw=" + pw + "]";
	}
	
	
	
	
}
