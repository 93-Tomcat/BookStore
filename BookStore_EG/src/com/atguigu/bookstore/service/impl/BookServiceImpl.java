package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService{
	private BookDao dao = new BookDaoImpl();
	@Override
	public boolean saveBook(Book book) {
		int i = dao.saveBook(book);
		return i>0;
	}

	@Override
	public boolean deleteBook(String bookId) {
		return dao.deleteBookById(bookId)>0;
	}

	@Override
	public boolean updateBook(Book book) {
		
		return dao.updateBookById(book)>0;
	}

	@Override
	public Book findBook(String bookId) {
		
		return dao.getBookById(bookId);
	}

	@Override
	public Page<Book> findPage(String pageNumber, int size) {
		//3、创建page对象携带参数
    	Page<Book> page = new Page<Book>();
    	//数据类型转换
    	int number = 1;
		try {
			number = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	page.setPageNumber(number);
    	page.setSize(size);
    	//4、调用BookDao的getPage方法查询分页数据
    	page = dao.getPage(page);
		return page;
	}

	@Override
	public Page<Book> findPageByPrice(String pageNumber, String min, String max, int size) {
		Page<Book> page = new Page<Book>();
		int number =1;
		double minPrice =0;
		double maxPrice =Double.MAX_VALUE;
		try {
			number = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			minPrice = Double.parseDouble(min);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			maxPrice = Double.parseDouble(max);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		page.setPageNumber(number);
		page.setSize(size);
		
		//page:data     totalCount
		//调用dao层查询和数据库相关的数据
		
		return dao.getPageByPrice(page,minPrice,maxPrice);
	}

}
