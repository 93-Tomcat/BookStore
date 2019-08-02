package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao implements BookDao{
	//查询指定id的图书信息
	@Override
	public Book getBookById(String bookId) {
			String sql = "SELECT id , title , img_path imgPath , author , sales , stock , price FROM bs_book WHERE id = ?";
			return getBean(Book.class, sql, bookId);
	}

	@Override
	public List<Book> getBookList() {
		String sql = "SELECT id , title , img_path imgPath , author , sales , stock , price FROM bs_book";
		return getBeanList(Book.class, sql);
	}

	@Override
	public int deleteBookById(String bookId) {
		String sql = "DELETE FROM bs_book WHERE id = ? ";
		return update(sql, bookId);
	}

	@Override
	public int saveBook(Book newBook) {
		String sql = "INSERT INTO bs_book(title , img_path , author , sales , stock , price) "
				+ " VALUES( ?,?,?,?,?,? )";
		return update(sql, newBook.getTitle() , newBook.getImgPath() ,newBook.getAuthor() , newBook.getSales()
				,newBook.getStock() , newBook.getPrice());
		
	}

	@Override
	public int updateBookById(Book oldBook) {
		String sql =  "UPDATE bs_book  SET title =? , author=? , sales=? ,"
				+ "  stock = ? , price = ? WHERE id = ? ";
		return update(sql, oldBook.getTitle() ,oldBook.getAuthor() , oldBook.getSales()
				,oldBook.getStock() , oldBook.getPrice() , oldBook.getId());
	}

	@Override
	public Page<Book> getPage(Page<Book> page) {
		//传入的page对象已知属性值：index、size、pageNumber
		//再查询和数据库相关的数据设置给该page对象即可
		//1、图书总记录条数  并设置给page对象
		String countSql = "SELECT COUNT(1) FROM bs_book ";
		int count = getCount(countSql);
		page.setTotalCount(count);
		//2、分页的图书集合 设置给page对象
		String sql = "SELECT id , title , img_path imgPath, author , sales ,  "
				+ "stock ,price FROM bs_book LIMIT ? , ?";
		List<Book> books = getBeanList(Book.class, sql, page.getIndex() , page.getSize());
		page.setData(books);
		//返回封装完成的分页对象
		return page;
	}

	@Override
	public Page<Book> getPageByPrice(Page<Book> page, double minPrice, double maxPrice) {
		//查询价格区间的总记录条数
		String sql = "SELECT COUNT(1) FROM bs_book WHERE price BETWEEN ? AND ?";
		int count = getCount(sql, minPrice,maxPrice);
		//设置给page对象
		page.setTotalCount(count);
		//查询分页的价格区间的图书集合
		sql= "SELECT id , title , img_path imgPath, author , sales ,  "
				+ "stock ,price FROM bs_book WHERE price BETWEEN ? AND ? LIMIT ?,?";
		List<Book> list = getBeanList(Book.class, sql, minPrice, maxPrice, page.getIndex(),page.getSize() );
		page.setData(list);
		
		//返回设置所有数据的page对象
		return page;
	}

}
