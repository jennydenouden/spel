<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spelers</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
	<h1>Spelers</h1>
	<c:forEach items="${spelers}" var="s">
		<hr>
		<p>
		<h3>Naam: ${s.name}</h3>
                Tokens:
                <ul>
			<li>Visjes: ${s.visjes} <a href="/visjebij${s.id}"><strong>+</strong></a>
				<a href="/visjeaf${s.id}"><strong>-</strong></a>
			</li>
			<li>Bananen: ${s.bananen} <a href="/banaanbij${s.id}"><strong>+</strong></a>
				<a href="/banaanaf${s.id}"><strong>-</strong></a>
			</li>
			<li>Schelpen: ${s.schelpen} <a href="/schelpbij${s.id}"><strong>+</strong></a>
				<a href="/schelpaf${s.id}"><strong>-</strong></a>
			</li>
		</ul>
		<br>
                Bootjes totale waarde: ${s.printWaardeInventaris()}
                <ul>
			<c:forEach items="${ s.inventaris }" var="bootje">
				<li>${bootje.toString()}</li>
			</c:forEach>

		</ul>
		</p>
	</c:forEach>
	
	<ul id="menu">
		<li class="ui-widget-header"><div>Menu</div></li>
		<li><nav id="/speler">Inventaris</nav></li>
		<li><nav id="/bord">Bord</nav></li>
		<li><nav id="/bootjes">Bootjeswinkel</nav></li>
		<li><nav id="/bordJenny">Bord Jenny</nav>
	</ul>
	
	<script>
		$(document).ready(function(){
			//Voeg functionaliteit toe aan de menubalk
			$("nav").click(function(){
				console.log("Je klikte op " + $(this).attr("id"));
				window.location = $(this).attr("id");
			});
		});
	</script>

</body>
</html>
