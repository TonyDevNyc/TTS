<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<li class="sidebar-search">
	<div class="input-group custom-search-form">
		<input type="text" class="form-control" placeholder="Search Matter...">
		<span class="input-group-btn">
			<button class="btn btn-default" type="button">
				<i class="fa fa-search"></i>
			</button>
		</span>
	</div> <!-- /input-group -->
</li>


<!-- iterate through menu items -->
<c:forEach var="menuItem" items="${userDetails.menuItems}">
	<c:choose>
		<c:when test="${menuItem.name == 'Home'}">
			<li class="active">
		</c:when>
  		<c:otherwise>
  			<li>
  		</c:otherwise>
	</c:choose>
	
		<a href="#">${menuItem.name}<span class="fa arrow"></span></a>
		<c:if test="${not empty menuItem.childMenuItems}">
			<ul class="nav nav-second-level">
				<c:forEach var="childMenuItem" items="${menuItem.childMenuItems}">
					<li>
						<a href="${childMenuItem.link}">${childMenuItem.name}</a>
					</li>
				</c:forEach>
			</ul>
		</c:if>
	</li>
</c:forEach>


