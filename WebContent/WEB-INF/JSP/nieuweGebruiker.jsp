<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<core:set var="page" scope="request" value="nieuwe_gebruiker" />
<!DOCTYPE html>
<html>
<head>
<core:import url="head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/styles.css">
</head>
<body>
	<div id="wrapper" class="${page}">

		<jsp:include page="header.jsp" />
		<nav id="main_menu">
			<h2>Nieuwe Gebruiker</h2>
		</nav>
		<section id="content">
			<h2>Registratie</h2>
			<core:url var="register" value="/registreer"/>
			<form method="post" action="${register}">
				<fieldset>
					<legend>Naam</legend>
					<div class="align_bottom">
						<div class="label_n_input">
							<label>Naam:</label> <input type="text" value="${familienaam}" name="familienaam" />
						</div>
						<div class="label_n_input">
							<label>Voornaam:</label> <input type="text" value="${userdata.voornaam}" name="voornaam"/>
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>Adres</legend>
					<div class="align_bottom">
						<div class="label_n_input">
							<label>Straat:</label><input type="text" value="${userdata.straat}" name="straat"/>
						</div>
						<div class="label_n_input">
							<label>Nr:</label> <input type="text" value="${userdata.huisnr}" name="huisnr"/>
						</div>
						<div class="label_n_input">
							<label>Postcode:</label> <input type="text" value="${userdata.postcode}" name="postcode"/>
						</div>
						<div class="label_n_input">
							<label>Gemeente:</label> <input type="text" value="${userdata.gemeente}" name="gemeente"/>
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>Gebruiker gegevens</legend>
					<div class="align_bottom">
						<div class="label_n_input">
						<core:if test="${persoon.gebruikersnaam.length != 0}">
						<core:set var="user" scope="request" value="${persoon.gebruikersnaam}"/>
						</core:if>
							<label>Gebruikersnaam:</label> <input type="text" value="${user}"
								name="username" />
						</div>
						<div class="label_n_input">
							<label>Wachtwoord:</label> <input type="password" name="pass" />
						</div>
						<div class="label_n_input">
							<label>Herhaal Wachtwoord:</label> <input type="password"
								name="confirmpass" />
						</div>
					</div>
				</fieldset>
				<div>
					<input type="submit" value="OK" name="verzend" />
				</div>
			</form>
			<ul class="error_msg">
				<core:forEach var="error" items="${errors}">
					<li>${error}</li>
				</core:forEach>
			</ul>
		</section>
	</div>
</body>
</html>