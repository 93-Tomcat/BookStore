package com.atguigu.bookstore.bean;

import java.io.Serializable;

public class Book implements Serializable{
	/**
	 * 图书id
	 */
	private Integer id;
	/**
	 * 图书作者
	 */
	private String author;
	/**
	 * 图书封面地址
	 */
	private String imgPath;
	/**
	 * 图书标题
	 */
	private String title;
	/**
	 * 图书单价
	 */
	private Double price;
	/**
	 * 图书销量
	 */
	private Integer sales;
	/**
	 * 图书库存
	 */
	private Integer stock;
	
	
	
	public Book() {
		super();
	}



	public Book(Integer id, String author, String imgPath, String title, Double price, Integer sales, Integer stock) {
		super();
		this.id = id;
		this.author = author;
		this.imgPath = imgPath;
		this.title = title;
		this.price = price;
		this.sales = sales;
		this.stock = stock;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Integer getSales() {
		return sales;
	}



	public void setSales(Integer sales) {
		this.sales = sales;
	}



	public Integer getStock() {
		return stock;
	}



	public void setStock(Integer stock) {
		this.stock = stock;
	}



	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", imgPath=" + imgPath + ", title=" + title + ", price="
				+ price + ", sales=" + sales + ", stock=" + stock + "]";
	}
	
	
	
	
}
