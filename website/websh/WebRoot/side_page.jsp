<section class="am-panel am-panel-default">
	<div class="am-panel-hd">文章目录</div>
	<%@ page import="com.CPage"%>
	<%!CPage cpage = new CPage();
	String[] info = cpage.getHotPage();%>
	<ul class="am-list blog-list">
		<%
			for (int i = 0; i < info.length; i++) {
				String[] s = info[i].split("\\|");
				out.println("<li><a href=\"page.jsp?id=" + s[7] + "\">" + s[2]
						+ "</a>");
			}
		%>
	</ul>
</section>