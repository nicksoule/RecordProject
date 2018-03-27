package com.gc.model;

public class Record {

	private int id;
	private String title;
	private String price;
	private String date;
	private String body;
	private String image;
	
	public Record() {
		super();
	}

	public Record(int id, String title, String price, String date, String body, String image) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.date = date;
		this.body = body;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
}
