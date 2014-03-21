<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cultuurhuis</title>
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/style.css" />
</head>
<body>
	<div id="wrapper">
		<core:import url="header.jsp" />
		<core:import url="/getGenres" />
		<%-- imports the menu --%>
		<section id="voorstellingen"> </section>
	</div>
</body>
</html>