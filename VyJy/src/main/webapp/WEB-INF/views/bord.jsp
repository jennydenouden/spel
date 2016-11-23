<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bord</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#menu").menu({
			items : "> :not(.ui-widget-header)"
		});
	});
</script>
</head>
<body>

	<h2>Het speelbord</h2>
	Spel ${spelId } tussen ${spelers }
	<h4>Tegels op bord:</h4>
	<ul>
		<c:if test="${tegelsOpBord.size() != 0 }">
			<c:forEach items="${tegelsOpBord}" var="tegel">
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
			<c:forEach items="${tegelsOpStapel}" var="tegel">
				<li>${ tegel.toString() }</li>
			</c:forEach>
		</c:if>
	</ul>

	<ul id="menu">
		<li class="ui-widget-header"><div>Menu</div></li>
		<li><nav id="/speler">Inventaris</nav></li>
		<li><nav id="/bord">Bord</nav></li>
		<li><nav id="/bootjes">Bootjeswinkel</nav></li>
		<li><nav id="/bordJenny">Bord Jenny</nav>
	</ul>

	<script>
		$(document).ready(function() {
			//Voeg functionaliteit toe aan de menubalk
			$("nav").click(function() {
				console.log("Je klikte op " + $(this).attr("id"));
				window.location = $(this).attr("id");
			});
		});
	</script>

</body>
</html>