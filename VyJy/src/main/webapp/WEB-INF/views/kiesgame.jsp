<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kies een spel</title>
</head>
<body>
<h3>Kies een bestaand spel, of maak een nieuw spel aan.</h3>
<form method="post" action="/init">
	<input type="submit" value="Nieuw spel">
</form>
<h4>Bestaande spellen</h4>
Voeg hier een lijst toe met bestaande spellen.
<ul>
	<c:forEach items="${ spellen }" var="spel">
		<li> <a href="/spel/${spel.id}/maakspeler">Spel ${spel.id }</a> : ${spel.toString() }</li>
	</c:forEach>
</ul>

</body>
</html>