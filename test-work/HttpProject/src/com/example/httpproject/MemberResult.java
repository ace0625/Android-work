package com.example.httpproject;

import java.io.Serializable;

public class MemberResult implements Serializable {

	private static final long serialVersionUID = 4619224916024525317L;
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "MemberResult [result=" + result + "]";
	}
	
	
}
