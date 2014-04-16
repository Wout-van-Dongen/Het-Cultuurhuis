<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<core:set var="page" scope="request" value="voorstellingen" />
<!DOCTYPE html>
<html>
<head>
<core:import url="head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/styles.css" />
</head>
<body>
	<div id="wrapper" class="${page}">
		<jsp:include page="header.jsp" />
		<core:import url="/genres" />
		<%-- imports the menu --%>
		<section id="content">
			<ul class="error_msg">
				<core:forEach var="error" items="${errors}">
					<li>${error}</li>
				</core:forEach>
			</ul>
			<core:choose>
				<core:when test="${not empty voorstellingList}">
					<h2>${subtitle}voorstellingen</h2>
					<%-- Begin Tabel met Voorstellingen --%>
					<table id="voorstelling_inhoud" class="voorstelling_weergave">
						<thead>
							<tr>
								<th colspan="2">Wanneer</th>
								<th rowspan="2">Titel</th>
								<th rowspan="2">Uitvoerders</th>
								<th rowspan="2">Prijs</th>
								<th rowspan="2">Vrije Plaatsen</th>
								<th rowspan="2"></th>
							</tr>
							<tr>
								<th>Datum</th>
								<th>Hoelaat</th>
							</tr>
						</thead>
						<tbody>
							<core:forEach var="vs" items="${voorstellingList}">
								<core:url var="urlGetVoorstelling"
									value="/reserveer?vID=${vs.voorstellingsNr}" />
								<tr>
									<td class="date"><fmt:formatDate value="${vs.datum}"
											pattern="dd/MM/yy" /></td>
									<td class="time"><fmt:formatDate value="${vs.datum}"
											pattern="HH:mm" /></td>
									<td class="title"><core:out value="${vs.title}" /></td>
									<td class="executors"><core:out value="${vs.uitvoerders}" /></td>
									<td class="price">€ ${vs.prijs}</td>
									<td class="seats">${vs.vrijePlaatsen}</td>
									<td class="interact"><core:choose>
											<core:when test="${vs.vrijePlaatsen > 0}">
												<a href='${urlGetVoorstelling}'>Reserveer</a>
											</core:when>
											<core:otherwise>
												<strong class="red">Volzet!</strong>
											</core:otherwise>
										</core:choose></td>
								</tr>
							</core:forEach>
						</tbody>
					</table>
					<%-- Einde Tabel met Voorstellingen --%>
				</core:when>
				<core:otherwise>
					<jsp:include page="Pages/voorstellingenInhoud.jsp" />
				</core:otherwise>
			</core:choose>
		</section>
	</div>
</body>
</html>