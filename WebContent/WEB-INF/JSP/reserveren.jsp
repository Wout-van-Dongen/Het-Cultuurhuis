<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<core:set var="page" scope="request" value="reserveren" />
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

			<core:if test="${voorstelling != null}">
				<h2>Reservatie</h2>
				<core:url var="urlreserveer" value="/reserveer" />
				<form method="post" action="${urlReserveer}">
					<ul id="vsReservatieInfo">
						<li><label>Voorstelling:</label> <span class="info">${voorstelling.title}</span>
						</li>
						<li><label>Uitvoerders:</label> <span class="info">${voorstelling.uitvoerders}</span>
						</li>
						<li><label>Datum:</label> <span class="info">${voorstelling.datum}</span>
						</li>
						<li><label>Prijs:</label> <span class="info"><core:out
									value="€" />${voorstelling.prijs}</span></li>
						<li><label>Vrije Plaatsen:</label> <span class="info">${voorstelling.vrijePlaatsen}</span></li>
						<li><label>Plaatsen:</label> <input type="text" name="seats" /><span
							class="info red bold">${controleFout}</span> <%--Insert Javascript to give warning if to-be-ordered number exceeds available seats --%>
							<input type="hidden" name="vID"
							value="${voorstelling.voorstellingsNr}" /> <input type="submit"
							value="Reserveren" /><label class="red">${foutmelding}</label></li>
					</ul>
				</form>
			</core:if>
			<ul class="error_msg">
				<core:forEach var="error" items="${errors}">
					<li>${error}</li>
				</core:forEach>
			</ul>
		</section>
	</div>
</body>
</html>