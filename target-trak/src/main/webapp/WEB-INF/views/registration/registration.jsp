<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Target-Trak Registration</title>
	<link href="/target-trak/resources/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet">
	<link href="/target-trak/resources/tts/target-trak.css" rel="stylesheet">
</head>
<body>
	<div class="row"></div>
	<div class="row">
    	<div class="col-md-6 col-md-offset-3">
           	<div class="panel panel-default">
               	<div class="panel-heading panel-heading-custom">Target-Trak User Registration</div>
                   	<div class="panel-body">
                   		<div class="row">
                       		<div class="col-md-6 col-md-offset-3">
			               		<c:url var="registerUrl" value="/registerNewUser.htm"/>
			               		<form:form method="POST" action="${registerUrl}" modelAttribute="userRegistrationForm">
			       					<spring:bind path="email">
							       		<div class="form-group ${status.error ? 'has-error' : '' }">
			                        		<form:label path="email">Email Address:<span class="text-danger">*</span></form:label> 
		                        			<c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
			                        		<form:input type="text" path="email" cssClass="form-control" value="${userRegistrationForm.email}" />
			                        	</div>
				                    </spring:bind>
				                    
							        <spring:bind path="firstName">
				                        <div class="form-group ${status.error ? 'has-error' : '' }">
				                            <form:label path="firstName">First Name:<span class="text-danger">*</span></form:label>
				                            <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
				                            <form:input type="text" cssClass="form-control" path="firstName" value="${userRegistrationForm.firstName}"/>
				                        </div>
				                    </spring:bind>
				                    
				                    <spring:bind path="lastName">     
				                        <div class="form-group ${status.error ? 'has-error' : '' }"">
				                            <form:label path="lastName">Last Name:<span class="text-danger">*</span></form:label>
				                            <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
				                            <form:input type="text" cssClass="form-control"  path="lastName" value="${userRegistrationForm.lastName}"/>
				                        </div>
				                    </spring:bind>
				                    
				                    <spring:bind path="telephoneNumber">          
				                        <div class="form-group ${status.error ? 'has-error' : '' }"">
				                            <form:label path="telephoneNumber">Telephone Number:<span class="text-danger">*</span></form:label>
				                            <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
				                            <form:input type="text" cssClass="form-control" path="telephoneNumber" value="${userRegistrationForm.telephoneNumber}"/>
				                        </div>
			                        </spring:bind>
			                        
			                        <spring:bind path="username">     
				                        <div class="form-group ${status.error ? 'has-error' : '' }"">
				                            <form:label path="username">Username:<span class="text-danger">*</span></form:label>
				                            <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
				                            <form:input type="text" cssClass="form-control" path="username" value="${userRegistrationForm.username}"/>
				                        </div>       
			                		</spring:bind>
			                		
			                		<spring:bind path="password">     
				                		<div class="form-group ${status.error ? 'has-error' : '' }"">
				                            <form:label path="password">Password:<span class="text-danger">*</span></form:label>
				                            <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
				                            <form:input type="password" cssClass="form-control" path="password" value="${userRegistrationForm.password}"/>
				                        </div>
			                        </spring:bind>
			                        
			                        <spring:bind path="repeatedPassword">     
				                        <div class="form-group ${status.error ? 'has-error' : '' }"">
				                            <form:label path="repeatedPassword">Password (confirm):<span class="text-danger">*</span></form:label>
				                            <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
				                            <form:input type="password" cssClass="form-control" path="repeatedPassword" value="${userRegistrationForm.repeatedPassword}"/>
				                        </div>
									</spring:bind>
									
			                		<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
			                		<a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
			                	</form:form>
			            	</div>
            			 </div>
      	 			</div>
    			</div>
			</div>
		 </div>
	  
</body>
</html>