<!DOCTYPE html>
<html>

<head lang="en">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
        content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

	<title>FullBBS | talk whatever you like</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta name="keywords" content="BBS,论坛" />
	<meta name="description" content="Board Page,板块页面" />
	<meta name="author" Content="azx,azx-c@163.com">
	<meta name="copyright" Content="本页版权归Zerospace所有。All Rights Reserved">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">

	<link rel="stylesheet" href="assets/css/amazeui.min.css" />
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
	
	<style>
	@media only screen and (min-width: 1200px) {
		.blog-g-fixed {
			max-width: 1200px;
		}
	}
	
	@media only screen and (min-width: 641px) {
		.blog-sidebar {
			font-size: 1.4rem;
		}
	}
	
	.blog-main {
		padding: 20px 0;
	}
	
	.blog-title {
		margin: 10px 0 20px 0;
	}
	
	.blog-meta {
		font-size: 14px;
		margin: 10px 0 20px 0;
		color: #222;
	}
	
	.blog-meta a {
		color: #27ae60;
	}
	
	.blog-pagination a {
		font-size: 1.4rem;
	}
	
	.blog-team li {
		padding: 4px;
	}
	
	.blog-team img {
		margin-bottom: 0;
	}
	
	.blog-footer {
		padding: 10px 0;
		text-align: center;
	}
	</style>

	<script>
		function functiona() {
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET", "demo_get.html", true);
			xmlhttp.send();
		}
		$(document).ready(function() {
			$("#addlist").click(function() {
				$("ol").append("<li>a</li>");
			});
		});
		
		function addBlog() {
		}
		
		function quitLogin() {
			window.location.href="servlet/slogout";
		}
	</script>
	
</head>


<body>
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	
	<!-- 顶栏 -->
 	<%@ include file="head.jsp" %> 

	<div class="am-g am-g-fixed blog-g-fixed">

		<%--  <%@ page language="java" import="h.com.dao.Blog.*"%>
		<%@ page language="java" import="h.com.dao.BlogManager.*"%>--%>
		
		<%@page import = "com.CPage" %>
		<%! CPage gb = new CPage();
			List blogs;
		%>

		<%! public String blog(String[] blog) {
			String back = "";
			int x = blog[3].lastIndexOf("[");
			//System.out.println(x);
			if(x>0){
				int y = blog[3].lastIndexOf("]");
				String z1 = blog[3].substring(x+1,x+2);
				String z2 = blog[3].substring(y-1,y);
				try{
					Integer.valueOf(z1);
					Integer.valueOf(z2);
					blog[3] = blog[3].substring(0,x);
				} catch(Exception e){e.printStackTrace();}
			}
			back += "<article class=\"blog-main\">" + "\n";
			back += "<h3 class=\"am-article-title blog-title\">" + "\n";
			back += "<a href=\"page.jsp?id=" + blog[0] + "\">" + blog[3] + "</a>" + "\n";
			back += "</h3>" + "\n";
			back += "<h4 class=\"am-article-meta blog-meta\">" + "\n";
			back += "by <a href=\"user.jsp?id=" + blog[7] + "\">" + blog[8] + "</a> posted " + blog[5] + " reply " + blog[6] + " page " + blog[4] + "\n"; //<a href=\"#\"></a>
			back += "</h4>" + "\n";
			back += "<div class=\"am-g blog-content\">" + "\n";
			back += "<div class=\"am-u-lg-7\"></div>" + "\n";
			back += "</div>" + "\n";
			back += "<div class=\"am-g\">" + "\n";
			back += "<div class=\"am-u-sm-12\"></div>" + "\n";
			back += "</div>" + "\n";
			back += "</article>" + "\n";
			back += "<hr class=\"am-article-divider blog-hr\">" + "\n";
			return back;
		}%>

		<div class="am-u-md-8">
			<%! int p = 0;
				boolean np = true; %>
			<%
				String spage = request.getParameter("page");
				if((spage != null)&&(!spage.equals("")))
					p = Integer.parseInt(spage);
				if((spage == null)||(spage.equals(""))) p = 0;
				blogs = gb.getPage();
				int l = (p+1)*10;
				if(l >= blogs.size()) {
					l = blogs.size();
					np = false;
				}
				for (int i = p*10; i < l; i++) {
					String[] blog = (String[]) blogs.get(i);
					String s = blog(blog);
					out.println(s);
				}

			%>

			<ul class="am-pagination blog-pagination">
				<%! String last_page = "", previous_page = "", next_page = ""; %>
				<%  if(blogs != null) last_page = "blog.jsp?page=" + blogs.size()/10; 
				    if(p != 0) previous_page = "blog.jsp?page=" + (p-1); 
					if(np) next_page = "blog.jsp?page=" + (p+1); %>
				<li class="am-pagination-prev"><a href="<%= previous_page %>">&laquo; 上一页</a></li>
				<li class="am-pagination-prev"><a href="<%= next_page %>">下一页 &raquo;</a></li>
				<li class="am-pagination-next"><a href="<%= last_page %>">尾页</a></li>
				<li class="am-pagination-next">&nbsp;</li>
				<li class="am-pagination-next"><a href="blog.jsp">首页</a></li>
			</ul>
		</div>

		<div class="am-u-md-3 blog-sidebar">
			<div class="am-panel-group">
				
				<!-- 用户信息 -->
				<%@ include file="side_user.jsp" %> 
				
				<!-- 文章信息 -->
				<%@ include file="side_page.jsp" %>

				<!-- 其他信息 -->
				<%@ include file="side_other.jsp" %>
				
			</div>
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