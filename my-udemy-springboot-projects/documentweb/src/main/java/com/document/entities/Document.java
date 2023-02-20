package com.document.entities;

import jakarta.persistence.*;

@Entity
public class Document {

	@Id
	private long id;
	private String name;
	//for long object we use lob annotation
	@Lob
	private byte[] data;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
