<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- 引入taglib  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- jsp页面的本质就是servlet，可以直接使用9大内置对象：request -->
   
 	 <!-- 
    	当有请求访问当前页面时，从请求报文中解析到url地中所有的内容
    	http://主机地址，端口号/项目名
    
     
     
	协议： <%=request.getScheme() %><br/>
   	主机地址：<%=request.getServerName() %><br/>
  	端口号：<%=request.getServerPort() %><br/>
  	项目名：<%=request.getContextPath() %><br/>
  	-->
<%-- <base href="http://localhost:8080/BookStore_EG02/"/>--%> 	
<%-- <base href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/"/>  --%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort }${pageContext.request.contextPath}/ "/>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<!-- 引入jquery -->
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/script/layer.js"></script>
