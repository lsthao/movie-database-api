<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/8/18
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Genres</title>
</head>
<table border="1px" width="100px">
    <th>Id</th>
    <th>Genre Name</th>

    <c:forEach items="${genreObject}" var="genre">

        <tr>
            <td>${genre.id}</td>
            <td>${genre.genreName}</td>
        </tr>
    </c:forEach>

</table>
</html>
