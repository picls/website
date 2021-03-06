<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8" />
        <title>Blog | Amaze UI Example</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport"
            content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <meta name="renderer" content="webkit">
        <meta http-equiv="Cache-Control" content="no-siteapp"/>

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
        <script>
            function functiona(){
              var xmlhttp = new XMLHttpRequest();
              xmlhttp.open("GET","demo_get.html",true);
              xmlhttp.send();
            }
            $(document).ready(function(){
              $("#addlist").click(function(){
                $("ol").append("<li>a</li>");
              });
            });
        </script>
        
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
			
		<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    </head>
    


    <body>
        <header class="am-topbar">
            <h1 class="am-topbar-brand">
                <a href="blog.jsp">blog</a>
            </h1>

            <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
                  data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
              class="am-icon-bars"></span></button>

            <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
                <ul class="am-nav am-nav-pills am-topbar-nav">
                  <li class="am-active"><a href="#">首页</a></li>
                  <li><a href="#">项目</a></li>
                  <li class="am-dropdown" data-am-dropdown>
                    <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                     	菜单 <span class="am-icon-caret-down"></span>
                    </a>
                    <ul class="am-dropdown-content">
                      <li class="am-dropdown-header">标题</li>
                      <li><a href="#">关于我们</a></li>
                      <li><a href="#">关于字体</a></li>
                      <li><a href="#">TIPS</a></li>
                    </ul>
                  </li>
                </ul>

                <form class="am-topbar-form am-topbar-left am-form-inline am-topbar-right" role="search">
                  <div class="am-form-group">
                    <input type="text" class="am-form-field am-input-sm" placeholder="搜索文章">
                  </div>
                  <button type="submit" class="am-btn am-btn-default am-btn-sm">搜索</button>
                </form>

            </div>
        </header>

        <div class="am-g am-g-fixed blog-g-fixed">

          <div class="am-u-md-3 blog-sidebar">
            <div class="am-panel-group">
              <section class="am-panel am-panel-default">
                <div class="am-panel-hd">关于我</div>
                <div class="am-panel-bd">
                  <p>前所未有的中文云端字型服务，让您在 web 平台上自由使用高品质中文字体，跨平台、可搜寻，而且超美。云端字型是我们的事业，推广字型学知识是我们的志业。从字体出发，关心设计与我们的生活，justfont blog
            正是為此而生。</p>
                  <a class="am-btn am-btn-success am-btn-sm" href="#">查看更多 →</a>
                </div>
              </section>
              <section class="am-panel am-panel-default">
                <div class="am-panel-hd">文章目录</div>
                <ul class="am-list blog-list">
                  <li><a href="#">Google fonts 的字體（sans-serif 篇）</a></li>
                  <li><a href="#">[but]服貿最前線？－再訪桃園機場</a></li>
                  <li><a href="#">到日星鑄字行學字型</a></li>
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
                             src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg" alt=""/>
                    </li>
                    <li><img class="am-thumbnail"
                             src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg" alt=""/>
                    </li>
                    <li><img class="am-thumbnail"
                             src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg" alt=""/>
                    </li>
                    <li><img class="am-thumbnail"
                             src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg" alt=""/>
                    </li>
                    <li><img class="am-thumbnail"
                             src="http://img4.duitang.com/uploads/blog/201406/15/20140615230159_kjTmC.thumb.224_0.jpeg" alt=""/>
                    </li>
                    <li><img class="am-thumbnail"
                             src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg" alt=""/>
                    </li>
                    <li><img class="am-thumbnail"
                             src="http://img4.duitang.com/uploads/blog/201406/15/20140615230220_F5LiM.thumb.224_0.jpeg" alt=""/>
                    </li>
                    <li><img class="am-thumbnail"
                             src="http://img4.duitang.com/uploads/blog/201406/15/20140615230159_kjTmC.thumb.224_0.jpeg" alt=""/>
                    </li>
                  </ul>
                </div>
              </section>
            </div>
          </div>

          <div class="am-u-md-8" sytle="float:left">
            <div id="area" class="container">
                <div class="main clearfix">
                    <div class="column">
                        <button id="notification-trigger" class="progress-button" onclick="getMessage()">
                            <span class="content">message</span>
                            <span class="progress"></span>
                        </button>
                        <button id="buttonConnect" onclick="connectServer()">Connect to server</button>
                    </div>
                </div>
            </div>
          </div>

          <!--
          <div class="am-u-md-8">
              <h2>ajax</h2>
              <button type="button" onclick="functiona">change</button>
              <ol>
              </ol>
              <table>
                <tr><td>1.</td><td>a</td></tr>
                <tr><td>2.</td><td>b</td></tr>
              </table>
              <button id="addlist">add a list</button>
            </div>
          -->
          
        </div>

        <footer class="blog-footer">
          <p>blog template<br/>
            <small>Â© Copyright XXX. by the AmazeUI Team.</small>
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