<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x_rt" uri="http://java.sun.com/jstl/xml_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  	<meta charset=utf-8/>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  	<meta name="description" content="Target-Trak System">
  	<meta name="author" content="Dev Group NYC">
  
  	<link href="/target-trak/resources/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet">
  	<link href="/target-trak/resources/metisMenu-2.0.2/metisMenu.css" rel="stylesheet">
  	<link href="/target-trak/resources/tts/target-trak.css" rel="stylesheet">
  	<link href="/target-trak/resources/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
 	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
  	<!--[if lt IE 9]>
  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
 	<![endif]-->

	<script src="/target-trak/resources/jqeury/2.1.4/jquery-2.1.4.min.js"></script>
   	<!-- Include all compiled plugins (below), or include individual files as needed -->
   	<script src="/target-trak/resources/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <script src="/target-trak/resources/metisMenu-2.0.2/metisMenu.js"></script>
    <script src="/target-trak/resources/tts/target-trak.js"></script>
    
    <script src="/target-trak/resources/typeahead/bootstrap3-typeahead.min.js"></script>
    
  <title>Target-Trak</title>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<tiles:insertAttribute name="top-navbar"/>
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<tiles:insertAttribute name="side-menu"/>
					</ul>
				</div>
			</div>
		</nav>
		
		<div id="page-wrapper">
			<tiles:insertAttribute name="content"/>
		</div>
	</div>

	<script type="text/javascript">
	$(function() {
	    $('#side-menu').metisMenu({
	    	toggle: true
	    });
	});
	</script>
	
</body>
</html>