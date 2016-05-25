package com.example.assignment;

import java.io.Serializable;

public class MyItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String address;
	private int radG;
	
	
	public MyItem() {}

	public MyItem(String name, String address, int radG)
	{
		this.name = name;
		this.address = address;
		this.radG = radG;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRadG() {
		
		return radG;
	}

	public void setRadG(int radG) {
		this.radG = radG;
	}

	@Override
	public String toString() {
		return "MyItem [name=" + name + ", address=" + address + ", radG="
				+ radG + "]";
	}




	
}
