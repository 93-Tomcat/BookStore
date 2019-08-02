<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<!-- include静态包含  静态资源 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">

$(function(){
	
	$(".delA").click(function(){
		
		var title = $(this).parents("tr").children("td").first().text();
		//获取被点击的a标签的href地址
		//this代表被点击的a的dom对象
		var address = this.href;
		
		layer.confirm("你真的要删除 《 "+ title + "》 吗？" , {title:"警告！" , icon: 3} , function(index){
			//点击确定按钮的处理方法
			layer.close(index);//关闭  当前提示框  index是提示框的层级索引
			//删除图书 只要让超链接提交默认行为即可
			//获取当前超链接的href地址，然后让浏览器跳转即可
			//修改浏览器的url地址栏地址，浏览器会自动向新地址发起请求
			window.location = address;
		});
		
		return false;
	});
});


<%--
	$(function(){
			$(".delA").click(function(){
				var title = $(this).parents("tr").children("td").first().text();
				if(confirm("你真的要删除 < " + title + "> 吗？")){
					//取消删除
					return flase;
				}
			});
	});
--%>
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<!-- 
			遍历显示图书的步骤：
				1、判断图书集合是否为空
				2、如果有图书遍历通过tr显示：  一个集合对应一个table,一行对应一个对象
		 -->
		<table>
		<c:choose>
			<c:when test="${empty page.data }">
				<!-- 没有图书集合 -->
				<tr>
				<td colspan="7"><h4 style="font-size: 30px; color: red;">生意太好了，图书都卖光了，赶紧添加吧！</h4></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>名称</td>
					<td>价格</td>
					<td>作者</td>
					<td>销量</td>
					<td>库存</td>
					<td colspan="2">操作</td>
				</tr>
			<!-- 遍历 -->
				<c:forEach items="${requestScope.page.data }" var="book">
					<tr>
						<td>${book.title }</td>
						<td>${book.price }</td>
						<td>${book.author }</td>
						<td>${book.sales }</td>
						<td>${book.stock }</td>
						<td><a href="BookManagerServlet?type=findBook&bookId=${book.id }">修改</a></td>
						<td><a class="delA" href="BookManagerServlet?type=deleteBook&bookId=${book.id }">删除</a></td>
					</tr>	
				</c:forEach>
			</c:otherwise>
		</c:choose>
					
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_add.jsp">添加图书</a></td>
			</tr>	
		</table>
	</div>
	<!-- 页面中可能需要使用的page对象没有数据，导致begin值在计算时会出现负数页面报错  -->
	<c:if test="${!empty page.data }">
	<!-- 
			<span class="current">1</span>
			<a href="#?page=2">2</a>
			<a href="#?page=3">3</a>
			<a href="#?page=4">4</a>
			<a href="#?page=5">5</a>
			<a href="#?page=6">6</a>
			<a href="#?page=7">7</a>...
			<a href="#?page=199">199</a>
			<a href="#?page=200">200</a>
	 -->
		<div class="scott">
		<!-- 根据总页码和当前页码动态计算begin和end的值 -->
		<c:choose>
			<c:when test="${page.totalPage<5 }">
				<!-- 向域中设置begin和end的值 -->
				<c:set value="1" var="begin"></c:set>
				<c:set value="${page.totalPage }"></c:set>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${page.pageNumber<=3 }">
					<!-- 总页码>5   当前页码<=3 -->
					<c:set value="1" var="begin"></c:set>
					<c:set value="5" var="end"></c:set>
					</c:when>
					<c:otherwise>
					<!-- 总页码>5   当前页码>3 -->
					<c:set value="${page.pageNumber-2 }" var="begin"></c:set>
					<c:set value="${page.pageNumber+2 }" var="end"></c:set>
					<!-- 总页码>5  当前页码>3 但是计算的end值超出了totalPage -->
					<c:if test="${end>page.totalPage }">
					<c:set value="${page.totalPage }" var="end"></c:set>
					<c:set value="${end-4 }" var="begin"></c:set>
					</c:if>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		<!-- 上一页就是当前页码-1 -->
			<a href="BookManagerServlet?type=findPage&pageNumber=${page.pageNumber-1 }"> &lt; </a>
			<!-- 遍历产生页码：1 ~ page.totalPage -->
			
			<c:forEach begin="${begin }" end="${end }" var="index">
			<!-- 判断当前产生索引值是不是当前页码，如果是高亮显示 -->
			<c:choose>
				<c:when test="${index==page.pageNumber }">
					<!-- 正在遍历当前的页面 -->
					<span class="current">${index }</span>
				</c:when>
				<c:otherwise>
				<a href="BookManagerServlet?type=findPage&pageNumber=${index }">${index }</a>
				</c:otherwise>
			</c:choose>
			</c:forEach>
			
			<a href="BookManagerServlet?type=findPage&pageNumber=${page.pageNumber+1 }"> &gt; </a>
		共${page.totalPage }页，${page.totalCount }条记录 到第<input
			value="${page.pageNumber }" name="pn" id="pn_input" />页 <input
			id="sendBtn" type="button" value="确定">
			
			
			<script type="text/javascript">
			//分页到导航栏的点击按钮绑定单击事件
				$("#sendBtn").click(function(){
					//获取用户输入的页面  然后跳转
					var pageNumber = $("#pn_input").val();
					//获取input修改之前的值
					var no = $("#pn_input")[0].defaultValue;
					//验证用户输入的 页码1是否正常
					if(!isNaN(pageNumber)){//isNan: is not a number 如果不是一个数字返回 true，如果是一个数字返回false
					//修改地址栏地址  浏览器会自动向新地址发起请求
					window.location = "BookManagerServlet?type=findPage&pageNumber="+pageNumber;	
					}else{
						alert("请输入正确的页码");
						$("#pn_input").val(no)//修改input的value属性值为修改之前的值
					}
					
				});

			</script>
			
			
		</div>
	</c:if> 
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>