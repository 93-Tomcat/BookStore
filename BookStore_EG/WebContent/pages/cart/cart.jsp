<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<!-- include静态包含  静态资源 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".countInp").change(function(){
			//alert(this.value);
			//获取修改后的数量
			var count = this.value;
			//获取要修改的购物项的图书id
			var bookId = this.id;//使用input携带图书的id
			
			//提交请求给服务器CartServlet.updateCartItem
			//只需要修改地址栏地址，浏览器会自动发起新的请求
			window.location = "CartServlet?type=updateCartItem&totalCount="+count+"&bookId="+bookId;
		});
	
		//删除超链点击时给用户选择题提示
		$(".delA").click(function(){
			var title= $(this).parents("tr").children("td").eq(1).text();
			if(!confirm("你真的要删除<"+ title +">吗？")){
				return false;//取消删除超链接的默认行为
			}
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<!-- 引入登录状态栏提取页面 -->
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty sessionScope.cart.map }">
				<h3 align="center">购物车空空如也，快去添加吧！</h3>
			</c:when>
			<c:otherwise>
			<table>
					<tr>
						<td>商品封面</td>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${cart.cartItemList }" var="cartItem">
						<tr>
							<td><img style="width: 50px;height: 50px;" alt="" src="${pageContext.request.contextPath }${pageScope.cartItem.book.imgPath }"></td>
							<td>${pageScope.cartItem.book.title }</td>
							<td><input class="countInp" id="${cartItem.book.id }" value="${cartItem.count }" style="width:40px;text-align:center;"/></td>
							<td>${cartItem.book.price }</td>
							<td>${cartItem.amount }</td>
							<td><a class="delA" href="CartServlet?type=deleteCartItem&bookId=${cartItem.book.id }">删除</a></td>
						</tr>	
					</c:forEach>		
				</table>
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount }</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${cart.totalAmount }</span>元</span>
					<span class="cart_span"><a href="CartServlet?type=clearCart">清空购物车</a></span>
					<span class="cart_span"><a href="OrderServlet?type=checkOut">去结账</a></span>
				</div>
			</c:otherwise>
		</c:choose>
	
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>