<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="Genres">
	<h2>Genres</h2>
	<ul>
		<core:forEach var="entry" items="${menuList}">
			<li><a href="getVoorstellingen?gID=${entry.key}"><core:out
						value="${entry.value}" /></a></li>
		</core:forEach>
	</ul>
</nav>
