<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bord</title>
</head>
<body>

<h2>Het speelbord</h2>
Spel ${spelId } tussen ${spelers }
<h4>Tegels op bord:</h4>
<ul>
	<c:if test="${tegelsOpBord.size() != 0 }">
		<c:forEach items = "${tegelsOpBord}" var= "tegel">
			<li>${tegel.toString() }</li>
		</c:forEach>
	</c:if>
</ul>

<h4>Huidige tegel:</h4>
<c:if test="${huidigeTegel != null }">
	${huidigeTegel.toString()} <br>
	<a href="/leg/${huidigeTegel.id}">Leg deze tegel op het bord</a>
</c:if>

<h4>Tegels op stapel:</h4>
<ul>
	<c:if test="${tegelsOpStapel.size() != 0 }">
		<c:forEach items = "${tegelsOpStapel}" var= "tegel">
			<li>${ tegel.toString() }</li>
		</c:forEach>
	</c:if>
</ul>

</body>
</html>