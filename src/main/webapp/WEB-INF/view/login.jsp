<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Expires" content="0"> 
<title>后台管理</title>
<link href="${ctx }/resources/css/login.css" rel="stylesheet" type="text/css" />

</head>
<style>

.service{
width:100%; 
float:left;
text-align:center;
margin-top:20px;
}
.service ul
{
    list-style:none; 
}

.service ul li
{
    display:inline;
    padding:100px;
    text-align:center;
}

</style>
<body>
<div class="login_box">
      <div class="login_l_img"><img src="${ctx }/resources/images/login-img.png" /></div>
      <div class="login">
          <div class="login_logo"><a href="#"><img src="${ctx }/resources/images/login_logo.png" /></a></div>
          <div class="login_name">
               <p>QuickApi后台管理系统</p>
          </div>
          <form method="post">
              <input name="username" type="text"  value="用户名" onfocus="this.value=''" onblur="if(this.value==''){this.value='用户名'}">
              <span id="password_text" onclick="this.style.display='none';document.getElementById('password').style.display='block';document.getElementById('password').focus().select();" >密码</span>
<input name="password" type="password" id="password" style="display:none;" onblur="if(this.value==''){document.getElementById('password_text').style.display='block';this.style.display='none'};"/>
              <input value="登录" style="width:100%;" type="submit">
          </form>
      </div>
      
</div>

      <div class="copyright">quickapi版权所有©2016-2018</div>
</body>
</html>
