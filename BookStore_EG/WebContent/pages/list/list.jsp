<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- include静态包含  静态资源 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//当加入购物车被点击时  提交Ajax请求
		$(".addA").click(function(){
			 var id = this.id;
			//CartServlet?type=addBook&bookId=${book.id }
			$.ajax({
				type:"GET",
				url:"CartServlet",
				data:{"type":"addBook","bookId":id},
				success:function(a){
					//alert(JSON.stringify(a));
					$("#countSpan").text("您的购物车中有"+ a.totalCount+"件商品");
					$("#titleDiv").html('您刚刚将<span style="color: red">'+a.title +'</span>加入到了购物车中');
				},
				dataType:"json"
			});
			//取消默认行为
			return false;
		});
	});

</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<!-- 引入登录状态栏提取页面 -->
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="BookClientServlet">
					<input type="hidden" name="type" value="findPageByPrice"/>
					<input type="hidden" name="pageNumber" value="1"/>
				价格：<input type="text" name="min" value="${param.min }"> 元 - <input type="text" name="max" value="${param.max }"> 元 
					<input type="submit" value="查询"/>
				</form>
			</div>
			<div style="text-align: center">
				<c:choose>
					<c:when test="${empty sessionScope.cart.map }">
							<span id="countSpan">您的购物车中还没有商品，快去添加吧！</span>
						<div id="titleDiv">
							<br/>
						</div>
					</c:when>
					<c:otherwise>
						<span id="countSpan">您的购物车中有${sessionScope.totalCount }件商品</span>
						<div id="titleDiv">
							您刚刚将<span style="color: red">${sessionScope.title }</span>加入到了购物车中
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<c:choose>
				<c:when test="${empty page.data }">
					<h4 style="text-align: center;color: red;margin-top: 80px;">你来晚了一步，图书已经卖空了！！！</h4>
				</c:when>
				<c:otherwise>
					<!-- 有分页数据 -->
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt="" src="${pageContext.request.contextPath }${book.imgPath }" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span>
									<span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span>
									<span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span>
									<span class="sp2">￥${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span>
									<span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span>
									<span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<a id="${book.id} " class="addA" href="CartServlet?type=addBook&bookId=${book.id }">加入购物车</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		
		</div>
	</div>
	
	
	<!-- 页面中可能需要使用的page对象没有数据，导致begin值在计算时会出现负数，页面报错 -->
		<c:if test="${!empty page.data }">
		<div class="scott">
			<!-- 根据总页码和当前页码动态计算 begin和end的值 -->
			<c:choose>
				<c:when test="${page.totalPage<5 }">
					<!-- 向域中设置begin和end的值 -->
					<c:set value="1" var="begin"></c:set>
					<c:set value="${page.totalPage }" var="end"></c:set>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${page.pageNumber<=3 }">
							<!-- 总页码>5 当前页码<=3 -->
							<c:set value="1" var="begin"></c:set>
							<c:set value="5" var="end"></c:set>
						</c:when>
						<c:otherwise>
							<!-- 总页码>5 当前页码>3 -->
							<c:set value="${page.pageNumber-2 }" var="begin"></c:set>
							<c:set value="${page.pageNumber+2 }" var="end"></c:set>
							<c:if test="${end>page.totalPage }">
								<!-- 总页码>5 当前页码>3 但是 计算的end值超出了totalPage -->
								<c:set value="${page.totalPage }" var="end"></c:set>
								<c:set value="${end-4 }" var="begin"></c:set>	
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<!-- 上一页就是当前页码-1 -->
			<a href="BookClientServlet?type=${type }&pageNumber=${page.pageNumber-1 }&min=${param.min}&max=${param.max}"> &lt; </a>
				<!-- 遍历产生页码： 1 ~ page.totalPage -->
				<c:forEach begin="${begin }" end="${end }" var="index">
					<!-- 判断当前产生索引值是不是当前页码，如果是高亮显示 -->
					<c:choose>
						<c:when test="${index==page.pageNumber }">
							<!-- 正在遍历当前页的页码 -->
							<span class="current">${index }</span>
						</c:when>
						<c:otherwise>
							<a href="BookClientServlet?type=${type }&pageNumber=${index }&min=${param.min}&max=${param.max}">${index }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			<a href="BookClientServlet?type=${type }&pageNumber=${page.pageNumber+1 }&min=${param.min}&max=${param.max}"> &gt; </a>
		共${page.totalPage }页，${page.totalCount }条记录 到第
			<input value="${page.pageNumber }" name="pn" id="pn_input" />页
			<input id="sendBtn" type="button" value="确定">
			
			<script type="text/javascript">
				//给分页导航栏的点击按钮绑定单击事件
				$("#sendBtn").click(function(){
					//获取用户输入的页码  然后跳转
					var pageNumber = $("#pn_input").val();
					//获取input修改之前的值:将jquery对象转为dom对象，获取dom对象的属性值
					var no = $("#pn_input")[0].defaultValue;
					//验证用户输入的 页码是否正确
					if(!isNaN(pageNumber)){//isNaN: is not a number ，如果不是一个数字返回true ，如果是一个数字返回false
						//修改地址栏地址  浏览器会自动向新地址发起请求
						window.location = "BookClientServlet?type=${type }&pageNumber="+pageNumber+"&min=${param.min}&max=${param.max}";
						//在js代码中如果需要使用EL表达式，必须在引号内使用
					}else{
						alert("请输入正确的页码！！");
						$("#pn_input").val(no);//修改input的value属性值为修改之前的值
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