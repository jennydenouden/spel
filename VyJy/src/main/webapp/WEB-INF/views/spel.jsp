<%@page import="nl.vyjy.Spel"%>
<%@page import="nl.vyjy.Tegel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#menu").menu({
                    items: "> :not(.ui-widget-header)"
                });
            });
        </script>
        <script type="application/javascript" src="/js/spel.js"></script>
        <link href="<c:url value="css/spel.css" />" rel="stylesheet">
        <title>Spel</title>
    </head>
    <body onload="draw();">

        <%-- De gehele pagina --%>
        <div id="wrapper">

            <%-- De linkerhelft van de pagina --%>
            <div id="linkerkant">

                <%-- Informatie over de spelers in dit spel --%>
                <div id="speler">
                    <strong> <span id = "huidigeSpeler">IEMAND</span> is aan de beurt</strong> <input type = "button" id = "wisselBeurt" value= "Geef de beurt door"> <br><br>
                    
                    <h1>Spelers</h1>
                    <c:forEach items="${spelers}" var="s">
                        <hr>
                        <p>
                        <h3>Naam: ${s.name}</h3>
                        Tokens:
                        <ul>
                            <li>
                                Visjes: ${s.visjes}
                                <a href="/visjebij${s.id}"><strong>+</strong></a>
                                <a href="/visjeaf${s.id}"><strong>-</strong></a>
                            </li>
                            <li>
                                Bananen: ${s.bananen}
                                <a href="/banaanbij${s.id}"><strong>+</strong></a>
                                <a href="/banaanaf${s.id}"><strong>-</strong></a>
                            </li>
                            <li>
                                Schelpen: ${s.schelpen}
                                <a href="/schelpbij${s.id}"><strong>+</strong></a>
                                <a href="/schelpaf${s.id}"><strong>-</strong></a>
                            </li>
                        </ul>
                        <br>
                        Bootjes totale waarde: ${s.printWaardeInventaris()}
                        <ul>
                            <c:forEach items = "${ s.inventaris }" var = "bootje">
                                <li>
                                    ${bootje.toString()}
                                </li>
                            </c:forEach>

                        </ul>
                        </p>
                    </c:forEach>
                </div>

                <%-- De tegel die deze beurt gelegd moet worden --%>
                <div id="canvasKaartje">
                    <canvas id="huidigeTegel">Your browser doesn't support canvas</canvas>
                </div>

                <%-- De bootjeswinkel staat onder de spelers --%>
                <div id="winkel">
                    <h1>Alle bootjes in het systeem:</h1>
                    <p>
                    <table>
                        <tr id="eersteRij">
                            <%--<th>Bootje (id)</th>--%>
                            <th>Kosten bananen</th>
                            <th>Kosten schelpen</th>
                            <th>Kosten vissen</th>
                            <th>Waarde</th>	
                        </tr>
                    </table>
                    </p>

                    <script>
                        $(document).ready(function () {
                            //Voeg click functie toe
                            function clickfunctie() {
                                //Print in de console op welke rij je klikt
                                var id = $(this).closest("tr").attr("id");

                                //Doe een post naar een controllermethode, die de boot op verkocht zet in de database,
                                //uit de winkel mikt en een nieuwe boot in de winkel doet
                                $.post("/koopBootje", {id: id}, function (bootje) {
                                    //Zorg dat het scherm wordt geupdate
                                    if (bootje !== "") {
                                        newElement = $("<tr id = " + bootje.id + ">"
                                                //+ "<td>" + "Bootje " + bootje.id + "<br></td>"
                                                + "<td>" + bootje.prijsBanaan + "</td>"
                                                + "<td>" + bootje.prijsVis + " </td>"
                                                + "<td>" + bootje.prijsSchelp + " </td>"
                                                + "<td>" + bootje.waarde + "</td></tr>");
                                        $("#eersteRij").after(newElement);
                                        newElement.click(clickfunctie);
                                    }
                                    $("#" + id).remove();
                                });
                            }

                            //Display de bootjes in de bootjeswinkel op het moment dat de pagina wordt geladen
                            $.get("/bootjesInWinkel", function (bootjes) {
                                for (var i = bootjes.length - 1; i >= 0; i--) {
                                    newElement = $("<tr id = " + bootjes[i].id + ">"
                                            //+ "<td>" + "Bootje " + bootjes[i].id + "<br></td>"
                                            + "<td>" + bootjes[i].prijsBanaan + " </td>"
                                            + "<td>" + bootjes[i].prijsVis + " </td>"
                                            + "<td>" + bootjes[i].prijsSchelp + " </td>"
                                            + "<td>" + bootjes[i].waarde + "</td></tr>");
                                    $("#eersteRij").after(newElement);
                                }

                                //Voeg functie toe die dingen doet als je op een rij klikt
                                $("td").click(clickfunctie);
                            });

                            //Voeg functionaliteit toe aan de menubalk
                            $("nav").click(function () {
                                //console.log("Je klikte op " + $(this).attr("id"));
                                window.location = $(this).attr("id");
                            });
                        });
                    </script>
                </div>

            </div>

            <%-- Het speelbord staat aan de linkerkant van het spel --%>
            <div id="bord">
                <canvas id="bordGrid" width="1000" height="1000"></canvas>

                <script>
                    $(document).ready(function () {
                        //Voeg functionaliteit toe aan de menubalk
                        $("nav").click(function () {
                            window.location = $(this).attr("id");
                        });
                    });
                </script>
            </div>
        </div>
    </body>
</html>
