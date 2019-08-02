package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 创建对bs_book表操作的约束方法
 * @author 93
 *
 */
public interface BookDao {
	//查询分页数据的方法
	Page<Book> getPage(Page<Book> page);
	//修改指定id的图书的方法
	int updateBookById(Book oldBook);
	//查询指定id图书
	Book getBookById(String bookId);
	//查询所有图书
	List<Book> getBookList();
	//删除指定id书籍
	int deleteBookById(String bookId);
	//保存图书到数据库
	int saveBook(Book newBook);
	Page<Book> getPageByPrice(Page<Book> page, double minPrice, double maxPrice);
}

