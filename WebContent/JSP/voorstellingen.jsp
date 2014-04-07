<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<!DOCTYPE html>
<html>
<head>
<core:import url="head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/styles.css" />
</head>
<body>
	<div id="wrapper">
	<core:set var="logo" scope="request" value="voorstellingen" />
		<core:import url="header.jsp" />
		<core:import url="/getGenres" />
		<%-- imports the menu --%>
		<section id="voorstellingen">
			<core:if test="${not empty voorstellingList}">
				<%-- Begin Tabel met Voorstellingen --%>
				<h2>${subtitle}voorstellingen</h2>
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
							<td><core:out value="${vs.title}" /></td>
							<td><core:out value="${vs.uitvoerders}" /></td>
							<td>${vs.prijs}</td>
							<td>${vs.vrijePlaatsen}</td>
							<td><core:choose>
									<core:when test="${vs.vrijePlaatsen > 0}">
										<a href='voorstellingen/reserveer?vID=${vs.voorstellingsNr}'>Reserveer</a>
									</core:when>
									<core:otherwise>
										<strong class="red">Volzet!</strong>
									</core:otherwise>
								</core:choose></td>
						</tr>
					</core:forEach>
				</table>
				<%-- Einde Tabel met Voorstellingen --%>
			</core:if>
		</section>
	</div>
</body>
</html>