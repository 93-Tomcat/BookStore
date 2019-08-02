<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<!-- include静态包含  静态资源 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		//给用户名表单项绑定内容改变的监听
		$("input[name='username']").change(function(){
			//提交当前表单项中的value值给服务器验证
			var username = this.value;
			var type = "checkUsername";
			$.post("UserServlet",{"type":type,"username":username},function(a){
				//alert(a);
				if(a==0){
					
					$(".errorMsg").text("用户名已存在！");
					//设置注册按钮禁用
					$("#sub_btn").prop("disabled",true);
					$("#sub_btn").css("background-color","gray");
				}else if(a==1){
					$(".errorMsg").text("恭喜你 用户名可用！");
					$("#sub_btn").prop("disabled",false);
					
				}
			});
		});
		
		
		
		
		var i = 1;
		//给验证码绑定单击事件  点击刷新验证码
		//每次请求code.jpg，servlet都会返回一张新的验证码图片
		$("#codeImg").click(function(){
			this.src="code.jpg?t="+(i++);
		});
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
							<%-- 
								<%
								String errorMsg = (String)request.getAttribute("errorMsg");
								if(errorMsg == null){
									errorMsg = "";
								}
								%>
								
								<span class="errorMsg"><%=errorMsg %></span> 
							--%>
							<span class="errorMsg">${errorMsg }</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="POST">
								<input type="hidden" name="type" value="regist">
									<label>用户名称：</label>
									<!-- 如果注册失败转发回来，requset对象中就包含注册失败的参数  
										request.getParameter()   获取用户提交的表单参数    类型（String）
										request.getAttrgetAttribute()  获取的是我们自己在java代码中设置的属性值  类型（Object）
									
									-->
									<input value="<%=request.getParameter("username")==null?"":request.getParameter("username") %>" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="<%=request.getParameter("email")==null?"":request.getParameter("email") %>" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img alt="" id="codeImg" src="code.jpg" style="float: right; margin-right: 40px; width:90px;height:40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>