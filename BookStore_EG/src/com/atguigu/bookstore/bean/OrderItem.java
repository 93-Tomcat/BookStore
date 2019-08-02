package com.atguigu.bookstore.bean;

public class OrderItem {
	private Integer id;
	private String title;
	private String author;
	private String imgPath;
	private double price;
	private int count;
	private double amount;
	private String orderId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public OrderItem(Integer id, String title, String author, String imgPath, double price, int count, double amount,
			String orderId) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.imgPath = imgPath;
		this.price = price;
		this.count = count;
		this.amount = amount;
		this.orderId = orderId;
	}
	public OrderItem() {
		super();
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", title=" + title + ", author=" + author + ", imgPath=" + imgPath + ", price="
				+ price + ", count=" + count + ", amount=" + amount + ", orderId=" + orderId + "]";
	}
	
	
}
