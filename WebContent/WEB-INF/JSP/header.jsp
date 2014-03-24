<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<header>
	<figure>
		<img src="${contextURL}/IMG/voorstellingen.png"
			alt="Het Cultuurhuis voorstellingenlogo: Twee Toneelmaskers"
			title="Voorstellingen" />
	</figure>
	<h1>
		Het Cultuurhuis:
		</h1><h2>
		<core:choose>
			<core:when test="${subtitle != null}">
				<core:out value="${subtitle}" />
			</core:when>
			<core:otherwise>
				<core:out value="Voorstellingen" />
			</core:otherwise>
		</core:choose>
	</h2>
</header>