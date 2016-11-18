<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Maak speler</title>
</head>
<body>

<h4>Je gaat meespelen in spel ${gameid }</h4>

<form method="post" action="/spel/${gameid }/nieuwespeler">
	Naam: <input type = "text" name = "naam">
	<input type ="submit" value = "Speel mee">
</form>

</body>
</html>