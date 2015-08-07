<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
		<form:form method="POST" action="/target-trak/createContact.htm" modelAttribute="contactItem" id="createContactForm">	
			<form:hidden path="id"/>
	        <div class="row">
            	<div class="col-lg-offset-0">
	            	<div class="panel panel-default">
	                	<div class="panel-heading panel-heading-custom">Create Contact</div>
		                    <div class="panel-body">
		                    	<c:if test="${not empty message}">
			                    	<div class="row">
			                    		<div class="col-sm-10 col-sm-offset-1 text-center">
			                    			<p class="text-success">${message}</p>
			                    		</div>
			                    	</div>
		                    	</c:if>
		                    	<c:if test="${not empty errorMessage}">
			                    	<div class="row">
			                    		<div class="col-sm-10 col-sm-offset-1 text-center">
			                    			<p class="text-danger">${errorMessage}</p>
			                    		</div>
			                    	</div>
		                    	</c:if>
		                    	
                       			<div class="col-md-5 col-sm-offset-1">
	                                <spring:bind path="contactType">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="contactType" cssClass="control-label input-sm">Contact Type:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:select path="contactType" cssClass="form-control input-sm">
                                            	<form:option value="">Please Select ...</form:option>
	                                        	<c:forEach var="contactType" items="${contactTypes}">
	                                            	<form:option value="${contactType.value}">${contactType.name}</form:option>
	                                            </c:forEach>
                                            </form:select>
		                                </div>
		                            </spring:bind>
	                                <spring:bind path="firstName">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
	                                    	<form:label path="firstName" cssClass="control-label input-sm">First Name:</form:label>
	                                    	<c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
	                                    	<form:input path="firstName" cssClass="form-control input-sm" />
	                               		</div>
	                               	</spring:bind>
                   				</div>
                   				<div class="col-md-5 col-sm-offset-1">
                                   	<spring:bind path="title">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="title" cssClass="control-label input-sm">Title:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:select path="title" cssClass="form-control input-sm">
                                            	<form:option value="">Please Select ...</form:option>
	                                        	<c:forEach var="title" items="${titles}">
	                                            	<form:option value="${title.value}">${title.name}</form:option>
	                                            </c:forEach>
                                            </form:select>
		                                </div>
		                            </spring:bind>
                               		<spring:bind path="lastName">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="lastName" cssClass="control-label input-sm">Last Name:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:input path="lastName" cssClass="form-control input-sm" />
		                                </div>
		                            </spring:bind>
                   				</div>
                   				<div class="col-md-5 col-sm-offset-1">
	                                <spring:bind path="middleInitial">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="middleInitial" cssClass="control-label input-sm">Middle Initial:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:input path="middleInitial" cssClass="form-control input-sm" />
		                                </div>
		                            </spring:bind>
	                             	<spring:bind path="telephoneNumber">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="telephoneNumber" cssClass="control-label input-sm">Telephone Number:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:input path="telephoneNumber" cssClass="form-control input-sm" />
		                                </div>
		                            </spring:bind>
                   				</div>
                   				<div class="col-md-5 col-sm-offset-1">
                               	 	<spring:bind path="suffix">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="suffix" cssClass="control-label input-sm">Suffix:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:select path="suffix" cssClass="form-control input-sm">
                                            	<form:option value="">Please Select ...</form:option>
	                                        	<c:forEach var="suffix" items="${suffixes}">
	                                            	<form:option value="${suffix.value}">${suffix.name}</form:option>
	                                            </c:forEach>
                                            </form:select>
		                                </div>
		                            </spring:bind>
                                   	<spring:bind path="email">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="email" cssClass="control-label input-sm">Email:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:input path="email" cssClass="form-control input-sm" />
		                                </div>
		                            </spring:bind>
                   				</div>
                   				<div class="col-md-5 col-sm-offset-1">
                                   	<spring:bind path="company">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="company" cssClass="control-label input-sm">Company:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:select path="company" cssClass="form-control input-sm">
                                            	<form:option value="">Please Select ...</form:option>
	                                        	<c:forEach var="company" items="${companies}">
	                                            	<form:option value="${company.value}">${company.name}</form:option>
	                                            </c:forEach>
                                            </form:select>
		                                </div>
		                            </spring:bind>
	                            </div>
	                            <div class="col-md-5 col-sm-offset-1">
                               		<spring:bind path="activeAtCompany">
		                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                    <form:label path="activeAtCompany" cssClass="control-label input-sm">Active Employee:</form:label>
		                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
                                            <form:select path="activeAtCompany" cssClass="form-control input-sm">
                                            	<form:option value="">Please Select ...</form:option>
	                                        	<c:forEach var="status" items="${statuses}">
	                                            	<form:option value="${status.value}">${status.name}</form:option>
	                                            </c:forEach>
                                            </form:select>
		                                </div>
		                            </spring:bind>
                   				</div>

                   				<div class="row">
		                        	<div class="form-group">
									    <label class="col-lg-12 control-label" for="singlebutton"></label>
									    <div class="col-lg-12">
									        <div class="col-lg-12 text-center"> 
												<button class="btn btn-primary" type="submit">Save</button> 
												<button class="btn btn-primary" type="submit" onclick="javascript:cancelEditContact();">Cancel</button> 
											</div>
									    </div>
									</div>
								</div>
		                    </div>
	                    </div>
                    </div>
                </div>
           	</div>
        </form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>