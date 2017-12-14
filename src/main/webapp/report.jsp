<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Отчёт</title>
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<table class="table table-condensed table-bordered table-striped">
    <c:if test="${groupBy=='year'}">
        <tr>
            <th>Автор</th>
            <th>Наименование</th>
        </tr>
        <c:forEach var="entry" items="${booksMap}">
            <tr>
                <th colspan="2" align="left">${entry.key}</th>
            </tr>
            <c:forEach var="book" items="${entry.value}">
                <tr>
                    <td>${book.author}</td>
                    <td>${book.title}</td>
                </tr>
            </c:forEach>
        </c:forEach>
    </c:if>
    <c:if test="${groupBy=='author'}">
        <tr>
            <th>Наименование</th>
            <th>Год издания</th>
        </tr>
        <c:forEach var="entry" items="${booksMap}">
            <tr>
                <th colspan="2" align="left">${entry.key}</th>
            </tr>
            <c:forEach var="book" items="${entry.value}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.year}</td>
                </tr>
            </c:forEach>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
