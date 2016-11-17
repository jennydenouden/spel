<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alle bootjes</title>
<jsp:include page="/WEB-INF/theme1/css/bootjes.css"/>
</head>
<body>
<h1>Alle bootjes in het systeem:</h1>
<div>
	<c:forEach items="${bootjes}" var="b">
		<c:if test="${!b.verkocht }">
			<div class="bootItem"> 
				Bootje ${b.id} <br>
				Kost:<br>
				${b.prijsBanaan } bananen<br>
				${b.prijsVis } vissen<br>
				${b.prijsSchelp } schelpen <br>
				<a href="/koop/${b.id }">Koop dit bootje</a>
			</div>
		</c:if>
	</c:forEach>
</div>
</body>
</html>