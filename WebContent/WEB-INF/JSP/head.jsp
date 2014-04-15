<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set value="${pageContext.servletContext.contextPath}"
	var="contextURL" />
<title>
Het Cultuurhuis: ${subtitle != null ? subtitle  : "Voorstellingen"}
</title>
<link rel="stylesheet" type="text/css"	href="${contextURL}/CSS/style.css" />
<link rel="stylesheet" type="text/css" href="${contextURL}/CSS/header.css"/>
<link rel="stylesheet" type="text/css"	href="${contextURL}/CSS/color.css" />
