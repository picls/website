<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor = "grey">
  
	  <!-- 居中对齐 -->
	  <center>
		      用户登录
		  <br>
		  <hr>
		  <%
		  	String flag = request.getParameter("errNo");
		  	try{
		  	
			  	if(flag.equals("1")){
			  		out.println("密码错误");
			  	}
			  	if(flag.equals("2")){
			  		out.println("用户名不存在");
			  	}
		  	}catch(Exception e){
		  		e.printStackTrace();
		  	}
		  	
		  	
		   %>
		  <form action = "loginCl.jsp" method="post">
		  	用户名：<input type="text" name="username"><br>
		  	密&nbsp;&nbsp;码：<input type="password" name ="passwd"><br>
		  	
		  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交">
		  	
		  	&nbsp;<input type="reset" value="重置">
		  	
		  	
		  </form>
	  </center>
  </body>
</html>
