<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="secondary_menu">
	<core:if test="${!empty winkelmand}">
		<ul>
			<core:choose>
				<core:when test="${pageTitle == 'winkelmand' }">
					<li><a class="first" href="#">Voorstellingen</a></li>
					<li><a href="#">Bevestiging Registratie</a></li>
				</core:when>
				<core:otherwise>
					<li><a class="first" href="#">Reservatiemandje</a></li>
					<li><a href="#">Bevestiging Registratie</a></li>
				</core:otherwise>
			</core:choose>
		</ul>
	</core:if>
</nav>
