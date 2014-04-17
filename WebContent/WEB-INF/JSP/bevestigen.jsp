<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<core:set var="page" scope="request" value="bevestigen" />
<core:url var="login" value="/login" />
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
			<h2>Bevestigen</h2>

		</nav>
		<section id="content">
			<h2>Stap 1:Wie ben je?</h2>
			<form id="login" method="post" action="${login}">
				<label>Gebruikersnaam:</label> <input type="text" name="username"
					value="${klantnr}"
					<core:if test="${klantnr != null}">disabled="true"</core:if> /> <label>Wachtwoord:</label>
				<input type="password" name="pass"
					<core:if test="${klantnr != null}">disabled="true"</core:if> />

				<button type="submit" value="lookup" name="action"
					<core:if test="${klantnr != null}">disabled="true"</core:if>>Zoek
					me op</button>
				<button type="submit" value="new" name="action"
					<core:if test="${klantnr != null}">disabled="true"</core:if>>Ik
					ben nieuw</button>
			</form>
			<ul class="error_msg">
				<core:forEach var="error" items="${errors}">
					<li>${error}</li>
				</core:forEach>
			</ul>
			<core:if test="">
				<dl>
					<dt>Naam:</dt>
					<dd>${userdata.naam}${userdata.voornaam}</dd>
					<dt>Adres:</dt>
					<dd>${userdata.straat}${userdata.huisnr}</dd>
					<dd>${userdata.postcode}${userdata.gemeente}</dd>
				</dl>
			</core:if>
			<h2>Stap 2:Bevestigen</h2>
			<form>
				<button type="submit"
					<core:if test="${klantnr == null}">disabled="true"</core:if>>Bevestig</button>
			</form>

		</section>

	</div>

</body>
</html>