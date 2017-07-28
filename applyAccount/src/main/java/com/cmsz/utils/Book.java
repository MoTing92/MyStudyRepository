package com.cmsz.utils;

public class Book {

	private String bookName;

	private byte [] image;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Book(String bookName, byte[] image) {
		super();
		this.bookName = bookName;
		this.image = image;
	}
	
	public Book() {
		super();
	}
}
