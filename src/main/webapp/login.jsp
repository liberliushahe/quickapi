<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>后台管理登录</title>
<link rel="shortcut icon" href="${ctx }/resources/images/icon.ico">
<meta charset="utf-8" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="${ctx }/resources/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/resources/framework/jquery-3.3.1.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
input.error {border: 1px solid red !important;}
.error {color: red;border-color: red;}
.valid {color: yellow;}
.tooltip-inner {color: white;}
</style>
<body>
	<div class="login_box">
		<div class="login_l_img">
			<img src="${ctx }/resources/images/login-img.png" />
		</div>
		<div class="login">
			<div class="login_logo">
				<a href="#"><img src="${ctx }/resources/images/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>QuickApi后台管理系统</p>
			</div>
			<div class=" col-sm-12">
				<span class="error">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</span>
			</div>
			<form method="post" action="${ctx }/login" id="login">
				<div class=" col-sm-12">
					<input id="username" name="username" type="text" placeholder="用户名" />
				</div>
				<div class=" col-sm-12">
					<input id="password" name="password" type="password"
						placeholder="密码" />
				</div>
				<div class="checkbox">
					<div class="form-group">
						<div class=" col-sm-6">
							<div class="checkbox">
								<label> <input type="checkbox" name="remember-me">请记住我
								</label>
							</div>
						</div>
						<div class=" col-sm-6">
							<div class="checkbox">
								<label> <a href="#" style="margin-left: 50px">忘记密码?</a>
								</label>
							</div>
						</div>
					</div>
				</div>
				<div class=" col-sm-12">
					<input value="登录" style="width: 100%;" type="submit" />
				</div>
			</form>
		</div>
	</div>
	<div class="copyright">Copyright © 2017-2018 jijiuxue All rightsReserved</div>
	<script type="text/javascript" src="${ctx }/resources/framework/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/js/login.js"></script>
</body>
</html>
