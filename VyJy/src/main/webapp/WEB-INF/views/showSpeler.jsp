<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spelers</title>
    </head>
    <body>
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
            </p>
        </c:forEach>
    </body>
</html>
