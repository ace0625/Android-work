package com.example.assignment2;

import java.io.Serializable;

public class Item implements Serializable {
	
	private static final long serialVersionUID = 631099752347879952L;
	
	private String title;
	private String count;
	private String price;
	private String image;
	private String category;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Item [title=" + title + ", count=" + count + ", price=" + price
				+ ", image=" + image + ", category=" + category + "]";
	}
	
	
	
}
