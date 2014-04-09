<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="main_menu" class="genres">
	<h2>Genres</h2>
	<ul>
		<core:choose>
			<core:when test="${!empty menuFout}">
				<li>${menuFout}</li>
			</core:when>
			<core:otherwise>
				<core:if test="${!empty menuList}">
					<core:forEach var="entry" items="${menuList}">
						<li><a href="getVoorstellingen?gID=${entry.key}"><core:out
									value="${entry.value}" /></a></li>
					</core:forEach>
				</core:if>
			</core:otherwise>
		</core:choose>
	</ul>
</nav>
