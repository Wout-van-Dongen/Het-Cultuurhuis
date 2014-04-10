<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="secondary_menu">
	<core:url var="urlBevestig" value="/bevestigen" />
	<core:url var="urlVoorstelling" value="/voorstellingen" />
	<core:url var="urlWinkelmand" value="/winkelmand" />
	<core:if test="${!empty winkelmand}">
		<ul>
			<core:choose>
				<core:when test="${logo == 'winkelmand' }">
					<li><a class="first" href="${urlVoorstelling}">Voorstellingen</a></li>
				</core:when>
				<core:otherwise>
					<li><a class="first" href="${urlWinkelmand}">Reservatiemandje</a></li>
				</core:otherwise>
			</core:choose>
			<li><a href="${urlBevestig}">Bevestiging Registratie</a></li>
		</ul>
	</core:if>
</nav>
