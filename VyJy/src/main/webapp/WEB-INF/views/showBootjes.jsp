<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alle bootjes</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>
<h1>Alle bootjes in het systeem:</h1>
<p>
<table>
	<tr id="eersteRij">
		<th>Bootje (id)</th>
		<th>Kosten bananen</th>
		<th>Kosten schelpen</th>
		<th>Kosten vissen</th>
		<th>Waarde</th>	
	</tr>
</table>
</p>

<script>
	$(document).ready(function(){
		//Voeg click functie toe
		function clickfunctie(){
			//Print in de console op welke rij je klikt
			var id = $(this).closest("tr").attr("id");
			
			//Doe een post naar een controllermethode, die de boot op verkocht zet in de database,
			//uit de winkel mikt en een nieuwe boot in de winkel doet
			$.post("/koopBootje", {id : id}, function(bootje){
				//Zorg dat het scherm wordt geupdate
				if(bootje !== ""){
					newElement = $("<tr id = "+bootje.id+"><td>" + "Bootje " + bootje.id + "<br></td>"
							  + "<td>" + bootje.prijsBanaan + " bananen </td>" 
							  + "<td>" + bootje.prijsVis + " vissen </td>"
							  + "<td>" + bootje.prijsSchelp + " schelpen </td>"
							  + "<td>" + bootje.waarde + "</td></tr>");
					$("#eersteRij").after(newElement);
					newElement.click(clickfunctie);
				}
				$("#"+ id).remove();
			});
		}
		
		
		//Display de bootjes in de bootjeswinkel op het moment dat de pagina wordt geladen
		$.get("/bootjesInWinkel", function(bootjes){
			for(var i = bootjes.length-1; i >= 0; i--){
				newElement = $("<tr id = "+bootjes[i].id+"><td>" + "Bootje " + bootjes[i].id + "<br></td>"
						  + "<td>" + bootjes[i].prijsBanaan + " bananen </td>" 
						  + "<td>" + bootjes[i].prijsVis + " vissen </td>"
						  + "<td>" + bootjes[i].prijsSchelp + " schelpen </td>"
						  + "<td>" + bootjes[i].waarde + "</td></tr>");
				$("#eersteRij").after(newElement);
			}
			
			//Voeg functie toe die dingen doet als je op een rij klikt
			$("td").click(clickfunctie);
		});		
	});
</script>


</body>
</html>