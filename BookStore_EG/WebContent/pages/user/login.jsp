<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
<!-- include静态包含  静态资源 -->
<%@ include file="/WEB-INF/include/base.jsp" %>

<script type="text/javascript">
/* 
	$(function(){
		$("#sub_btn").click(function(){
			//获取账号密码验证规则
			var username = $("input[name='username']").val();
			var password = $("input[name='password']").val();
			//希望验证规则,js支持正则表达式，可以直接创建正则对象，然后调用该对象的test方法，传入要验证的字符串，如果验证通过返回true
			//每个类型的字符串如果规则不一样，需要创建一个对应的正则对象
			var unameReg = /^[a-z0-9_-]{3,16}$/;
			var pwdReg = /^[a-z0-9_-]{6,18}$/;
			
			var flag = unameReg.test(username);
			if(!flag){
				layer.msg("用户名规则错误！！！", {time:5000, icon:1});
				//layer.load(2 , {time:10000});
				//取消提交按钮的默认行为
				return false;
			}
			if(!pwdReg.test(password)){
				alert("密码规则错误！！");
				//取消提交按钮的默认行为
				return false;
			}
		});
	});
 */
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								
								<%-- <%
								//获取域中设置的错误消息
								String errorMsg = (String)request.getAttribute("errorMsg");
								//当前页面需要被复用
								
								//如果有，则显示errorMsg
								if(errorMsg == null){
									//如果没有，代表正常访问：希望显示  请输入用户名和密码
									errorMsg = "请输入用户名和密码";
								}
								%> 
								<!-- 输出变量值需要jsp表达式 -->
								<span class="errorMsg"><%=errorMsg %></span>	--%>
								<span class="errorMsg">${empty errorMsg?"请输入用户名和密码":errorMsg }</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="POST">
								<input type="hidden" name="type" value="login">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
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