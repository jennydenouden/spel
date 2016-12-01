<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/nieuweSpeler.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Maak speler</title>
</head>
<body>

<section id="alles">
	<h3>Voeg een speler toe aan spel ${gameid }</h3>
	
	<form method="post" action="/spel/${gameid }/nieuwespeler" >
	 	<button type="button" disabled class="colored form">Naam</button> 
		<input type = "text" name = "naam" class="form">
		<input type ="submit" value = "Speel mee" id= "extraBorder" class="colored form">
	</form>
</section>

</body>
</html>