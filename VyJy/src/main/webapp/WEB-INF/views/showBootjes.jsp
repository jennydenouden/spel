<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alle bootjes</title>
</head>
<body>
<h1>Alle bootjes in het systeem:</h1>
<list>
	<c:forEach items="${bootjes}" var="b">
		<li> 
			Bootje ${b.id} <br>
			Kost:<br>
			${b.prijsBanaan } bananen<br>
			${b.prijsVis } vissen<br>
			${b.prijsSchelp } schelpen<br>
			Verkocht : ${b.verkocht}		
		</li>
	</c:forEach>

</list>
</body>
</html>