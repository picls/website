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
  
	  <!-- ���ж��� -->
	  <center>
		      �û���¼
		  <br>
		  <hr>
		  <%
		  	String flag = request.getParameter("errNo");
		  	try{
		  	
			  	if(flag.equals("1")){
			  		out.println("�������");
			  	}
			  	if(flag.equals("2")){
			  		out.println("�û���������");
			  	}
		  	}catch(Exception e){
		  		e.printStackTrace();
		  	}
		  	
		  	
		   %>
		  <form action = "loginCl.jsp" method="post">
		  	�û�����<input type="text" name="username"><br>
		  	��&nbsp;&nbsp;�룺<input type="password" name ="passwd"><br>
		  	
		  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="�ύ">
		  	
		  	&nbsp;<input type="reset" value="����">
		  	
		  	
		  </form>
	  </center>
  </body>
</html>
