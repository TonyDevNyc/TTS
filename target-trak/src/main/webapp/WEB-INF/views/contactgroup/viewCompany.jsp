<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
		<form:form method="GET" action="/target-trak/showEditCompanyItem.htm" modelAttribute="companyItem" id="viewCompanyItemForm">	
			<form:hidden path="id"/>
	        <div class="row">
            	<div class="col-lg-offset-0">
	            	<div class="panel panel-default">
	                	<div class="panel-heading panel-heading-custom">View Company Details</div>
		                    <div class="panel-body">
		                    	<fieldset disabled>
                        			<div class="col-md-4 col-sm-offset-1">
		                                <div class="form-group">
		                                    <form:label path="name" cssClass="control-label input-md">Name:</form:label>
		                                    <form:input path="name" cssClass="form-control input-sm" />
		                                </div>
	                                   	<div class="form-group">
	                                    	<form:label path="addressLine2" cssClass="control-label input-md">Address Line 2</form:label>
                                            <form:input path="addressLine2" cssClass="form-control input-sm" />
	                                   	</div>
                    				</div>
                    				<div class="col-md-4 col-sm-offset-1">
		                                <div class="form-group">
		                                    <form:label path="addressLine1" cssClass="control-label input-md">Address Line 1:</form:label>
		                                    <form:input path="addressLine1" cssClass="form-control input-sm" />
		                                </div>
	                                   	<div class="form-group">
	                                    	<form:label path="city" cssClass="control-label input-md">City</form:label>
                                            <form:input path="city" cssClass="form-control input-sm" />
	                                   	</div>
                    				</div>
                    				<div class="col-md-4 col-sm-offset-1">
		                                <div class="form-group">
		                                    <form:label path="state" cssClass="control-label input-md">State:</form:label>
		                                    <form:input path="state" cssClass="form-control input-sm" />
		                                </div>
	                                   	<div class="form-group">
	                                    	<form:label path="country" cssClass="control-label input-md">Country</form:label>
                                            <form:input path="country" cssClass="form-control input-sm" />
	                                   	</div>
                    				</div>
                    				<div class="col-md-4 col-sm-offset-1">
		                                <div class="form-group">
		                                    <form:label path="zipcode" cssClass="control-label input-md">Zip Code:</form:label>
		                                    <form:input path="zipcode" cssClass="form-control input-sm" />
		                                </div>
	                                   	<div class="form-group">
	                                    	<form:label path="createdBy" cssClass="control-label input-md">Created By</form:label>
                                            <form:input path="createdBy" cssClass="form-control input-sm" />
	                                   	</div>
                    				</div>
                    				<div class="col-md-4 col-sm-offset-1">
	                                   	<div class="form-group">
	                                    	<form:label path="createdDate" cssClass="control-label input-md">Created Date</form:label>
                                            <form:input path="createdDate" cssClass="form-control input-sm" />
	                                   	</div>
	                                   	<div class="form-group">
		                                    <form:label path="lastUpdatedDate" cssClass="control-label input-md">Last Updated Date:</form:label>
		                                    <form:input path="lastUpdatedDate" cssClass="form-control input-sm" />
		                                </div>
                    				</div>
                    				<div class="col-md-4 col-sm-offset-1">
	                                   	<div class="form-group">
		                                    <form:label path="lastUpdatedBy" cssClass="control-label input-md">Last Updated By:</form:label>
		                                    <form:input path="lastUpdatedBy" cssClass="form-control input-sm" />
		                                </div>
                    				</div>
                   				</fieldset>
                   				<div class="row">
		                        	<div class="form-group">
									    <label class="col-lg-12 control-label" for="singlebutton"></label>
									    <div class="col-lg-12">
									        <div class="col-lg-12 text-center"> 
												<button class="btn btn-primary" type="submit">Edit</button> 
												<button class="btn btn-primary" type="submit" onclick="javascript:backToCompanySearchResults();">Search Results</button> 
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