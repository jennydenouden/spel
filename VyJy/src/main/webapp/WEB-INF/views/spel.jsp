<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="css/spel.css" />" rel="stylesheet">
        <title>Spel</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="speler">
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
            <div id="winkel">
                <c:forEach items="${bootjeswinkel.bootjesTeKoop}" var="b">
                    <c:if test="${!b.verkocht }">
                        <div class="bootItem"> 
                            Bootje ${b.id} <br>
                            Kost:<br>
                            ${b.prijsBanaan } bananen<br>
                            ${b.prijsVis } vissen<br>
                            ${b.prijsSchelp } schelpen <br>
                            <a href="/koop/${b.id }">Koop dit bootje</a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div id="bord">Speelbord deel</div>
        </div>
    </body>
</html>
