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
			<core:choose>
				<core:when test="${fouten != null}">
					<h2>Not Found!</h2>
					<p class="error_msg">${fouten}</p>
				</core:when>
				<core:otherwise>
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
									<core:url var="urlGetVoorstelling" value="/reserveer?vID=${vs.voorstellingsNr}"/>
										<tr>
											<td class="date"><fmt:formatDate value="${vs.datum}"
													pattern="dd/MM/yy" /></td>
											<td class="time"><fmt:formatDate value="${vs.datum}"
													pattern="HH:mm" /></td>
											<td class="title"><core:out value="${vs.title}" /></td>
											<td class="executors"><core:out
													value="${vs.uitvoerders}" /></td>
											<td class="price">€ ${vs.prijs}</td>
											<td class="seats">${vs.vrijePlaatsen}</td>
											<td class="interact"><core:choose>
													<core:when test="${vs.vrijePlaatsen > 0}">
														<a
															href='${urlGetVoorstelling}'>Reserveer</a>
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
							<h2>Welcome</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								In eros orci, viverra at urna et, lobortis eleifend nulla.
								Phasellus tincidunt tellus lectus. Pellentesque habitant morbi
								tristique senectus et netus et malesuada fames ac turpis
								egestas. Maecenas est tortor, dapibus eget sodales at, tempus a
								massa. Cras ut egestas lacus, non tempus urna. Mauris vel
								interdum arcu. Etiam sed metus urna. Nunc eu orci tempor,
								egestas diam sed, eleifend erat. In cursus eleifend tellus, id
								aliquet mauris feugiat ut. Vestibulum nec magna vel erat porta
								mattis a vitae justo. Duis posuere eros non lacus consectetur
								venenatis. Morbi facilisis, orci ac porta euismod, felis ligula
								varius tellus, id commodo neque nulla eget tellus. Sed nibh
								arcu, mattis sit amet vehicula in, feugiat ut enim.</p>

							<p>Nunc nec purus vel leo vestibulum consequat. Sed est
								felis, blandit ut gravida vitae, facilisis ut tellus. Praesent
								augue mauris, cursus sed lorem ut, eleifend dictum tellus.
								Maecenas convallis quam quis neque ultrices tempor in sed nibh.
								Nullam sagittis odio sed fringilla congue. Aenean gravida neque
								est, sed ornare odio ultricies vel. Vivamus suscipit sem
								adipiscing, vehicula mauris et, ornare felis. Donec interdum est
								a fermentum suscipit. Cras posuere felis diam, a lacinia massa
								feugiat sit amet.</p>

							<p>Suspendisse enim sapien, eleifend eget magna non, euismod
								mattis erat. Vivamus porta sit amet nulla eu venenatis. Aenean
								porttitor elementum elementum. Cras sit amet tortor turpis.
								Morbi vestibulum faucibus lectus ac placerat. Nunc congue
								lacinia tellus, ut pretium diam posuere vitae. In hac habitasse
								platea dictumst. Ut volutpat congue lectus, in semper nunc
								ultricies at. Integer bibendum eget risus ac viverra. Aenean
								cursus dignissim pharetra. Fusce in sagittis turpis, vitae
								pulvinar risus. Maecenas semper adipiscing venenatis. Praesent
								ultrices porta dui, id blandit metus sagittis a.</p>

							<p>Integer nisl ligula, pretium eu placerat sit amet,
								vestibulum quis enim. Pellentesque ut pharetra magna, eu rhoncus
								mauris. Nunc bibendum fringilla accumsan. Quisque facilisis
								egestas urna, ac laoreet leo molestie quis. Quisque luctus
								adipiscing laoreet. Cras mollis lorem vestibulum egestas
								eleifend. Cras eget venenatis quam. Duis non turpis lacus.</p>

							<p>Etiam at porttitor odio. Maecenas mattis dui in nulla
								feugiat dignissim. Praesent nunc lacus, sollicitudin non
								eleifend eu, fermentum eu nibh. Curabitur placerat auctor tellus
								id laoreet. Aenean venenatis ac sapien non ultrices. Aenean
								vestibulum consequat elit, non auctor turpis varius non. Donec
								sit amet leo eu eros hendrerit vestibulum at eget magna. Duis
								rutrum nulla a mauris blandit molestie. Maecenas lobortis lectus
								et magna pharetra commodo. Integer fringilla bibendum dolor,
								quis cursus quam viverra vel. Vestibulum ut mattis enim. Nunc
								nisi elit, sollicitudin et bibendum eget, lacinia id sapien.
								Mauris pretium sagittis quam, a tempus leo tempor id. Vestibulum
								suscipit lacus vel porttitor accumsan.</p>

						</core:otherwise>
					</core:choose>
				</core:otherwise>
			</core:choose>
		</section>
	</div>
</body>
</html>