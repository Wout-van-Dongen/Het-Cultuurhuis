<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
	<core:set var="page" scope="request" value="error500" />
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html">
<title>An error has occured!</title>
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/styles.css" />
</head>
<body>
<div id="wrapper" class="${page}">
		<jsp:include page="../header.jsp">
			<jsp:param name="subtitle" value="${currentGenre}" />
		</jsp:include>
		<core:import url="/genres" />
		<%-- imports the menu --%>
		<section>
			<h1>500</h1>
			<h2>An error occured!</h2>
		</section>
	</div>
</body>
</html>