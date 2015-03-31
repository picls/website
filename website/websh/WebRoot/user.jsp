<!DOCTYPE html>
<html>

<head lang="en">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="format-detection" content="telephone=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	
	<title>FullBBS | talk whatever you like</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta name="keywords" content="BBS,论坛" />
	<meta name="description" content="User Page,用户页面" />
	<meta name="author" Content="azx,azx-c@163.com">
	<meta name="copyright" Content="本页版权归Zerospace所有。All Rights Reserved">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
	
	<link rel="stylesheet" href="assets/css/amazeui.min.css" />
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>

</head>

<body>
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<!-- 顶栏 -->
	<%@ include file="head.jsp" %>

	<div class="am-g am-g-fixed blog-g-fixed">

		<div class="am-u-md-3 blog-sidebar">
			<div class="am-panel-group">

				<!-- 用户信息 -->
				<%@ include file="side_user.jsp"%>

				<!-- 文章信息 -->
				<%@ include file="side_page.jsp"%>

				<!-- 其他信息 -->
				<%@ include file="side_other.jsp"%>

			</div>
		</div>

		<div class="am-u-md-8">
			<%@ page import="com.CUser"%>
			<%!CUser cuser = new CUser();%>
			<%
				String id = request.getParameter("id");
				String[] info = null;
				if ((id == null) || (id == "")) {
					String username = null;
					Cookie cookie = null;
					Cookie[] cookies = null;
					cookies = request.getCookies();
					if (cookies != null) {
						for (int i = 0; i <= cookies.length - 1; i++) {
							cookie = cookies[i];
							if (cookies[i].getName().equals("username")) {
								username = cookies[i].getValue();
								break;
							}
						}
						info = cuser.getUserPost(username);
					} 
				}else {
					info = cuser.getUserPostById(id);
				}
				
				int l = info.length;
				if (l > 20)
					l = 20;
				for (int i = 0; i < l; i++) {
					String[] s = info[i].split("\\|");
					out.println("文章：" + s[1] + "<br>");
					out.println("内容：" + s[3] + "<br>");
					out.println("时间：" + s[4] + "<br>");
					out.println("来源：" + s[5] + "<br>");
					out.println("<br>");
				}

				//for (int i=0;i<info.length;i++)
				//	out.println(info[i]+"<br>");
			%>
		</div>
	
	</div>
	
	
	
	<!-- 底栏 -->
	<%@ include file="foot.jsp" %>


	<!--[if lt IE 9]>
	<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
	<script src="assets/js/polyfill/rem.min.js"></script>
	<script src="assets/js/polyfill/respond.min.js"></script>
	<script src="assets/js/amazeui.legacy.js"></script>
	<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<!--<![endif]-->

</body>

</html>