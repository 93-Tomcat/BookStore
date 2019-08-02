package com.atguigu.bookstore.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * BaseServlet:
 * 		1、当前Servlet需要继承BaseServlet
 * 		2、当前servlet被访问时，走生命周期方法时，调用doGet方法必须调用BaseServlet
 * 			自己的doGet和doPost删除掉
 * 		3、当访问当前Servlet时必须提供 type参数=要调用的方法名
 * 			当前Servlet需要提供一个处理请求的方法
 * 
 * 处理管理员图书相关的请求
 * 
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
  //private BookDao dao = new BookDaoImpl();
    private BookService service = new BookServiceImpl();
  //6.处理查询分页数据的请求
    protected void findPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	//1.获取请求参数:要查询分页的页码
    	String pageNumber = request.getParameter("pageNumber");
    	//2.定义size  每页显示的记录条数
    	int size = 4;
    	//调用service的方法处理业务
    	Page<Book> page = service.findPage(pageNumber, size);
    	//5、将page对象存到域中
    	request.setAttribute("page", page);
    	//6、转发到book_manager.jsp页面显示分页数据
    	request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    	
    	
    }
    
    
  //5.处理修改图书的请求
    protected void updateBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	//获取表单提交的请求参数：修改之前的页面地址字符串
    	String ref = request.getParameter("ref");
    	
    	//1、获取请求参数封装为book对象
    	//使用WebUtils.param2bean方法：必须保证提交的参数名 和 对象的属性名一样
    	//参数从book_edit.jsp页面中提交的
    	Book book = WebUtils.param2Bean(request, new Book());
    	//2、调用BookService修改图书
    	service.updateBook(book);
    	//3、重定向到图书列表显示页面
    	response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=findPage&pageNumber=1");
    }   
    
  //4.处理查询指定id图书请求
    protected void findBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	//获取修改之前的页面
    	String referer = request.getHeader("referer");
    	//1.获取请求参数
    	String bookId = request.getParameter("bookId");
    	//2.调用service查询
    	Book book = service.findBook(bookId);
    	//3.存到域中并转发到book_edit.jsp页面
    	request.setAttribute("book",book);
    	//将修改之前的页面地址存到域中
    	request.setAttribute("ref",referer);
    	request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }
    

  //3.处理管理员添加图书的请求
    protected void addBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	//1.获取请求参数封装给对象
    	//当页面提交请求时，name属性值和Book类的属性名一样，值就是属性名对应的值
    	Book book = WebUtils.param2Bean(request, new Book());
    	//2.调用BookService的saveBook方法处理业务
    	service.saveBook(book);
    	//3.重定向跳转到图书列表显示页面
    	response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=findPage&pageNumber=1");
    }
    
    
   
  //2.处理管理员删除指定图书的请求：访问此方法是BookManagerServlet?type=deleteBook
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	//1.获取请求参数
    	String bookId = request.getParameter("bookId");
    	//2.调用service删除图书的方法
    	service.deleteBook(bookId);
    	//获取当前请求从哪个页面跳转过来  referer地址
    	String referer=request.getHeader("referer");
    	//3.给用户响应：显示删除后的所有图书列表
    	//如果需要使用request域共享数据使用 转发，如果不需要使用重定向
    	//跳转会删除之前的页面
    	response.sendRedirect(referer);//告诉浏览器访问Servlet并携带参数
    	//book_manager 用来显示图书数据  必须先查询才能再显示
    }
    
  /**  
  //以后不再使用 
  //1、处理查询所有图书的请求: 如果浏览器请求希望服务器此方法调用，BookManagerServlet?type=findAllBooks
    protected void findAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用其它类查询所有图书集合
    	List<Book> books = dao.getBookList();
    	//将图书集合共享到域中
    	request.setAttribute("books", books);
    	//转发到/pages/manager/book_manager.jsp 遍历显出图书集合
    	request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	*/


}
