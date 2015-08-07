<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Target-Trak Login</title>
	<link href="/target-trak/resources/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet">
	<link href="/target-trak/resources/tts/login.css" rel="stylesheet">
</head>
<body>
	<div class="container">
	    <div class="row">
	        <div class="col-md-4 col-md-offset-4">
    	        <h1 class="text-center login-title">Target-Trak Login</h1>
            	<div class="account-wall">
            		<c:if test="${not empty param.invalidcreds}">
            			<p class="text-danger text-center">Invalid username or password. Please try again.</p>
            		</c:if>
            		<c:if test="${not empty param.accessdenied}">
            			<p class="text-danger text-center">Access Denied!</p>
            		</c:if>
            		<c:if test="${not empty param.sessiontimeout}">
            			<p class="text-danger text-center">You have logged out due to inactivity.</p>
            		</c:if>
            		<c:if test="${not empty param.logout}">
            			<p class="text-success text-center">You have been successfully logged out.</p>
            		</c:if>
               		<c:url var="loginUrl" value="/j_spring_security_check"/>
               		<form class="form-signin" role="form" method="POST" action="${loginUrl}">
               			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                		<input type="text" class="form-control" placeholder="Username" required autofocus name="j_username">
                		<input type="password" class="form-control" placeholder="Password" required name="j_password">
                		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                		<label class="checkbox pull-left">
                   			<input type="checkbox" value="remember-me">Remember me
                		</label>
                		<a href="#" class="pull-right need-help">Forgot Password? </a><span class="clearfix"></span>
                		<a href="/target-trak/registration.htm" class="text-center new-account">New User</a>
                	</form>
            	</div>
            	
       	 </div>
    	</div>
	</div>
</body>
</html>