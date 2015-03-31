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
		<meta name="description" content="Post Page,帖子页面" />
		<meta name="author" Content="azx,azx-c@163.com">
		<meta name="copyright" Content="本页版权归Zerospace所有。All Rights Reserved">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
    
        <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="css/ns-default.css" />
        <link rel="stylesheet" type="text/css" href="css/ns-style-growl.css" />

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
        <script src="js/modernizr.custom.js"></script>
        <script src="js/classie.js"></script>
		<script src="js/notificationFx.js"></script>
        <script>
            var s_width = window.innerWidth / 2;
            var s_height = window.innerHeight / 2;
            var messages = new Array();
            var mes_length = 5;
            var mes_id = 0;
        </script>
        <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script>
            function deleteMessage(notification){
                if(messages.length<=mes_length){
                    messages.push({notification : notification,tid : tid});
                }
                else{
                    messages[0].notification.dismissttl = 6000;
                    messages.shift().notification.dismiss();
                    messages.push({notification : notification,tid : tid});
                }

            }

            function getMessage(mes_con) {
                var l = s_width * Math.random();
                var t = s_height * Math.random();
                var i = mes_id;
                // simulate loading (for demo purposes only)

                // create the notification
                var notification = new NotificationFx({
                    message : '<p>' + mes_con + '</p>',
                    layout : 'growl',
                    effect : 'jelly',
                    type : 'notice', // notice, warning, error or success
                    onClose : function() {
                    },
                    id : i,
                    is_hold : true,
                    is_abs : true,
                    pos : { left:l, top:t },
                    wrapper : document.getElementById("area")
                });

                // show the notification
                notification.show();

                tid = 9999;

                //deleteMessage(notification);

                if(messages.length<=mes_length){
                    messages.push({n:notification,t:tid});
                }
                else{
                    messages[0].n.dismissttl = 6000;
                    messages.shift().n.dismiss();
                    messages.push({n:notification,t:tid});
                }

                for(var i = 0; i <= messages.length-1; i++){
                    clearTimeout(messages[i].t);
                    messages[i].t = setTimeout(function(){
                        messages.shift().n.dismiss();
                    },i*i*3000+3000);
                }

//                setTimeout(function(){
//                    notification.dismiss();
//                },(6-messages.length)*2000);

                
                mes_id = mes_id + 1;
			};
		</script>
			
		
		<script type="text/javascript">
			var connButton = document.getElementById('buttonConnect');// å»ºç«è¿æ¥æé®
			var demo = {
			socket : null, 	// WebSocketè¿æ¥å¯¹è±¡
			host : '',		// WebSocketè¿æ¥ url
			connect : function() { 	// è¿æ¥æå¡å¨
				window.WebSocket = window.WebSocket || window.MozWebSocket;
				if (!window.WebSocket) {	// æ£æµæµè§å¨æ¯æ
					getMessage('Error: WebSocket is not supported .');
					return;
				}
				this.socket = new WebSocket(this.host); // åå»ºè¿æ¥å¹¶æ³¨åååºå½æ°
				this.socket.onopen = function(){getMessage("websocket is opened .");};
				this.socket.onmessage = function(message) {getMessage(message.data)}//{console.log(message.data);};
				this.socket.onclose = function(){
					getMessage("websocket is closed .");
					demo.socket = null; // æ¸ç
				};
			},
			send : function(message) {	// åéæ¶æ¯æ¹æ³
				if (this.socket) {
					this.socket.send(message);
					return true;
				}
				getMessage('please connect to the server first !!!');
				return false;
				}
			};
			// åå§åWebSocketè¿æ¥ url
			demo.host=(window.location.protocol == 'http:') ? 'ws://' : 'wss://' ;
			demo.host += window.location.host + '/webs/servlet/say';
			demo.host += '?id=' + '<%= request.getParameter("id")%>';
			// åå§åæé®ç¹å»äºä»¶å½æ°
			sendButton.onclick = function() {
				alert('xxx');
				var message = textBox.value;
				if (!message) return;
				if (!demo.send(message)) return;
				textBox.value = '';
			};
			connButton.onclick = function() {
				if (!demo.socket) 	demo.connect();
				else getMessage('websocket already exists .');
			};
			discButton.onclick = function() {
				if (demo.socket) demo.socket.close();
				else getMessage('websocket is not found .');
			};
			
			function connectServer(){
				if (!demo.socket) 	demo.connect();
				else getMessage('websocket already exists .');
			}
		</script>
			
    </head>
    


    <body>
   	 	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    
		<!-- 顶栏 -->
	 	<%@ include file="head.jsp" %> 

        <div class="am-g am-g-fixed blog-g-fixed">

          <div class="am-u-md-3 blog-sidebar">
            <div class="am-panel-group">
            
				<!-- 用户信息 -->
				<%@ include file="side_user.jsp" %> 

 				<!-- 其他信息 -->
				<%@ include file="side_other.jsp" %>
				
            </div>
          </div>

          <div class="am-u-md-8">
		    <!-- <img hidefocus="true" src="http://www.baidu.com/img/bd_logo1.png" width="270" height="129"> -->
            <div id="area" class="container">
                <div class="main clearfix">
                    <div class="column">
						<button onclick="window.history.back()" >Get back</button>
						
                        <button id="notification-trigger" class="progress-button" onclick="getMessage()">
                            <span class="content">Message</span>
                            <span class="progress"></span>
                        </button>
                        <button id="buttonConnect" onclick="connectServer()">Connect to server</button>
                    </div>
                    <div id="post">
                    	<%@ page import = "com.CPost" %>
                    	<%!	String id;
                    		CPost cp = new CPost();
						%>
                    	<%  id = request.getParameter("id");
                    		List posts = cp.getPost(id);
                    		for (int i = 0; i < posts.size(); i++) {
                    			String result = (String)posts.get(i);
                    			result = result.replaceAll("src=\"", "src=\"bbs.byr.cn");
								//out.println("<p>"+result+"</p>");
								String[] s = result.split("\\|");
								out.println("<a href=\"user.jsp?id=" + s[0] + "\">" + s[1] + "</a>:" + "</br>");
								out.println("<p>" + s[2] + "</p>");
								out.println("<p>at " + s[3] + "</p>");
								out.println("</br>");
							}
                    	%>
                    </div>
                </div>
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