package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{
	/**
	 * 购物项对应的图书信息
	 */
	private Book book;
	/**
	 * 单本图书购买数量
	 */
	private int count;
	/**
	 * 单本图书的总金额
	 *  -book.getPrice()*count;
	 */
	private double amount;
	
	public CartItem() {
		super();
	}

	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		//解决精度问题
				//1、将计算的数字都转为 BigDecimal对象
				BigDecimal bd1 = new BigDecimal(book.getPrice()+"");
				BigDecimal bd2 = new BigDecimal(count+"");
				//2、调用对象的方法计算
				//bd1.multiply(multiplicand) 乘
				//bd1.subtract(subtrahend) 减
				//bd1.divide(divisor) 除
				//bd1.add(augend) 加
				BigDecimal result = bd1.multiply(bd2);
				//3、将结果转为需要使用的基本类型
				amount = result.doubleValue();
		return amount;
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount=" + getAmount() + "]";
	}


	
	
	
	
}
