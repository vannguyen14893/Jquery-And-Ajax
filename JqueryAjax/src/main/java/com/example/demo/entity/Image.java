package com.example.demo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Image {
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	

}
