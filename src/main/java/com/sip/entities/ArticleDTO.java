package com.sip.entities;
public class ArticleDTO {

	private long id;

	private String label;

	private float price;

	private String picture;

	public ArticleDTO() {
	} 

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPicture() {
		return picture;
	}

}
