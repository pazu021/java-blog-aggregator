<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="../layout/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<tilesx:useAttribute name="current" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	
<script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
	
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href='<spring:url value="/" />'>JBA</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="${current =='index'? 'active' : ''}"><a href='<spring:url value="/" />'>Home</a></li>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<li class="${current =='users'? 'active' : ''}"><a href='<spring:url value="/users.html" />'>Users</a></li>
					</security:authorize>
					<li class="${current =='register'? 'active' : ''}"><a href='<spring:url value="/register.html" />'>Register</a></li>
					<security:authorize access="! isAuthenticated()">
						<li class="${current =='login'? 'active' : ''}"><a href='<spring:url value="/login.html" />'>Login</a></li>					
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<li class="${current =='account'? 'active' : ''}"><a href='<spring:url value="/account.html" />'>My account</a></li>				
					</security:authorize>	
					<security:authorize access="isAuthenticated()">
						<li class="${current =='logout'? 'active' : ''}"><a href='<spring:url value="/logout.html" />'>Logout</a></li>				
					</security:authorize>				

				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
		<!--/.container-fluid --> </nav>

		<tiles:insertAttribute name="body" />

		<br /> <br />

		<center>
			<tiles:insertAttribute name="footer" />
		</center>

	</div>
</body>
</html>