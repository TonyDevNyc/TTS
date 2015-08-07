<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
		<form:form method="GET" action="/target-trak/showEditContactItem.htm" modelAttribute="contactItem" id="viewContactItemForm">	
			<form:hidden path="id"/>
	        <div class="row">
            	<div class="col-lg-offset-0">
	            	<div class="panel panel-default">
	                	<div class="panel-heading panel-heading-custom">View Contact Details</div>
		                    <div class="panel-body">
		                    	<fieldset disabled>
                        			<div class="col-md-5 col-sm-offset-1">
		                                <div class="form-group">
		                                    <form:label path="contactType" cssClass="control-label input-sm">Contact Type:</form:label>
		                                    <form:input path="contactType" cssClass="form-control input-sm" />
		                                </div>
		                                <div class="form-group">
		                                    <form:label path="firstName" cssClass="control-label input-sm">First Name:</form:label>
		                                    <form:input path="firstName" cssClass="form-control input-sm" />
		                                </div>
	                                   	
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
		                                <div class="form-group">
	                                    	<form:label path="title" cssClass="control-label input-sm">Title:</form:label>
                                            <form:input path="title" cssClass="form-control input-sm" />
	                                   	</div>
	                                   	<div class="form-group">
	                                    	<form:label path="lastName" cssClass="control-label input-sm">Last Name:</form:label>
                                            <form:input path="lastName" cssClass="form-control input-sm" />
	                                   	</div>
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
		                                <div class="form-group">
		                                    <form:label path="middleInitial" cssClass="control-label input-sm">Middle Initial:</form:label>
		                                    <form:input path="middleInitial" cssClass="form-control input-sm" />
		                                </div>
		                                <div class="form-group">
		                                    <form:label path="telephoneNumber" cssClass="control-label input-sm">Telephone Number:</form:label>
		                                    <form:input path="telephoneNumber" cssClass="form-control input-sm" />
		                                </div>	                                   	
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
		                                <div class="form-group">
	                                    	<form:label path="suffix" cssClass="control-label input-sm">Suffix:</form:label>
                                            <form:input path="suffix" cssClass="form-control input-sm" />
	                                   	</div>
	                                   	<div class="form-group">
	                                    	<form:label path="email" cssClass="control-label input-sm">Email:</form:label>
                                            <form:input path="email" cssClass="form-control input-sm" />
	                                   	</div>
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
	                                   	<div class="form-group">
	                                    	<form:label path="companyName" cssClass="control-label input-sm">Company:</form:label>
                                            <form:input path="companyName" cssClass="form-control input-sm" />
	                                   	</div>
	                                   	<div class="form-group">
		                                    <form:label path="createdBy" cssClass="control-label input-sm">Created By:</form:label>
		                                    <form:input path="createdBy" cssClass="form-control input-sm" />
		                                </div>
	                                   	
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
	                                   	<div class="form-group">
		                                    <form:label path="activeAtCompany" cssClass="control-label input-sm">Active Employee:</form:label>
		                                    <form:input path="activeAtCompany" cssClass="form-control input-sm" />
		                                </div>
		                                <div class="form-group">
		                                    <form:label path="createdDate" cssClass="control-label input-sm">Create Date:</form:label>
		                                    <form:input path="createdDate" cssClass="form-control input-sm" />
		                                </div>
                    				</div>
                    				<div class="col-md-5 col-sm-offset-1">
	                                   	<div class="form-group">
		                                    <form:label path="lastUpdatedBy" cssClass="control-label input-sm">Last Updated By:</form:label>
		                                    <form:input path="lastUpdatedBy" cssClass="form-control input-sm" />
		                                </div>
	                                </div>
	                                <div class="col-md-5 col-sm-offset-1">
		                                <div class="form-group">
		                                    <form:label path="lastUpdatedDate" cssClass="control-label input-sm">Last Updated Date:</form:label>
		                                    <form:input path="lastUpdatedDate" cssClass="form-control input-sm" />
		                                </div>
                    				</div>
                   				</fieldset>
                   				<div class="row">
		                        	<div class="form-group">
									    <label class="col-lg-12 control-label" for="singlebutton"></label>
									    <div class="col-lg-12">
									        <div class="col-lg-12 text-center"> 
												<button class="btn btn-primary" type="submit">Edit</button> 
												<button class="btn btn-primary" type="submit" onclick="javascript:backToContactSearchResults();">Search Results</button> 
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