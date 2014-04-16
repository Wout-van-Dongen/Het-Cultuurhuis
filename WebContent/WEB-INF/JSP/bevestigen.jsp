<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<core:set var="page" scope="request" value="bevestigen" />
<core:url var="login" value="/login"/>
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
				<label>Gebruikersnaam:</label> <input type="text" name="username" />
				<label>Wachtwoord:</label> <input type="password" name="pass" />

				<button type="submit" value="lookup" name="action">Zoek me op</button>
				<button type= "submit" value="new" name="action">Ik ben
					nieuw</button>
			</form>
			<h2>Stap 2:Bevestigen</h2>
			<form>
			<button type="submit">Bevestig</button>	
			</form>
			
		</section>

	</div>

</body>
</html>