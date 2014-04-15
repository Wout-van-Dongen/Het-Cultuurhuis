<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />

<header id="voostelling_header">
	<figure>
		<core:choose>
			<core:when test="${page == null}">
				<img src="${contextURL}/IMG/logo_voorstellingen.png" alt="" title="" />
			</core:when>
			<core:otherwise>
				<img src="${contextURL}/IMG/logo_${page}.png" alt="${logoAlt}"
					title="${logoTitle}" />
			</core:otherwise>
		</core:choose>
	</figure>
	<h1>Het Cultuurhuis:</h1>
	<h2>
		<core:choose>
			<core:when test="${subtitle != null}">
				<core:out value="${subtitle}" />
			</core:when>
			<core:otherwise>
				<core:out value="Voorstellingen" />
			</core:otherwise>
		</core:choose>
	</h2>
	<jsp:include page="secondaryMenu.jsp" />
</header>
