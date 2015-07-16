<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
		<form:form method="POST" action="/target-trak/searchReferenceDataByCriteria.htm" modelAttribute="searchReferenceDataForm">	
	        <div class="row">
	            <div class="col-lg-offset-0">
	            	<div class="panel panel-default">
	                	<div class="panel-heading panel-heading-custom">Search Reference Data</div>
	                	
		                    <div class="panel-body">
		                    	<div class="row">
		                        	<div class="col-lg-6">
		                                <div class="form-group">
		                                    <form:label path="referenceDataLabel" cssClass="control-label input-sm form-indent-30">Label:</form:label>&nbsp;
		                                    <form:input path="referenceDataLabel" cssClass="form-control input-sm form-control-inline" />
		                                </div>
	                                   	<div class="form-group">
	                                    	<form:label path="status" cssClass="control-label input-sm form-indent-30">Status:</form:label>
	                                        <form:select path="status" cssClass="form-control input-sm form-control-inline">
	                                        	<form:option value="">Please Select ...</form:option>
	                                        	<c:forEach var="status" items="${statusList}">
	                                            	<form:option value="${status.value}">${status.name}</form:option>
	                                            </c:forEach>
	                                        </form:select>
	                                   	</div>
		                    		</div>
		                           	<div class="col-lg-6">
	                                	<div class="form-group">
	                                    	<form:label path="referenceDataType" cssClass="control-label input-sm form-indent-30">Types:</form:label>
	                                    	<form:select path="referenceDataType" cssClass="form-control input-sm form-control-inline ">
	                                  			 <form:option value="">Please Select ...</form:option>
	                                      			 <c:forEach var="referenceDataType" items="${referenceDataTypes}">
	                                             		<form:option value="${referenceDataType.value}">${referenceDataType.name}</form:option>
	                                           		 </c:forEach>
	                                        </form:select>
	                              		</div>
	                                  	<div class="form-group">
	                                    	<form:label path="createdBy" cssClass="control-label input-sm">Created By:</form:label>
	                                        <form:select path="createdBy" cssClass="form-control input-sm form-control-inline">
	                                        	<form:option value="">Please Select ...</form:option>
	                                        	<c:forEach var="user" items="${usersList}">
	                                            	<form:option value="${user.value}">${user.name}</form:option>
	                                            </c:forEach>
	                                        </form:select>
	                                    </div>
		                          	</div>
		                    	</div>
		                    	<div class="row">
		                        	<div class="col-lg-6">
	                               		<div class="form-group">
	                           			   	<form:label path="lastUpdatedBy" class="control-label input-sm">Updated By:</form:label>
	                                       	<form:select path="lastUpdatedBy" class="form-control input-sm form-control-inline">
	                                       		<form:option value="">Please Select ...</form:option>
	                                       		<c:forEach var="user" items="${usersList}">
	                                           		<form:option value="${user.value}">${user.name}</form:option>
	                                           	</c:forEach>
	                                       	</form:select>
	                               		</div>
		                    		</div>
		                    	</div>
		                    	<div class="row">
		                        	<div class="form-group">
									    <div class="col-lg-12">
									        <div class="col-xs-2 col-md-offset-5 text-center"> 
												<button class="btn btn-sm btn-primary btn-block" type="submit">Search</button>
											</div>
									    </div>
									</div>
								</div>
		                   </div>
	                   
	              </div>
	         </div>
			
        
       <c:if test="${not empty referenceDataList}"> 
	       <div class="row">
	            <div class="col-lg-12">
	            	<div class="panel">
	                	<div class="panel-heading"></div>
	                    <div class="panel-body">
	                    	<div class="row">
	                    		<table class="table table-bordered table-striped table-hover">
				 					<thead>
								        <tr>
								            <th class="text-center" data-field="id">Id</th>
								            <th class="text-center" data-field="type">Type</th>
								            <th class="text-center" data-field="label">Label</th>
								            <th class="text-center" data-field="value">Value</th>
								            <th class="text-center" data-field="status">Status</th>
								        </tr>
								    </thead>
								    <tbody class="table-hover">
								    	<c:forEach var="referenceDataItem" items="${referenceDataList}">
								    	<tr>
								            <td class="text-center" data-field="id"><a href="${referenceDataItem.url}">${referenceDataItem.id}</a></td>
								            <td class="text-center" data-field="type">${referenceDataItem.type}</td>
								            <td class="text-center" data-field="label">${referenceDataItem.label}</td>
								            <td class="text-center" data-field="value">${referenceDataItem.value}</td>
								            <td class="text-center" data-field="status">${referenceDataItem.status}</td>
								        </tr>
								        </c:forEach>
								    </tbody>
								</table>
	                    	</div>
	                    	<div class="row text-center">
	                    		<ul class="pagination pagination-sm">
	                    			<c:forEach var="paginationItem" items="${pagingList}">
										<li class="${paginationItem.cssClass}"><a href="${paginationItem.link}">${paginationItem.pageText}</a></li>
									</c:forEach>
								</ul>
	                    	</div>
	                   	</div>
	                </div>
	             </div>  	
	         </div>
        </c:if>
        </form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>