<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
		<form:form method="POST" action="/target-trak/updateCompany.htm" modelAttribute="companyItem" id="editCompanyForm">	
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
			<form:hidden path="id"/>
			<form:hidden path="version" />
	        <div class="row">
            	<div class="col-lg-offset-0">
	            	<div class="panel panel-default">
	                	<div class="panel-heading panel-heading-custom">Edit Company Details</div>
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
                        				<spring:bind path="name">
			                                <div class="form-group ${status.error ? 'has-error' : '' }">
			                                    <form:label path="name" cssClass="control-label input-md">Name:</form:label>
			                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
			                                    <form:input path="name" cssClass="form-control input-sm" />
			                                </div>
		                                </spring:bind>
		                            </div>
		                            <div class="col-md-5 col-sm-offset-1">   
		                                <spring:bind path="addressLine1">
		                            	    <div class="form-group ${status.error ? 'has-error' : '' }">
			                                    <form:label path="addressLine1" cssClass="control-label input-md">Address Line 1:</form:label>
			                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
			                                    <form:input path="addressLine1" cssClass="form-control input-sm" />
			                                </div>
		                                </spring:bind>
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
		                                <spring:bind path="addressLine2">
		                                   	<div class="form-group ${status.error ? 'has-error' : '' }">
		                                    	<form:label path="addressLine2" cssClass="control-label input-md">Address Line 2</form:label>
		                                    	<c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
	                                            <form:input path="addressLine2" cssClass="form-control input-sm" />
		                                   	</div>
	                                   	</spring:bind>
		                             </div>
		                             <div class="col-md-5 col-sm-offset-1">      
		                                <spring:bind path="city">
		                                   	<div class="form-group ${status.error ? 'has-error' : '' }">
		                                    	<form:label path="city" cssClass="control-label input-md">City</form:label>
		                                    	<c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
	                                            <form:input path="city" cssClass="form-control input-sm" />
		                                   	</div>
	                                   	</spring:bind>
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
			                           	<spring:bind path="state">
                                   			<div class="form-group ${status.error ? 'has-error' : '' }">
		                                    	<form:label path="state" cssClass="control-label input-md">State</form:label>
		                                    	<c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
	                                            <form:select path="state" cssClass="form-control input-sm">
	                                            	<form:option value="">Please Select ...</form:option>
		                                        	<c:forEach var="state" items="${statesList}">
		                                            	<form:option value="${state.value}">${state.name}</form:option>
		                                            </c:forEach>
	                                            </form:select>
                                            </div>
                                         </spring:bind>
		                           	</div>
		                           	<div class="col-md-5 col-sm-offset-1">   
			                           	<spring:bind path="country">
		                                   	<div class="form-group ${status.error ? 'has-error' : '' }">
		                                    	<form:label path="country" cssClass="control-label input-md">State</form:label>
		                                    	<c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
	                                            <form:select path="country" cssClass="form-control input-sm">
	                                            	<form:option value="">Please Select ...</form:option>
		                                        	<c:forEach var="country" items="${countriesList}">
		                                            	<form:option value="${country.value}">${country.name}</form:option>
		                                            </c:forEach>
	                                            </form:select>
                                            </div>
	                                   	</spring:bind>
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
                    					<spring:bind path="zipcode">
			                                <div class="form-group ${status.error ? 'has-error' : '' }">
			                                    <form:label path="zipcode" cssClass="control-label input-md">Zip Code:</form:label>
			                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
			                                    <form:input path="zipcode" cssClass="form-control input-sm" />
			                                </div>
			                            </spring:bind>
		                            </div>
		                            <div class="col-md-5 col-sm-offset-1">   
	                                   	<div class="form-group">
	                                    	<form:label path="createdBy" cssClass="control-label input-md">Created By</form:label>
                                            <input name="createdBy" class="form-control input-sm" value="${companyItem.createdBy}" readonly/>
	                                   	</div>
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
	                                   	<div class="form-group">
	                                    	<form:label path="createdDate" cssClass="control-label input-md">Created Date</form:label>
                                            <input name="createdDate" class="form-control input-sm" value="${companyItem.createdDate}" readonly/>
	                                   	</div>
                                   	</div>
                                   	<div class="col-md-5 col-sm-offset-1">   
	                                   	<div class="form-group">
		                                    <form:label path="lastUpdatedDate" cssClass="control-label input-md">Last Updated Date:</form:label>
		                                    <input name="lastUpdatedDate" class="form-control input-sm" value="${companyItem.lastUpdatedDate}" readonly/>
		                                </div>
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
	                                   	<div class="form-group">
		                                    <form:label path="lastUpdatedBy" cssClass="control-label input-md">Last Updated By:</form:label>
		                                    <input name="lastUpdatedBy" class="form-control input-sm" value="${companyItem.lastUpdatedBy}" readonly/>
		                                </div>
                    				</div>
                   				<div class="row">
		                        	<div class="form-group">
									    <label class="col-lg-12 control-label" for="singlebutton"></label>
									    <div class="col-lg-12">
									        <div class="col-lg-12 text-center"> 
												<button class="btn btn-primary" type="submit">Save</button> 
												<button class="btn btn-primary" type="submit" onclick="javascript:cancelEditCompany();">Cancel</button> 
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