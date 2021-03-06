<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<core:set var="page" scope="request" value="winkelmand" />
<core:url var="urlRemove" value="/winkelmand/remove" />
<!DOCTYPE html>
<html>
<head>
<core:import url="head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/styles.css">
<core:url var="urlVoorstellingen" value="/voorstellingen" />
</head>
<body>
	<div id="wrapper" class="winkelmand">

		<jsp:include page="header.jsp" />
		<nav id="main_menu">
			<h2>Winkelmand</h2>
		</nav>
		<section id="content">
			<h2>Overzicht Winkelmand</h2>
			<form method="post" action="${urlRemove}">
				<table id="winkelmand_inhoud" class="voorstelling_weergave">
					<thead>
						<tr>
							<th colspan="2">Wanneer</th>
							<th class="title" rowspan="2">Titel</th>
							<th class="executors" rowspan="2">Uitvoerders</th>
							<th class="price" rowspan="2">Prijs</th>
							<th class="seats" rowspan="2">Plaatsen</th>
							<th class="interact" rowspan=2><input type="submit"
								value="Verwijderen" /></th>
						</tr>
						<tr>
							<th class="date">Datum</th>
							<th class="time">Hoelaat</th>
						</tr>
					</thead>
					<tbody>
						<core:set var="totalPrice" value="0" />
						<core:choose>
							<core:when test="${!empty basket}">
								<core:forEach items="${basket}" var="entry">
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
										<td class="interact"><input type="checkbox"
											value="${entry.voorstelling.voorstellingsNr}" name="vID" /></td>
									</tr>
									<core:set var="totalPrice"
										value="${totalPrice + (entry.voorstelling.prijs*entry.aantalPlaatsen)}" />
								</core:forEach>
							</core:when>
							<core:otherwise>
								<tr>
									<td colspan=7>Uw winkelmand is leeg!</td>
								</tr>
							</core:otherwise>
						</core:choose>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4"></td>
							<td class="bold" colspan="2" class="price">Totaal: €
								${totalPrice}</td>
							<td></td>
						</tr>
					</tfoot>
				</table>
			</form>
		</section>
	</div>
</body>
</html>