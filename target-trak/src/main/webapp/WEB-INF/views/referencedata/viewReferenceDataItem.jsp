<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
		<form:form method="GET" action="/target-trak/showEditReferenceDataItem.htm" modelAttribute="referenceDataItem" id="viewRefDataForm">	
			<form:hidden path="id"/>
	        <div class="row">
            	<div class="col-lg-offset-0">
	            	<div class="panel panel-default">
	                	<div class="panel-heading panel-heading-custom">View Reference Data</div>
		                    <div class="panel-body">
		                    	<div class="row">
		                    		<fieldset disabled>
	                        			<div class="col-md-4 col-sm-offset-1">
			                                <div class="form-group">
			                                    <form:label path="type" cssClass="control-label input-md">Type:</form:label>
			                                    <form:input path="type" cssClass="form-control input-sm" />
			                                </div>
		                                   	<div class="form-group">
		                                    	<form:label path="value" cssClass="control-label input-md">Value</form:label>
	                                            <form:input path="value" cssClass="form-control input-sm" />
		                                   	</div>
	                    				</div>
	                           			<div class="col-md-4 col-sm-offset-1">
		                                	<div class="form-group">
			                                    <form:label path="label" cssClass="control-label input-md">Label:</form:label>
			                                    <form:input path="label" cssClass="form-control input-sm" />
			                                </div>
		                                   	<div class="form-group">
		                                    	<form:label path="status" cssClass="control-label input-md">Status</form:label>
	                                            <form:select path="status" cssClass="form-control input-sm">
	                                            	<form:option value="">Please Select ...</form:option>
		                                        	<c:forEach var="status" items="${statusList}">
		                                            	<form:option value="${status.value}">${status.name}</form:option>
		                                            </c:forEach>
	                                            </form:select>
		                                   	</div>
	                          			</div>
                          				<div class="col-md-4 col-sm-offset-1">
			                                <div class="form-group">
			                                    <form:label path="createdBy" cssClass="control-label input-md">Created By:</form:label>
			                                    <form:input path="createdBy" cssClass="form-control input-sm" />
			                                </div>
		                                   	<div class="form-group">
		                                    	<form:label path="createdDate" cssClass="control-label input-md">Created Date</form:label>
	                                            <form:input path="createdDate" cssClass="form-control input-sm" />
		                                   	</div>
	                    				</div>
	                           			<div class="col-md-4 col-sm-offset-1">
		                                	<div class="form-group">
			                                    <form:label path="lastUpdatedBy" cssClass="control-label input-md">Last Updated By:</form:label>
			                                    <form:input path="lastUpdatedBy" cssClass="form-control input-sm" />
			                                </div>
		                                   	<div class="form-group">
		                                    	<form:label path="lastUpdateDate" cssClass="control-label input-md">Last Update Date</form:label>
	                                            <form:input path="lastUpdateDate" cssClass="form-control input-sm"/>
		                                   	</div>
	                          			</div>
	                          		</fieldset>	
                          			<div class="row">
			                        	<div class="form-group">
										    <label class="col-lg-12 control-label" for="singlebutton"></label>
										    <div class="col-lg-12">
										        <div class="col-lg-12 text-center"> 
													<button class="btn btn-primary" type="submit">Edit</button> 
													<button class="btn btn-primary" type="submit" onclick="javascript:backToReferenceDataSearchResults();">Search Results</button> 
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