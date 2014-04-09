<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<!DOCTYPE html>
<html>
<head>
<core:import url="head.jsp" />
<link rel="stylesheet" type="text/css"
	href="${contextURL}/CSS/styles.css">
</head>
<body>
	<div id="wrapper" class="nieuwe_gebruiker">
		<core:set var="logo" scope="request" value="nieuwe_gebruiker" />
		<core:import url="header.jsp" />
		<nav id="main_menu">
			<h2>Nieuwe Gebruiker</h2>
		</nav>
		<section id="content">
			<h2>Registratie</h2>
			<form>
				<fieldset>
					<legend>Naam</legend>
					<div class="label_n_input">
						<label>Naam:</label> <input type="text" />
					</div>
					<div class="label_n_input">
						<label>Voornaam</label> <input type="text" />
					</div>
				</fieldset>
				<fieldset>
					<legend>Adres</legend>
					<div class="label_n_input">
						<label>Straat</label> <input type="text" />
					</div>
					<div class="label_n_input">
						<label>Nr</label> <input type="text" />
					</div>
					<div class="label_n_input">
						<label>Postcode:</label> <input type="text" />
					</div>
					<div class="label_n_input">
						<label>Gemeente:</label> <input type="text" />
					</div>
				</fieldset>
				<fieldset>
					<legend>Gebruiker gegevens</legend>
					<div class="label_n_input">
						<label>Gebruikersnaam:</label> <input type="text" />
					</div>
					<div class="label_n_input">
						<label>Wachtwoord</label> <input type="password" />
					</div>
					<div class="label_n_input">
						<label>Herhaal Wachtwoord</label> <input type="password" />
					</div>
				</fieldset>
				<input type="submit" value="OK" name="okay	" />
			</form>
		</section>
	</div>
</body>
</html>