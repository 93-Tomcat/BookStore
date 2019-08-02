package com.atguigu.bookstore.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * 处理用户相关请求
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();
	
    
    //2.处理按价格区间查询分页数据的请求
	protected void findPageByPrice(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//1.获取参数
		//页码
		String pageNumber = request.getParameter("pageNumber");
		//设置size
		int size = 4;
		//获取价格区间参数
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		//2.调用Service层的业务方法处理请求
		Page<Book> page=service.findPageByPrice(pageNumber,min,max,size);
		//将当前方法名作为参数交给分页导航栏使用
		request.setAttribute("type", "findPageByPrice");
		//3.将page对象设置到域中
		request.setAttribute("page", page);//存储的属性名需要和findPage方法名一样，因为list页面复用了
		//4.转发到list页面显示分页数据
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
	}

	//1.处理查询分页的请求
	protected void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取参数
		String pageNumber = request.getParameter("pageNumber");
		int size = 4;
		//2.调用其它类处理业务
		Page<Book> page = service.findPage(pageNumber, size);
		//将当前方法名作为参数交给分页导航栏使用
		request.setAttribute("type", "findPage");
		//3.将page对象共享到域中
		request.setAttribute("page", page);
		//4.转发到index页面显示分页数据 
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
	}

}
