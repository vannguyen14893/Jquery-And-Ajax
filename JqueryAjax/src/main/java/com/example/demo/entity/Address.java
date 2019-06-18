package com.example.demo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String nameAddress;

	public String getNameAddress() {
		return nameAddress;
	}

	public void setNameAddress(String nameAddress) {
		this.nameAddress = nameAddress;
	}

}
