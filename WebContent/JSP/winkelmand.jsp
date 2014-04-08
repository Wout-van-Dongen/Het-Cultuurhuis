<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<!DOCTYPE html>
<html>
<head>
<core:import url="head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/styles.css">
</head>
<body>
	<div id="wrapper"  class="winkelmand">
		<core:set var="logo" scope="request" value="winkelmand" />
		<core:import url="header.jsp" />
		<nav id="main_menu"></nav>
		<section id="content">
		
		</section>
</div>
</body>
</html>