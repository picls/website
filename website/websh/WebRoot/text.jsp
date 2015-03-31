<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>Blog | Amaze UI Example</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
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
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
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
</script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
</head>


<body>
	<header class="am-topbar">
		<h1 class="am-topbar-brand">
			<a href="#">blog</a>
		</h1>

		<button
			class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
			data-am-collapse="{target: '#doc-topbar-collapse'}">
			<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
		</button>

		<div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
			<ul class="am-nav am-nav-pills am-topbar-nav">
				<li class="am-active"><a href="#">首页</a></li>
				<li><a href="#">项目</a></li>
				<li class="am-dropdown" data-am-dropdown><a
					class="am-dropdown-toggle" data-am-dropdown-toggle
					href="javascript:;"> 菜单 <span class="am-icon-caret-down"></span>
				</a>
					<ul class="am-dropdown-content">
						<li class="am-dropdown-header">标题</li>
						<li><a href="#">关于我们</a></li>
						<li><a href="#">关于字体</a></li>
						<li><a href="#">TIPS</a></li>
					</ul>
				</li>
			</ul>

			<form
				class="am-topbar-form am-topbar-left am-form-inline am-topbar-right"
				role="search">
				<div class="am-form-group">
					<input type="text" class="am-form-field am-input-sm"
						placeholder="搜索文章">
				</div>
				<button type="submit" class="am-btn am-btn-default am-btn-sm">搜索</button>
			</form>

		</div>
	</header>

	<div class="am-g am-g-fixed blog-g-fixed">

		<%@ page import="dao.blog_text"%>
		<%!blog_text b = new blog_text();
	String[] column = { "id", "title", "author_id", "create_time" };
	String[] res = b.get_xsAll(column);
	String cid = res[0].split("\\|")[0];
	String cname = res[0].split("\\|")[1];%>

	



		<div class="am-u-md-3 blog-sidebar">
			<div class="am-panel-group">
				<section class="am-panel am-panel-default">
					<div class="am-panel-hd">关于我</div>
					<div class="am-panel-bd">
						<p>
							<%@ page import="dao.user" %>
							<%! user u = new user();
								String desc = null; %>
							<%	String username = null;
							  	Cookie cookie = null;
							  	Cookie[] cookies = null;
							  	cookies = request.getCookies();
							  	if(cookies != null){
							  		for(int i = 0;i <= cookies.length-1; i++){
							  			cookie = cookies[i];
							  			if(cookies[i].getName().equals("username"))
							  				username = cookies[i].getValue();
							  		}
							    desc = u.get_x("personal_desc",1);
							  	} %>
							<%= desc %>
						</p>
						<a class="am-btn am-btn-success am-btn-sm" href="#">查看更多 →</a>
					</div>
				</section>
				<section class="am-panel am-panel-default">
					<div class="am-panel-hd">文章目录</div>
					<ul class="am-list blog-list">
						<li><a href="#">Google fonts 的字體（sans-serif 篇）</a></li>
						<li><a href="#">[but]服貿最前線？－再訪桃園機場</a></li>
						<li><a href="#">到日星鑄字行學字型</a></li>
						<li><a href="#">glyph font vs. 漢字（上）</a></li>
						<li><a href="#">浙江民間書刻體上線</a></li>
						<li><a href="#">[極短篇] Android v.s iOS，誰的字體好讀？</a></li>
					</ul>
				</section>

				<section class="am-panel am-panel-default">
					<div class="am-panel-hd">团队成员</div>
					<div class="am-panel-bd">
						<ul class="am-avg-sm-4 blog-team">
							<li><img class="am-thumbnail"
								src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg"
								alt="" />
							</li>
							<li><img class="am-thumbnail"
								src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg"
								alt="" />
							</li>
							<li><img class="am-thumbnail"
								src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg"
								alt="" />
							</li>
							<li><img class="am-thumbnail"
								src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg"
								alt="" />
							</li>
							<li><img class="am-thumbnail"
								src="http://img4.duitang.com/uploads/blog/201406/15/20140615230159_kjTmC.thumb.224_0.jpeg"
								alt="" />
							</li>
							<li><img class="am-thumbnail"
								src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg"
								alt="" />
							</li>
							<li><img class="am-thumbnail"
								src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg"
								alt="" />
							</li>
							<li><img class="am-thumbnail"
								src="http://img4.duitang.com/uploads/blog/201406/15/20140615230159_kjTmC.thumb.224_0.jpeg"
								alt="" />
							</li>
						</ul>
					</div>
				</section>
			</div>
		</div>

	</div>



	<div class="am-u-md-8">
		<h2>ajax</h2>
		<button type="button" onclick="functiona">change</button>
		<ol>
		</ol>
		<table>
			<tr>
				<td>1.</td>
				<td>a</td>
			</tr>
			<tr>
				<td>2.</td>
				<td>b</td>
			</tr>
		</table>
		<button id="addlist">add a list</button>
	</div>



	<footer class="blog-footer">
		<p>
			blog template<br /> <small>© Copyright XXX. by the AmazeUI
				Team.</small>
		</p>
	</footer>

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