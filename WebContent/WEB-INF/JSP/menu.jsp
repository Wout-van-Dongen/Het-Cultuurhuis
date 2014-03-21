<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="${menuName}">
<h2>${menuName}</h2>
	<ul>
		<core:forEach var="entry" items="${menuList}">
			<li><a href=""><core:out value="${entry.value}"/></a></li>
		</core:forEach>
	</ul>
</nav>