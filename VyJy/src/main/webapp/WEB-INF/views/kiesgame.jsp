<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href=<c:url value = "/css/kiesgame.css"></c:url>>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$( function() {	  
	 $( "#accordion" ).accordion({
		  collapsible: true,
		  active: false
	  });
} );
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kies een spel</title>
</head>
<body>

<section id="alles">
	<h3>Kies een bestaand spel, of maak een nieuw spel aan.</h3>
	<!--  Huidig spel weergeven, als er al een spel actief is -->
	
	
	
	<section>
		<form method="post" action="/init">
			<input type="submit" value="Nieuw spel" id="nieuwSpel">
		</form>
	</section>
	
	
	<h4>Bestaande spellen</h4>	
	<section id="accordion">
		<c:forEach items="${ spellen }" var="spel">
			<h3>
				<c:if test="${ spelId == spel.id}">
					<strong> Huidig spel: </strong>
				</c:if>
				Spel ${spel.id }
			</h3>
			<div>	
				<p> ${spel.toString() } </p>
				<!-- <form method="get" action="/spel/${spel.id}/maakspeler">
					<input type = "submit" value="Voeg speler toe" class="button">
				</form>
				<form method="get" action="/spel/${spel.id}">
					<input type = "submit" value="Ga dit spel binnen" class="button">
				</form> -->
				<input type="button" value = "Voeg speler toe" onclick= "voegSpelerToe(${spel.id})">
				<input type="button" value = "Ga dit spel binnen" onclick= "gaSpelBinnen(${spel.id})">
			</div>
		</c:forEach>
	</section>

</section>

<script>
	function voegSpelerToe(id){
		console.log("voeg speler toe");
		window.location = "/spel/"+id+"/maakspeler";
	}
	
	function gaSpelBinnen(id){
		console.log("ga spel binnen");
		window.location = "/spel/" + id;
	}
</script>


</body>
</html>