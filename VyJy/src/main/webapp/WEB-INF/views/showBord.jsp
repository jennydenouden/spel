<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
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
<script type="text/javascript" src="/js/showBord.js"></script>
<style type="text/css">
canvas {
	border: 1px solid black;
}
</style>
</head>
<body onload="draw();">
	<canvas id="bordGrid" width="1000" height="1000"></canvas>
	
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
