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
		<form>
			<table id="winkelmand">
			<tr>
			<th>Datum</th>
			<th>Titel</th>
			<th>Uitvoerders</th>
			<th>Prijs</th>
			<th>Plaatsen</th>
			<td><input type="submit" value="Verwijderen"/></td>
			</tr>
			<core:forEach items="${basket}" var="entry">
			<tr>
			<td></td><td></td><td></td><td></td><td></td><td><input type="checkbox" value="${entry.voorstellingsNr}"/></td>
			</tr>
			</core:forEach>
			</table>
			</form>
		</section>
</div>
</body>
</html>