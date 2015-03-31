<html>
	<head>
		<title>Hello World</title>
	</head>
	<body>
		Hello World!<br/>
		<%
			out.println("Your IP address is " + request.getRemoteAddr());
			out.println(new java.util.Date().toLocaleString());
			//
			/**/
		%>
		<%-- --%>
		
		<p>
			<font color="#f0035">
				Today's date: <%= (new java.util.Date()).toLocaleString()%>
			</font>
		</p>
		
		
		<%for (int fontSize = 1; fontSize <= 3; fontSize++){ %>
   			<font color="green" size="<%= fontSize %>">
    			JSP Tutorial
   			</font>
   			<br />
		<%}%>
		
		<p id="a">
			color
		</p>
		
		<%for (int i = 1; i <= 1; i++){ %>
		<script>
			<%-- Thread.sleep(1000); --%>
			document.getElementById("a").color="#ff<%= 0523 %>";
		</script>
		<%} %>
		
		<p>
		<%@page import="com.calculate"%>
		<%= new calculate().cal()%>
		</p>
		
		<input id="input1" />
		<button onclick = "input()">submit</button>
		<script>
			function input(){
			//var x = document.getElementById("input1").innerHTML;
			var z = document.getElementById("input1").value;
			<%@page import="com.login"%>
			<%calculate c = new calculate();%>
			<%//= c.add(z)%>
			}
		</script>
		
		<p id="x">xxx </p>
		
		

		
	</body>
</html>