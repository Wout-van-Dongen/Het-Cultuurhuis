<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="secondary_menu">
	<core:url var="urlBevestig" value="/bevestigen" />
	<core:url var="urlVoorstelling" value="/voorstellingen" />
	<core:url var="urlWinkelmand" value="/winkelmand" />
	<core:choose>
		<core:when test="${page == 'winkelmand' }">
			<ul>
				<li><a class="first" href="${urlVoorstelling}">Voorstellingen</a></li>
				<li><a href="${urlBevestig}">Bevestiging Registratie</a></li>
			</ul>
		</core:when>
		<core:otherwise>
			<core:if test="${!empty winkelmand}">
				<ul>
					<li><a class="first" href="${urlWinkelmand}">Reservatiemandje</a></li>
					<li><a href="${urlBevestig}">Bevestiging Registratie</a></li>
				</ul>
			</core:if>
		</core:otherwise>
	</core:choose>
</nav>