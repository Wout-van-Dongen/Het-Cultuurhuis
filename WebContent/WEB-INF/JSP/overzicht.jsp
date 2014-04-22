<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<h2>Overzicht</h2>
			<ul>
				<li><a href="${urlGetVoorstelling}">Terug naar
						voorstellingen</a></li>
			</ul>
		</nav>
		<section id="content">
			<h2>Gelukte Reserveringen</h2>

			<table id="overzicht_inhoud" class="voorstelling_weergave">
				<thead>
					<tr>
						<th colspan="2">Wanneer</th>
						<th class="title" rowspan="2">Titel</th>
						<th class="executors" rowspan="2">Uitvoerders</th>
						<th class="price" rowspan="2">Prijs</th>
						<th class="seats" rowspan="2">Plaatsen</th>
						<th class="interact" rowspan=2></th>
					</tr>
					<tr>
						<th class="date">Datum</th>
						<th class="time">Hoelaat</th>
					</tr>
				</thead>
				<tbody>
					<core:set var="totalPrice" value="0" />
					<core:choose>
						<core:when test="${!empty regSuccesful}">
							<core:forEach items="${regSuccesful}" var="entry">
								<tr>
									<td class="date"><fmt:formatDate
											value="${entry.voorstelling.datum}" pattern="dd/MM/yy" /></td>
									<td class="time"><fmt:formatDate
											value="${entry.voorstelling.datum}" pattern="HH:mm" /></td>
									<td class="title"><core:out
											value="${entry.voorstelling.title}" /></td>
									<td class="executor"><core:out
											value="${entry.voorstelling.uitvoerders}" /></td>
									<td class="price">${entry.voorstelling.prijs}</td>
									<td class="seats">${entry.aantalPlaatsen}</td>
									<td class="interact"></td>
								</tr>
								<core:set var="totalPrice"
									value="${totalPrice + (entry.voorstelling.prijs*entry.aantalPlaatsen)}" />
							</core:forEach>
						</core:when>
						<core:otherwise>
							<tr>
								<td colspan=7>Geen gelukte reserveringen!</td>
							</tr>
						</core:otherwise>
					</core:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7"></td>
					</tr>
				</tfoot>
			</table>

			<h2>Mislukte Reserveringen</h2>
			<table id="overzicht_inhoud" class="voorstelling_weergave">
				<thead>
					<tr>
						<th colspan="2">Wanneer</th>
						<th class="title" rowspan="2">Titel</th>
						<th class="executors" rowspan="2">Uitvoerders</th>
						<th class="price" rowspan="2">Prijs</th>
						<th class="seats" rowspan="2">Plaatsen</th>
						<th class="interact" rowspan=2>Vrije Plaatsen</th>
					</tr>
					<tr>
						<th class="date">Datum</th>
						<th class="time">Hoelaat</th>
					</tr>
				</thead>
				<tbody>
					<core:set var="totalPrice" value="0" />
					<core:choose>
						<core:when test="${!empty regFailed}">
							<core:forEach items="${regFailed}" var="entry">
								<tr>
									<td class="date"><fmt:formatDate
											value="${entry.key.voorstelling.datum}" pattern="dd/MM/yy" /></td>
									<td class="time"><fmt:formatDate
											value="${entry.key.voorstelling.datum}" pattern="HH:mm" /></td>
									<td class="title"><core:out
											value="${entry.key.voorstelling.title}" /></td>
									<td class="executor"><core:out
											value="${entry.key.voorstelling.uitvoerders}" /></td>
									<td class="price">${entry.key.voorstelling.prijs}</td>
									<td class="seats">${entry.key.aantalPlaatsen}</td>
									<td class="interact">${entry.value}</td>
								</tr>
								<core:set var="totalPrice"
									value="${totalPrice + (entry.voorstelling.prijs*entry.aantalPlaatsen)}" />
							</core:forEach>
						</core:when>
						<core:otherwise>
							<tr>
								<td colspan=7>Geen mislukte reserveringen!</td>
							</tr>
						</core:otherwise>
					</core:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7"></td>
					</tr>
				</tfoot>
			</table>
			
		</section>

	</div>

</body>
</html>