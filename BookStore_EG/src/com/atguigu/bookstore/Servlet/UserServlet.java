package com.atguigu.bookstore.Servlet;

import java.io.IOException;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 	处理和用户相关的请求
 * 	步骤：
 * 		1、在login.jsp和regist页面中添加隐藏域携带 type=login和type=regist参数
 * 			再修改action地址提交请求给UserServlet
 * 		2、UserServlet中根据type值判断调用哪个方法
 * 
 *  当前UserServlet只能处理用户相关的请求
 * 	以后还有图书相关请求：BookServlet
 * 	以后还有订单相关请求：OrderServlet
 * 	以后还有购物车相关请求：CartServlet
 * 
 * 	以后为了避免doGet中的反射代码编写多次，可以提取到一个父类BaseServlet中，统一管理子类对象方法的调用
 * 
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService service = new UserServiceImpl();
	
	//处理验证用户名的请求
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		//调用业务层处理业务
		boolean b = service.checkUsername(username);
		//给ajax响应  一般交给浏览器状态码  0代表不可用  1代表可用
		if(b) {
			response.getWriter().write("1");//注意一定要写成字符串
		}else {
			response.getWriter().write("0");
		}
		
	}
	
	
	//处理注销请求的代码
	protected void Logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//移除session域中的user对象
		//销毁session对象
		HttpSession session = request.getSession();
		session.invalidate();//立即销毁
		//跳转回注销之前的页面
		response.sendRedirect(request.getHeader("referer"));

	}
	
	//private UserDao dao = new UserDaoImpl();
	
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求参数中的验证码
		String code = request.getParameter("code");
		//2.获取KaptchaServlet被访问时存在session域中的字符串，属性名默认是KAPTCHA_SESSION_KEY
		HttpSession session = request.getSession();
		String serverCode = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
		//3.比较字符串
		if(code != null && code.equals(serverCode)) {
			//4.如果一样处理注册请求并移除session中的验证码字符串
			session.removeAttribute("KAPTCHA_SESSION_KEY");
			//1.获取请求参数
			User registUser = WebUtils.param2Bean(request, new User());
//			String username = request.getParameter("username");
//			String password = request.getParameter("password");
//			String email = request.getParameter("email");
			//将参数封装成对象
			//User registUser = new User(null, username, password, email);
			//2.调用其它类处理业务逻辑
			//int i = dao.saveUser(registUser);
			boolean b = service.doRegist(registUser);
			//3.根据处理结果给用户响应
			if(b) {
				//注册成功 重定向到成功页面
				//重定向的绝对路径有浏览器解析，基准地址到服务器(http://localhost:8080)
				response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
			}else {
				//注册失败，设置错误消息
				request.setAttribute("errorMsg", "用户名已存在！");
				//request.getRequestDispatcher("pages/user/regist.html").forward(request,response);
				//转发的绝对路径有服务器解析，基准地址到项目(http://localhost:8080/BookStore_EG01)
				//转发会造成相对路径失效
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			}
		
		}else {
			//5.如果不一样给出提示
		
			request.setAttribute("errorMsg", "验证码错误！");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
		
		
	}	
		
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.获取请求参数
		User loginUser = WebUtils.param2Bean(request, new User());
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		User loginUser = new User(null, username, password, null);
		
		//2.调用其它类处理业务
		User user = service.doLogin(loginUser);
		
		//3.根据处理结果给用户响应
		if(user == null) {
			//设置错误信息在域中共享
			request.setAttribute("errorMsg", "账号或者密码错误！");
			//查询失败  登陆失败，转发到Login.html让用户继续登陆,转发会造成转发后的页面中的相对路径失效
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//登陆成功 重定向到成功页面
			//将用户对象共享到session域中保存登录状态
			HttpSession session = request.getSession();//默认传入true，如果本次会出啊存在session对象则直接返回，如果没有则新创建返回
			//request.getSession(false)  若果本次会话有session则返回  若果没有则返回null
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	}

}
