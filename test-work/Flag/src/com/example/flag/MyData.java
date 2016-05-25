package com.example.flag;

import java.io.Serializable;

public class MyData implements Serializable
{

	private static final long serialVersionUID = -6242211925652756325L;
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "MyData [name=" + name + ", age=" + age + "]";
	}
	

}
