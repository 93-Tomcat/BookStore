package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookService {
	boolean saveBook(Book book); //处理添加图书的业务
	boolean deleteBook(String bookId);//处理删除图书的业务
	boolean updateBook(Book book);//处理修改图书的业务
	Book findBook(String bookId);//查询一本图书
	Page<Book> findPage(String pageNumber ,int size);//查询分页图书信息
	Page<Book> findPageByPrice(String pageNumber, String min, String max, int size);
}
