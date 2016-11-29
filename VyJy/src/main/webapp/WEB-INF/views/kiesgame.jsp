<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href=<c:url value = "css/kiesgame.css"></c:url>>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kies een spel</title>
</head>
<body>
<h3>Kies een bestaand spel, of maak een nieuw spel aan.</h3>
<!--  Huidig spel weergeven, als er al een spel actief is -->

<c:if test="${spelId != null}">
	<section>
		<h4>Huidig spel: ${spelId }</h4>
	</section>
</c:if>


<section>
	<form method="post" action="/init">
		<input type="submit" value="Nieuw spel">
	</form>
</section>


<h4>Bestaande spellen</h4>
<ul>
	<c:forEach items="${ spellen }" var="spel">
		<section>
		<li> <a href="/spel/${spel.id}">Spel ${spel.id }</a> : ${spel.toString() } <a href = "/spel/${spel.id}/maakspeler"> + </a> </li>
		</section>
	</c:forEach>
</ul>

</body>
</html>