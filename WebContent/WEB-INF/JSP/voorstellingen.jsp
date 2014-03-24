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
		<section id="voorstellingen"> <core:if
			test="${not empty voorstellingList}">
			<table id="voorstelling_tabel">
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Vrije Plaatsen</th>
					<th></th>
				</tr>
				<core:forEach var="vs" items="${voorstellingList}">
					<tr>
						<td>${vs.datum}</td>
						<td>${vs.title}</td>
						<td>${vs.uitvoerders}</td>
						<td>${vs.prijs}</td>
						<td>${vs.vrijePlaatsen}</td>
						<td><core:choose>
								<core:when test="${vs.vrijePlaatsen > 0}">
									<a href='reserveer?id=${vs.voorstellingsNr}'>Reserveer</a>
								</core:when>
								<core:otherwise>
									<strong class="red">Volzet!</strong>
								</core:otherwise>
							</core:choose></td>
					</tr>
				</core:forEach>
			</table>
		</core:if> </section>
	</div>
</body>
</html>