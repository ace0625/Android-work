package org.kimjw.navermovie;

import java.io.Serializable;

public class MyItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4130954094521764859L;
	private String title;
	private String link;
	private String image;
	private String subTitle;
	private String pubDate;
	private String director;
	private String actor;
	private String userRating;
	
	public MyItem(){
		title = "";
		link = "";
		image = "";
		subTitle = "";
		pubDate = "";
		director = "";
		actor = "";
		userRating = "";
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	
	
}
