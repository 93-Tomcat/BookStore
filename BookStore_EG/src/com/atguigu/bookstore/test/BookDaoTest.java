package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class BookDaoTest {
	private BookDao dao = new BookDaoImpl();
	@Test
	public void testGetBook() {
		//如果查询到的列的字段名和 javabean的属性名不一样，属性值设置失败，解决：查询时只要保证列名和bean的属性名一样即可
		String bookId = "4";
		Book book = dao.getBookById(bookId);
		System.out.println(book);
	}
	@Test
	public void testGetBookList() {
		List<Book> list = dao.getBookList();
		System.out.println(list);
	}
	@Test
	public void testSaveBook() {
		Book b1 = new Book(null, "宋红康", "/static/img/default.jpg", "java从入门到放弃", 1.99, 1000, 1000000);
		Book b2 = new Book(null, "大海", "/static/img/default.jpg", "bigdata从入门到放弃", 1.99, 1000, 1000000);
		Book b3 = new Book(null, "阿福", "/static/img/default.jpg", "Andorid从入门到放弃", 1.99, 1000, 1000000);
		Book b4 = new Book(null, "志勇", "/static/img/default.jpg", "H5从入门到嫁人", 199.0, 1000, 1000000);
		Book b5 = new Book(null, "柴柴", "/static/img/default.jpg", "python从入门到放弃", 199.0, 1000, 1000000);
		
		int i = dao.saveBook(b1);
		dao.saveBook(b2);
		dao.saveBook(b3);
		dao.saveBook(b4);
		dao.saveBook(b5);
		
		System.out.println(i>0?"保存成功":"插入失败");
	}
	@Test
	public void testDeleteBook() {
		String bookId = "4";
		int deleteBookById = dao.deleteBookById(bookId);
		System.out.println(deleteBookById);
	}

}
