<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>FullBBS——talk whatever you like</title>
		
		<meta name="keywords" content="BBS,论坛" />
		<meta name="description" content="login page,登录页面" />
		<meta name="author" Content="azx,azx-c@163.com">
		<meta name="copyright" Content="本页版权归Zerospace所有。All Rights Reserved">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="images/icon/main_icon_32.ico" rel="shortcut icon">
		
		<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<link href="css/bootstrap-social.css" rel="stylesheet" type="text/css">	
		<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">	
		<script type="text/javascript">
			function stopmusic(){
				//document.getElementById('bgaudio').disabled=true;
				document.getElementById('bgaudio').pause();
			}
		</script>
	</head>
	  
	  
	<body class="templatemo-bg-image-1">
		<audio id="bgaudio" src="music/mirror night.mp3"></audio>
		<script type="text/javascript">
			document.getElementById('bgaudio').play();
		</script>
		<div class="container">
			<div class="col-md-12">		
				<button class="pull-right" onclick="stopmusic()">stop</button>
				<form class="form-horizontal templatemo-login-form-2" role="form" action="slogin" method="POST">
					<div class="row">
						<div class="col-md-12">
							<h1>Welcome aboard</h1>
						</div>
					</div>
					<div class="row">
						<div class="templatemo-one-signin col-md-6">
					        <div class="form-group">
					          <div class="col-md-12">		          	
					            <label for="username" class="control-label">Username</label>
					            <div class="templatemo-input-icon-container">
					            	<i class="fa fa-user"></i>
					            	<input type="text" class="form-control" id="username" name="username" placeholder="" />
					            </div>		            		            		            
					          </div>              
					        </div>
					        <div class="form-group">
					          <div class="col-md-12">
					            <label for="password" class="control-label">Password</label>
					            <div class="templatemo-input-icon-container">
					            	<i class="fa fa-lock"></i>
					            	<input type="password" class="form-control" id="password" name="password" placeholder="" />
					            </div>
					          </div>
					        </div>
					        <div class="form-group">
					          <div class="col-md-12">
					            <div class="checkbox">
					                <label>
					                  <input type="checkbox"> Remember me
					                </label>
					            </div>
					          </div>
					        </div>
					        <div class="form-group">
					          <div class="col-md-12">
					            <input type="submit" id="login" name="login" value="LOG IN" class="btn btn-warning" />
					          </div>
					        </div>
					        <div class="form-group">
					          	<div class="col-md-12">
								    <a href="createaccount.html" class="text-center">Don't have account?</a>
									&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					        		<a href="forgetpassword.html" class="text-center">Cannot login?</a>
					       	 	</div>
					    	</div>
						</div>
						<div class="templatemo-other-signin col-md-6">
							<label class="control-label">
								One-click sign in using other services. 
							</label>
							<a class="btn btn-block btn-social btn-facebook margin-bottom-20">
							    <i class="fa fa-facebook"></i> Sign in with Facebook
							</a>
							<a class="btn btn-block btn-social btn-twitter margin-bottom-20">
							    <i class="fa fa-twitter"></i> Sign in with Twitter
							</a>
							<a class="btn btn-block btn-social btn-google-plus margin-bottom-15">
							    <i class="fa fa-google-plus"></i> Sign in with Google
							</a>
							<label class="margin-bottom-15">
								Credit goes to <a rel="nofollow" href="http://lipis.github.io/bootstrap-social/">Bootstrap Social</a> for social sign in buttons.
							</label>
						</div>   
					</div>				 	
				<form>		     
			</div>
		</div>
	</body>
</html>