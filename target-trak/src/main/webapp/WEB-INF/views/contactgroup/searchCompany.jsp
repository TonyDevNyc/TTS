<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<tiles:insertDefinition name="bootstrap">
	<tiles:putAttribute name="content">
		
	<div class="row">
        <div class="col-lg-12"><br/></div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h5 class="text-primary">Companies</h5>
        </div>
   	</div>
   	
   	<form:form method="POST" action="/target-trak/searchCompanyByCriteria.htm" modelAttribute="searchCompanyForm" cssClass="form-horizontal" id="searchCompanyForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
  		<div class="form-group">
    		<div class="col-sm-10">
	      		<div class="input-group custom-search-form"> 
					<input name="text" type="text" class="form-control" placeholder="Enter search text"/>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="javascript:submitCompanySearchForm();">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
    		</div>
    		<!-- <div class="col-sm-2">
		        <div> 
					<button type="button" class="btn btn-sm btn-primary btn-block" data-toggle="modal" data-target="#createCompanyModal">Create Company</button>
				</div>
		    </div>
		     -->
  		</div>
   	</form:form>
    
    <c:if test="${not empty companiesList}"> 
   		<div class="row">
   			<c:forEach var="companyItem" items="${companiesList}">
    			<div class="col-md-3">
    				<div class="thumbnail">
    					<div class="caption">
    						<h6><a href="${companyItem.url}">${companyItem.name}</a></h6>
    						<p> ${companyItem.addressLine1}<br/>
    							${companyItem.addressLine2}<br/>
    							${companyItem.city} &nbsp;${companyItem.state}&nbsp;${companyItem.zipcode}
    						</p>
    					</div>
    				</div>
    			</div>
   			</c:forEach>
   		</div>
   		
   		<div class="row text-center">
       		<ul class="pagination pagination-sm">
       			<c:forEach var="paginationItem" items="${pagingList}">
					<li class="${paginationItem.cssClass}"><a href="${paginationItem.link}">${paginationItem.pageText}</a></li>
				</c:forEach>
			</ul>
        </div>
     </c:if>
     
     <!-- Add Company Modal -->
     <div id="createCompanyModal" class="modal fade" role="dialog">
 		<div class="modal-dialog">
    		<div class="modal-content">
	      		<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        	<h4 class="modal-title">Create Company</h4>
		      	</div>
		      	<div class="modal-body">
		        	<form:form method="POST" action="/target-trak/createCompany.htm" modelAttribute="createCompanyForm" cssClass="form-horizontal" id="createCompanyForm">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
						
						<div class="form-group">
	                    	<label for="exampleInputEmail1">Email address</label>
	                      	<input type="email" class="form-control input-sm" id="exampleInputEmail1" placeholder="Enter email"/>
	                  </div>
					</form:form>
		      	</div>
		      	<div class="modal-footer">
		       		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      	</div>
    		</div>
		</div>
	</div>
     
	</tiles:putAttribute>
</tiles:insertDefinition>