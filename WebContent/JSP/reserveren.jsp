<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<!DOCTYPE html>
<html>
<head>
<core:import url="head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/reservatie.css">
</head>
<body>
	<div id="wrapper">
		<core:set var="logo" scope="request" value="reserveer" />
		<core:import url="header.jsp" />
		<section id="reservatie">

			<nav>
				<a class="button" href="../">Terug naar
					voorstellingen</a>
			</nav>
			<form>
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
					<li><label>Plaatsen:</label> <input type="text" name="naam" />
						<%--Insert Javescript to give warning if to-be-ordered number exceeds available seats --%>
						<input type="hidden" name="vID"
						value="${voorstelling.voorstellingsNr}" /> <input type="submit"
						value="Reserveren" /></li>
				</ul>
			</form>

		</section>
	</div>
</body>
</html>