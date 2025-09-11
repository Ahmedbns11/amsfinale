package com.sip.entities;

import org.hibernate.annotations.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "Label is mandatory")
	@Column(name = "label")
	private String label;
	@Column(name = "price")
	private float price;
	@Column(name = "picture")
	private String picture;

	public Article() {
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

	/**** Many To One ****/
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "provider_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Provider provider;

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
}
