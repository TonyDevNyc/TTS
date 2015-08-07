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
            <h5 class="text-primary">Contacts</h5>
        </div>
   	</div>
   	
   	<form:form method="POST" action="/target-trak/searchContactByCriteria.htm" modelAttribute="searchContactForm" cssClass="form-horizontal" id="searchContactForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
  		<div class="form-group">
    		<div class="col-sm-10">
	      		<div class="input-group custom-search-form"> 
					<input name="text" type="text" class="form-control" placeholder="Enter search text"/>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="javascript:submitContactSearchForm();">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
    		</div>
  		</div>
   	</form:form>
    
    <c:if test="${not empty contactsList}"> 
   		<div class="row">
   			<c:forEach var="contactItem" items="${contactsList}">
    			<div class="col-md-3">
    				<div class="thumbnail">
    					<div class="caption">
    						<h6><a href="${contactItem.url}">${contactItem.displayName}</a></h6>
    						<p> ${contactItem.companyName}<br/>
    							${contactItem.telephoneNumber}<br/>
    							${contactItem.email}
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
     
	</tiles:putAttribute>
</tiles:insertDefinition>