<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<core:set var="page" scope="request" value="bevestigen" />
<core:url var="urlGetVoorstelling" value="/voorstellingen" />
<!DOCTYPE html>
<html>
<head>
<core:import url="head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/styles.css">
</head>
<body>
	<div id="wrapper" class="${page}">
		<core:import url="header.jsp" />
		<nav id="main_menu">
			<h2>Reserveren</h2>
			<ul>
				<li><a href="${urlGetVoorstelling}">Terug naar
						voorstellingen</a></li>
			</ul>
		</nav>
		<section id="content">
		
		</section>
		
	</div>

</body>
</html>