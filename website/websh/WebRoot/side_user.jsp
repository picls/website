				<section class="am-panel am-panel-default">
					<div class="am-panel-hd">关于我</div>
					<div class="am-panel-bd">
						<p>
							<%@ page import="com.CUser" %>
							<%! CUser cuserSU = new CUser(); %>
							<%	String usernameSU = null;
							  	Cookie cookieSU = null;
							  	Cookie[] cookiesSU = null;
							  	cookiesSU = request.getCookies();
							  	if(cookiesSU != null){
							  		for(int i = 0;i <= cookiesSU.length-1; i++){
							  			cookieSU = cookiesSU[i];
							  			if(cookiesSU[i].getName().equals("username")){
							  				usernameSU = cookiesSU[i].getValue();
							  				break;
							  			}
							  		}
							  	String[] infoSU = cuserSU.getUserOnly(usernameSU);
							  	out.println("用户："+infoSU[0]+"<br>");
							  	out.println("昵称："+infoSU[1]+"<br>");
							  	out.println("等级："+infoSU[2]+"<br>");
							  	out.println("文章："+infoSU[3]+"<br>");
							  	out.println("积分："+infoSU[4]+"<br>");
							  	//for (int i=0;i<info.length;i++)
							  	//	out.println(info[i]+"<br>");
							  	} 
							  %>
							<%-- <%= desc %> --%>
						</p>
						<a class="am-btn am-btn-success am-btn-sm" href="user.jsp">查看更多</a> 
						<a class="am-btn am-btn-success am-btn-sm" onclick="quitLogin()">退出登录</a>
						<!-- <input type=button onclick='quitLogin()' name=look value='查看' >
						<a class="am-btn am-btn-success am-btn-sm" href="servlet/slogout">1</a>
						<a class="am-btn am-btn-success am-btn-sm" href="http://www.baidu.com">baidu</a> -->
					</div>
				</section>