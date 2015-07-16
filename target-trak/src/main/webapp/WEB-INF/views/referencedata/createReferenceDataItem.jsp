<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
		<form:form method="POST" action="/target-trak/createReferenceDataItem.htm" modelAttribute="createReferenceDataForm">	
	        <div class="row">
            	<div class="col-lg-offset-0">
	            	<div class="panel panel-default">
	                	<div class="panel-heading panel-heading-custom">Create Reference Data</div>
		                    <div class="panel-body">
		                    	<c:if test="${not empty errorMessage}">
			                    	<div class="row">
			                    		<div class="col-sm-10 col-sm-offset-1 text-center">
			                    			<p class="text-danger">${errorMessage}</p>
			                    		</div>
			                    	</div>
		                    	</c:if>
		                    	<div class="row">
                        			<div class="col-md-4 col-sm-offset-1">
                        				<spring:bind path="type">
			                                <div class="form-group ${status.error ? 'has-error' : '' }">
			                                    <label class="control-label input-md">Type:</label>
			                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
			                                    <form:input class="form-control input-sm" path="type" />
			                                </div>
		                                </spring:bind>
		                                
		                                <spring:bind path="value">
		                                   	<div class="form-group ${status.error ? 'has-error' : '' }">
		                                    	<form:label path="value" cssClass="control-label input-md">Value</form:label>
		                                    	<c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
	                                            <form:input path="value" cssClass="form-control input-sm" />
		                                   	</div>
	                                   	</spring:bind>
                    				</div>
                           			<div class="col-md-4 col-sm-offset-1">
                           				<spring:bind path="label">
		                                	<div class="form-group ${status.error ? 'has-error' : '' }">
			                                    <form:label path="label" cssClass="control-label input-md">Label:</form:label>
			                                    <c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
			                                    <form:input path="label" cssClass="form-control input-sm" />
			                                </div>
			                            </spring:bind>
                                   		<spring:bind path="status">
                                   			<div class="form-group ${status.error ? 'has-error' : '' }">
		                                    	<form:label path="status" cssClass="control-label input-md">Status</form:label>
		                                    	<c:if test="${status.error}"><span class="text-danger"><small>${status.errorMessage}</small></span></c:if>
	                                            <form:select path="status" cssClass="form-control input-sm">
	                                            	<form:option value="">Please Select ...</form:option>
		                                        	<c:forEach var="status" items="${statusList}">
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
													<button class="btn btn-primary" type="submit">Create</button> 
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